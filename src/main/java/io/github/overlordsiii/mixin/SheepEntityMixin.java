package io.github.overlordsiii.mixin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.github.overlordsiii.BRUH;
import io.github.overlordsiii.config.SheepWoolChances;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;

@Mixin(SheepEntity.class)
public class SheepEntityMixin {

	private static Random random = new Random();

	//use modify arg instead of overwrite/redirect
	@ModifyArg(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;setColor(Lnet/minecraft/util/DyeColor;)V"), index = 0)
	public DyeColor modifyColorArgs(DyeColor color) {
		if (!BRUH.CONFIG.generalConfig.colorfulSheep) {
			return color;
		}


		DyeColor color1 = null;

		for (SheepWoolChances value : randomize(SheepWoolChances.values())) {
			if (test(value.getProbability())) {
				color1 = value.getColor();
				break;
			}
		}

		if (color1 == null) {
			color1 = SheepWoolChances.values()[random.nextInt(SheepWoolChances.values().length)].getColor();
		}

		return color1;
	}

	private boolean test(double prob) {
		return prob >= random.nextInt(100) + 1;
	}

	private <T> List<T> randomize(T[] array) {
		List<T> list = Arrays.asList(array);
		Collections.shuffle(list);
		return list;
	}
}
