package com.zeflyyt.hcf;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.zeflyyt.hcf.balance.EconomyCommand;
import com.zeflyyt.hcf.balance.EconomyManager;
import com.zeflyyt.hcf.balance.FlatFileEconomyManager;
import com.zeflyyt.hcf.balance.PayCommand;
import com.zeflyyt.hcf.balance.ShopSignListener;
import com.zeflyyt.hcf.classes.PvpClassManager;
import com.zeflyyt.hcf.classes.archer.ArcherClass;
import com.zeflyyt.hcf.classes.bard.BardRestorer;
import com.zeflyyt.hcf.classes.type.RogueClass;
import com.zeflyyt.hcf.combatlog.CombatLogListener;
import com.zeflyyt.hcf.combatlog.CustomEntityRegistration;
import com.zeflyyt.hcf.commands.BroadcastCommand;
import com.zeflyyt.hcf.commands.ClearCommand;
import com.zeflyyt.hcf.commands.CobbleCommand;
import com.zeflyyt.hcf.commands.CoordsCommand;
import com.zeflyyt.hcf.commands.CrowbarCommand;
import com.zeflyyt.hcf.commands.DiamondReviveCommand;
import com.zeflyyt.hcf.commands.EnchantCommand;
import com.zeflyyt.hcf.commands.EndPortalCommand;
import com.zeflyyt.hcf.commands.FFACommand;
import com.zeflyyt.hcf.commands.FeedCommand;
import com.zeflyyt.hcf.commands.FixCommand;
import com.zeflyyt.hcf.commands.FlyCommand;
import com.zeflyyt.hcf.commands.FreezeCommand;
import com.zeflyyt.hcf.commands.GMCCommand;
import com.zeflyyt.hcf.commands.GMSCommand;
import com.zeflyyt.hcf.commands.GameModeCommand;
import com.zeflyyt.hcf.commands.GiveCommand;
import com.zeflyyt.hcf.commands.GodCommand;
import com.zeflyyt.hcf.commands.GoppleCommand;
import com.zeflyyt.hcf.commands.HatCommand;
import com.zeflyyt.hcf.commands.HealCommand;
import com.zeflyyt.hcf.commands.HelpCommand;
import com.zeflyyt.hcf.commands.HelpOpCommand;
import com.zeflyyt.hcf.commands.InvSeeCommand;
import com.zeflyyt.hcf.commands.ItemCommand;
import com.zeflyyt.hcf.commands.KillCommand;
import com.zeflyyt.hcf.commands.KingReviveCommand;
import com.zeflyyt.hcf.commands.LFFCommand;
import com.zeflyyt.hcf.commands.LagCommand;
import com.zeflyyt.hcf.commands.ListCommand;
import com.zeflyyt.hcf.commands.LockdownCommand;
import com.zeflyyt.hcf.commands.LogoutCommand;
import com.zeflyyt.hcf.commands.MapKitCommand;
import com.zeflyyt.hcf.commands.MessageCommand;
import com.zeflyyt.hcf.commands.MiscCommands;
import com.zeflyyt.hcf.commands.MoreCommand;
import com.zeflyyt.hcf.commands.OreStatsCommand;
import com.zeflyyt.hcf.commands.PanicCommand;
import com.zeflyyt.hcf.commands.PingCommand;
import com.zeflyyt.hcf.commands.PlayTimeCommand;
import com.zeflyyt.hcf.commands.PlayerVaultCommand;
import com.zeflyyt.hcf.commands.PvpTimerCommand;
import com.zeflyyt.hcf.commands.RandomCommand;
import com.zeflyyt.hcf.commands.RankCommand;
import com.zeflyyt.hcf.commands.RefundCommand;
import com.zeflyyt.hcf.commands.RenameCommand;
import com.zeflyyt.hcf.commands.ReplyCommand;
import com.zeflyyt.hcf.commands.ReportCommand;
import com.zeflyyt.hcf.commands.ResetCommand;
import com.zeflyyt.hcf.commands.SetBorderCommand;
import com.zeflyyt.hcf.commands.SetCommand;
import com.zeflyyt.hcf.commands.SetKBCommand;
import com.zeflyyt.hcf.commands.SkullCommand;
import com.zeflyyt.hcf.commands.SpawnCommand;
import com.zeflyyt.hcf.commands.SpawnerCommand;
import com.zeflyyt.hcf.commands.StaffChatCommand;
import com.zeflyyt.hcf.commands.StaffModeCommand;
import com.zeflyyt.hcf.commands.StatsCommand;
import com.zeflyyt.hcf.commands.SudoCommand;
import com.zeflyyt.hcf.commands.TLCommand;
import com.zeflyyt.hcf.commands.TeamspeakCommand;
import com.zeflyyt.hcf.commands.TeleportAllCommand;
import com.zeflyyt.hcf.commands.TeleportCommand;
import com.zeflyyt.hcf.commands.TeleportHereCommand;
import com.zeflyyt.hcf.commands.ToggleMessageCommand;
import com.zeflyyt.hcf.commands.TopCommand;
import com.zeflyyt.hcf.commands.VanishCommand;
import com.zeflyyt.hcf.commands.WhoisCommand;
import com.zeflyyt.hcf.commands.WorldCommand;
import com.zeflyyt.hcf.config.PlayerData;
import com.zeflyyt.hcf.config.PotionLimiterData;
import com.zeflyyt.hcf.config.WorldData;
import com.zeflyyt.hcf.deathban.Deathban;
import com.zeflyyt.hcf.deathban.DeathbanListener;
import com.zeflyyt.hcf.deathban.DeathbanManager;
import com.zeflyyt.hcf.deathban.FlatFileDeathbanManager;
import com.zeflyyt.hcf.deathban.lives.LivesExecutor;
import com.zeflyyt.hcf.deathban.lives.StaffReviveCommand;
import com.zeflyyt.hcf.doublekeys.DoubleKeyCommand;
import com.zeflyyt.hcf.doublekeys.DoubleKeyListener;
import com.zeflyyt.hcf.event.CaptureZone;
import com.zeflyyt.hcf.event.EventExecutor;
import com.zeflyyt.hcf.event.EventScheduler;
import com.zeflyyt.hcf.event.conquest.ConquestExecutor;
import com.zeflyyt.hcf.event.eotw.EOTWHandler;
import com.zeflyyt.hcf.event.eotw.EotwCommand;
import com.zeflyyt.hcf.event.eotw.EotwListener;
import com.zeflyyt.hcf.event.faction.CapturableFaction;
import com.zeflyyt.hcf.event.faction.ConquestFaction;
import com.zeflyyt.hcf.event.faction.KothFaction;
import com.zeflyyt.hcf.event.glmountain.GlowstoneMountain;
import com.zeflyyt.hcf.event.koth.KothExecutor;
import com.zeflyyt.hcf.faction.FactionExecutor;
import com.zeflyyt.hcf.faction.FactionManager;
import com.zeflyyt.hcf.faction.FactionMember;
import com.zeflyyt.hcf.faction.FlatFileFactionManager;
import com.zeflyyt.hcf.faction.claim.Claim;
import com.zeflyyt.hcf.faction.claim.ClaimHandler;
import com.zeflyyt.hcf.faction.claim.ClaimWandListener;
import com.zeflyyt.hcf.faction.claim.Subclaim;
import com.zeflyyt.hcf.faction.type.ClaimableFaction;
import com.zeflyyt.hcf.faction.type.EndPortalFaction;
import com.zeflyyt.hcf.faction.type.Faction;
import com.zeflyyt.hcf.faction.type.GlowstoneFaction;
import com.zeflyyt.hcf.faction.type.PlayerFaction;
import com.zeflyyt.hcf.faction.type.RoadFaction;
import com.zeflyyt.hcf.faction.type.SpawnFaction;
import com.zeflyyt.hcf.inventory.implementation.ClaimSettingsInventory;
import com.zeflyyt.hcf.keyall.KeyAllCommand;
import com.zeflyyt.hcf.keyall.KeyAllListener;
import com.zeflyyt.hcf.keysale.KeySaleCommand;
import com.zeflyyt.hcf.keysale.KeySaleListener;
import com.zeflyyt.hcf.listener.AutoSmeltOreListener;
import com.zeflyyt.hcf.listener.BookDeenchantListener;
import com.zeflyyt.hcf.listener.BorderListener;
import com.zeflyyt.hcf.listener.BottledExpListener;
import com.zeflyyt.hcf.listener.ChatListener;
import com.zeflyyt.hcf.listener.CoreListener;
import com.zeflyyt.hcf.listener.CrowbarListener;
import com.zeflyyt.hcf.listener.DeathListener;
import com.zeflyyt.hcf.listener.DeathMessageListener;
import com.zeflyyt.hcf.listener.ElevatorListener;
import com.zeflyyt.hcf.listener.EnderPearlFix;
import com.zeflyyt.hcf.listener.EntityLimitListener;
import com.zeflyyt.hcf.listener.ExpMultiplierListener;
import com.zeflyyt.hcf.listener.FactionListener;
import com.zeflyyt.hcf.listener.FactionsCoreListener;
import com.zeflyyt.hcf.listener.FoundDiamondsListener;
import com.zeflyyt.hcf.listener.FurnaceSmeltSpeederListener;
import com.zeflyyt.hcf.listener.GodListener;
import com.zeflyyt.hcf.listener.KitMapListener;
import com.zeflyyt.hcf.listener.LoginEvent;
import com.zeflyyt.hcf.listener.OreCountListener;
import com.zeflyyt.hcf.listener.PearlGlitchListener;
import com.zeflyyt.hcf.listener.PlayTimeManager;
import com.zeflyyt.hcf.listener.PotionLimitListener;
import com.zeflyyt.hcf.listener.SignSubclaimListener;
import com.zeflyyt.hcf.listener.SkullListener;
import com.zeflyyt.hcf.listener.StaffChatListener;
import com.zeflyyt.hcf.listener.StaffModeListener;
import com.zeflyyt.hcf.listener.UnRepairableListener;
import com.zeflyyt.hcf.listener.VanishListener;
import com.zeflyyt.hcf.listener.WorldListener;
import com.zeflyyt.hcf.listener.fixes.ArmorFixListener;
import com.zeflyyt.hcf.listener.fixes.BeaconStrengthFixListener;
import com.zeflyyt.hcf.listener.fixes.BlockHitFixListener;
import com.zeflyyt.hcf.listener.fixes.BlockJumpGlitchFixListener;
import com.zeflyyt.hcf.listener.fixes.BoatGlitchFixListener;
import com.zeflyyt.hcf.listener.fixes.BookQuillFixListener;
import com.zeflyyt.hcf.listener.fixes.CommandBlocker;
import com.zeflyyt.hcf.listener.fixes.DupeGlitchFix;
import com.zeflyyt.hcf.listener.fixes.EnchantLimitListener;
import com.zeflyyt.hcf.listener.fixes.EnderChestRemovalListener;
import com.zeflyyt.hcf.listener.fixes.HungerFixListener;
import com.zeflyyt.hcf.listener.fixes.InfinityArrowFixListener;
import com.zeflyyt.hcf.listener.fixes.NaturalMobSpawnFixListener;
import com.zeflyyt.hcf.listener.fixes.PexCrashFixListener;
import com.zeflyyt.hcf.listener.fixes.PortalListener;
import com.zeflyyt.hcf.listener.fixes.StrengthListener;
import com.zeflyyt.hcf.listener.fixes.SyntaxBlocker;
import com.zeflyyt.hcf.listener.fixes.VoidGlitchFixListener;
import com.zeflyyt.hcf.listener.fixes.WeatherFixListener;
import com.zeflyyt.hcf.reboot.RebootCommand;
import com.zeflyyt.hcf.reboot.RebootListener;
import com.zeflyyt.hcf.reclaim.ReclaimManager;
import com.zeflyyt.hcf.sale.SaleCommand;
import com.zeflyyt.hcf.sale.SaleListener;
import com.zeflyyt.hcf.scoreboard.ScoreboardHandler;
import com.zeflyyt.hcf.signs.EventSignListener;
import com.zeflyyt.hcf.signs.KitSignListener;
import com.zeflyyt.hcf.sotw.SotwCommand;
import com.zeflyyt.hcf.sotw.SotwListener;
import com.zeflyyt.hcf.sotw.SotwTimer;
import com.zeflyyt.hcf.stattracker.OreTrackerListener;
import com.zeflyyt.hcf.stattracker.StatTrackListener;
import com.zeflyyt.hcf.tablist.TablistManager;
import com.zeflyyt.hcf.tablist.tablist.TablistAdapter;
import com.zeflyyt.hcf.timer.TimerExecutor;
import com.zeflyyt.hcf.timer.TimerManager;
import com.zeflyyt.hcf.tripplekeys.TrippleKeyCommand;
import com.zeflyyt.hcf.tripplekeys.TrippleKeyListener;
import com.zeflyyt.hcf.user.ConsoleUser;
import com.zeflyyt.hcf.user.FactionUser;
import com.zeflyyt.hcf.user.UserManager;
import com.zeflyyt.hcf.util.SignHandler;
import com.zeflyyt.hcf.util.itemdb.ItemDb;
import com.zeflyyt.hcf.util.itemdb.SimpleItemDb;
import com.zeflyyt.hcf.visualise.ProtocolLibHook;
import com.zeflyyt.hcf.visualise.VisualiseHandler;
import com.zeflyyt.hcf.visualise.WallBorderListener;
import com.zeflyyt.hcf.weekendsale.WeekendSaleCommand;
import com.zeflyyt.hcf.weekendsale.WeekendSaleListener;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.chat.Chat;
import net.minecraft.util.com.google.common.base.Joiner;

