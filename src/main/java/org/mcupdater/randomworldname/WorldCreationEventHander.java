package org.mcupdater.randomworldname;

import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldCreationEventHander {
    @SubscribeEvent
    public void modifyWorldCreation(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiCreateWorld) {
            GuiCreateWorld createWorld = (GuiCreateWorld)event.getGui();
            event.setGui(new CreateNewRandomWorld(createWorld.parentScreen));
        }
    }
}
