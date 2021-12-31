package it.giuseppeimpesi.thecrown.placeholderapi;

import it.giuseppeimpesi.thecrown.TheCrown;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class TheCrownPlaceholderAPI extends PlaceholderExpansion {

    private TheCrown plugin;

    public TheCrownPlaceholderAPI(TheCrown plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "thecrown";
    }

    @Override
    public String getAuthor() {
        return "zFavourite";
    }

    @Override
    public String getVersion() {
        return "version-1";
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        try {
            if (identifier.equals("owner")) {
                return plugin.getManager().getRound().getOwner().getName();
            }

            if (identifier.equals("facing")) {
                return plugin.getManager().getRound().getPlayerManager().getFacing(p,
                        plugin.getManager().getRound().getOwner()).toString();
            }

            switch (identifier) {
                case "first":
                    return plugin.getManager().getRound().getPlayerManager().getData(0);
                case "second":
                    return plugin.getManager().getRound().getPlayerManager().getData(1);
                case "third":
                    return plugin.getManager().getRound().getPlayerManager().getData(2);
            }
        } catch (Exception e) {
            return "";
        }

        return "";
    }

}

