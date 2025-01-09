package net.fcontr.tutorialmod.item;

import net.fcontr.tutorialmod.TutorialMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    /*
        Create values for each piece of armor, set the enchantibility, it's toughness, knockback resistance, and what material is needed to repair it.
        In this case, it is sapphire instead of alexandrite due to the combination of using two different tutorial playlists that are each a different version of the game.
        I am also too lazy to go back and redo it.
     */
    public static final Holder<ArmorMaterial> ALEXANDRITE_ARMOR_MATERIAL = register("alexandrite", Util.make(new EnumMap<>(ArmorItem.Type.class),
                    attribute -> {
                        attribute.put(ArmorItem.Type.BOOTS, 5);
                        attribute.put(ArmorItem.Type.LEGGINGS, 7);
                        attribute.put(ArmorItem.Type.CHESTPLATE, 9);
                        attribute.put(ArmorItem.Type.HELMET, 5);
                        attribute.put(ArmorItem.Type.BODY, 11);
                    }), 15, 4F, 0.1F, () -> ModItems.SAPPHIRE.get());

    // method which registers the armor
    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE; // add what type of sound is used for the armor
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get()); // set what type of material is needed when repairing the armor
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
