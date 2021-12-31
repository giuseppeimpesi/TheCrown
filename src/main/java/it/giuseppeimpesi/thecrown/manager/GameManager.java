package it.giuseppeimpesi.thecrown.manager;

import it.giuseppeimpesi.thecrown.TheCrown;
import it.giuseppeimpesi.thecrown.files.FileManager;
import it.giuseppeimpesi.thecrown.game.TheCrownRound;
import it.giuseppeimpesi.thecrown.gui.ParticipantsGUI;
import it.giuseppeimpesi.thecrown.utils.Utils;
import lombok.Data;
import org.bukkit.Bukkit;

@Data
public class GameManager {

    private TheCrown plugin;
    private GameStatus gameStatus = GameStatus.NO_ACTIVE;
    private TheCrownRound round;
    private FileManager playerConfig;
    private ParticipantsGUI gui;

    public GameManager(TheCrown plugin) {
        this.plugin = plugin;
        this.playerConfig = new FileManager(this, "participants.yml");
        this.gui = new ParticipantsGUI(this);
    }

    public void setGameStatus(GameStatus gameStatus) {
        switch (gameStatus) {
            case NO_ACTIVE: {
                Bukkit.broadcastMessage(Utils.toColor("&6&lTheCrown &7L'evento è terminato."));
                round = null;
                break;
            }
            case ACTIVE: {
                round = new TheCrownRound(this);
                break;
            }
        }

        this.gameStatus = gameStatus;
    }
}
