package io.github.overlordsiii.mixin;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(CactusBlock.class)
public class CactusBlockMixin {

	@Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
	public void stopEntityDamageForCactus(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		if (entity instanceof ItemEntity itemEntity) {
			if (itemEntity.getStack().getItem().equals(Items.CACTUS) && BRUH.CONFIG.safeCactusModule.safeCactus) {
				ci.cancel();
			} else if (BRUH.CONFIG.safeCactusModule.safeItems) {
				ci.cancel();
			}
		}
	}
}
