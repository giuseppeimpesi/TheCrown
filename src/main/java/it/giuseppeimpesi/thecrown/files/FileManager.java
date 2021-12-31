package it.giuseppeimpesi.thecrown.files;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import lombok.Data;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Data
public class FileManager {

    private File file;
    private FileConfiguration fileConfiguration;

    public FileManager(GameManager manager, String fileName) {
        file = new File(manager.getPlugin().getDataFolder(), fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            manager.getPlugin().saveResource(fileName, false);
        }

        fileConfiguration = new YamlConfiguration();

        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public boolean save() {
        try {
            fileConfiguration.save(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void reloadConfiguration() {
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void registerPlayer(Player player) {
        fileConfiguration.createSection("participants." + player.getName());
        fileConfiguration.set("participants." + player.getName() + ".date", LocalDate.now().toString());
    }

    public void unregisterPlayer(Player player) {
        fileConfiguration.set("participants." + player.getName(), null);
    }

    public boolean isRegistered(Player player) {
        return fileConfiguration.getConfigurationSection("participants").contains(player.getName());
    }

    public String getDate(Player player) {
        if (!isRegistered(player)) return null;

        return fileConfiguration.getString("participants." + player.getName() + ".date");
    }


}
