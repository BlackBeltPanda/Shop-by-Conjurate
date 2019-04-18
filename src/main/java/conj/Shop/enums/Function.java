package conj.Shop.enums;

import org.bukkit.*;
import org.apache.commons.lang.*;

public enum Function
{
    NONE("NONE", 0, "The item will do nothing when clicked"), 
    BUY("BUY", 1, "The item can be purchased"), 
    SELL("SELL", 2, "The item can only be sold"), 
    TRADE("TRADE", 3, "The item can be traded for"), 
    COMMAND("COMMAND", 4, "The item will run commands when clicked"), 
    CONFIRM("CONFIRM", 5, "The item will confirm the sell");
    
    private String description;
    
    private Function(final String s, final int n, final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return ChatColor.translateAlternateColorCodes('&', this.description);
    }
    
    @Override
    public String toString() {
        return WordUtils.capitalizeFully(this.name().toLowerCase().replaceAll("_", " "));
    }
    
    public static Function fromString(String string) {
        Function function = Function.NONE;
        string = string.replaceAll(" ", "_");
        string = string.toUpperCase();
        function = valueOf(string);
        return function;
    }
}
