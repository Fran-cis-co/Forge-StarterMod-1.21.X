package net.fcontr.tutorialmod.event;

import net.fcontr.tutorialmod.TutorialMod;
import net.fcontr.tutorialmod.item.custom.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
    // A certain block gets mined in the middle of a 3x3 and now all those blocks in that 3x3 are in a list of blocks
    // this event gets triggered when a block breaks
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        // When a block is broken this if statement checks if the player is holding a hammer in their hand and if we are in a server
        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer){
            // get the initial block position
            BlockPos initialBlockPos = event.getPos();

            // if the block is already in the hash set, then don't do anything
            // this is the base case so we don't break the game
            if(HARVESTED_BLOCKS.contains(initialBlockPos)){
                return;
            }

            // Go through all the blocks in the 3x3 matrix
            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(2, initialBlockPos, serverPlayer)){
                // Checks if the block is a pickaxe mineable item
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                // Do recursion by keep on doing the block break event to add the blocks in the 3x3 and eventually removing them after mining them
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    // Custom event to understand events
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event){
        // This event triggers when a living entity is about to take damage.
        // For this case, we want to make sure the entity is a sheep and the player is the one doing damage
        if(event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {

            /*
            *   If the player is holding an end rod in their hand while holding the sheep then send a message in the
            *   in-game chat along with poisoning the sheep. Remove an end rod after
            */
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit a sheep."));
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 6));
                player.getMainHandItem().shrink(1);
            }
        }
    }

}
