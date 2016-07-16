package me.mcplayhd.spellwar;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.mcplayhd.spellwar.listeners.PlayerListener;
import me.mcplayhd.spellwar.managers.AbilityManager;
import me.mcplayhd.spellwar.managers.InventoryManager;
import me.mcplayhd.spellwar.managers.WorldManager;

public class SpellWar extends JavaPlugin {
	
	public final String prefix = "�2SpellWar �7| ";
	
	public PlayerListener pl;
	public AbilityManager am;
	public InventoryManager im;
	
	public boolean ready = false;
	
	public void onEnable() {
		pl = new PlayerListener(this);
		am = new AbilityManager(this);
		im = new InventoryManager();
		getServer().getPluginManager().registerEvents(pl, this);
		if(new WorldManager(this).loadWorld("SpellWar")) {
			ready = true;
		} else {
			System.out.println("�cCouldn't find any map named \"SpellWar\". Stopping the server...");
			new BukkitRunnable() {
				@Override
				public void run() {
					Bukkit.getServer().shutdown();
				}
			}.runTaskLater(this, 20);
		}
	}
	
}
