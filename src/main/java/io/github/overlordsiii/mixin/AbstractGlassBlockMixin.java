package io.github.overlordsiii.mixin;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.TransparentBlock;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

@Mixin(AbstractGlassBlock.class)
public class AbstractGlassBlockMixin extends TransparentBlock {
	public AbstractGlassBlockMixin(Settings settings) {
		super(settings);
	}

//	@ModifyVariable(method = "<init>", at = @At("HEAD"), index = 0, argsOnly = true)
//	private static AbstractGlassBlock settings(AbstractGlassBlock value) {
//		AbstractBlock.Settings settings = AbstractBlock.Settings.copy(value).hardness(1.5f).resistance(6f);
//		return value;
//	}

	@ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/TransparentBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V"))
	private static AbstractBlock.Settings changeSettings(Settings settings) {
		if (BRUH.CONFIG.generalConfig.fastGlassHoneycombHeadBreaking) {
			return settings.requiresTool();
		}

		return settings;
	}
}
