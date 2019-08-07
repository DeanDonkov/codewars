package com.zeflyyt.hcf.event.argument;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.zeflyyt.hcf.Base;
import com.zeflyyt.hcf.event.EventTimer;
import com.zeflyyt.hcf.event.EventType;
import com.zeflyyt.hcf.event.tracker.KothTracker;
import com.zeflyyt.hcf.faction.type.Faction;
import com.zeflyyt.hcf.util.command.CommandArgument;

public class EventCancelArgument
  extends CommandArgument
{
  private final Base plugin;
  
  public EventCancelArgument(Base plugin)
  {
    super("cancel", "Cancels a running event", new String[] { "stop", "end" });
    this.plugin = plugin;
    this.permission = ("hcf.command.event.argument." + getName());
  }
  
  
  public String getUsage(String label)
  {
    return '/' + label + ' ' + getName();
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    EventTimer eventTimer = this.plugin.getTimerManager().eventTimer;
    Faction eventFaction = eventTimer.getEventFaction();
    if (!eventTimer.clearCooldown())
    {
      sender.sendMessage(ChatColor.RED + "There is not a running event.");
      return true;
    }
    return true;
  }
}
