package com.bekvon.bukkit.residence.allNms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftThrownPotion;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import com.bekvon.bukkit.residence.NMS;
import com.bekvon.bukkit.residence.Residence;
import net.minecraft.server.v1_9_R1.NBTTagCompound;

public class v1_9 implements NMS {
    @Override
    public List<Block> getPistonRetractBlocks(BlockPistonRetractEvent event) {
	List<Block> blocks = new ArrayList<Block>();
	blocks.addAll(event.getBlocks());
	return blocks;
    }

    @Override
    public boolean isAnimal(Entity ent) {
	return (ent instanceof Horse || ent instanceof Bat || ent instanceof Snowman || ent instanceof IronGolem || ent instanceof Ocelot || ent instanceof Pig
	    || ent instanceof Sheep || ent instanceof Chicken || ent instanceof Wolf || ent instanceof Cow || ent instanceof Squid || ent instanceof Villager
	    || ent instanceof Rabbit);
    }

    @Override
    public boolean isArmorStandEntity(EntityType ent) {
	return ent == EntityType.ARMOR_STAND;
    }

    @Override
    public boolean isArmorStandMaterial(Material material) {
	return material == Material.ARMOR_STAND;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isCanUseEntity_BothClick(Material mat, Block block) {
	switch (mat) {
	case LEVER:
	case STONE_BUTTON:
	case WOOD_BUTTON:
	case WOODEN_DOOR:
	case SPRUCE_DOOR:
	case BIRCH_DOOR:
	case JUNGLE_DOOR:
	case ACACIA_DOOR:
	case DARK_OAK_DOOR:
	case SPRUCE_FENCE_GATE:
	case BIRCH_FENCE_GATE:
	case JUNGLE_FENCE_GATE:
	case ACACIA_FENCE_GATE:
	case DARK_OAK_FENCE_GATE:
	case TRAP_DOOR:
	case IRON_TRAPDOOR:
	case FENCE_GATE:
	case PISTON_BASE:
	case PISTON_STICKY_BASE:
	case DRAGON_EGG:
	    return true;
	default:
	    return Residence.getConfigManager().getCustomBothClick().contains(Integer.valueOf(block.getTypeId()));
	}
    }

    @Override
    public boolean isEmptyBlock(Block block) {
	switch (block.getType()) {
	case AIR:
	case WEB:
	case STRING:
	case WALL_BANNER:
	case WALL_SIGN:
	case SAPLING:
	case VINE:
	case TRIPWIRE_HOOK:
	case TRIPWIRE:
	case STONE_BUTTON:
	case WOOD_BUTTON:
	case PAINTING:
	case ITEM_FRAME:
	    return true;
	default:
	    break;
	}
	return false;
    }

    @Override
    public boolean isSpectator(GameMode mode) {
	return mode == GameMode.SPECTATOR;
    }

    @Override
    public void addDefaultFlags(Map<Material, String> matUseFlagList) {
	/* 1.8 Doors */
	matUseFlagList.put(Material.SPRUCE_DOOR, "door");
	matUseFlagList.put(Material.BIRCH_DOOR, "door");
	matUseFlagList.put(Material.JUNGLE_DOOR, "door");
	matUseFlagList.put(Material.ACACIA_DOOR, "door");
	matUseFlagList.put(Material.DARK_OAK_DOOR, "door");
	/* 1.8 Fence Gates */
	matUseFlagList.put(Material.SPRUCE_FENCE_GATE, "door");
	matUseFlagList.put(Material.BIRCH_FENCE_GATE, "door");
	matUseFlagList.put(Material.JUNGLE_FENCE_GATE, "door");
	matUseFlagList.put(Material.ACACIA_FENCE_GATE, "door");
	matUseFlagList.put(Material.DARK_OAK_FENCE_GATE, "door");
	matUseFlagList.put(Material.IRON_TRAPDOOR, "door");
    }

    public boolean isPlate(Material mat) {
	return mat == Material.GOLD_PLATE || mat == Material.IRON_PLATE;
    }

    @Override
    public boolean isMainHand(PlayerInteractEvent event) {
	return event.getHand() == EquipmentSlot.HAND ? true : false;
    }
    
    @Override
    public ItemStack itemInMainHand(Player player) {
	return player.getInventory().getItemInMainHand();
    }
    
    @Override
    public Block getTargetBlock(Player player, int range) {
	return player.getTargetBlock((Set<Material>) null, range);
    }

    @Override
    public String getPotionType(ItemStack potion) {
	net.minecraft.server.v1_9_R1.ItemStack stack = CraftItemStack.asNMSCopy(potion);
	NBTTagCompound tagCompound = stack.getTag();
	if (tagCompound == null)
	    return null;
	if (tagCompound.getString("Potion").isEmpty())
	    return null;
	return tagCompound.getString("Potion").replace("minecraft:", "");
    }

    @Override
    public ItemStack getLingeringPotionItem(Entity ent) {

	if (!(ent instanceof CraftThrownPotion))
	    return null;

	CraftThrownPotion potion = (CraftThrownPotion) ent;
	
	return potion.getItem();
    }
}