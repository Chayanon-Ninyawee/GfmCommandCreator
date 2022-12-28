package me.garfieldcmix.gfmcommandcreator.gfmcommand;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface GfmCommandHandler {
	boolean execute(CommandSender sender, String[] args);
}
