package io.github.overlordsiii.mixin;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.mob.MobEntity;

@Mixin(MobEntity.class)
public class MobEntityMixin {

	@Redirect(method = "checkDespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;discard()V", ordinal = 0))
	public void stopPeacefulDespawn(MobEntity instance) {
		if (instance.hasCustomName() && BRUH.CONFIG.generalConfig.namedMonstersNoDespawn) {
			return;
		}

		instance.discard();
	}
}
