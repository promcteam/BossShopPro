package org.black_ixx.bossshop.managers.item;

import org.black_ixx.bossshop.core.BSBuy;
import org.black_ixx.bossshop.managers.ClassManager;
import org.black_ixx.bossshop.managers.misc.InputReader;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemDataPartDurability extends ItemDataPart {

    @Override
    public ItemStack transform(ItemStack item, String usedName, String argument) {
        int damage = InputReader.getInt(argument, -1);

        if (damage == -1) {
            ClassManager.manager.getBugFinder()
                    .severe("Mistake in Config: '" + argument + "' is not a valid '" + usedName + "'. " +
                            "It needs to be an integer number like '0', '5' or '200'. ");
            return item;
        }

        if (!(item.getItemMeta() instanceof Damageable)) {
            ClassManager.manager.getBugFinder()
                    .severe("Mistake in Config: Unable to add damage/durability to items of type '" + item.getType()
                            + "'.");
            return item;
        }
        Damageable d = (Damageable) item.getItemMeta();
        d.setDamage(damage);

        item.setItemMeta((ItemMeta) d);
		
		/*// TEMPORARY FIX USE SILKSPAWNERS INSTEAD!
		if(item.getType() == Material.MONSTER_EGG){
			SpawnEgg egg = new SpawnEgg(EntityType.fromId(durability));
			ItemStack eggItem = egg.toItemStack(item.getAmount());
			ClassManager.manager.getItemStackTranslator().copyTexts(eggItem, item); //Copying all data because ItemStack 'i' might contain information that would get lost otherwise
			return eggItem;
		}
		//END OF TEMPORARY FIX*/
        return item;
    }

    @Override
    public int getPriority() {
        return PRIORITY_EARLY;
    }

    @Override
    public boolean removeSpaces() {
        return true;
    }

    @Override
    public String[] createNames() {
        return new String[]{"damage", "durability", "subid"};
    }


    @Override
    public List<String> read(ItemStack i, List<String> output) {
        if (i.hasItemMeta()) {
            if (i.getItemMeta() instanceof Damageable) {
                Damageable d = (Damageable) i.getItemMeta();
                output.add("durability:" + d.getDamage());
            }
        }
        return output;
    }


    @Override
    public boolean isSimilar(ItemStack shopItem, ItemStack playerItem, BSBuy buy, Player p) {
        if (shopItem.getItemMeta() instanceof Damageable != playerItem.getItemMeta() instanceof Damageable) {
            return false;
        }
        if (shopItem.getItemMeta() instanceof Damageable) {
            Damageable a = (Damageable) shopItem.getItemMeta();
            Damageable b = (Damageable) playerItem.getItemMeta();
            if (a.getDamage() != b.getDamage()) {
                return false;
            }
        }
        return true;
    }

}
