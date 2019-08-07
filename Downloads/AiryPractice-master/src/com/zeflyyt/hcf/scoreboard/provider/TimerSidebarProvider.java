package com.zeflyyt.hcf.scoreboard.provider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.zeflyyt.hcf.Base;
import com.zeflyyt.hcf.ConfigurationService;
import com.zeflyyt.hcf.DateTimeFormats;
import com.zeflyyt.hcf.classes.PvpClass;
import com.zeflyyt.hcf.classes.archer.ArcherClass;
import com.zeflyyt.hcf.classes.bard.BardClass;
import com.zeflyyt.hcf.classes.type.MinerClass;
import com.zeflyyt.hcf.classes.type.RogueClass;
import com.zeflyyt.hcf.commands.StaffModeCommand;
import com.zeflyyt.hcf.event.EventTimer;
import com.zeflyyt.hcf.event.eotw.EOTWHandler;
import com.zeflyyt.hcf.event.faction.ConquestFaction;
import com.zeflyyt.hcf.event.faction.EventFaction;
import com.zeflyyt.hcf.event.tracker.ConquestTracker;
import com.zeflyyt.hcf.faction.type.PlayerFaction;
import com.zeflyyt.hcf.listener.StaffChatListener;
import com.zeflyyt.hcf.listener.VanishListener;
import com.zeflyyt.hcf.reboot.RebootTimer;
import com.zeflyyt.hcf.scoreboard.SidebarEntry;
import com.zeflyyt.hcf.scoreboard.SidebarProvider;
import com.zeflyyt.hcf.sotw.SotwCommand;
import com.zeflyyt.hcf.sotw.SotwTimer;
import com.zeflyyt.hcf.timer.GlobalTimer;
import com.zeflyyt.hcf.timer.PlayerTimer;
import com.zeflyyt.hcf.timer.Timer;
import com.zeflyyt.hcf.timer.type.TeleportTimer;
import com.zeflyyt.hcf.util.BukkitUtils;
import com.zeflyyt.hcf.util.DurationFormatter;

public class TimerSidebarProvider implements SidebarProvider {

	protected static String STRAIGHT_LINE = BukkitUtils.STRAIGHT_LINE_DEFAULT.substring(0, 14);
	protected static final String NEW_LINE = ChatColor.STRIKETHROUGH + "----------";

	private Base plugin;

	public TimerSidebarProvider(Base plugin) {
		this.plugin = plugin;
	}

	private static String handleBardFormat(long millis, boolean trailingZero) {
		return ((DecimalFormat) (trailingZero ? DateTimeFormats.REMAINING_SECONDS_TRAILING
				: DateTimeFormats.REMAINING_SECONDS).get()).format(millis * 0.001D);
	}

	public SidebarEntry add(String s) {

		if (s.length() < 10) {
			return new SidebarEntry(s);
		}

		if (s.length() > 10 && s.length() < 20) {
			return new SidebarEntry(s.substring(0, 10), s.substring(10, s.length()), "");
		}

		if (s.length() > 20) {
			return new SidebarEntry(s.substring(0, 10), s.substring(10, 20), s.substring(20, s.length()));
		}

		return null;
	}

