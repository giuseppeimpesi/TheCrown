package it.giuseppeimpesi.thecrown.commands.subcommands;

import it.giuseppeimpesi.thecrown.commands.SubCommand;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListCommand implements SubCommand {

    private GameManager manager;

    public ListCommand(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "thecrown.list";
    }

    @Override
    public String getDescription() {
        return "Print the list of players.";
    }

    @Override
    public String getSyntax() {
        return "/thecrown list";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.openInventory(manager.getGui().getInv());
    }
}
