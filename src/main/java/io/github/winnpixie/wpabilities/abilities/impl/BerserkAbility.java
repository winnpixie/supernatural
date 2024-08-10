package io.github.winnpixie.wpabilities.abilities.impl;

import io.github.winnpixie.wpabilities.abilities.Ability;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class BerserkAbility extends Ability {
    private double baseDamage;

    public BerserkAbility() {
        super(10 * 20, 60 * 20);
    }

    @Override
    public void activate(Player player) {
        AttributeInstance attackDamageAttribute = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        baseDamage = attackDamageAttribute.getBaseValue();
        attackDamageAttribute.setBaseValue(baseDamage * 10.0);
    }

    @Override
    public void tick(Player player) {
        player.spawnParticle(Particle.DUST, player.getLocation().add(0, 1, 0), 1, new Particle.DustOptions(Color.RED, 1f));
    }

    @Override
    public void finish(Player player) {
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(baseDamage);
    }
}
