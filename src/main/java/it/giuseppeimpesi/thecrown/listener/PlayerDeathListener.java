package it.giuseppeimpesi.thecrown.listener;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private GameManager manager;

    public PlayerDeathListener(GameManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        if (manager.getGameStatus() != GameStatus.ACTIVE) return;
        if (manager.getRound() == null) return;

        Player victim = ev.getEntity();
        Player killer = victim.getKiller();

        if (victim.equals(manager.getRound().getOwner().getPlayer())) {
            if (killer == null) {
                manager.getRound().setOwner(manager.getRound().getPlayerManager().getRandomPlayer());
                return;
            }
            manager.getRound().setOwner(killer);
        }
    }
}
