package conj.Shop.base;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.configuration.serialization.*;
import java.util.*;
import org.bukkit.*;
import conj.Shop.tools.*;

public class ItemSerialize
{
    public static final List<ItemStack> deserialize(final List<HashMap<Map<String, Object>, Map<String, Object>>> items) {
        final List<ItemStack> retrieveditems = new ArrayList<ItemStack>();
        if (items == null) {
            return retrieveditems;
        }
        for (final HashMap<Map<String, Object>, Map<String, Object>> serializemap : items) {
            final Map.Entry<Map<String, Object>, Map<String, Object>> serializeditems = serializemap.entrySet().iterator().next();
            final Map<String, Object> item = serializeditems.getKey();
            final ItemStack i = ItemStack.deserialize((Map)item);
            if (serializeditems.getValue() != null) {
                final ItemMeta meta = (ItemMeta)ConfigurationSerialization.deserializeObject((Map)serializeditems.getValue(), ConfigurationSerialization.getClassByAlias("ItemMeta"));
                i.setItemMeta(meta);
            }
            if (i != null) {
                retrieveditems.add(i);
            }
        }
        return retrieveditems;
    }
    
    public static final ItemStack deserializeSingle(final List<HashMap<Map<String, Object>, Map<String, Object>>> itemserial) {
        for (final HashMap<Map<String, Object>, Map<String, Object>> serializemap : itemserial) {
            final Map.Entry<Map<String, Object>, Map<String, Object>> serializeditems = serializemap.entrySet().iterator().next();
            final Map<String, Object> item = serializeditems.getKey();
            final ItemStack i = ItemStack.deserialize((Map)item);
            if (serializeditems.getValue() != null) {
                final ItemMeta meta = (ItemMeta)ConfigurationSerialization.deserializeObject((Map)serializeditems.getValue(), ConfigurationSerialization.getClassByAlias("ItemMeta"));
                i.setItemMeta(meta);
            }
            if (i != null) {
                return i;
            }
        }
        return null;
    }
    
    public static final List<HashMap<Map<String, Object>, Map<String, Object>>> serialize(final List<ItemStack> items) {
        final List<HashMap<Map<String, Object>, Map<String, Object>>> serialized = new ArrayList<HashMap<Map<String, Object>, Map<String, Object>>>();
        for (ItemStack item : items) {
            final HashMap<Map<String, Object>, Map<String, Object>> serialization = new HashMap<Map<String, Object>, Map<String, Object>>();
            if (item == null) {
                item = new ItemStack(Material.AIR);
            }
            final Map<String, Object> itemmeta = (Map<String, Object>)(item.hasItemMeta() ? item.getItemMeta().serialize() : null);
            item.setItemMeta((ItemMeta)null);
            final Map<String, Object> itemstack = (Map<String, Object>)item.serialize();
            serialization.put(itemstack, itemmeta);
            serialized.add(serialization);
        }
        return serialized;
    }
    
    public static final List<HashMap<Map<String, Object>, Map<String, Object>>> serializeSingle(ItemStack item) {
        final List<HashMap<Map<String, Object>, Map<String, Object>>> serialized = new ArrayList<HashMap<Map<String, Object>, Map<String, Object>>>();
        final HashMap<Map<String, Object>, Map<String, Object>> serialization = new HashMap<Map<String, Object>, Map<String, Object>>();
        if (item == null) {
            item = new ItemStack(Material.AIR);
        }
        final Map<String, Object> itemmeta = (Map<String, Object>)(item.hasItemMeta() ? item.getItemMeta().serialize() : null);
        item.setItemMeta((ItemMeta)null);
        final Map<String, Object> itemstack = (Map<String, Object>)item.serialize();
        serialization.put(itemstack, itemmeta);
        serialized.add(serialization);
        return serialized;
    }
    
    public static String serializeSoft(final ItemStack item) {
        String serial = String.valueOf(item.getType().toString()) + ":" + item.getDurability();
        final ItemCreator ic = new ItemCreator(item);
        if (ic.hasDisplayName()) {
            serial = String.valueOf(serial) + ":" + ic.getName();
        }
        if (ic.hasLore()) {
            serial = String.valueOf(serial) + ":" + ic.getLore();
        }
        if (ic.hasEnchantments()) {
            serial = String.valueOf(serial) + ic.getEnchants();
        }
        return serial;
    }
    
    public static String serializeSoftPerfect(final ItemStack item) {
        String serial = String.valueOf(item.getType().toString()) + ":/:" + item.getDurability();
        final ItemCreator ic = new ItemCreator(item);
        if (ic.hasDisplayName()) {
            serial = String.valueOf(serial) + ":/:" + ic.getName();
        }
        if (ic.hasLore()) {
            serial = String.valueOf(serial) + ":/:" + ic.getLore();
        }
        if (ic.hasEnchantments()) {
            serial = String.valueOf(serial) + ic.getEnchants();
        }
        return serial;
    }
}
