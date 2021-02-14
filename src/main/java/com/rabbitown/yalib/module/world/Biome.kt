package com.rabbitown.yalib.module.world

import org.bukkit.NamespacedKey
import org.bukkit.NamespacedKey.minecraft

/**
 * @author Yoooooory
 */
class Biome(val key: NamespacedKey, val type: Type, val temperature: Double) {

    enum class Type {
        SNOWY, COLD, TEMPERATE, DRY, OCEAN, NEUTRAL, UNUSED, THE_NETHER, THE_END
    }

    @Suppress("unused")
    companion object {

        //region Vanilla biomes defines
        @JvmField val SNOWY_TUNDRA = Biome(minecraft("snowy_tundra"), Type.SNOWY, 0.0)
        @JvmField val ICE_SPIKES = Biome(minecraft("ice_spikes"), Type.SNOWY, 0.0)
        @JvmField val SNOWY_TAIGA = Biome(minecraft("snowy_taiga"), Type.SNOWY, -0.5)
        @JvmField val SNOWY_TAIGA_HILLS = Biome(minecraft("snowy_taiga_hills"), Type.SNOWY, -0.5)
        @JvmField val SNOWY_TAIGA_MOUNTAINS = Biome(minecraft("snowy_taiga_mountains"), Type.SNOWY, -0.5)
        @JvmField val FROZEN_RIVER = Biome(minecraft("frozen_river"), Type.SNOWY, 0.0)
        @JvmField val SNOWY_BEACH = Biome(minecraft("snowy_beach"), Type.SNOWY, 0.05)
        @JvmField val SNOWY_MOUNTAINS = Biome(minecraft("snowy_mountains"), Type.SNOWY, 0.0)

        @JvmField val MOUNTAINS = Biome(minecraft("mountains"), Type.COLD, 0.2)
        @JvmField val GRAVELLY_MOUNTAINS = Biome(minecraft("gravelly_mountains"), Type.COLD, 0.2)
        @JvmField val WOODED_MOUNTAINS = Biome(minecraft("wooded_mountains"), Type.COLD, 0.2)
        @JvmField val MODIFIED_GRAVELLY_MOUNTAINS = Biome(minecraft("modified_gravelly_mountains"), Type.COLD, 0.2)
        @JvmField val TAIGA = Biome(minecraft("taiga"), Type.COLD, 0.25)
        @JvmField val TAIGA_HILLS = Biome(minecraft("taiga_hills"), Type.COLD, 0.25)
        @JvmField val TAIGA_MOUNTAINS = Biome(minecraft("taiga_mountains"), Type.COLD, 0.25)
        @JvmField val GIANT_TREE_TAIGA = Biome(minecraft("giant_tree_taiga"), Type.COLD, 0.3)
        @JvmField val GIANT_TREE_TAIGA_HILLS = Biome(minecraft("giant_tree_taiga_hills"), Type.COLD, 0.3)
        @JvmField val GIANT_SPRUCE_TAIGA = Biome(minecraft("giant_spruce_taiga"), Type.COLD, 0.25)
        @JvmField val GIANT_SPRUCE_TAIGA_HILLS = Biome(minecraft("giant_spruce_taiga_hills"), Type.COLD, 0.25)
        @JvmField val STONE_SHORE = Biome(minecraft("stone_shore"), Type.COLD, 0.2)

        @JvmField val PLAINS = Biome(minecraft("plains"), Type.TEMPERATE, 0.8)
        @JvmField val SUNFLOWER_PLAINS = Biome(minecraft("sunflower_plains"), Type.TEMPERATE, 0.8)
        @JvmField val FOREST = Biome(minecraft("forest"), Type.TEMPERATE, 0.7)
        @JvmField val FLOWER_FOREST = Biome(minecraft("flower_forest"), Type.TEMPERATE, 0.7)
        @JvmField val BIRCH_FOREST = Biome(minecraft("birch_forest"), Type.TEMPERATE, 0.6)
        @JvmField val BIRCH_FOREST_HILLS = Biome(minecraft("birch_forest_hills"), Type.TEMPERATE, 0.6)
        @JvmField val TALL_BIRCH_HILLS = Biome(minecraft("tall_birch_hills"), Type.TEMPERATE, 0.6)
        @JvmField val TALL_BIRCH_FOREST = Biome(minecraft("tall_birch_forest"), Type.TEMPERATE, 0.7)
        @JvmField val DARK_FOREST = Biome(minecraft("dark_forest"), Type.TEMPERATE, 0.7)
        @JvmField val DARK_FOREST_HILLS = Biome(minecraft("dark_forest_hills"), Type.TEMPERATE, 0.7)
        @JvmField val SWAMP = Biome(minecraft("swamp"), Type.TEMPERATE, 0.8)
        @JvmField val SWAMP_HILLS = Biome(minecraft("swamp_hills"), Type.TEMPERATE, 0.8)
        @JvmField val JUNGLE = Biome(minecraft("jungle"), Type.TEMPERATE, 0.95)
        @JvmField val JUNGLE_HILLS = Biome(minecraft("jungle_hills"), Type.TEMPERATE, 0.95)
        @JvmField val MODIFIED_JUNGLE = Biome(minecraft("modified_jungle"), Type.TEMPERATE, 0.95)
        @JvmField val JUNGLE_EDGE = Biome(minecraft("jungle_edge"), Type.TEMPERATE, 0.95)
        @JvmField val MODIFIED_JUNGLE_EDGE = Biome(minecraft("modified_jungle_edge"), Type.TEMPERATE, 0.95)
        @JvmField val BAMBOO_JUNGLE = Biome(minecraft("bamboo_jungle"), Type.TEMPERATE, 0.95)
        @JvmField val BAMBOO_JUNGLE_HILLS = Biome(minecraft("bamboo_jungle_hills"), Type.TEMPERATE, 0.95)
        @JvmField val RIVER = Biome(minecraft("river"), Type.TEMPERATE, 0.5)
        @JvmField val BEACH = Biome(minecraft("beach"), Type.TEMPERATE, 0.8)
        @JvmField val MUSHROOM_FIELDS = Biome(minecraft("mushroom_fields"), Type.TEMPERATE, 0.9)
        @JvmField val MUSHROOM_FIELD_SHORE = Biome(minecraft("mushroom_field_shore"), Type.TEMPERATE, 0.9)
        @JvmField val WOODED_HILLS = Biome(minecraft("wooded_hills"), Type.TEMPERATE, 0.7)

        @JvmField val DESERT = Biome(minecraft("desert"), Type.DRY, 2.0)
        @JvmField val DESERT_HILLS = Biome(minecraft("desert_hills"), Type.DRY, 2.0)
        @JvmField val DESERT_LAKES = Biome(minecraft("desert_lakes"), Type.DRY, 2.0)
        @JvmField val SAVANNA = Biome(minecraft("savanna"), Type.DRY, 1.2)
        @JvmField val SHATTERED_SAVANNA = Biome(minecraft("shattered_savanna"), Type.DRY, 1.1)
        @JvmField val SHATTERED_SAVANNA_PLATEAU = Biome(minecraft("shattered_savanna_plateau"), Type.DRY, 1.1)
        @JvmField val BADLANDS = Biome(minecraft("badlands"), Type.DRY, 2.0)
        @JvmField val ERODED_BADLANDS = Biome(minecraft("eroded_badlands"), Type.DRY, 2.0)
        @JvmField val WOODED_BADLANDS_PLATEAU = Biome(minecraft("wooded_badlands_plateau"), Type.DRY, 2.0)
        @JvmField val MODIFIED_WOODED_BADLANDS_PLATEAU = Biome(minecraft("modified_wooded_badlands_plateau"), Type.DRY, 2.0)
        @JvmField val BADLANDS_PLATEAU = Biome(minecraft("badlands_plateau"), Type.DRY, 2.0)
        @JvmField val SAVANNA_PLATEAU = Biome(minecraft("savanna_plateau"), Type.DRY, 1.0)
        @JvmField val MODIFIED_BADLANDS_PLATEAU = Biome(minecraft("modified_badlands_plateau"), Type.DRY, 2.0)
        @JvmField val MODIFIED_SAVANNA_PLATEAU = Biome(minecraft("modified_savanna_plateau"), Type.DRY, 1.0)

        @JvmField val WARM_OCEAN = Biome(minecraft("warm_ocean"), Type.OCEAN, 0.5)
        @JvmField val LUKEWARM_OCEAN = Biome(minecraft("lukewarm_ocean"), Type.OCEAN, 0.5)
        @JvmField val DEEP_LUKEWARM_OCEAN = Biome(minecraft("deep_lukewarm_ocean"), Type.OCEAN, 0.5)
        @JvmField val OCEAN = Biome(minecraft("ocean"), Type.OCEAN, 0.5)
        @JvmField val DEEP_OCEAN = Biome(minecraft("deep_ocean"), Type.OCEAN, 0.5)
        @JvmField val COLD_OCEAN = Biome(minecraft("cold_ocean"), Type.OCEAN, 0.5)
        @JvmField val DEEP_COLD_OCEAN = Biome(minecraft("deep_cold_ocean"), Type.OCEAN, 0.5)
        @JvmField val FROZEN_OCEAN = Biome(minecraft("frozen_ocean"), Type.OCEAN, 0.0)
        @JvmField val DEEP_FROZEN_OCEAN = Biome(minecraft("deep_frozen_ocean"), Type.OCEAN, 0.5)

        @JvmField val THE_VOID = Biome(minecraft("the_void"), Type.NEUTRAL, 0.5)

        @JvmField val NETHER_WASTES = Biome(minecraft("nether_wastes"), Type.THE_NETHER, 2.0)
        @JvmField val SOUL_SAND_VALLEY = Biome(minecraft("soul_sand_valley"), Type.THE_NETHER, 2.0)
        @JvmField val CRIMSON_FOREST = Biome(minecraft("crimson_forest"), Type.THE_NETHER, 2.0)
        @JvmField val WARPED_FOREST = Biome(minecraft("warped_forest"), Type.THE_NETHER, 2.0)
        @JvmField val BASALT_DELTAS = Biome(minecraft("basalt_deltas"), Type.THE_NETHER, 2.0)

        @JvmField val THE_END = Biome(minecraft("the_end"), Type.THE_END, 0.5)
        @JvmField val SMALL_END_ISLANDS = Biome(minecraft("small_end_islands"), Type.THE_END, 0.5)
        @JvmField val END_MIDLANDS = Biome(minecraft("end_midlands"), Type.THE_END, 0.5)
        @JvmField val END_HIGHLANDS = Biome(minecraft("end_highlands"), Type.THE_END, 0.5)
        @JvmField val END_BARRENS = Biome(minecraft("end_barrens"), Type.THE_END, 0.5)

        @JvmField val MOUNTAIN_EDGE = Biome(minecraft("mountain_edge"), Type.UNUSED, 0.2)
        @JvmField val DEEP_WARM_OCEAN = Biome(minecraft("deep_warm_ocean"), Type.UNUSED, 0.5)
        //endregion

        @JvmStatic
        fun fromEnum(enum: org.bukkit.block.Biome) = Biome::class.java.getField(enum.name).get(null) as Biome

        fun org.bukkit.block.Biome.moreInfo() = fromEnum(this)

    }

}