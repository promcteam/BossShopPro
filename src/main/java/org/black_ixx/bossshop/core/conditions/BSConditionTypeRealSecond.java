package org.black_ixx.bossshop.core.conditions;

import org.black_ixx.bossshop.core.BSBuy;
import org.black_ixx.bossshop.core.BSShopHolder;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class BSConditionTypeRealSecond extends BSConditionTypeNumber {

    @Override
    public double getNumber(BSBuy shopItem, BSShopHolder holder, Player p) {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    @Override
    public boolean dependsOnPlayer() {
        return false;
    }

    @Override
    public String[] createNames() {
        return new String[]{"realsecond", "second"};
    }


    @Override
    public void enableType() {
    }


}
