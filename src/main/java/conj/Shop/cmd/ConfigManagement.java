package conj.Shop.cmd;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import conj.Shop.enums.*;
import conj.Shop.control.*;
import java.util.*;

public class ConfigManagement
{
    public void run(final Player player, final Command cmd, final String label, final String[] args) {
        if (args.length < 2) {
            if (!Manager.getAvailableCommands(player, "config").isEmpty()) {
                player.sendMessage(ChatColor.GRAY + "/shop config help");
            }
            else {
                player.sendMessage(Config.PERMISSION_ERROR.toString());
            }
            return;
        }
        final String command = args[1];
        if (command.equalsIgnoreCase("edit")) {
            if (!player.hasPermission("shop.config.edit")) {
                player.sendMessage(Config.PERMISSION_ERROR.toString());
            }
        }
        else {
            if (!command.equalsIgnoreCase("help")) {
                return;
            }
            final List<String> help = Manager.getAvailableCommands(player, "config");
            if (help.isEmpty()) {
                player.sendMessage(Config.PERMISSION_ERROR.toString());
                return;
            }
            int index = 1;
            if (args.length == 3) {
                try {
                    index = Integer.parseInt(args[2]);
                }
                catch (NumberFormatException ex) {}
            }
            final String header = ChatColor.GRAY + "  === " + ChatColor.DARK_GREEN + "Shop Config Help" + ChatColor.GRAY + " === " + ChatColor.DARK_GREEN + "Page " + ChatColor.GREEN + "%index%" + ChatColor.GRAY + "/" + ChatColor.GREEN + "%size%" + ChatColor.GRAY + " ===";
            Control.list(player, help, index, header, 7);
        }
    }
}
