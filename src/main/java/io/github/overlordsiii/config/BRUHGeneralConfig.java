package io.github.overlordsiii.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class BRUHGeneralConfig implements ConfigData {

	@Comment("Enables colorful sheep to randomly spawn based on percentages (see module for more details)")
	public boolean colorfulSheep = true;

	@Comment("Enables safe cactus module (See module for more details)")
	public boolean safeCactus = true;

	@Comment("Logs Death Coordinates and can tell them to you when you die (See Death Coordinates Tab for more details)")
	public boolean deathCoordinates = true;

	@Comment("Shift right clicking an item frame with an amythest shard makes it go invisible")
	public boolean invisibleFrames = true;

	@Comment("Baby mobs named with a nametag that contains any form of the word 'baby' will not grow up")
	public boolean babyMobs = true;

	@Comment("Ensures that named monsters do not despawn even when switching difficulties")
	public boolean namedMonstersNoDespawn = true;

	@Comment("Using a wet sponge on lava will have the same effect that dry spongs have on water. Wet sponge will be converted to dry sponge afterward")
	public boolean wetLavaSponge = true;

	@Comment("Shift right clicking on a vehicle will force its passengers to exit the vehicle")
	public boolean dismountableEntity = true;

	@Comment("Allows you to configure the percentage of damage reduction when falling on hay bales (see module)")
	public boolean leapOfFaith = true;

	@Comment("Enables fast break of glass and honeycombs using pickaxes and hoes. Right clicking shears on player heads makes them instantly break")
	public boolean fastGlassHoneycombHeadBreaking = true;

}
