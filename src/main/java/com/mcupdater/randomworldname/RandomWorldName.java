package com.mcupdater.randomworldname;

import com.mcupdater.randomworldname.setup.Config;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

@Mod(RandomWorldName.MODID)
public class RandomWorldName {
    public static final String MODID = "randomworldname";
    public static final Logger LOGGER = LogManager.getLogger();

    public RandomWorldName(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        MinecraftForge.EVENT_BUS.addListener(this::injectButton);
    }

    private void injectButton(ScreenEvent.Init.Post evt) {
        LOGGER.info("Screen Init: " + evt.getScreen().getClass().toString());
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
				        if (child instanceof GridLayout worldNameLayout) {
					        worldNameLayout.addChild(generateName,2,0);
				        }
			        });
			        gameTab.layout.arrangeElements();
		        }
	        });
	        cws.tabNavigationBar.selectTab(1,false);
	        cws.tabNavigationBar.selectTab(0,false);
	        generateName.onPress();
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
