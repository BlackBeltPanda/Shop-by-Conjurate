package conj.Shop.base;

import java.net.*;
import java.io.*;
import org.bukkit.event.player.*;
import conj.Shop.enums.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class VersionChecker implements Listener
{
    public static String check() {
        try {
            final HttpURLConnection con = (HttpURLConnection)new URL("http://www.spigotmc.org/api/general.php").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=8185".getBytes("UTF-8"));
            final String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (version.length() <= 7) {
                return version;
            }
        }
        catch (Exception ex) {
            Bukkit.getLogger().info("Failed to check for an update on Spigot.");
        }
        return null;
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void updateAvailable(final PlayerJoinEvent event) {
        if (!Config.UPDATE_CHECK.isActive()) {
            return;
        }
        if (event.getPlayer().isOp()) {
            final String pluginversion = check();
            if (pluginversion != null) {
                final String version = ((Initiate)Initiate.getPlugin((Class)Initiate.class)).getDescription().getVersion();
                if (!version.equals(pluginversion)) {
                    event.getPlayer().sendMessage(ChatColor.RED + version + ChatColor.GRAY + " Shop is outdated");
                    event.getPlayer().sendMessage(ChatColor.GREEN + pluginversion + ChatColor.GRAY + " Shop is available for download");
                    event.getPlayer().sendMessage(ChatColor.GRAY + "Go to http://spigotmc.org/resources/shop.8185/ to update");
                }
            }
        }
    }
}
