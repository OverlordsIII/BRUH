package io.github.overlordsiii.mixin;

import com.mojang.authlib.GameProfile;
import io.github.overlordsiii.BRUH;
import io.github.overlordsiii.config.DeathCoordinateMessageType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

	@Shadow @Final public MinecraftServer server;

	@Shadow public abstract void sendChatMessage(SentMessage message, boolean filterMaskEnabled, MessageType.Parameters params);

	@Shadow public abstract void sendMessage(Text message);

	public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}

	@Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
	public void onBroadcast(DamageSource damageSource, CallbackInfo ci) {
		DeathCoordinateMessageType type = BRUH.CONFIG.deathCoordinatesModule.msgType;

		if (type == DeathCoordinateMessageType.BROADCAST) {
			this.server.getPlayerManager().broadcast(this.getDisplayName().copy().append(" has died at " + this.getBlockPos()), false);
		} else if (type == DeathCoordinateMessageType.WHISPER) {
			this.sendMessage(Text.literal("You died at " + this.getBlockPos()));
		}
	}
}
