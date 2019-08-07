package com.zeflyyt.hcf.timer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.zeflyyt.hcf.Base;
import com.zeflyyt.hcf.timer.argument.TimerCheckArgument;
import com.zeflyyt.hcf.timer.argument.TimerSetArgument;
import com.zeflyyt.hcf.util.BukkitUtils;
import com.zeflyyt.hcf.util.command.ArgumentExecutor;
import com.zeflyyt.hcf.util.command.CommandArgument;

/**
 * Handles the execution and tab completion of the timer command.
 */
public class TimerExecutor extends ArgumentExecutor {

    public TimerExecutor(Base plugin) {
        super("timer");

        addArgument(new TimerCheckArgument(plugin));
        addArgument(new TimerSetArgument(plugin));
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
      if (args.length < 1)
      {
        if (sender.hasPermission("timer.admin"))
        {
          sender.sendMessage(ChatColor.DARK_GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
          sender.sendMessage(ChatColor.GOLD + "Timer Help");
          sender.sendMessage(ChatColor.YELLOW + " /timer set" + ChatColor.GOLD +" � " + ChatColor.GRAY + "Set remaining timer time.");
          sender.sendMessage(ChatColor.YELLOW + " /timer check" + ChatColor.GOLD +" � " + ChatColor.GRAY + "Check remaining timer time.");
          sender.sendMessage(ChatColor.DARK_GRAY + BukkitUtils.STRAIGHT_LINE_DEFAULT);
        }
        return true;
      }
      CommandArgument argument2 = getArgument(args[0]);
      String permission2 = argument2 == null ? null : argument2.getPermission();
      if ((argument2 == null) || ((permission2 != null) && (!sender.hasPermission(permission2))))
      {
        sender.sendMessage(ChatColor.RED + "Command not found");
        return true;
      }
      argument2.onCommand(sender, command, label, args);
      return true;
    }
}