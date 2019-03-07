package org.mcupdater.randomworldname;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;
import org.mcupdater.randomworldname.compat.topography.TopographyWorldCreationEventHandler;

import java.util.Arrays;
import java.util.HashSet;

@Mod(modid = "randomworldname", useMetadata = true, clientSideOnly = true, guiFactory = "org.mcupdater.randomworldname.GuiFactory")
public class RandomWorldName {

    public static ModMetadata metadata;
    public static HashSet<String> placeNames = new HashSet<>();
    public static HashSet<String> adjectives = new HashSet<>();
    public static Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        metadata = event.getModMetadata();
        config = new Configuration(event.getSuggestedConfigurationFile());
        try {
            config.load();

            placeNames.addAll(Arrays.asList(config.get("names", "Places", new String[] {"Fortress","Citadel","Forest","Island","Planet","Temple","Orchard","Tower","Dungeon","Castle","Nation","Kingdom","Mountain","Cave","Pillar","Graveyard","Gardens","Library","Mausoleum","Chapel","Cathedral","Altar","Vault","Tomb","Prison","Oubliette", "Spire", "Ziggurat"}, "List of place names", Property.Type.STRING).getStringList()));
            adjectives.addAll(Arrays.asList(config.get("names", "Adjectives", new String[] {"Solitude","Sorrow","Life","Death","Wealth","Greed","Lust","Wrath","Pride","Sloth","Envy","Gluttony","Avarice","Chastity","Temperance","Charity","Diligence","Patience","Kindness","Humility","Righteousness","Piety","Sacrilege","Fire","Ice","Power","Rapture","Wanderlust","Light","Darkness","Hope","Despair","Desolation","Abundance","Fortitude","Energy"}, "List of adjectives", Property.Type.STRING).getStringList()));

        } catch (Exception ex) {
            event.getModLog().log(Level.ERROR, "Random World Name had a problem loading its configuration", ex);
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (Loader.isModLoaded("topography")) {
            MinecraftForge.EVENT_BUS.register(new TopographyWorldCreationEventHandler());
        } else {
            MinecraftForge.EVENT_BUS.register(new WorldCreationEventHander());
        }

    }
    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        config.save();
    }
}
