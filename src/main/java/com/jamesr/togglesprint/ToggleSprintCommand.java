package com.jamesr.togglesprint;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class ToggleSprintCommand implements ICommand {

	private List<String> aliases;
	private List<String> tabCompletions;
	
	public ToggleSprintCommand() {
		aliases = new ArrayList<String>();
		aliases.add("ts");
		
		tabCompletions = new ArrayList<String>();
		tabCompletions.add("enable");
		tabCompletions.add("disable");

	}
	
	@Override
	public String getName() {
		return "togglesprint";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public boolean checkPermission(MinecraftServer sevrer, ICommandSender sender) {
		return true;
	}

	@Override
	public void execute(MinecraftServer sevrer, ICommandSender sender, String[] args) throws CommandException {
		// basically toggle
		if(args.length == 0) {
			ToggleSprint.enabled = !ToggleSprint.enabled;
		}
		
		if(args.length > 0) {
			// cant switch strings in java 1.6? bruh
			if(args[0].equals("enable"))
				ToggleSprint.enabled = true;
			else if(args[0].equals("disable")) {
				ToggleSprint.enabled = false;
				ToggleSprint.toggled = false;
			}else if(args[0].equals("toggle"))
				ToggleSprint.enabled = !ToggleSprint.enabled;
			else {
				sender.sendMessage(new TextComponentTranslation("[togglesprint] " + ChatFormatting.RED + "error" + ChatFormatting.WHITE + " usage: /togglesprint toggle, enable or disable"));
				return;
			}
		}
		
		if(ToggleSprint.enabled)
			sender.sendMessage(new TextComponentTranslation("[togglesprint] " + ChatFormatting.BLUE + "enabled"));
		else
			sender.sendMessage(new TextComponentTranslation("[togglesprint] " + ChatFormatting.RED + "disabled"));
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos blockpos) {
		return null;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] arg0, int arg1) {
		return false;
	}
}