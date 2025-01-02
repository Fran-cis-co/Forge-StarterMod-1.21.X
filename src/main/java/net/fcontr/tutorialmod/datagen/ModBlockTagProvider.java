package net.fcontr.tutorialmod.datagen;

import net.fcontr.tutorialmod.TutorialMod;
import net.fcontr.tutorialmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    // add items which should be mineable with a certain tool
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // add modded items which are minable with a pickaxe
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.RAW_SAPPHIRE_BLOCK.get())
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.SOUND_BLOCK.get());

        // let the sapphire ore and raw sapphire block be mineable with an iron tool (pickaxe)
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
        // let the sapphire block be mineable with a diamond tool (pickaxe)
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.SAPPHIRE_BLOCK.get());
    }
}
