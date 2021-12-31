package it.giuseppeimpesi.thecrown.listener;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerSwitchWorldListener implements Listener {

    private GameManager manager;

    public PlayerSwitchWorldListener(GameManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent ev) {
        if (manager.getGameStatus() != GameStatus.ACTIVE) return;
        if (manager.getRound() == null) return;
        if (ev.getPlayer().getWorld().getName().equals("TheCrown")) return;

        Player player = ev.getPlayer();

        if (player.equals(manager.getRound().getOwner().getPlayer())) {
            manager.getRound().setOwner(manager.getRound().getPlayerManager().getRandomPlayer()); // Imposta la corona ad un giocatore random
        }
    }

}
