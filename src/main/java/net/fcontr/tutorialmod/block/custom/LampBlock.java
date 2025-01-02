package net.fcontr.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

// Using a custom lamp to discover how block states work in minecraft

public class LampBlock extends Block {
    // Create a boolean property to determine whether a lamp is clicked since a basic boolean value will not work due to creating one class of the block
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    public LampBlock(Properties properties) {
        super(properties);
        // have the lamp be off when placed down
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
    }

    /*
    *  When interacting with the block without an item in hand,
    *  the animation will play to let the player know the interaction was successful.
    *  Along with this, the boolean property which determines if the lamp is on/off will change to the opposite of what it was.
    * */
    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if(!pLevel.isClientSide()){
            boolean currentState = pState.getValue(CLICKED);
            pLevel.setBlockAndUpdate(pPos, pState.setValue(CLICKED, !currentState));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CLICKED);
    }
}
