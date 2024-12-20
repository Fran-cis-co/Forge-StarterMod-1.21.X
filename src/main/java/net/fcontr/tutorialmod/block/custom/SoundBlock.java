package net.fcontr.tutorialmod.block.custom;
import java.util.List;
import java.util.Random;
import net.fcontr.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
/*
* ------ Custom sound block ------
* This block has the same function as the note block.
* The user has to right-click on the box to play a sound without an item in their hand.
* */


public class SoundBlock extends Block {
    public SoundBlock(Properties pProperties) {
        super(pProperties);
    }


    // Method that was from the parent class. This method is used to play a sound when the user interacts with the block without an item in their hand
    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        // Plays note block sound when the player right-clicks on the block
        pLevel.playSound(pPlayer, pPos, SoundEvents.NOTE_BLOCK_DIDGERIDOO.get(), SoundSource.BLOCKS,
                1f, 1f);
        // returns the interaction animation
        return InteractionResult.SUCCESS;
    }

    // Method which allows a tooltip to be added onto the block which is readable when hovering over the item in your inventory screen.
    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.literal("Makes sounds when right-clicked"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
