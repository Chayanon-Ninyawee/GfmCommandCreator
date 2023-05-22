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
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmusage")
						.setUsage("Usage works!")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setUsage("Usage works!")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setUsage("Usage works!")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setUsage("Usage works!")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmdefaultusage")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmhandler")
						.setUsage("Head command handler test")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("Handler works!");
							return false;
						})
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setUsage("Head command handler test")
								.setGfmCommandHandler((commandSender, strings) -> {
									commandSender.sendMessage("Handler works!");
									return false;
								})
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setUsage("Head command handler test")
										.setGfmCommandHandler((commandSender, strings) -> {
											commandSender.sendMessage("Handler works!");
											return false;
										})
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setUsage("Head command handler test")
												.setGfmCommandHandler((commandSender, strings) -> {
													commandSender.sendMessage("Handler works!");
													return false;
												})
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmpermission")
						.setUsage("Head command permission test")
						.setPermission("gfmtest.commands.gfmpermission")
						.setNoPermissionMessage("No permission naja")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
							return false;
						})
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setUsage("Head command permission test")
								.setPermission("gfmtest.commands.gfmpermission")
								.setNoPermissionMessage("No permission naja")
								.setGfmCommandHandler((commandSender, strings) -> {
									commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
									return false;
								})
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setUsage("Head command permission test")
										.setPermission("gfmtest.commands.gfmpermission")
										.setNoPermissionMessage("No permission naja")
										.setGfmCommandHandler((commandSender, strings) -> {
											commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
											return false;
										})
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setUsage("Head command permission test")
												.setPermission("gfmtest.commands.gfmpermission")
												.setNoPermissionMessage("No permission naja")
												.setGfmCommandHandler((commandSender, strings) -> {
													commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
													return false;
												})
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmdefaultpermission")
						.setUsage("Head command default permission test")
						.setPermission("gfmtest.commands.gfmpermission")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
							return false;
						})
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setUsage("Head command default permission test")
								.setPermission("gfmtest.commands.gfmpermission")
								.setGfmCommandHandler((commandSender, strings) -> {
									commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
									return false;
								})
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setUsage("Head command default permission test")
										.setPermission("gfmtest.commands.gfmpermission")
										.setGfmCommandHandler((commandSender, strings) -> {
											commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
											return false;
										})
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setUsage("Head command default permission test")
												.setPermission("gfmtest.commands.gfmpermission")
												.setGfmCommandHandler((commandSender, strings) -> {
													commandSender.sendMessage("If you have permission, it works! But if you don't, ...why?");
													return false;
												})
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmtabcomplete")
						.addTabCompleteArg("tab1", "tab2", "tab3")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.addTabCompleteArg("tab1", "tab2", "tab3")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.addTabCompleteArg("tab1", "tab2", "tab3")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.addTabCompleteArg("tab1", "tab2", "tab3")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.build()
										)
										.build()
								)
								.build()
						)
						.build()
		);

		GfmCommandCreator.register(plugin, commands);
	}
}
