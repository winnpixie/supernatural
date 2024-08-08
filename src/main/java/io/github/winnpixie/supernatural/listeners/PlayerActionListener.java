package io.github.winnpixie.supernatural.listeners;

import io.github.winnpixie.supernatural.PlayerData;
import io.github.winnpixie.supernatural.SupernaturalPlugin;
import io.github.winnpixie.supernatural.abilities.impl.OculaserAbility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerActionListener implements Listener {
    private final SupernaturalPlugin plugin;

    public PlayerActionListener(SupernaturalPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        OculaserAbility oculaser = PlayerData.getData(event.getPlayer()).abilityManager.getAbility(OculaserAbility.class);
        if (oculaser.isReady()) {
            oculaser.toggle(event.getPlayer());
        }
    }
}
