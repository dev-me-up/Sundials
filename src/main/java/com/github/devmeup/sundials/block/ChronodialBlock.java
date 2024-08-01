package com.github.devmeup.sundials.block;

import com.github.devmeup.sundials.config.ModConfig;
import com.github.devmeup.sundials.references.ModDamageTypes;
import com.github.devmeup.sundials.references.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ChronodialBlock extends SundialBlock {
    private static final DirectionProperty FACING = Properties.FACING;
    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final BooleanProperty SUN_FORM = BooleanProperty.of("sun_form");

    public ChronodialBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SUN_FORM, true));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        boolean damageOnUse = ModConfig.CONFIG.instance().applyTemporalShockDamage;
        int damage = ModConfig.CONFIG.instance().temporalShockDamage;

        if (world.isClient()) {
            return ActionResult.CONSUME;
        }
        if (player.isSneaking()) {
            changeForm(state, world, pos);
        } else {
            if (hasSkyAccess(world, pos)) {
                if (damageOnUse) {
                    player.damage(ModDamageTypes.of(world, ModDamageTypes.TEMPORAL_DAMAGE), (float)damage);
                }
                changeTime(state, world, pos);
            } else {
                player.sendMessage(Text.literal("You are unable to change the time."));
            }
        }
        return ActionResult.SUCCESS;
    }

    private void changeForm(BlockState state, World world, BlockPos pos) {
        if (world.getBlockState(pos).equals(state.with(SUN_FORM, true))) {
            world.setBlockState(pos, state.with(SUN_FORM, false));
        } else {
            world.setBlockState(pos, state.with(SUN_FORM, true));
        }
        world.playSound(null, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat(1.1F, 1.2F));
    }

    private void changeTime(BlockState state, World world, BlockPos pos) {
        ServerWorld serverWorld = (ServerWorld)world;
        serverWorld.setTimeOfDay(state.get(SUN_FORM) ? 0: 13000); // Set to day if sun form, night if moon form
        serverWorld.spawnParticles(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 10,0.0D, 0.1D, 0.0D, 0.05D);
        serverWorld.playSound(null, pos, ModSounds.TICK_TOCK, SoundCategory.BLOCKS, 1.0F, new Random().nextFloat(1.0F, 1.1F));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SUN_FORM);
    }

}
