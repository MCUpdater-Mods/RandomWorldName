package org.mcupdater.randomworldname;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ConfigGuiScreen extends GuiConfig {
    public ConfigGuiScreen(final GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), RandomWorldName.metadata.modId, false, false, "Random World Name Config");
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configElements = new ArrayList<>();
        configElements.addAll(new ConfigElement(RandomWorldName.config.getCategory("names")).getChildElements());
        return configElements;
    }
}