package it.giuseppeimpesi.thecrown.commands.subcommands;

import it.giuseppeimpesi.thecrown.commands.SubCommand;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import it.giuseppeimpesi.thecrown.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StopCommand implements SubCommand {

    private GameManager manager;

    public StopCommand(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "thecrown.stop";
    }

    @Override
    public String getDescription() {
        return "Stop TheCrown event";
    }

    @Override
    public String getSyntax() {
        return "/thecrown stop";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (manager.getGameStatus() != GameStatus.ACTIVE) {
            player.sendMessage(Utils.toColor("&6&lTheCrown &7L'evento non è avviato."));
            return;
        }

        manager.setGameStatus(GameStatus.NO_ACTIVE);
    }
}
