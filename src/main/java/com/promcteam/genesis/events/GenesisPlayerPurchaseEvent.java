package com.promcteam.genesis.events;

import com.promcteam.genesis.core.GenesisBuy;
import com.promcteam.genesis.core.GenesisShop;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;

public class GenesisPlayerPurchaseEvent extends GenesisEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Player      player;
    private final GenesisBuy  buy;
    private final GenesisShop shop;
    private final ClickType   clickType;

    private boolean cancelled = false;

    public GenesisPlayerPurchaseEvent(Player player, GenesisShop shop, GenesisBuy buy, ClickType clickType) {
        this.player = player;
        this.buy = buy;
        this.shop = shop;
        this.clickType = clickType;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public GenesisBuy getShopItem() {
        return buy;
    }

    public GenesisShop getShop() {
        return shop;
    }

    public ClickType getClickType() {
        return clickType;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}