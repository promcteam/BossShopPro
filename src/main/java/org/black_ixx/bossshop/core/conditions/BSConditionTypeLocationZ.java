package org.black_ixx.bossshop.core.conditions;


import org.black_ixx.bossshop.core.BSBuy;
import org.black_ixx.bossshop.core.BSShopHolder;
import org.bukkit.entity.Player;

public class BSConditionTypeLocationZ extends BSConditionTypeNumber {

    @Override
    public double getNumber(BSBuy shopItem, BSShopHolder holder, Player p) {
        return p.getLocation().getZ();
    }

    @Override
    public boolean dependsOnPlayer() {
        return true;
    }

    @Override
    public String[] createNames() {
        return new String[]{"locationz", "z"};
    }


    @Override
    public void enableType() {
    }


}
