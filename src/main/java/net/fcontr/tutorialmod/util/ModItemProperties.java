package net.fcontr.tutorialmod.util;

import net.fcontr.tutorialmod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

// Item properties allows us to change the model state of any custom item.
// For example, if an item has ran out of durability we can change the model of said item to represent the durability.
public class ModItemProperties {
    public static void addCustomItemProperties(){
        // call method to register modded bow
        makeCustomBow(ModItems.KAUPEN_BOW.get());
    }

    // custom private method which allows us to create a custom bow with the use of the in-game code to register the vanilla bow
    private static void makeCustomBow(Item item){
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (p_340951_, p_340952_, p_340953_, p_340954_) -> {
            if (p_340953_ == null) {
                return 0.0F;
            } else {
                return p_340953_.getUseItem() != p_340951_ ? 0.0F : (float)(p_340951_.getUseDuration(p_340953_) - p_340953_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
            return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
        });
    }
}
