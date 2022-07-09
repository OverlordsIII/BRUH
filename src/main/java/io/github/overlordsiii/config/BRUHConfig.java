package io.github.overlordsiii.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "bruh")
public class BRUHConfig implements ConfigData {

	@Comment("If a mob's name contains any form of 'baby', then it will not grow up")
	public boolean stopGrowingUp = true;
}
