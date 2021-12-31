package it.giuseppeimpesi.thecrown;

import it.giuseppeimpesi.thecrown.commands.CommandManager;
import it.giuseppeimpesi.thecrown.gui.PreventInteractionListener;
import it.giuseppeimpesi.thecrown.listener.*;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.placeholderapi.TheCrownPlaceholderAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class TheCrown extends JavaPlugin {

    private GameManager manager;

    @Override
    public void onEnable() {
        manager = new GameManager(this);

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new TheCrownPlaceholderAPI(this).register();
        }

        registerCommands();
        registerListener();

        getLogger().info("TheCrown abilitato correttamente.");
    }

    @Override
    public void onDisable() {
        getLogger().info("TheCrown disabilitato correttamente.");
    }

    private void registerCommands() {
        getCommand("thecrown").setExecutor(new CommandManager(manager));
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(manager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(manager), this);
        getServer().getPluginManager().registerEvents(new PreventInteractionListener(manager), this);
        getServer().getPluginManager().registerEvents(new PlayerSwitchWorldListener(manager), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(manager), this);
    }

}
