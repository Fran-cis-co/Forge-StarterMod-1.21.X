package net.fcontr.tutorialmod.sound;

import net.fcontr.tutorialmod.TutorialMod;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MOD_ID);

    public static final RegistryObject<SoundEvent> DETECTOR_USE = registerSoundEvent("detector_use");



    // custom method to register our custom sound events
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name)));
    }

    // method which allows us to register this class file to be able to hear the sound events
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
