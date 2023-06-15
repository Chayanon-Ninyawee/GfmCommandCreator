package me.garfieldcmix.testplugin.commands;

import me.garfieldcmix.gfmcommandcreator.GfmCommandCreator;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmHeadCommand;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmSubCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TestCommand {
	private static final List<GfmHeadCommand> commands = new ArrayList<>();

	public static void register(JavaPlugin plugin) {
		commands.add(TestUsage.gfmUsage());
		commands.add(TestUsage.gfmDefaultUsage());
		commands.add(TestHandler.gfmHandler());


		commands.add(TestPermission.gfmPermissionA());
		commands.add(TestPermission.gfmPermissionD());
		commands.add(TestPermission.gfmDefaultPermission());
		commands.add(TestPermission.gfmBlockSubcommandA());
		commands.add(TestPermission.gfmBlockSubcommandD());

		commands.add(TestTabcomplete.gfmTabcomplete());
		commands.add(TestTabcomplete.gfmDefaultTabcomplete());
		commands.add(TestTabcomplete.gfmPermissionTabcomplete());
		commands.add(TestTabcomplete.gfmPlayerTabcomplete());

		GfmCommandCreator.register(plugin, commands);
	}
}
