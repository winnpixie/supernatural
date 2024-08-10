package io.github.winnpixie.supernatural.abilities.impl;

import io.github.winnpixie.supernatural.abilities.Ability;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class HollowPurpleAbility extends Ability {
    private final Vector moveVec = new Vector();

    private ArmorStand stand;
    private Vector lookVec;
    private int age;

    public HollowPurpleAbility() {
        super(20, 5 * 20);
    }

    @Override
    public void activate(Player player) {
        Location eyeLocation = player.getLocation().add(0, player.getEyeHeight(), 0);
        stand = (ArmorStand) player.getWorld().spawnEntity(eyeLocation, EntityType.ARMOR_STAND);
        stand.setGravity(false);
        stand.setInvisible(true);
        stand.setCustomName("\247dHollow \2475Purple");
        lookVec = eyeLocation.getDirection().normalize();
        age = 0;
    }

    @Override
    public void tick(Player player) {
        World world = stand.getWorld();

        for (Entity entity : world.getNearbyEntities(stand.getBoundingBox().expand(2.5))) {
            if (entity == stand) continue;
            if (entity == player) continue;
            if (!(entity instanceof Mob mob)) continue;

            mob.damage(mob.getHealth(),
                    DamageSource.builder(DamageType.MAGIC)
                            .withDirectEntity(stand)
                            .withCausingEntity(player)
                            .build());
        }

        if (age > 10) {
            for (int x = -3; x <= 3; x++) {
                for (int y = -3; y <= 3; y++) {
                    for (int z = -3; z <= 3; z++) {
                        Location blockLoc = stand.getLocation().add(x, y, z);
                        if (blockLoc.getY() < -64) continue;

                        Block block = world.getBlockAt(blockLoc);
                        if (block.getType() == Material.BEDROCK || Tag.AIR.isTagged(block.getType())) continue;
                        if (blockLoc.distanceSquared(stand.getLocation()) > 9) continue;

                        block.breakNaturally();
                    }
                }
            }
        }

        world.spawnParticle(Particle.DUST, stand.getLocation(), 20, 1.5, 1.5, 1.5, new Particle.DustOptions(Color.PURPLE, 2));
        world.spawnParticle(Particle.DUST, stand.getLocation(), 20, 1.5, 1.5, 1.5, new Particle.DustOptions(Color.FUCHSIA, 2));
        stand.teleport(stand.getLocation().add(moveVec.copy(lookVec).multiply(0.5)));
        age++;
    }

    @Override
    public void finish(Player player) {
        if (stand != null) stand.remove();
    }
}
