package io.github.overlordsiii.mixin;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.HayBlock;

@Mixin(HayBlock.class)
public class HayBlockMixin {

	@ModifyArg(method = "onLandedUpon", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z"), index = 1)
	public float modifyDamageMultiplier(float fallDistance) {
		return (float) ((100 - BRUH.CONFIG.leapOfFaithModule.haybaleBlockReductionPercentage) / 100f);
	}
}
