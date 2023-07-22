package io.github.overlordsiii.mixin;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;

@Mixin(Blocks.class)
public class BlocksMixin {

	@ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V", ordinal = 123), index = 0)
	private static AbstractBlock.Settings modifyHoneyCombSettings(AbstractBlock.Settings settings) {
		if (BRUH.CONFIG.generalConfig.fastGlassHoneycombHeadBreaking) {
			return settings.requiresTool();
		}

		return settings;
	}

}
