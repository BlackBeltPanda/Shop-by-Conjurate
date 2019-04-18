package conj.UA.api.files;

import java.io.*;
import java.util.*;

public class SmartFileConfig
{
    private SmartFile sf;
    
    public SmartFileConfig(final SmartFile sf) {
        this.sf = sf;
    }
    
    public void overrideData() {
        this.sf.reset();
        try {
            this.sf.getConfig().save(this.sf.getFile());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveData() {
        try {
            this.sf.getConfig().save(this.sf.getFile());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Set<String> getCategories() {
        return (Set<String>)this.sf.getConfig().getConfigurationSection("").getKeys(false);
    }
    
    public Object getValue(final String path) {
        final Iterator<String> iterator = this.getCategories().iterator();
        if (iterator.hasNext()) {
            final String s = iterator.next();
            return this.sf.getConfig().get(String.valueOf(s) + "." + path);
        }
        return null;
    }
}
