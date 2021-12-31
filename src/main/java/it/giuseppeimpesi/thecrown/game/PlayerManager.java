package it.giuseppeimpesi.thecrown.game;

import com.google.common.collect.Lists;
import it.giuseppeimpesi.thecrown.exception.InsufficientParticipantsException;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {

    private GameManager manager;

    @Getter
    @Setter
    private LinkedHashMap<Player, Long> participants;

    public PlayerManager(GameManager manager) {
        this.manager = manager;
        this.participants = new LinkedHashMap<>();

        try {
            initializeParticipants();
        } catch (InsufficientParticipantsException e) {
            e.printStackTrace();
            return;
        }
    }

    private void initializeParticipants() throws InsufficientParticipantsException {
        for (String s : manager.getPlayerConfig().getFileConfiguration().getStringList("partecipanti")) {
            if (Bukkit.getPlayer(s) == null) continue;
            participants.put(Bukkit.getPlayer(s), 0L);
        }
        if (participants.size() == 0)
            throw new InsufficientParticipantsException("Giocatori insufficienti");
    }


    public Player getRandomPlayer() {
        List<Player> players = Lists.newArrayList();

        for (Player player : participants.keySet())
            players.add(player);

        final int min = 0;
        int max = players.size() - 1;

        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return players.get(random);
    }

    public LinkedHashMap<Player, Long> orderMap(LinkedHashMap<Player, Long> hm) {
        List<Map.Entry<Player, Long>> list = new LinkedList<>(hm.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        LinkedHashMap<Player, Long> temp = new LinkedHashMap<>();
        list.forEach(x -> temp.put(x.getKey(), x.getValue()));
        return temp;
    }

    public Player topPlayer() {
        Long l = 0L;
        Player player = null;
        for (Map.Entry<Player, Long> entry : participants.entrySet())
            if (entry.getValue() > l) {
                l = entry.getValue();
                player = entry.getKey();
            }

        return player;
    }

    public String getData(int index) {
        String playerName = new ArrayList<>(participants.keySet()).get(index).getName();
        String time = " " + new ArrayList<>(participants.values()).get(index) + "s";
        return playerName + time;
    }

    public BlockFace getFacing(Entity entityFrom, Entity entityTo) {
        Location direction = entityFrom.getLocation().subtract(entityTo.getLocation());
        double phi = Math.toDegrees(Math.atan2(direction.getZ(), direction.getX()));

        if (entityFrom.getLocation().distanceSquared(entityTo.getLocation()) < 10e-2) return BlockFace.SELF;
        if (phi > -45 && phi <= 45) return BlockFace.WEST;
        if (phi > 45 && phi <= 135) return BlockFace.NORTH;
        if (phi > -135 && phi <= -45) return BlockFace.SOUTH;

        return BlockFace.EAST;
    }


}
