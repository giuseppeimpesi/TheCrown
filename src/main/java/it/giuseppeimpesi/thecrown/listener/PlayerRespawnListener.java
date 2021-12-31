package it.giuseppeimpesi.thecrown.listener;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import me.SuperRonanCraft.BetterRTP.BetterRTP;
import me.SuperRonanCraft.BetterRTP.player.rtp.RTPSetupInformation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    private GameManager manager;

    public PlayerRespawnListener(GameManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (manager.getGameStatus() != GameStatus.ACTIVE) return;
        if (manager.getRound() == null) return;
        Player player = event.getPlayer();

        if (manager.getRound().getPlayerManager().getParticipants().keySet().contains(player)) {
            BetterRTP.getInstance().getRTP().start(new RTPSetupInformation("TheCrown", player, player, true));
        }
    }
}
