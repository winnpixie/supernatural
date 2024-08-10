package io.github.winnpixie.wpabilities.abilities;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AbilityManager {
    private final List<Ability> abilities = new ArrayList<>();

    public List<Ability> getAbilities() {
        return abilities;
    }

    public <T extends Ability> T getAbility(Class<T> abilityCls) {
        for (Ability ability : abilities) {
            if (ability.getClass().isAssignableFrom(abilityCls)) return abilityCls.cast(ability);
        }

        return null;
    }

    public <T extends Ability> T grant(T ability) {
        abilities.add(ability);
        return ability;
    }

    public void revoke(Ability ability) {
        abilities.remove(ability);
    }

    public void tick(Player player) {
        abilities.forEach(ability -> {
            if (ability.isActive()) {
                ability.tick(player);

                ability.setTicksActive(Math.min(ability.getTicksActive() + 1, ability.getEffectLength()));
                if (ability.getTicksActive() >= ability.getEffectLength()) ability.toggle(player);
            } else if (ability.getRemainingCooldown() > 0) {
                ability.setRemainingCooldown(Math.max(ability.getRemainingCooldown() - 1, 0));
            }
        });
    }
}
