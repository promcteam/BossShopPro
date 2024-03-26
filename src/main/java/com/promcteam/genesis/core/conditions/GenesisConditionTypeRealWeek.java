package com.promcteam.genesis.core.conditions;

import com.promcteam.genesis.core.GenesisBuy;
import com.promcteam.genesis.core.GenesisShopHolder;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class GenesisConditionTypeRealWeek extends GenesisConditionTypeNumber {

    @Override
    public double getNumber(GenesisBuy shopItem, GenesisShopHolder holder, Player p) {
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    @Override
    public boolean dependsOnPlayer() {
        return false;
    }

    @Override
    public String[] createNames() {
        return new String[]{"realweek", "week"};
    }


    @Override
    public void enableType() {
    }


}