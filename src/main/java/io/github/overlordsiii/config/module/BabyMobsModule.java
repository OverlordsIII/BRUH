package io.github.overlordsiii.config.module;

import io.github.overlordsiii.api.Module;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class BabyMobsModule implements Module, ConfigData {

	@Comment("If a mob's name contains any form of 'baby', then it will not grow up")
	public boolean stopGrowingUp = true;

	@Override
	public String getName() {
		return "Baby Mobs";
	}
}
