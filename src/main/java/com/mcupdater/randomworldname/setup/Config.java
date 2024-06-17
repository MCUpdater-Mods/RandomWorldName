package com.mcupdater.randomworldname.setup;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Arrays;
import java.util.List;

public class Config {

    public static final String CATEGORY_NAMES = "names";
    public static ModConfigSpec.ConfigValue<List<? extends String>> PLACES;
    public static ModConfigSpec.ConfigValue<List<? extends String>> ADJECTIVES;

    public static ModConfigSpec CLIENT_CONFIG;

    public static final ModConfigSpec.ConfigValue<String> SEPARATOR;

    public static final ModConfigSpec.BooleanValue ORDER;

    static {
        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

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

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
