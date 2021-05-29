package pro.dracarys.configlib.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pro.dracarys.configlib.ConfigLib;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public abstract class CustomFile implements ICustomFile {

    private YamlConfiguration config;
    private File file;
    private File configFile;

    public CustomFile(String parent) {
        JavaPlugin instance = ConfigLib.getPlugin();
        if (!instance.getDataFolder().exists())
            instance.getDataFolder().mkdir();

        if (parent != null) {
            file = new File(instance.getDataFolder(), File.separator + parent);
            if (!file.exists()) {
                file.mkdir();
            }
            configFile = new File(file, getName() + ".yml");
        } else {
            configFile = new File(instance.getDataFolder(), getName() + ".yml");
        }
        try {
            if (configFile.createNewFile()) {
                instance.getLogger().log(Level.INFO, "Creating Config file " + getName() + ".yml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

}
