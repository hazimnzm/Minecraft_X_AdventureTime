/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.creatureencyclo;

/**
 *
 * @author aliyaishak
 */
import java.util.Arrays;
import java.util.List;

public class CreatureInitializer {
    public static void addPreExistingCreatures(Encyclopedia encyclopedia) {
        List<String> drops;
        List<String> strengths;
        List<String> weaknesses;

        drops = Arrays.asList("Rotten Flesh", "Bone");
        strengths = Arrays.asList("Strength in numbers");
        weaknesses = Arrays.asList("Sunlight", "Fire");
        Creature zombie = new Creature("Zombie", "Hostile", "Overworld", drops, strengths, weaknesses, 20);
        encyclopedia.addCreature(zombie);

        drops = Arrays.asList("String", "Spider Eye");
        strengths = Arrays.asList("Climbing walls", "Jumping");
        weaknesses = Arrays.asList("Sunlight", "Lava");
        Creature spider = new Creature("Spider", "Hostile", "Overworld", drops, strengths, weaknesses, 16);
        encyclopedia.addCreature(spider);

        drops = Arrays.asList("Gunpowder");
        strengths = Arrays.asList("Explosive power");
        weaknesses = Arrays.asList("Arrows", "Water");
        Creature creeper = new Creature("Creeper", "Hostile", "Overworld", drops, strengths, weaknesses, 20);
        encyclopedia.addCreature(creeper);

        drops = Arrays.asList("Ender Pearl");
        strengths = Arrays.asList("Teleportation", "High attack damage");
        weaknesses = Arrays.asList("Water", "Sunlight");
        Creature enderman = new Creature("Enderman", "Neutral", "Overworld, The End", drops, strengths, weaknesses, 40);
        encyclopedia.addCreature(enderman);

        drops = Arrays.asList("Blaze Rod");
        strengths = Arrays.asList("Fireballs", "Flying");
        weaknesses = Arrays.asList("Water", "Snowballs");
        Creature blaze = new Creature("Blaze", "Hostile", "Nether Fortress", drops, strengths, weaknesses, 20);
        encyclopedia.addCreature(blaze);

        
        drops = Arrays.asList("Gold Nugget", "Gold Ingot");
        strengths = Arrays.asList("Quick", "Aggressive");
        weaknesses = Arrays.asList("None");
        Creature piglin = new Creature("Piglin", "Neutral", "Nether", drops, strengths, weaknesses, 16);
        encyclopedia.addCreature(piglin);

        drops = Arrays.asList("Shulker Shell");
        strengths = Arrays.asList("Levitation Bullets", "High Defense");
        weaknesses = Arrays.asList("Projectiles");
        Creature shulker = new Creature("Shulker", "Hostile", "End Cities", drops, strengths, weaknesses, 30);
        encyclopedia.addCreature(shulker);

        drops = Arrays.asList("Prismarine Shard", "Prismarine Crystals");
        strengths = Arrays.asList("High Speed", "Ranged Attack");
        weaknesses = Arrays.asList("Tridents", "Dry Land");
        Creature guardian = new Creature("Guardian", "Hostile", "Ocean Monuments", drops, strengths, weaknesses, 30);
        encyclopedia.addCreature(guardian);

        drops = Arrays.asList("Phantom Membrane");
        strengths = Arrays.asList("Flying", "Surprise Attacks");
        weaknesses = Arrays.asList("Sunlight", "Bows");
        Creature phantom = new Creature("Phantom", "Hostile", "Overworld Night Sky", drops, strengths, weaknesses, 20);
        encyclopedia.addCreature(phantom);

        drops = Arrays.asList("Bone", "Arrow");
        strengths = Arrays.asList("Ranged Attacks");
        weaknesses = Arrays.asList("Sunlight", "Melee");
        Creature skeleton = new Creature("Skeleton", "Hostile", "Overworld", drops, strengths, weaknesses, 20);
        encyclopedia.addCreature(skeleton);

        drops = Arrays.asList("String", "Spider Eye", "Fermented Spider Eye");
        strengths = Arrays.asList("Climbing walls", "Jumping");
        weaknesses = Arrays.asList("Lava", "Sunlight");
        Creature caveSpider = new Creature("Cave Spider", "Hostile", "Mineshafts", drops, strengths, weaknesses, 12);
        encyclopedia.addCreature(caveSpider);

        drops = Arrays.asList("Wither Skeleton Skull", "Coal", "Bone");
        strengths = Arrays.asList("High Damage", "Wither Effect");
        weaknesses = Arrays.asList("Sunlight", "Ranged Attacks");
        Creature witherSkeleton = new Creature("Wither Skeleton", "Hostile", "Nether Fortresses", drops, strengths, weaknesses, 20);
        encyclopedia.addCreature(witherSkeleton);

        drops = Arrays.asList("Iron Ingot", "Poppy");
        strengths = Arrays.asList("High Health", "Strong Melee");
        weaknesses = Arrays.asList("None");
        Creature ironGolem = new Creature("Iron Golem", "Neutral", "Villages", drops, strengths, weaknesses, 100);
        encyclopedia.addCreature(ironGolem);

        drops = Arrays.asList("Nether Star");
        strengths = Arrays.asList("Explosive Skulls", "High Health");
        weaknesses = Arrays.asList("Melee Weapons");
        Creature wither = new Creature("Wither", "Boss", "Nether", drops, strengths, weaknesses, 300);
        encyclopedia.addCreature(wither);

        drops = Arrays.asList("Dragon Egg", "Experience");
        strengths = Arrays.asList("Flying", "Fire Breath");
        weaknesses = Arrays.asList("End Crystals");
        Creature enderDragon = new Creature("Ender Dragon", "Boss", "The End", drops, strengths, weaknesses, 200);
        encyclopedia.addCreature(enderDragon);

        drops = Arrays.asList("Emerald", "Experience");
        strengths = Arrays.asList("Trading");
        weaknesses = Arrays.asList("Weak Health");
        Creature villager = new Creature("Villager", "Passive", "Villages", drops, strengths, weaknesses, 20);
        encyclopedia.addCreature(villager);

        drops = Arrays.asList("Ink Sac");
        strengths = Arrays.asList("Swimming", "Camouflage");
        weaknesses = Arrays.asList("Land");
        Creature squid = new Creature("Squid", "Passive", "Oceans", drops, strengths, weaknesses, 10);
        encyclopedia.addCreature(squid);

        drops = Arrays.asList("Milk");
        strengths = Arrays.asList("Passive");
        weaknesses = Arrays.asList("None");
        Creature cow = new Creature("Cow", "Passive", "Overworld", drops, strengths, weaknesses, 10);
        encyclopedia.addCreature(cow);

        drops = Arrays.asList("Feather", "Chicken Meat");
        strengths = Arrays.asList("Passive");
        weaknesses = Arrays.asList("None");
        Creature chicken = new Creature("Chicken", "Passive", "Overworld", drops, strengths, weaknesses, 4);
        encyclopedia.addCreature(chicken);

        drops = Arrays.asList("Porkchop");
        strengths = Arrays.asList("Passive");
        weaknesses = Arrays.asList("None");
        Creature pig = new Creature("Pig", "Passive", "Overworld", drops, strengths, weaknesses, 10);
        encyclopedia.addCreature(pig);

        System.out.println("Pre-existing creatures added to the encyclopedia.");
    }
}
