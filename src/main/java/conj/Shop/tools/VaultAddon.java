package conj.Shop.tools;

import net.milkbowl.vault.economy.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class VaultAddon
{
    Economy economy;
    
    public VaultAddon(final Economy economy) {
        this.economy = economy;
    }
    
    public boolean canAfford(final Player player, final double cost) {
        final double balance = this.economy.getBalance((OfflinePlayer)player);
        return balance >= cost;
    }
    
    public boolean canAfford(final Player player, final double cost, final int quantity) {
        final double balance = this.economy.getBalance((OfflinePlayer)player);
        return balance >= cost * quantity;
    }
    
    public int getAffordable(final Player player, final double cost, final int quantity) {
        final double balance = this.economy.getBalance((OfflinePlayer)player);
        int affordable = 0;
        for (int x = 1; x <= quantity; ++x) {
            final double c = cost * x;
            if (balance >= c) {
                ++affordable;
            }
        }
        return affordable;
    }
}
