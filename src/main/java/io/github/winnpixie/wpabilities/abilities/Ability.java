package io.github.winnpixie.wpabilities.abilities;

import org.bukkit.entity.Player;

public class Ability {
    private final int effectLength;
    private final int cooldownLength;

    private int ticksActive;
    private int remainingCooldown;
    private boolean active;

    public Ability(int cooldownLength, int effectLength) {
        this.cooldownLength = cooldownLength;
        this.effectLength = effectLength;
    }

    public int getEffectLength() {
        return effectLength;
    }

    public int getCooldownLength() {
        return cooldownLength;
    }

    public int getTicksActive() {
        return ticksActive;
    }

    public void setTicksActive(int ticksActive) {
        this.ticksActive = ticksActive;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void toggle(Player player) {
        setActive(!active);

        if (active) {
            setTicksActive(0);
            activate(player);
        } else {
            finish(player);
            setRemainingCooldown(getCooldownLength());
        }
    }

    public boolean isReady() {
        return getRemainingCooldown() <= 0f;
    }

    public void activate(Player player) {
    }

    public void finish(Player player) {
    }

    public void tick(Player player) {
    }
}
