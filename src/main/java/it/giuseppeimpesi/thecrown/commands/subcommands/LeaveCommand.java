package it.giuseppeimpesi.thecrown.commands.subcommands;

import it.giuseppeimpesi.thecrown.commands.SubCommand;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.utils.Utils;
import org.bukkit.entity.Player;

public class LeaveCommand implements SubCommand {

    private GameManager manager;

    public LeaveCommand(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public String getPermission() {
        return "thecrown.left";
    }

    @Override
    public String getDescription() {
        return "leave the event";
    }

    @Override
    public String getSyntax() {
        return "/thecrown leave";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!manager.getPlayerConfig().isRegistered(player.getName())) {
            player.sendMessage(Utils.toColor("&6&lTheCrown &7Non sei nella lista dei partecipanti."));
            return;
        }
        manager.getPlayerConfig().unregisterPlayer(player);
        manager.getPlayerConfig().save();
        manager.getGui().removeParticipant(player);
        player.sendMessage(Utils.toColor("&6&lTheCrown &7Sei stato rimosso dalla lista dei partecipanti."));
    }
}
