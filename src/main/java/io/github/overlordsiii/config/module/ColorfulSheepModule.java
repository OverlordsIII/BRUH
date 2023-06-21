package io.github.overlordsiii.config.module;

import io.github.overlordsiii.api.Module;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class ColorfulSheepModule implements Module, ConfigData {

	@Comment("Allows for all colors of sheep to be available to be chosen when a sheep is spawned")
	public boolean colorfulSheep = true;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double whiteChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double orangeChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double magentaChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double lightBlueChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double yellowChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double limeChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double pinkChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double grayChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double lightGrayChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double cyanChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double purpleChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double blueChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double brownChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double greenChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double redChance = 6.25;

	@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
	@ConfigEntry.Gui.RequiresRestart
	public double blackChance = 6.25;

	@Override
	public String getName() {
		return "Colorful Sheep";
	}
}
