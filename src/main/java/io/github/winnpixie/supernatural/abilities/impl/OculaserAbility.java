package io.github.winnpixie.supernatural.abilities.impl;

import io.github.winnpixie.supernatural.abilities.Ability;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class OculaserAbility extends Ability {
    public OculaserAbility() {
        super(10 * 20, 5 * 20);
    }

    @Override
    public void tick(Player player) {
        double beamDistance = 16;
        World world = player.getWorld();
        Location eyeLoc = player.getLocation().add(0, player.getEyeHeight(), 0);
        RayTraceResult result = world.rayTraceEntities(eyeLoc, eyeLoc.getDirection(), 16.0, 0.47, entity -> entity != player);

        if (result != null && result.getHitEntity() instanceof Mob victim) {
            victim.damage(2, player);

            beamDistance = eyeLoc.distance(victim.getLocation());
        }

        for (int step = 0; step < beamDistance; step++) {
            for (int i = 0; i < 10; i++) {
                Vector lookVec = eyeLoc.getDirection().normalize();
                Location particleLoc = eyeLoc.add(lookVec.multiply(step + (i / 10.0)));

                world.spawnParticle(Particle.DUST, particleLoc, 1, new Particle.DustOptions(Color.RED, 0.5f));
            }
        }
    }
}
