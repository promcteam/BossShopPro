package org.black_ixx.bossshop.core.rewards;

import net.milkbowl.vault.permission.Permission;
import org.black_ixx.bossshop.core.BSBuy;
import org.black_ixx.bossshop.managers.ClassManager;
import org.black_ixx.bossshop.managers.misc.InputReader;
import org.black_ixx.bossshop.managers.misc.StringManipulationLib;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class BSRewardTypePermission extends BSRewardType {


    public Object createObject(Object o, boolean forceFinalState) {
        return InputReader.readStringList(o);
    }

    public boolean validityCheck(String itemName, Object o) {
        if (o != null) {
            return true;
        }
        ClassManager.manager.getBugFinder()
                .severe("Was not able to create ShopItem " + itemName
                        + "! The reward object needs to be a list of permissions (text lines).");
        return false;
    }

    public void enableType() {
        ClassManager.manager.getSettings().setPermissionsEnabled(true);
        ClassManager.manager.getSettings().setVaultEnabled(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean canBuy(Player p, BSBuy buy, boolean messageIfNoSuccess, Object reward, ClickType clickType) {
        for (String s : (List<String>) reward) {
            if (!p.hasPermission(s)) {
                return true; // Player is missing one of the permissions? Can buy!
            }
        }
        if (messageIfNoSuccess) {
            ClassManager.manager.getMessageHandler().sendMessage("Main.AlreadyBought", p);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void giveReward(Player p, BSBuy buy, Object reward, ClickType clickType) {
        List<String> permissions = (List<String>) reward;

        Permission per = ClassManager.manager.getVaultHandler().getPermission();
        for (String s : permissions) {
            per.playerAdd(p, s);
        }
    }

    @Override
    public String getDisplayReward(Player p, BSBuy buy, Object reward, ClickType clickType) {
        @SuppressWarnings("unchecked")
        List<String> permissions = (List<String>) reward;
        String permissionsFormatted = StringManipulationLib.formatList(permissions);
        return ClassManager.manager.getMessageHandler()
                .get("Display.Permission")
                .replace("%permissions%", permissionsFormatted);
    }

    @Override
    public String[] createNames() {
        return new String[]{"permission", "permissions"};
    }

    @Override
    public boolean mightNeedShopUpdate() {
        return true;
    }

}
