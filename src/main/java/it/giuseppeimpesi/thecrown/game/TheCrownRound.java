package it.giuseppeimpesi.thecrown.game;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.runnable.RoundTask;
import it.giuseppeimpesi.thecrown.utils.Utils;
import lombok.Data;
import me.SuperRonanCraft.BetterRTP.BetterRTP;
import me.SuperRonanCraft.BetterRTP.player.rtp.RTPSetupInformation;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.ParticlePacket;


@Data
public class TheCrownRound {

    private GameManager manager;

    private PlayerManager playerManager;
    private Player owner;
    private BukkitTask task;

    public TheCrownRound(GameManager manager) {
        this.manager = manager;

        this.playerManager = new PlayerManager(manager);
        this.owner = playerManager.getRandomPlayer();

        teleportPlayers();

        this.task = new RoundTask(manager).runTaskTimer(manager.getPlugin(), 0L, 20L);
        Bukkit.broadcastMessage(Utils.toColor("&6&lTheCrown &7L'evento è iniziato, " + owner.getPlayer().getName() + " ha la corona."));
    }

    private void teleportPlayers() {

        Bukkit.getScheduler().runTask(manager.getPlugin(), () -> {
            for (Player player : playerManager.getParticipants().keySet()) {
                BetterRTP.getInstance().getRTP().start(new RTPSetupInformation("TheCrown", player, player, true));
                player.setGameMode(GameMode.SURVIVAL);
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 2);
            }
        });
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}
