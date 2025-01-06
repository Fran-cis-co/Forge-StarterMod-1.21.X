package net.fcontr.tutorialmod.item;

import net.fcontr.tutorialmod.TutorialMod;
import net.fcontr.tutorialmod.item.custom.FuelItem;
import net.fcontr.tutorialmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    // Custom food item
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    // Custom fuel item
    // burnTime is 20 seconds
    // 20 ticks == 1 second
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    // ----- Register the custom tools -----
    public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register("alexandrite_sword",
            () -> new SwordItem(ModToolTiers.ALEXANDRITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.ALEXANDRITE, 3, -2.4f))));

    public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register("alexandrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ALEXANDRITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 1, -2.8f))));

    public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register("alexandrite_shovel",
            () -> new ShovelItem(ModToolTiers.ALEXANDRITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.ALEXANDRITE, 1.5f, -3.0f))));

    public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register("alexandrite_axe",
            () -> new AxeItem(ModToolTiers.ALEXANDRITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 6, -3.2f))));

    public static final RegistryObject<Item> ALEXANDRITE_HOE = ITEMS.register("alexandrite_hoe",
            () -> new HoeItem(ModToolTiers.ALEXANDRITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.ALEXANDRITE, 0, -3f))));
    // --------------------------------------



    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }
}
