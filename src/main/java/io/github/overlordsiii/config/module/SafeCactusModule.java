package io.github.overlordsiii.config.module;

import io.github.overlordsiii.api.Module;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class SafeCactusModule implements Module, ConfigData {

	@Comment("Ensures that cactus item entities cannot be destroyed by other cactus blocks")
	public boolean safeCactus = true;

	@Comment("Ensures that any item entities cannot be destroyed by cactus blocks")
	public boolean safeItems = false;

	@Override
	public String getName() {
		return "Safe Cactus";
	}
}
