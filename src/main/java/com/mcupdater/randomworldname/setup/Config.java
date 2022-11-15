package com.mcupdater.randomworldname.setup;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class Config {

    public static final String CATEGORY_NAMES = "names";
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> PLACES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ADJECTIVES;

    public static final String CATEGORY_PLACEMENT = "location";
    public static ForgeConfigSpec.IntValue X;
    public static ForgeConfigSpec.IntValue Y;

    public static ForgeConfigSpec CLIENT_CONFIG;

    public static final ForgeConfigSpec.ConfigValue<String> SEPARATOR;

    public static final ForgeConfigSpec.BooleanValue ORDER;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("Name Lists").push(CATEGORY_NAMES);
        PLACES = CLIENT_BUILDER.comment("List of place names").defineList("places", Arrays.asList(
                "Fortress",
                "Citadel",
                "Forest",
                "Island",
                "Planet",
                "Temple",
                "Orchard",
                "Tower",
                "Dungeon",
                "Castle",
                "Nation",
                "Kingdom",
                "Mountain",
                "Cave",
                "Pillar",
                "Graveyard",
                "Gardens",
                "Library",
                "Mausoleum",
                "Chapel",
                "Cathedral",
                "Altar",
                "Vault",
                "Tomb",
                "Prison",
                "Oubliette",
                "Spire",
                "Ziggurat"
        ), (Object o) -> true);
        ADJECTIVES = CLIENT_BUILDER.comment("List of adjectives").defineList("adjectives", Arrays.asList(
                "Solitude",
                "Sorrow",
                "Life",
                "Death",
                "Wealth",
                "Greed",
                "Lust",
                "Wrath",
                "Pride",
                "Sloth",
                "Envy",
                "Gluttony",
                "Avarice",
                "Chastity",
                "Temperance",
                "Charity",
                "Diligence",
                "Patience",
                "Kindness",
                "Humility",
                "Righteousness",
                "Piety",
                "Sacrilege",
                "Fire",
                "Ice",
                "Power",
                "Rapture",
                "Wanderlust",
                "Light",
                "Darkness",
                "Hope",
                "Despair",
                "Desolation",
                "Abundance",
                "Fortitude",
                "Energy",
                "Evil",
                "Doom",
                "Stone",
                "Iron",
                "Gold",
                "Diamond"
        ), (Object o) -> true);
        SEPARATOR = CLIENT_BUILDER.comment("Separator between places and adjectives").define("separator", " of ");
        ORDER = CLIENT_BUILDER.comment("<Place> before <Adjective>").define("order",true);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Button Position").push(CATEGORY_PLACEMENT);
        X = CLIENT_BUILDER.comment("X Coordinate for the button").defineInRange("x",110,0,Integer.MAX_VALUE);
        Y = CLIENT_BUILDER.comment("Y Coordinate for the button").defineInRange("y",60,0,Integer.MAX_VALUE);
        CLIENT_BUILDER.pop();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
