package io.github.overlordsiii.config;

import io.github.overlordsiii.config.module.ColorfulSheepModule;
import io.github.overlordsiii.config.module.DeathCoordinatesModule;
import io.github.overlordsiii.config.module.SafeCactusModule;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "bruh")
@Config.Gui.Background("minecraft:textures/block/barrel_side.png")
@Config.Gui.CategoryBackground(category = "safeCactus", background = "minecraft:textures/block/cactus_side.png")
@Config.Gui.CategoryBackground(category = "colorfulSheep", background = "minecraft:textures/block/pink_wool.png")
@Config.Gui.CategoryBackground(category = "deathCoordinates", background = "minecraft:textures/block/iron_block.png")
public class BRUHConfig implements ConfigData {

	@ConfigEntry.Category(value = "safeCactus")
	@ConfigEntry.Gui.CollapsibleObject
	public final SafeCactusModule safeCactusModule = new SafeCactusModule();

	@ConfigEntry.Category(value = "colorfulSheep")
	@ConfigEntry.Gui.CollapsibleObject
	public final ColorfulSheepModule colorfulSheepModule = new ColorfulSheepModule();

	@ConfigEntry.Category(value = "deathCoordinates")
	@ConfigEntry.Gui.CollapsibleObject
	public final DeathCoordinatesModule deathCoordinatesModule = new DeathCoordinatesModule();


}
