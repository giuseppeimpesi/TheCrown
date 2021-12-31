package it.giuseppeimpesi.thecrown.listener;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private GameManager manager;

    public PlayerQuitListener(GameManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent ev) {
        if (manager.getGameStatus() != GameStatus.ACTIVE) return;
        if (manager.getRound() == null) return;

        Player player = ev.getPlayer();

        if (manager.getRound().getOwner().getPlayer().equals(player)) {
            manager.getRound().setOwner(manager.getRound().getPlayerManager().getRandomPlayer()); // Assegna la corona ad un giocatore random
            return;
        }
    }

}
