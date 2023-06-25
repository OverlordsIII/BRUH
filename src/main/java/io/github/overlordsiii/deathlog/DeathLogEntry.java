package io.github.overlordsiii.deathlog;

import java.time.LocalDateTime;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class DeathLogEntry {

	private final String damageText;

	private final TimeEntry timeOfDeath;

	private final Vec3d coords;

	private final Identifier dimension;

	public DeathLogEntry(String damageText, TimeEntry timeOfDeath, Vec3d coords, Identifier dimension) {
		this.damageText = damageText;
		this.timeOfDeath = timeOfDeath;
		this.coords = coords;
		this.dimension = dimension;
	}

	public Identifier getDimension() {
		return dimension;
	}

	public String getDamageText() {
		return damageText;
	}

	public TimeEntry getTimeOfDeath() {
		return timeOfDeath;
	}

	public Vec3d getCoords() {
		return coords;
	}
}


