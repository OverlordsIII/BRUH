package io.github.overlordsiii.mixin;

import io.github.overlordsiii.BRUH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.SpongeBlock;
import net.minecraft.block.WetSpongeBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

// pretty much just copied from sponge block with small changes
@Mixin(WetSpongeBlock.class)
public abstract class WetSpongeBlockMixin extends Block {
	public WetSpongeBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "onBlockAdded", at = @At("HEAD"), cancellable = true)
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
		if (BRUH.CONFIG.generalConfig.wetLavaSponge) {
			if (oldState.isOf(state.getBlock())) {
				return;
			}
			this.update(world, pos);
			ci.cancel();
		}
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		if (BRUH.CONFIG.generalConfig.wetLavaSponge) {
			this.update(world, pos);
		}
		super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
	}

	protected void update(World world, BlockPos pos) {
		if (this.absorbWater(world, pos)) {
			world.setBlockState(pos, Blocks.SPONGE.getDefaultState(), Block.NOTIFY_LISTENERS);
			world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(Blocks.LAVA.getDefaultState()));
		}
	}

	private boolean absorbWater(World world, BlockPos pos) {
		return BlockPos.iterateRecursively(pos, 6, 65, (currentPos, queuer) -> {
			for (Direction direction : Direction.values()) {
				queuer.accept(currentPos.offset(direction));
			}
		}, currentPos -> {
			FluidDrainable fluidDrainable;
			if (currentPos.equals(pos)) {
				return true;
			}
			BlockState blockState = world.getBlockState((BlockPos)currentPos);
			FluidState fluidState = world.getFluidState((BlockPos)currentPos);
			if (!fluidState.isIn(FluidTags.LAVA)) {
				return false;
			}
			Block block = blockState.getBlock();
			if (block instanceof FluidDrainable && !(fluidDrainable = (FluidDrainable)((Object)block)).tryDrainFluid(world, (BlockPos)currentPos, blockState).isEmpty()) {
				return true;
			}
			if (blockState.getBlock() instanceof FluidBlock) {
				world.setBlockState((BlockPos)currentPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
			} else if (blockState.isOf(Blocks.KELP) || blockState.isOf(Blocks.KELP_PLANT) || blockState.isOf(Blocks.SEAGRASS) || blockState.isOf(Blocks.TALL_SEAGRASS)) {
				BlockEntity blockEntity = blockState.hasBlockEntity() ? world.getBlockEntity((BlockPos)currentPos) : null;
				SpongeBlock.dropStacks(blockState, world, currentPos, blockEntity);
				world.setBlockState((BlockPos)currentPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
			} else {
				return false;
			}
			return true;
		}) > 1;
	}
}
