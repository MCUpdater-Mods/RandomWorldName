package com.mcupdater.randomworldname;

import com.mcupdater.randomworldname.setup.Config;
import net.minecraft.client.gui.screen.CreateWorldScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
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

    private void injectButton(GuiScreenEvent.InitGuiEvent evt) {
        if (evt.getGui() instanceof CreateWorldScreen) {
            evt.addWidget(new Button(evt.getGui().width / 2 + Config.X.get(), Config.Y.get(), 100, 20, new StringTextComponent("Generate Name"), buttonPress -> {
                String name = getRandomEntry(Config.PLACES.get()) + " of " + getRandomEntry(Config.ADJECTIVES.get());
                CreateWorldScreen screen = (CreateWorldScreen) evt.getGui();
                screen.worldNameField.setText(name);
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
