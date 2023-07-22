package io.github.overlordsiii.mixin;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityMixin extends AbstractDecorationEntity {
	protected ItemFrameEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "interact", at = @At("HEAD"), cancellable = true)
	public void addInvisAbility(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
		if (BRUH.CONFIG.generalConfig.invisibleFrames) {
			ItemStack stack = player.getStackInHand(hand);
			if (stack.getItem().equals(Items.AMETHYST_SHARD)) {
				stack.decrement(1);
				this.setInvisible(true);
				cir.setReturnValue(ActionResult.SUCCESS);
			}
		}
	}
}