	@Override
	public String getTitle() {
		return ConfigurationService.SCOREBOARD_TITLE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public List<SidebarEntry> getLines(Player player) {

		List<SidebarEntry> lines = new ArrayList<SidebarEntry>();
		EOTWHandler.EotwRunnable eotwRunnable = this.plugin.getEotwHandler().getRunnable();
		PvpClass pvpClass = this.plugin.getPvpClassManager().getEquippedClass(player);
		EventTimer eventTimer = this.plugin.getTimerManager().eventTimer;
		List<SidebarEntry> conquestLines = null;
		Collection<Timer> timers = this.plugin.getTimerManager().getTimers();
		EventFaction eventFaction = eventTimer.getEventFaction();


		if ((ConfigurationService.KIT_MAP) == true) {
			
			 lines.add(this.add(Base.getPlugin().getConfig().getString("Scoreboard.Kills.Name").replace("%kills%", String.valueOf(player.getStatistic(Statistic.PLAYER_KILLS))).replaceAll("&", "§")));
	         lines.add(this.add(Base.getPlugin().getConfig().getString("Scoreboard.Deaths.Name").replace("%deaths%", String.valueOf(player.getStatistic(Statistic.DEATHS))).replaceAll("&", "§")));
	        }
		
		if ((StaffModeCommand.getInstance().isMod(player))) {

			//for (String onlinedonator : Base.config.getStringList("Online-Donator-Broadcast")) {
				//getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', onlinedonator).replace("%player%", players)); }
			
			//for (String staffmode : Base.config.getStringList("Scoreboard.StaffMode")) {
			//	lines.add(new SidebarEntry(ChatColor.translateAlternateColorCodes('&', staffmode).replace("%vanish%", VanishListener.getInstance().isVanished(player) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled".replace("%staffchat%", StaffChatListener.getInstance().isInStaffChat(player) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled".replace("%online%", String.valueOf(Bukkit.getServer().getOnlinePlayers().length)))))); }
			
		    lines.add(this.add(ChatColor.translateAlternateColorCodes('&', Base.getPlugin().getConfig().getString("Scoreboard.Staffboard.Name"))));
	        lines.add(this.add(ChatColor.translateAlternateColorCodes('&', Base.getPlugin().getConfig().getString("Scoreboard.Vanish.Name").replaceAll("%vanish%", VanishListener.getInstance().isVanished(player) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"))));
	        lines.add(this.add(ChatColor.translateAlternateColorCodes('&', Base.getPlugin().getConfig().getString("Scoreboard.StaffChat.Name").replaceAll("%staffchat%", StaffChatListener.getInstance().isInStaffChat(player) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"))));
		    lines.add(this.add(ChatColor.translateAlternateColorCodes('&', Base.getPlugin().getConfig().getString("Scoreboard.OnlinePlayers.Name").replaceAll("%onlineplayers%", String.valueOf(Bukkit.getServer().getOnlinePlayers().length)))));
		    

			//lines.add(new SidebarEntry(ChatColor.GRAY.toString() + "" + ChatColor.GOLD.toString() + ChatColor.GOLD, "Staff Mode" + ": ", VanishListener.getInstance().isVanished(player) ? ChatColor.GREEN + "" : ChatColor.RED + ""));
			//lines.add(new SidebarEntry(ChatColor.YELLOW.toString() + "  §e»" + ChatColor.WHITE.toString(), " Vanish§7: " + "", VanishListener.getInstance().isVanished(player) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
			//lines.add(new SidebarEntry(ChatColor.YELLOW.toString() + "  §e»" + ChatColor.WHITE.toString(), " Staff Chat§7: " + "", StaffChatListener.getInstance().isInStaffChat(player) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"));
			}
		{
		}
	
		if ((pvpClass != null) && ((pvpClass instanceof BardClass))) {
			BardClass bardClass = (BardClass) pvpClass;
			lines.add(new SidebarEntry(ChatColor.AQUA + ChatColor.BOLD.toString() + "Bard ",
					ChatColor.AQUA + ChatColor.BOLD.toString() + "Energy", ChatColor.GRAY + ": " + ChatColor.RED
							+ handleBardFormat(bardClass.getEnergyMillis(player), true)));
			long remaining2 = bardClass.getRemainingBuffDelay(player);
			if (remaining2 > 0L) {
				lines.add(new SidebarEntry(ChatColor.GREEN + ChatColor.BOLD.toString() + "Bard ",
						ChatColor.GREEN + ChatColor.BOLD.toString() + "Effect",
						ChatColor.GRAY + ": " + ChatColor.RED + Base.getRemaining(remaining2, true)));
			}
		}
		final SotwTimer.SotwRunnable sotwRunnable = this.plugin.getSotwTimer().getSotwRunnable();
		if (sotwRunnable != null) {
				if(SotwCommand.enabled.contains(player.getUniqueId())) {
					lines.add(new SidebarEntry(String.valueOf(ChatColor.GRAY.toString()), "§a§lSOTW", ChatColor.GRAY + ": " + String.valueOf(((ConfigurationService.TIMER_COLOR))) + ChatColor.STRIKETHROUGH + Base.getRemaining(sotwRunnable.getRemaining(), true)));

				} else {
					lines.add(new SidebarEntry(String.valueOf(ChatColor.GRAY.toString()), "§a§lSOTW", ChatColor.GRAY + ": " + String.valueOf(((ConfigurationService.TIMER_COLOR))) + Base.getRemaining(sotwRunnable.getRemaining(), true)));
					}
		}


/*		if ((pvpClass instanceof MinerClass)) {
			lines.add(new SidebarEntry(ChatColor.GREEN.toString(), "Active Class",
					ChatColor.GRAY + ": " + ChatColor.RED + "Miner"));
		}

		if ((pvpClass instanceof ArcherClass)) {
			lines.add(new SidebarEntry(ChatColor.GREEN.toString(), "Active Class",
					ChatColor.GRAY + ": " + ChatColor.RED + "Archer"));
		}

		if ((pvpClass instanceof BardClass)) {
			lines.add(new SidebarEntry(ChatColor.GREEN.toString(), "Active Class",
					ChatColor.GRAY + ": " + ChatColor.RED + "Bard"));
		}

		if ((pvpClass instanceof RogueClass)) {
			lines.add(new SidebarEntry(ChatColor.GREEN.toString(), "Active Class",
					ChatColor.GRAY + ": " + ChatColor.RED + "Rogue"));
		}*/

		for (Timer timer : timers) {
			if (((timer instanceof PlayerTimer)) && (!(timer instanceof TeleportTimer))) {
				PlayerTimer playerTimer = (PlayerTimer) timer;
				long remaining3 = playerTimer.getRemaining(player);
				if (remaining3 > 0L) {
					String timerName1 = playerTimer.getName();
					if (timerName1.length() > 14) {
						timerName1 = timerName1.substring(0, timerName1.length());
					}
					lines.add(new SidebarEntry(playerTimer.getScoreboardPrefix(), timerName1 + ChatColor.GRAY,
							": " + String.valueOf(((ConfigurationService.TIMER_COLOR))) + Base.getRemaining(remaining3, true)));
				}
			} else if ((timer instanceof GlobalTimer)) {
				GlobalTimer playerTimer2 = (GlobalTimer) timer;
				long remaining3 = playerTimer2.getRemaining();
				if (remaining3 > 0L) {
					String timerName = playerTimer2.getName();
					if (timerName.length() > 14) {
						timerName = timerName.substring(0, timerName.length());
					}
					if (!timerName.equalsIgnoreCase("Conquest")) {
						lines.add(new SidebarEntry(playerTimer2.getScoreboardPrefix(), timerName + ChatColor.GRAY,
								": " + String.valueOf(((ConfigurationService.TIMER_COLOR))) + Base.getRemaining(remaining3, true)));
					}
				}
			}
		}

		if (eotwRunnable != null) {
			long remaining4 = eotwRunnable.getTimeUntilStarting();
			if (remaining4 > 0L) {
				lines.add(new SidebarEntry(ChatColor.DARK_RED.toString() + ChatColor.BOLD, "EOTW" + ChatColor.GRAY + "",
						"" + ChatColor.GRAY + ": " + ChatColor.RED + Base.getRemaining(remaining4, true)));
			} else if ((remaining4 = eotwRunnable.getTimeUntilCappable()) > 0L) {
				lines.add(new SidebarEntry(ChatColor.DARK_RED.toString() + ChatColor.BOLD, "EOTW" + ChatColor.GRAY + "",
						"" + ChatColor.GRAY + ": " + ChatColor.RED + Base.getRemaining(remaining4, true)));
			}
		}

		if ((eventFaction instanceof ConquestFaction)) {
			ConquestFaction conquestFaction = (ConquestFaction) eventFaction;
			CONQUEST_FORMATTER.get();
			conquestLines = new ArrayList<SidebarEntry>();
			ConquestTracker conquestTracker = (ConquestTracker) conquestFaction.getEventType().getEventTracker();
			int count = 0;
			for (Iterator<?> localIterator = conquestTracker.getFactionPointsMap().entrySet().iterator(); localIterator
					.hasNext(); count = 3) {
				Map.Entry<PlayerFaction, Integer> entry = (Map.Entry) localIterator.next();
				String factionName = ((PlayerFaction) entry.getKey()).getDisplayName(player);
				if (factionName.length() > 14) {
					factionName = factionName.substring(0, 14);
				}
				lines.add(new SidebarEntry(ChatColor.WHITE + " * " + ChatColor.RED, factionName,
						ChatColor.GRAY + ": " + ChatColor.RED + entry.getValue()));
				if (++count == 3) {
					break;
				}
			}
		}

		if ((conquestLines != null) && (!conquestLines.isEmpty())) {
			if (player.hasPermission("command.mod")) {
				conquestLines.add(new SidebarEntry("§7§m------", "------", "--------"));
			}
			conquestLines.addAll(lines);
			lines = conquestLines;
		}
		if (!lines.isEmpty()) {
			lines.add(0, new SidebarEntry(ChatColor.GRAY, ChatColor.STRIKETHROUGH + "-----------", "---------"));
			if ((ConfigurationService.FOOTER) == true) {
			lines.add(new SidebarEntry(""));
			lines.add(new SidebarEntry(ChatColor.translateAlternateColorCodes('&', ConfigurationService.IP)));
			}
			lines.add(lines.size(), new SidebarEntry(ChatColor.GRAY, NEW_LINE, "----------"));
		}
		return lines;

	}

	public static final ThreadLocal<DecimalFormat> CONQUEST_FORMATTER = new ThreadLocal<DecimalFormat>() {
		protected DecimalFormat initialValue() {
			return new DecimalFormat("00.0");
		}
	};
}
