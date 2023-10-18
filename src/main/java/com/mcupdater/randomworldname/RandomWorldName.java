package com.mcupdater.randomworldname;

import com.mcupdater.randomworldname.setup.Config;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

@Mod(RandomWorldName.MODID)
public class RandomWorldName {
    public static final String MODID = "randomworldname";
    public static final Logger LOGGER = LogManager.getLogger();

    public RandomWorldName() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        MinecraftForge.EVENT_BUS.addListener(this::injectButton);
    }

    private void injectButton(ScreenEvent.Init evt) {
        LOGGER.info("Screen Init: " + evt.getScreen().getClass().toString());
        if (evt.getScreen() instanceof CreateWorldScreen) {
            ((CreateWorldScreen) evt.getScreen()).addRenderableWidget(new Button(evt.getScreen().width / 2 + Config.X.get(), Config.Y.get(), 100, 20, Component.translatable("button.randomworldname.generate"), buttonPress -> {
                String name;
                if (Config.ORDER.get()) {
                    name = getRandomEntry(Config.PLACES.get()) + Config.SEPARATOR.get() + getRandomEntry(Config.ADJECTIVES.get());
                } else {
                    name = getRandomEntry(Config.ADJECTIVES.get()) + Config.SEPARATOR.get() + getRandomEntry(Config.PLACES.get());
                }
                CreateWorldScreen screen = (CreateWorldScreen) evt.getScreen();
                screen.nameEdit.setValue(name);
            }));
        }
    }

    private String getRandomEntry(List<? extends String> sourceSet) {
        int size = sourceSet.size();
        int entry = new Random().nextInt(size);
        int i = 0;
        for (String name : sourceSet) {
            if (i == entry) {
                return name;
            }
            i++;
        }
        return "";
    }
}
