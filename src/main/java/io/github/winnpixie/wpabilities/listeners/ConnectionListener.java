package io.github.winnpixie.wpabilities.listeners;

import io.github.winnpixie.wpabilities.PlayerData;
import io.github.winnpixie.wpabilities.WPAbilitiesPlugin;
import io.github.winnpixie.wpabilities.abilities.impl.BerserkAbility;
import io.github.winnpixie.wpabilities.abilities.impl.HollowPurpleAbility;
import io.github.winnpixie.wpabilities.abilities.impl.OculaserAbility;
import io.github.winnpixie.wpabilities.abilities.impl.SpeedsterAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {
    private final WPAbilitiesPlugin plugin;

    public ConnectionListener(WPAbilitiesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        PlayerData data = PlayerData.getData(event.getPlayer());

        data.abilityManager.grant(new BerserkAbility());
        data.abilityManager.grant(new HollowPurpleAbility());
        data.abilityManager.grant(new OculaserAbility());
        data.abilityManager.grant(new SpeedsterAbility()).toggle(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerData data = PlayerData.getData(player);

        data.abilityManager.getAbilities().forEach(ability -> {
            if (ability.isActive()) ability.toggle(player);
        });

        PlayerData.erase(data);
    }
}
