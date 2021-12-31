package it.giuseppeimpesi.thecrown.runnable;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.manager.GameStatus;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class RoundTask extends BukkitRunnable {

    private GameManager manager;

    @Getter
    private int time = 5 * 60;

    public RoundTask(GameManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        if (time <= 0) {
            manager.setGameStatus(GameStatus.NO_ACTIVE);
            cancel();
            return;
        }
        if (manager.getGameStatus() == GameStatus.ACTIVE) {
            Player player = manager.getRound().getOwner().getPlayer();

            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 3));

            manager.getRound().getPlayerManager().getParticipants().put(player, manager.getRound().getPlayerManager().getParticipants().get(player) + 1);
            manager.getRound().getPlayerManager().setParticipants(
                    manager.getRound().getPlayerManager().orderMap(
                            manager.getRound().getPlayerManager().getParticipants()
                    )
            );
        } else {
            cancel();
            return;
        }

        time--;
    }
}
