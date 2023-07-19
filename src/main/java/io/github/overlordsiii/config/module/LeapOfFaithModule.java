package io.github.overlordsiii.config.module;

import io.github.overlordsiii.api.Module;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class LeapOfFaithModule implements Module {

	@Comment("Indicates how much damage from a fall on hayblocks should be reduced by. Default minecraft sets this to 80%")
	public double haybaleBlockReductionPercentage = 80;

	@Override
	public String getName() {
		return "Leap of Faith";
	}
}
