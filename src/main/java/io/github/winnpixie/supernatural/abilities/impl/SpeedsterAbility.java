package io.github.winnpixie.supernatural.abilities.impl;

import io.github.winnpixie.supernatural.abilities.Ability;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class SpeedsterAbility extends Ability {
    public SpeedsterAbility() {
        super(0, 2);
    }

    @Override
    public void activate(Player player) {
        player.setWalkSpeed(0.69f);
    }

    @Override
    public void finish(Player player) {
        player.setWalkSpeed(0.2f);
    }

    @Override
    public void tick(Player player) {
        setTicksActive(0);

        player.getWorld().spawnParticle(Particle.DUST, player.getLocation(), 5, 0.1, 0.25, 0.1,
                new Particle.DustOptions(Color.YELLOW, 1f));
        player.getWorld().spawnParticle(Particle.DUST, player.getLocation(), 5, 0.1, 0.25, 0.1,
                new Particle.DustOptions(Color.WHITE, 1f));
        player.getWorld().spawnParticle(Particle.DUST, player.getLocation(), 5, 0.1, 0.25, 0.1,
                new Particle.DustOptions(Color.ORANGE, 1f));
    }
}
