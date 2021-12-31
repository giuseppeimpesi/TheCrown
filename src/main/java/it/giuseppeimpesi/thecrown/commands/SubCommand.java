package it.giuseppeimpesi.thecrown.commands;

import org.bukkit.entity.Player;

public interface SubCommand {
    String getPermission();

    String getDescription();

    String getSyntax();

    void perform(Player player, String[] args);
}
