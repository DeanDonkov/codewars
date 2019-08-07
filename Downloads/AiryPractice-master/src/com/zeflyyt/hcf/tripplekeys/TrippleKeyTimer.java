package com.zeflyyt.hcf.tripplekeys;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.zeflyyt.hcf.Base;
import com.zeflyyt.hcf.timer.GlobalTimer;
import com.zeflyyt.hcf.timer.event.TimerExpireEvent;

import net.md_5.bungee.api.ChatColor;

public class TrippleKeyTimer extends GlobalTimer implements Listener {

	private final Base plugin;
	
	public TrippleKeyTimer(Base plugin) {
		super("TripleKey",  TimeUnit.SECONDS.toMillis(1L));
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onExpire(TimerExpireEvent event) {
		if (event.getTimer() == this) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "broadcast &7The store-wide &4&lTripleKey Sale! &7has ended.");
		}
	}
	
	@Override
	public String getScoreboardPrefix() {
		return ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString();
	}
	
}
