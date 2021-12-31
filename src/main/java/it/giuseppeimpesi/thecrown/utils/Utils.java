package it.giuseppeimpesi.thecrown.utils;

import com.google.common.collect.Lists;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public final class Utils {

    public static String toColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
