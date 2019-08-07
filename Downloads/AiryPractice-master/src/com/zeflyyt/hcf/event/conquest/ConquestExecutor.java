package com.zeflyyt.hcf.event.conquest;

import com.zeflyyt.hcf.Base;
import com.zeflyyt.hcf.util.command.ArgumentExecutor;

public class ConquestExecutor
  extends ArgumentExecutor
{
  public ConquestExecutor(Base plugin)
  {
    super("conquest");
    addArgument(new ConquestSetpointsArgument(plugin));
  }
}
