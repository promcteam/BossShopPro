package org.black_ixx.bossshop.core.conditions;

import org.bukkit.entity.Player;

public class BSConditionTypePermission extends BSConditionTypeMatch {


    @Override
    public boolean matches(Player p, String singleCondition) {
        return p.hasPermission(singleCondition);
    }


    @Override
    public boolean dependsOnPlayer() {
        return true;
    }

    @Override
    public String[] createNames() {
        return new String[]{"permission"};
    }


    @Override
    public void enableType() {
    }


}
