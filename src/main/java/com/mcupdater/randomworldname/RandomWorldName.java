package com.mcupdater.randomworldname;

import com.mcupdater.randomworldname.setup.Config;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.util.List;
import java.util.Random;

@Mod(value=RandomWorldName.MODID, dist= Dist.CLIENT)
public class RandomWorldName {
    public static final String MODID = "randomworldname";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RandomWorldName(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        NeoForge.EVENT_BUS.addListener(this::injectButton);
    }

    private void injectButton(ScreenEvent.Init.Post evt) {
        if (evt.getScreen() instanceof CreateWorldScreen cws) {
            Button generateName = Button.builder(Component.translatable("button.randomworldname.generate"), button -> {
                cws.tabNavigationBar.tabs.stream().forEach(internalTab -> {
                    if (internalTab instanceof CreateWorldScreen.GameTab gTab) {
                        String name;
                        if (Config.ORDER.get()) {
                            name = getRandomEntry(Config.PLACES.get()) + Config.SEPARATOR.get() + getRandomEntry(Config.ADJECTIVES.get());
                        } else {
                            name = getRandomEntry(Config.ADJECTIVES.get()) + Config.SEPARATOR.get() + getRandomEntry(Config.PLACES.get());
                        }
                        gTab.nameEdit.setValue(name);
                    }
                });
            }).size(210, 20).build();
            cws.tabNavigationBar.tabs.stream().forEach(tab -> {
                if (tab instanceof CreateWorldScreen.GameTab gameTab) {
                    gameTab.layout.visitChildren(child -> {
                        if (child instanceof LinearLayout worldNameLayout) {

                            worldNameLayout.addChild(generateName);
                        }
                    });
                    gameTab.layout.arrangeElements();
                }
            });
            cws.tabNavigationBar.selectTab(1,false);
            cws.tabNavigationBar.selectTab(0,false);
            generateName.onPress(null); //Note: no input modifiers are needed
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
