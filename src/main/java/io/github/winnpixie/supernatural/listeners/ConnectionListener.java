package io.github.winnpixie.supernatural.listeners;

import io.github.winnpixie.supernatural.PlayerData;
import io.github.winnpixie.supernatural.SupernaturalPlugin;
import io.github.winnpixie.supernatural.abilities.impl.OculaserAbility;
import io.github.winnpixie.supernatural.abilities.impl.SpeedsterAbility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {
    private final SupernaturalPlugin plugin;

    public ConnectionListener(SupernaturalPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        PlayerData data = PlayerData.getData(event.getPlayer());
        data.abilityManager.grant(new OculaserAbility());
        data.abilityManager.grant(new SpeedsterAbility()).toggle(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        PlayerData.erase(event.getPlayer());
    }
}
