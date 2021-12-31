package it.giuseppeimpesi.thecrown.commands.subcommands;

import it.giuseppeimpesi.thecrown.commands.SubCommand;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import it.giuseppeimpesi.thecrown.utils.Utils;
import org.bukkit.entity.Player;

public class InfoCommand implements SubCommand {

    private GameManager manager;

    public InfoCommand(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "thecrown.info";
    }

    @Override
    public String getDescription() {
        return "Give information about the round.";
    }

    @Override
    public String getSyntax() {
        return "/thecrown info";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (manager.getGameStatus() != GameStatus.ACTIVE) {
            player.sendMessage(Utils.toColor("&6&lTheCrown &7L'evento non è avviato."));
            return;
        }

        player.sendMessage(manager.getRound().toString());
    }
}
