package net.fcontr.tutorialmod.datagen;

import net.fcontr.tutorialmod.TutorialMod;
import net.fcontr.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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

        handheldItem(ModItems.ALEXANDRITE_AXE);
        handheldItem(ModItems.ALEXANDRITE_HOE);
        handheldItem(ModItems.ALEXANDRITE_PICKAXE);
        handheldItem(ModItems.ALEXANDRITE_SHOVEL);
        handheldItem(ModItems.ALEXANDRITE_SWORD);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "item/" + item.getId().getPath()));
    }

}
