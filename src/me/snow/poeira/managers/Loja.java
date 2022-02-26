package me.snow.poeira.managers;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Loja {

    private ItemStack icon;
    private List<String> lore;
    private String name;
    private int slot;
    private double priceMoney;
    private String command;

    public Loja(ItemStack icon, List<String> lore, String name, int slot, double priceMoney, String command) {
        this.icon = icon;
        this.lore = lore;
        this.name = name;
        this.slot = slot;
        this.priceMoney = priceMoney;
        this.command = command;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public String getCommand() {
        return command;
    }

    public void setIcon(String command) {
        this.command = command;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public double getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(double priceMoney) {
        this.priceMoney = priceMoney;
    }
}
