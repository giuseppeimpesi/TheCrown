package it.giuseppeimpesi.thecrown.gui;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

@Data
public class ParticipantsGUI implements Listener {

    private GameManager manager;
    private final Inventory inv;

    public ParticipantsGUI(GameManager manager) {
        this.manager = manager;
        inv = Bukkit.createInventory(null, 45, "Partecipanti");

        for (String s : manager.getPlayerConfig().getFileConfiguration().getConfigurationSection("participants").getKeys(false)) {
            inv.addItem(getPlayerHead(s));
        }
    }

    public void reloadGui() {
        inv.clear();
        for (String s : manager.getPlayerConfig().getFileConfiguration().getConfigurationSection("participants").getKeys(false)) {
            inv.addItem(getPlayerHead(s));
        }
    }

    public void addParticipant(Player player) {
        inv.addItem(getPlayerHead(player.getName()));
        reloadGui();
    }

    public void removeParticipant(Player player) {
        inv.remove(getPlayerHead(player.getName()));
        reloadGui();
    }

    private ItemStack getPlayerHead(String s) {
        final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(s);
        meta.setDisplayName("§8> §b" + s);
        meta.setLore(Arrays.asList("§7Data iscrizione: §f" + manager.getPlayerConfig().getDate(Bukkit.getPlayer(s))));
        item.setItemMeta(meta);
        return item;
    }

}
