package net.fcontr.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    /*
    *   Create strawberry object
    *   with this object there will be a random chance of 10 percent when eating this which will make the user have an increase movement speed
    *   for 200 seconds
    * */
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(2).fast()
            .saturationModifier(0.2f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.1f).build();
}
