package net.fcontr.tutorialmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/*
Metal Detector Custom Item:

The way the detector works in-game is by right-clicking the surface.
The code will look through all Y levels until it hits bedrock.
The code will try to find if that column of Y levels has iron ore or diamond ore.
If it finds those 2 ores, a message will get printed out in the in-game chat with the coordinates of the ore.
If it can't find it, another message will be printed saying no ore was found.
*/


public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            // Metal detector goes through every single y level until bedrock to detect desired ore
            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));
                // Print out the y level the ore is found on
                if(isValuableBlock(state)){
                    outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }
            }

            // Send message to user if the ore is not found
            if(!foundBlock) {
                player.sendSystemMessage(Component.literal("No Valuables Found!"));
            }
        }

//        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
//                player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        var handUsed = pContext.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), handUsed);


        return InteractionResult.SUCCESS;
    }

    // Method which prints out the coordinates of where the ore is. This gets sent to the in-game chat
    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }

    // Method which returns if the metal detector detects iron ore or diamond ore at the bottom
    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.DIAMOND_ORE);
    }
}
