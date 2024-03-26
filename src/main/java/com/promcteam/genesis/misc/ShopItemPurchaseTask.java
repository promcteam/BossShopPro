package com.promcteam.genesis.misc;

import com.promcteam.genesis.core.GenesisBuy;
import com.promcteam.genesis.core.GenesisShop;
import com.promcteam.genesis.core.GenesisShopHolder;
import com.promcteam.genesis.core.prices.GenesisPriceType;
import com.promcteam.genesis.core.rewards.GenesisRewardType;
import com.promcteam.genesis.managers.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class ShopItemPurchaseTask implements Runnable {


    private final UUID                uuid;
    private final GenesisBuy          buy;
    private final GenesisShop         shop;
    private final GenesisShopHolder   holder;
    private final ClickType           clickType;
    private final GenesisRewardType   rewardtype;
    private final GenesisPriceType    priceType;
    private final InventoryClickEvent event;

    public ShopItemPurchaseTask(Player p,
                                GenesisBuy buy,
                                GenesisShop shop,
                                GenesisShopHolder holder,
                                ClickType clickType,
                                GenesisRewardType rewardtype,
                                GenesisPriceType priceType,
                                InventoryClickEvent event) {
        this.uuid = p.getUniqueId();
        this.buy = buy;
        this.shop = shop;
        this.holder = holder;
        this.clickType = clickType;
        this.rewardtype = rewardtype;
        this.priceType = priceType;
        this.event = event;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        Player p = Bukkit.getPlayer(uuid);

        if (p != null) {
            buy.purchase(p,
                    shop,
                    holder,
                    clickType,
                    rewardtype,
                    priceType,
                    event,
                    ClassManager.manager.getPlugin(),
                    true);
        }
    }

}