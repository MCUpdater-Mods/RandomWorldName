package com.mcupdater.randomworldname.setup;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class Config {

    public static final String CATEGORY_NAMES = "names";
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> PLACES;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ADJECTIVES;

    public static ForgeConfigSpec CLIENT_CONFIG;

    public static final ForgeConfigSpec.ConfigValue<String> SEPARATOR;

    public static final ForgeConfigSpec.BooleanValue ORDER;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("Name Lists").push(CATEGORY_NAMES);
        PLACES = CLIENT_BUILDER.comment("List of place names").defineList("places", Arrays.asList(
		        "Altar",
		        "Asylum",
		        "Castle",
		        "Cathedral",
		        "Cave",
		        "Chapel",
		        "Citadel",
		        "City",
		        "Dungeon",
		        "Factory",
		        "Forest",
		        "Fortress",
		        "Gardens",
		        "Graveyard",
		        "Hamlet",
		        "Haven",
		        "Hill",
		        "Island",
		        "Kingdom",
		        "Library",
		        "Mausoleum",
		        "Monument",
		        "Mountain",
		        "Museum",
		        "Nation",
		        "Ocean",
		        "Orchard",
		        "Oubliette",
		        "Pillar",
		        "Planet",
		        "Prison",
		        "Ruin",
		        "Spire",
		        "Temple",
		        "Tomb",
		        "Tower",
		        "Town",
		        "Vault",
		        "Village",
		        "Volcano",
		        "Ziggurat"
        ), (Object o) -> true);
        ADJECTIVES = CLIENT_BUILDER.comment("List of adjectives").defineList("adjectives", Arrays.asList(
		        "Abundance",
		        "Avarice",
		        "Charity",
		        "Chastity",
		        "Copper",
		        "Darkness",
		        "Death",
		        "Desolation",
		        "Despair",
		        "Diamond",
		        "Diligence",
		        "Doom",
		        "Energy",
		        "Envy",
		        "Essence",
		        "Evil",
		        "Fertility",
		        "Fire",
		        "Fortitude",
		        "Glass",
		        "Gluttony",
		        "Gold",
		        "Greed",
		        "Growth",
		        "Hope",
		        "Humility",
		        "Ice",
		        "Iron",
		        "Kindness",
		        "Life",
		        "Light",
		        "Lust",
		        "Magic",
		        "Mana",
		        "Nature",
		        "Netherite",
		        "Patience",
		        "Pestilence",
		        "Piety",
		        "Power",
		        "Pride",
		        "Quartz",
		        "Rapture",
		        "Redstone",
		        "Righteousness",
		        "Sacrilege",
		        "Sloth",
		        "Solitude",
		        "Sorrow",
		        "Stone",
		        "Temperance",
		        "Wanderlust",
		        "Wealth",
		        "Wrath"
        ), (Object o) -> true);
        SEPARATOR = CLIENT_BUILDER.comment("Separator between places and adjectives").define("separator", " of ");
        ORDER = CLIENT_BUILDER.comment("<Place> before <Adjective>").define("order",true);
        CLIENT_BUILDER.pop();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
