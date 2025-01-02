package net.fcontr.tutorialmod.datagen;

import net.fcontr.tutorialmod.TutorialMod;
import net.fcontr.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_SAPPHIRE.get());
        basicItem(ModItems.PINE_CONE.get());
        basicItem(ModItems.STRAWBERRY.get());
        basicItem(ModItems.SAPPHIRE.get());
        basicItem(ModItems.METAL_DETECTOR.get());
    }
}
