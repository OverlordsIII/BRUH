package io.github.overlordsiii.mixin;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.mojang.authlib.GameProfile;
import io.github.overlordsiii.BRUH;
import io.github.overlordsiii.config.DeathCoordinateMessageType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

	@Shadow @Final public MinecraftServer server;

	@Shadow public abstract void sendChatMessage(SentMessage message, boolean filterMaskEnabled, MessageType.Parameters params);

	@Shadow public abstract void sendMessage(Text message);

	@Shadow public abstract ServerWorld getServerWorld();

	public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}

	@Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V", shift = At.Shift.AFTER))
	public void onBroadcast(DamageSource damageSource, CallbackInfo ci) {
		DeathCoordinateMessageType type = BRUH.CONFIG.deathCoordinatesModule.msgType;

		if (type == DeathCoordinateMessageType.WHISPER && BRUH.CONFIG.deathCoordinatesModule.enabled) {
			this.sendMessage(Text.literal("You died in " + getDimension() + " at x = " + this.getBlockPos().getX() + ", y = " + this.getBlockPos().getY() + ", z = " + this.getBlockPos().getZ()));
		}
	}

	@ModifyArg(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"), index = 0)
	public Text modifyIncomingDeathText(Text message) {
		if (BRUH.CONFIG.deathCoordinatesModule.msgType == DeathCoordinateMessageType.BROADCAST && BRUH.CONFIG.deathCoordinatesModule.enabled) {
			return message.copy().append(" in " + getDimension() + " at x = " + this.getBlockPos().getX() + ", y = " + this.getBlockPos().getY() + ", z = " + this.getBlockPos().getZ());
		}

		return message;
	}

	private String getDimension() {
		String rawDim = this.getServerWorld().getDimensionKey().getValue().getPath();
		return Arrays.stream(rawDim.split("_"))
			.map(this::capitalize)
			.collect(Collectors.joining(" "));
	}

	private String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() +
			s.substring(1);
	}
}
