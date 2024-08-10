package io.github.winnpixie.wpabilities;

import io.github.winnpixie.wpabilities.abilities.AbilityManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {
    private static final Map<UUID, PlayerData> DATA_MAP = new HashMap<>();

    public final UUID uuid;

    public final AbilityManager abilityManager = new AbilityManager();

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public static PlayerData getData(UUID uuid) {
        return DATA_MAP.computeIfAbsent(uuid, PlayerData::new);
    }

    public static PlayerData getData(Player player) {
        return getData(player.getUniqueId());
    }

    public static void erase(UUID uuid) {
        DATA_MAP.remove(uuid);
    }

    public static void erase(Player player) {
        erase(player.getUniqueId());
    }

    public static void erase(PlayerData data) {
        erase(data.uuid);
    }
}
