package net.fcontr.tutorialmod.trim;

import net.fcontr.tutorialmod.TutorialMod;
import net.fcontr.tutorialmod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class ModTrimMaterials {
    // resource materials
    public static final ResourceKey<TrimMaterial> ALEXANDRITE =
            ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "alexandrite"));

    // bootstrap method to register as many trim materials
    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        /*
        *   From the third parameter onwards:
        *       1. Item that should be made into the material
        *       2. Text color when hovering over the armor that has been trimmed with the material
        *       3. model index which refers to which texture will go onto the armor. This calls to the ModItemModelProvider class and uses the trimmaterials variable
        * */
        register(context, ALEXANDRITE, ModItems.SAPPHIRE.get(), Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()), 0.8F);
    }

    // Register method which makes sures the trim material is created properly
    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item,
                                 Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimmaterial);
    }
}