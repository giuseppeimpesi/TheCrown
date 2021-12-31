package it.giuseppeimpesi.thecrown.commands.subcommands;

import it.giuseppeimpesi.thecrown.commands.SubCommand;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.utils.Utils;
import org.bukkit.entity.Player;

public class JoinCommand implements SubCommand {

    private GameManager manager;

    public JoinCommand(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "thecrown.join";
    }

    @Override
    public String getDescription() {
        return "join in to the event";
    }

    @Override
    public String getSyntax() {
        return "/thecrown join";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (manager.getPlayerConfig().isRegistered(player)) {
            player.sendMessage(Utils.toColor("&6&lTheCrown &7Sei già nella lista dei partecipanti."));
            return;
        }
        manager.getPlayerConfig().registerPlayer(player);
        manager.getPlayerConfig().save();
        manager.getGui().addParticipant(player);
        player.sendMessage(Utils.toColor("&6&lTheCrown &7Sei stato aggiunto alla lista dei partecipanti."));
    }
}
