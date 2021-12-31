package it.giuseppeimpesi.thecrown.commands.subcommands;

import it.giuseppeimpesi.thecrown.commands.SubCommand;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import it.giuseppeimpesi.thecrown.utils.Utils;
import org.bukkit.entity.Player;

public class StartCommand implements SubCommand {

    private GameManager manager;

    public StartCommand(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "thecrown.start";
    }

    @Override
    public String getDescription() {
        return "Start TheCrown event";
    }

    @Override
    public String getSyntax() {
        return "/thecrown start";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (manager.getGameStatus() != GameStatus.NO_ACTIVE) {
            player.sendMessage(Utils.toColor("&6&lTheCrown &7L'evento è già avviato."));
            return;
        }
        manager.setGameStatus(GameStatus.ACTIVE);
    }
}
