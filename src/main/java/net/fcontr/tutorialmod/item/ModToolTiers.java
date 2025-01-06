package net.fcontr.tutorialmod.item;

import net.fcontr.tutorialmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier ALEXANDRITE = new ForgeTier(1400, 4, 3f, 20,
            ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL, () -> Ingredient.of(ModItems.SAPPHIRE.get()),
            ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL);
}
