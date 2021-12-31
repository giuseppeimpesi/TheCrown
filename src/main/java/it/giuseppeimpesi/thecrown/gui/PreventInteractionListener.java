package it.giuseppeimpesi.thecrown.gui;

import it.giuseppeimpesi.thecrown.manager.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class PreventInteractionListener implements Listener {

    private GameManager manager;

    public PreventInteractionListener(GameManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(manager.getGui().getInv())) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(manager.getGui().getInv())) {
            e.setCancelled(true);
        }
    }

}
