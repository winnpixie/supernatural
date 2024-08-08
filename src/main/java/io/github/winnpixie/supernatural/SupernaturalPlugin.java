package io.github.winnpixie.supernatural;

import io.github.winnpixie.supernatural.listeners.ConnectionListener;
import io.github.winnpixie.supernatural.listeners.PlayerActionListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SupernaturalPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ConnectionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerActionListener(this), this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (Player player : getServer().getOnlinePlayers()) {
                PlayerData data = PlayerData.getData(player);

                data.abilityManager.tick(player);
            }
        }, 0L, 0L);
    }
}
