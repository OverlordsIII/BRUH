package io.github.overlordsiii.config;

import io.github.overlordsiii.BRUH;

import net.minecraft.util.DyeColor;

import static io.github.overlordsiii.BRUH.CONFIG;

public enum SheepWoolChances {
	WHITE(CONFIG.colorfulSheepModule.whiteChance, DyeColor.WHITE),
	ORANGE(CONFIG.colorfulSheepModule.orangeChance, DyeColor.ORANGE),
	MAGENTA(CONFIG.colorfulSheepModule.magentaChance, DyeColor.MAGENTA),
	LIGHT_BLUE(CONFIG.colorfulSheepModule.lightBlueChance, DyeColor.LIGHT_BLUE),
	YELLOW(CONFIG.colorfulSheepModule.yellowChance, DyeColor.YELLOW),
	LIME(CONFIG.colorfulSheepModule.limeChance, DyeColor.LIME),
	PINK(CONFIG.colorfulSheepModule.pinkChance, DyeColor.PINK),
	GRAY(CONFIG.colorfulSheepModule.grayChance, DyeColor.GRAY),
	LIGHT_GRAY(CONFIG.colorfulSheepModule.lightGrayChance, DyeColor.LIGHT_GRAY),
	CYAN(CONFIG.colorfulSheepModule.cyanChance, DyeColor.CYAN),
	PURPLE(CONFIG.colorfulSheepModule.purpleChance, DyeColor.PURPLE),
	BLUE(CONFIG.colorfulSheepModule.blueChance, DyeColor.BLUE),
	BROWN(CONFIG.colorfulSheepModule.brownChance, DyeColor.BROWN),
	GREEN(CONFIG.colorfulSheepModule.greenChance, DyeColor.GREEN),
	RED(CONFIG.colorfulSheepModule.redChance, DyeColor.RED),
	BLACK(CONFIG.colorfulSheepModule.blackChance, DyeColor.BLACK);

	final DyeColor color;

	final double probability;

	SheepWoolChances(double probability, DyeColor dyeColor) {
		this.probability = probability;
		this.color = dyeColor;
	}

	public double getProbability() {
		return probability;
	}

	public DyeColor getColor() {
		return color;
	}
}
