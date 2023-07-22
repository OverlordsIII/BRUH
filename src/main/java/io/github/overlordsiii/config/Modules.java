package io.github.overlordsiii.config;

public enum Modules {
	COLORFUL_SHEEP("colorfulSheep"),
	SAFE_CACTUS("safeCactus"),
	DEATH_COORDINATES("deathCoordinates"),
	INVISIBLE_FRAMES("invisibleFrames"),
	BABY_MOBS("babyMobs"),
	NAMED_MONSTERS_NO_DESPAWN("namedMonstersNoDespawn"),
	WET_LAVA_SPONGE("wetLavaSponge"),
	DISMOUNTABLE_ENTITY("dismountableEntity"),
	LEAP_OF_FAITH("leapOfFaith"),
	FAST_GLASS_HONEYCOMB_HEAD_BREAKING("fastGlassHoneycombHeadBreaking");

	private String generalConfigEnableFieldName;

	Modules(String generalConfigEnableFieldName) {
		this.generalConfigEnableFieldName = generalConfigEnableFieldName;
	}

	public String getGeneralConfigEnableFieldName() {
		return generalConfigEnableFieldName;
	}
}
