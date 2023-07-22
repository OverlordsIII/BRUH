package io.github.overlordsiii.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {

	@Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
	public void playerHeadDrop(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
		if (context.getPlayer() != null) {
			World world = context.getWorld();
			PlayerEntity playerEntity = context.getPlayer();
			BlockPos pos = context.getBlockPos();
			ItemStack stack = context.getStack();

			if (stack.getItem().equals(Items.SHEARS) && world.getBlockState(pos).getBlock() instanceof AbstractSkullBlock) {
				Block.dropStacks(context.getWorld().getBlockState(context.getBlockPos()), context.getWorld(), context.getBlockPos());
				context.getWorld().setBlockState(context.getBlockPos(), Blocks.AIR.getDefaultState());
				stack.damage(1, playerEntity, playerx -> playerx.sendToolBreakStatus(context.getHand()));
				world.emitGameEvent(playerEntity, GameEvent.SHEAR, pos);
				playerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
			}
		}
	}
}