public class Base extends JavaPlugin implements CommandExecutor {

	private CombatLogListener combatLogListener;
	
	public CombatLogListener getCombatLogListener() {
		return this.combatLogListener;
	}
		
	@Getter
	private Chat chat;
	
	@Getter
	private ClaimSettingsInventory claimSettings;
	
	public void onEnable() {
		plugin = this;

        

		
		BasePlugins.getPlugin().init(this);
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		conf = new File(getDataFolder(), "config.yml");
		WorldData.getInstance().setup(this);
		PlayerData.getInstance().setup(this);
		PotionLimiterData.getInstance().setup(this);
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.GREEN.toString() + ChatColor.ITALIC + "aHCF is now enabled.");
		Bukkit.getConsoleSender()
				.sendMessage("");
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------");
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.GREEN.toString() + "Made by Emirate & ZeflyYT");
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.GRAY.toString() + " If there is any bugs, or any issues please dm Emirate#9246 or Zefly#4954");
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------");
		ProtocolLibHook.hook(this);
		CustomEntityRegistration.registerCustomEntities();
		Plugin wep = Bukkit.getPluginManager().getPlugin("WorldEdit");
		this.craftBukkitVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		this.worldEdit = (((wep instanceof WorldEditPlugin)) && (wep.isEnabled()) ? (WorldEditPlugin) wep : null);
		
		registerConfiguration();
		if(!new AdvancedLicense(Base.config.getString("HWID"), "https://ahcf.000webhostapp.com/verify.php", this).register()) return;
		registerCommands();
		registerManagers();
		registerListeners();
		this.claimSettings = new ClaimSettingsInventory(this);
		if ((ConfigurationService.TAB_LIST) == true) {
			new TablistManager(this, new TablistAdapter(this), TimeUnit.SECONDS.toMillis((long) 0.5));
	}
		Cooldowns.createCooldown("helpop_cooldown");
		Cooldowns.createCooldown("report_cooldown");
		Cooldowns.createCooldown("king_cooldown");
		Cooldowns.createCooldown("diamond_cooldown");
		Cooldowns.createCooldown("emerald_cooldown");
		Cooldowns.createCooldown("ruby_cooldown");
		Cooldowns.createCooldown("gold_cooldown");
		Cooldowns.createCooldown("archer_speed_cooldown");
		Cooldowns.createCooldown("archer_jump_cooldown");
		Cooldowns.createCooldown("rogue_speed_cooldown");
		Cooldowns.createCooldown("rogue_jump_cooldown");
		Cooldowns.createCooldown("rogue_cooldown");
		Cooldowns.createCooldown("lff_cooldown");
		
		new BukkitRunnable() {
			public void run() {
				Base.this.saveData();
				getServer().broadcastMessage(ChatColor.AQUA + "Saving data...");
			}
		}.runTaskTimerAsynchronously(this, TimeUnit.SECONDS.toMillis(20L), TimeUnit.SECONDS.toMillis(20L));
		
	    new BukkitRunnable() {

	        @Override
	        public void run() {
	          @SuppressWarnings("deprecation")
			String players = Arrays.stream(Bukkit.getOnlinePlayers())
	              .filter(player -> player.hasPermission("top.rank") && !player.isOp() && !player.hasPermission("*"))
					.map(Player::getName)
					.collect(Collectors.joining(", "));

	          
				for (String onlinedonator : Base.config.getStringList("Online-Donator-Broadcast")) {
					getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', onlinedonator).replace("%player%", players)); }
	          
	         // getServer().broadcastMessage(ChatColor.YELLOW + "");              
	         // getServer().broadcastMessage(ConfigurationService.PRIMAIRY_COLOR + ConfigurationService.TOP_RANK + " Users " + ChatColor.GRAY + "» " + ChatColor.WHITE + players);
	         // getServer().broadcastMessage(ChatColor.DARK_AQUA + "Purchase the " + ConfigurationService.TOP_RANK + " rank at " + ConfigurationService.DONATE_URL);  
	         // getServer().broadcastMessage(ChatColor.WHITE + "");        }
	        }
	      }.runTaskTimer(this, 60L, 20 * 60 * 5);
	    }
	
	
	    

	private void saveData() {
		this.combatLogListener.removeCombatLoggers();
		this.deathbanManager.saveDeathbanData();
		this.economyManager.saveEconomyData();
		this.factionManager.saveFactionData();
		this.playTimeManager.savePlaytimeData();
		this.userManager.saveUserData();
		this.signHandler.cancelTasks(null);

		PlayerData.getInstance().saveConfig();
		
	}

	public void onDisable() {
		this.pvpClassManager.onDisable();
		this.scoreboardHandler.clearBoards();
		this.deathbanManager.saveDeathbanData();
		this.economyManager.saveEconomyData();
		this.factionManager.saveFactionData();
		this.playTimeManager.savePlaytimeData();
		this.userManager.saveUserData();
		StaffModeCommand.onDisableMod();
		saveData();
		plugin = null;
	}

	private void registerConfiguration() {
		ConfigurationSerialization.registerClass(CaptureZone.class);
		ConfigurationSerialization.registerClass(Deathban.class);
		ConfigurationSerialization.registerClass(Claim.class);
		ConfigurationSerialization.registerClass(ConsoleUser.class);
		ConfigurationSerialization.registerClass(Subclaim.class);
		ConfigurationSerialization.registerClass(FactionUser.class);
		ConfigurationSerialization.registerClass(ClaimableFaction.class);
		ConfigurationSerialization.registerClass(ConquestFaction.class);
		ConfigurationSerialization.registerClass(CapturableFaction.class);
		ConfigurationSerialization.registerClass(KothFaction.class);
		ConfigurationSerialization.registerClass(GlowstoneFaction.class);
		ConfigurationSerialization.registerClass(EndPortalFaction.class);
		ConfigurationSerialization.registerClass(Faction.class);
		ConfigurationSerialization.registerClass(FactionMember.class);
		ConfigurationSerialization.registerClass(PlayerFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.class);
		ConfigurationSerialization.registerClass(SpawnFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.NorthRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.EastRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.SouthRoadFaction.class);
		ConfigurationSerialization.registerClass(RoadFaction.WestRoadFaction.class);
	}

	private void registerListeners() {
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new OreStatsCommand(), this);
		manager.registerEvents(new GodListener(), this);
		manager.registerEvents(new VanishListener(), this);
		manager.registerEvents(new ArcherClass(this), this);
		manager.registerEvents(new RogueClass(this), this);
		manager.registerEvents(new PotionLimitListener(this), this);
		manager.registerEvents(new LoginEvent(), this);
		manager.registerEvents(new DupeGlitchFix(), this);
		manager.registerEvents(new PortalListener(this), this);
		manager.registerEvents(new WeatherFixListener(), this);
		manager.registerEvents(this.combatLogListener = new CombatLogListener(this), this);
		manager.registerEvents(new NaturalMobSpawnFixListener(), this);
		manager.registerEvents(new AutoSmeltOreListener(), this);
		manager.registerEvents(new BlockHitFixListener(), this);
		manager.registerEvents(new BlockJumpGlitchFixListener(), this);
		manager.registerEvents(new CommandBlocker(), this);
		manager.registerEvents(new BoatGlitchFixListener(), this);
		manager.registerEvents(new BookDeenchantListener(), this);
		manager.registerEvents(new PexCrashFixListener(this), this);
		manager.registerEvents(new BookQuillFixListener(this), this);
		manager.registerEvents(new BorderListener(), this);
		manager.registerEvents(new ChatListener(this), this);
		manager.registerEvents(new ClaimWandListener(this), this);
		manager.registerEvents(new BottledExpListener(), this);
		manager.registerEvents(new CoreListener(this), this);
		manager.registerEvents(new CrowbarListener(this), this);
		manager.registerEvents(new DeathListener(this), this);
		manager.registerEvents(new ElevatorListener(this), this);
		manager.registerEvents(new DeathMessageListener(this), this);
		if (ConfigurationService.KIT_MAP == false) {
		
	 	 manager.registerEvents(new DeathbanListener(this), this);

		}
		manager.registerEvents(new EnchantLimitListener(), this);
		manager.registerEvents(new EnderChestRemovalListener(), this);
		manager.registerEvents(new FlatFileFactionManager(this), this);
		manager.registerEvents(new StrengthListener(), this);
		manager.registerEvents(new ArmorFixListener(), this);
		manager.registerEvents(new EotwListener(this), this);
		manager.registerEvents(new EventSignListener(), this);
		manager.registerEvents(new ExpMultiplierListener(), this);
		manager.registerEvents(new FactionListener(this), this);
		manager.registerEvents(new FoundDiamondsListener(this), this);
		manager.registerEvents(new FurnaceSmeltSpeederListener(), this);
		manager.registerEvents(new KitMapListener(this), this);
		manager.registerEvents(new InfinityArrowFixListener(), this);
		manager.registerEvents(new HungerFixListener(), this);
		manager.registerEvents(new PearlGlitchListener(this), this);
		manager.registerEvents(new FactionsCoreListener(this), this);
		manager.registerEvents(new PearlGlitchListener(this), this);
		manager.registerEvents(new EnderPearlFix(this), this);
		manager.registerEvents(new SignSubclaimListener(this), this);
		manager.registerEvents(new EndPortalCommand(getPlugin()), this);
		manager.registerEvents(new ShopSignListener(this), this);
		manager.registerEvents(new SkullListener(), this);
		manager.registerEvents(new BeaconStrengthFixListener(this), this);
		manager.registerEvents(new VoidGlitchFixListener(), this);
		manager.registerEvents(new WallBorderListener(this), this);
		manager.registerEvents(this.playTimeManager, this);
		manager.registerEvents(new WorldListener(this), this);
		manager.registerEvents(new UnRepairableListener(), this);
		manager.registerEvents(new StaffModeListener(), this);
		manager.registerEvents(new SyntaxBlocker(), this);
		manager.registerEvents(new OreTrackerListener(), this);
		manager.registerEvents(new OreCountListener(), this);
		manager.registerEvents(new SotwListener(this), this);
		manager.registerEvents(new KitSignListener(), this);
		manager.registerEvents(new RebootListener(), this);
		manager.registerEvents(new SaleListener(), this);
		manager.registerEvents(new KeySaleListener(), this);
		manager.registerEvents(new WeekendSaleListener(), this);
		manager.registerEvents(new KeyAllListener(), this);
		manager.registerEvents(new TrippleKeyListener(), this);
		manager.registerEvents(new DoubleKeyListener(), this);
		manager.registerEvents(new EntityLimitListener(this), this);
		manager.registerEvents(new StatTrackListener(), this);
		manager.registerEvents(new StaffChatListener(), this);
	}

	private void registerCommands() {

		getCommand("top").setExecutor(new TopCommand());
		getCommand("list").setExecutor(new ListCommand());
		getCommand("setborder").setExecutor(new SetBorderCommand());
		getCommand("helpop").setExecutor(new HelpOpCommand());
		getCommand("report").setExecutor(new ReportCommand());
		getCommand("hat").setExecutor(new HatCommand());
		getCommand("world").setExecutor(new WorldCommand());
		getCommand("endportal").setExecutor(new EndPortalCommand(getPlugin()));
		getCommand("fix").setExecutor(new FixCommand());
		getCommand("setkb").setExecutor(new SetKBCommand());
		getCommand("enchant").setExecutor(new EnchantCommand());
		getCommand("freeze").setExecutor(new FreezeCommand(this));
		getCommand("staffrevive").setExecutor(new StaffReviveCommand(this));
		getCommand("lag").setExecutor(new LagCommand());
		getCommand("broadcast").setExecutor(new BroadcastCommand());
		getCommand("togglemessage").setExecutor(new ToggleMessageCommand());
		getCommand("king").setExecutor(new KingReviveCommand(this));
		getCommand("reply").setExecutor(new ReplyCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("message").setExecutor(new MessageCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("pv").setExecutor(new PlayerVaultCommand(this));
		getCommand("setspawn").setExecutor(new SpawnCommand());
		getCommand("ping").setExecutor(new PingCommand());
		getCommand("togglemessage").setExecutor(new ToggleMessageCommand());
		getCommand("teleportall").setExecutor(new TeleportAllCommand());
		getCommand("teleporthere").setExecutor(new TeleportHereCommand());
		getCommand("give").setExecutor(new GiveCommand());
		getCommand("gamemode").setExecutor(new GameModeCommand());
		getCommand("item").setExecutor(new ItemCommand());
		getCommand("lockdown").setExecutor(new LockdownCommand(this));
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("invsee").setExecutor(new InvSeeCommand(this));
		getCommand("god").setExecutor(new GodCommand());
		getCommand("gms").setExecutor(new GMSCommand());
		getCommand("gmc").setExecutor(new GMCCommand());
		getCommand("vanish").setExecutor(new VanishCommand());
		getCommand("sotw").setExecutor(new SotwCommand(this));
		getCommand("random").setExecutor(new RandomCommand(this));
		getCommand("conquest").setExecutor(new ConquestExecutor(this));
		getCommand("crowbar").setExecutor(new CrowbarCommand());
		getCommand("economy").setExecutor(new EconomyCommand(this));
		getCommand("eotw").setExecutor(new EotwCommand(this));
		getCommand("event").setExecutor(new EventExecutor(this));
		getCommand("faction").setExecutor(new FactionExecutor(this));
		getCommand("playtime").setExecutor(new PlayTimeCommand(this));
		getCommand("gopple").setExecutor(new GoppleCommand(this));
		getCommand("cobble").setExecutor(new CobbleCommand());
		getCommand("koth").setExecutor(new KothExecutor(this));
		getCommand("lives").setExecutor(new LivesExecutor(this));
		getCommand("logout").setExecutor(new LogoutCommand(this));
		getCommand("more").setExecutor(new MoreCommand());
		getCommand("panic").setExecutor(new PanicCommand());
		getCommand("staffchat").setExecutor(new StaffChatCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("pay").setExecutor(new PayCommand(this));
		getCommand("pvptimer").setExecutor(new PvpTimerCommand(this));
		getCommand("LFF").setExecutor(new LFFCommand(this));
		getCommand("refund").setExecutor(new RefundCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("FFA").setExecutor(new FFACommand());
		getCommand("timer").setExecutor(new TimerExecutor(this));
		getCommand("kill").setExecutor(new KillCommand());
		getCommand("ores").setExecutor(new OreStatsCommand());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("rename").setExecutor(new RenameCommand());
		getCommand("teamspeak").setExecutor(new TeamspeakCommand());
		getCommand("coords").setExecutor(new CoordsCommand());
		getCommand("fsay").setExecutor(new MiscCommands());
		getCommand("mapkit").setExecutor(new MapKitCommand(this));
		getCommand("diamond").setExecutor(new DiamondReviveCommand(this));
		getCommand("emerald").setExecutor(new DiamondReviveCommand(this));
		getCommand("gold").setExecutor(new DiamondReviveCommand(this));
		getCommand("ruby").setExecutor(new DiamondReviveCommand(this));
		getCommand("staffmode").setExecutor(new StaffModeCommand());
		getCommand("spawner").setExecutor(new SpawnerCommand());
		getCommand("set").setExecutor(new SetCommand(this));
		getCommand("ci").setExecutor(new ClearCommand());
		getCommand("drop").setExecutor(new MiscCommands());
		getCommand("copyinv").setExecutor(new MiscCommands());
		getCommand("teleport").setExecutor(new TeleportCommand());
		getCommand("skull").setExecutor(new SkullCommand());
		getCommand("reset").setExecutor(new ResetCommand());
		getCommand("sudo").setExecutor(new SudoCommand());
		getCommand("whois").setExecutor(new WhoisCommand(this));
		getCommand("tl").setExecutor(new TLCommand());
		getCommand("reboot").setExecutor(new RebootCommand(plugin));
		getCommand("sale").setExecutor(new SaleCommand(plugin));
		getCommand("keysale").setExecutor(new KeySaleCommand(plugin));
		getCommand("wsale").setExecutor(new WeekendSaleCommand(plugin));
		getCommand("tkey").setExecutor(new TrippleKeyCommand(plugin));
		getCommand("dkey").setExecutor(new DoubleKeyCommand(plugin));
		getCommand("keyall").setExecutor(new KeyAllCommand(plugin));
		getCommand("stats").setExecutor(new StatsCommand());
		getCommand("glowstone").setExecutor(new GlowstoneMountain(this));

		Map<String, Map<String, Object>> map = getDescription().getCommands();
		for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
			PluginCommand command = getCommand((String) entry.getKey());
			command.setPermission("command." + (String) entry.getKey());
			command.setPermissionMessage(ChatColor.RED.toString() + "You do not have permission to use this command.");
		}
	}

	private void registerManagers() {
		this.claimHandler = new ClaimHandler(this);
		this.deathbanManager = new FlatFileDeathbanManager(this);
		this.economyManager = new FlatFileEconomyManager(this);
		this.eotwHandler = new EOTWHandler(this);
		this.eventScheduler = new EventScheduler(this);
		this.factionManager = new FlatFileFactionManager(this);
		this.itemDb = new SimpleItemDb(this);
		this.playTimeManager = new PlayTimeManager(this);
		this.pvpClassManager = new PvpClassManager(this);
		this.timerManager = new TimerManager(this);
		this.scoreboardHandler = new ScoreboardHandler(this);
		this.userManager = new UserManager(this);
		this.visualiseHandler = new VisualiseHandler();
		this.sotwTimer = new SotwTimer();
		this.message = new Message(this);
		this.signHandler = new SignHandler(this);
		this.reclaimManager = new ReclaimManager(this);
		new BardRestorer(this);
	}

	public Message getMessage() {
		return this.message;
	}

	public ItemDb getItemDb() {
		return this.itemDb;
	}

	public Random getRandom() {
		return this.random;
	}

	public PlayTimeManager getPlayTimeManager() {
		return this.playTimeManager;
	}

	public WorldEditPlugin getWorldEdit() {
		return this.worldEdit;
	}

	public ClaimHandler getClaimHandler() {
		return this.claimHandler;
	}

	public SotwTimer getSotwTimer() {
		return this.sotwTimer;
	}

	public SignHandler getSignHandler() {
		return this.signHandler;
	}

	public ConfigurationService getConfiguration() {
		return this.configuration;
	}

	public DeathbanManager getDeathbanManager() {
		return this.deathbanManager;
	}

	public VanishListener getVanish() {
		return this.vanish;
	}

	public EconomyManager getEconomyManager() {
		return this.economyManager;
	}

	public EOTWHandler getEotwHandler() {
		return this.eotwHandler;
	}

	public FactionManager getFactionManager() {
		return this.factionManager;
	}

	public PvpClassManager getPvpClassManager() {
		return this.pvpClassManager;
	}

	public ScoreboardHandler getScoreboardHandler() {
		return this.scoreboardHandler;
	}

	public TimerManager getTimerManager() {
		return this.timerManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public VisualiseHandler getVisualiseHandler() {
		return this.visualiseHandler;
	}

	public Base() {
		this.random = new Random();
	}

	public ServerHandler getServerHandler() {
		return this.serverHandler;
	}

	public static Base getPlugin() {
		return plugin;
	}

	public static Base getInstance() {
		return instance;
	}

	public static String getReaming(long millis) {
		return getRemaining(millis, true, true);
	}

	public String getCraftBukkitVersion() {
		return this.craftBukkitVersion;
	}

	public static String getRemaining(long millis, boolean milliseconds) {
		return getRemaining(millis, milliseconds, true);
	}

	public static String getRemaining(long duration, boolean milliseconds, boolean trail) {
		if ((milliseconds) && (duration < MINUTE)) {
			return ((DecimalFormat) (trail ? DateTimeFormats.REMAINING_SECONDS_TRAILING
					: DateTimeFormats.REMAINING_SECONDS).get()).format(duration * 0.001D) + 's';
		}
		return DurationFormatUtils.formatDuration(duration, (duration >= HOUR ? "HH:" : "") + "mm:ss");
	}

	public static File conf;
	public static FileConfiguration config;
	private String craftBukkitVersion;
	public static Base instance;
	private ConfigurationService configuration;
	private static final long MINUTE = TimeUnit.MINUTES.toMillis(1L);
	private static final long HOUR = TimeUnit.HOURS.toMillis(1L);
	private static Base plugin;
	public static Plugin pl;
	private ServerHandler serverHandler;
	public BukkitRunnable clearEntityHandler;
	public BukkitRunnable announcementTask;
	private Message message;
	@Getter private ReclaimManager reclaimManager;

	public EventScheduler eventScheduler;
	public static final Joiner SPACE_JOINER = Joiner.on(' ');
	public static final Joiner COMMA_JOINER = Joiner.on(", ");
	private Random random;
	private PlayTimeManager playTimeManager;
	private WorldEditPlugin worldEdit;
	private ClaimHandler claimHandler;
	private ItemDb itemDb;

	private DeathbanManager deathbanManager;
	private EconomyManager economyManager;
	private EOTWHandler eotwHandler;
	private FactionManager factionManager;
	private PvpClassManager pvpClassManager;
	private VanishListener vanish;
	private ScoreboardHandler scoreboardHandler;
	private SotwTimer sotwTimer;
	private TimerManager timerManager;
	private UserManager userManager;
	private VisualiseHandler visualiseHandler;
	private SignHandler signHandler;

}
