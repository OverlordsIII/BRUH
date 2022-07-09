package io.github.overlordsiii.mixin;

import java.util.Locale;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;

@Mixin(PassiveEntity.class)
public class PassiveEntityMixin extends PathAwareEntity {
	protected PassiveEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "growUp(IZ)V", at = @At("HEAD"), cancellable = true)
	private void stopGrowingUp(int age, boolean overGrow, CallbackInfo ci) {
		if (this.hasCustomName() && BRUH.CONFIG.stopGrowingUp) {
			if (this.getCustomName().getString().toLowerCase(Locale.ROOT).contains("baby")) {
				ci.cancel();
			}
		}
	}
}
