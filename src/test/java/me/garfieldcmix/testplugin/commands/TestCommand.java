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
						.setUsage("Head Usage works!")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setUsage("Sub1 Usage works!")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setUsage("Sub2 Usage works!")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setUsage("Sub3 Usage works!")
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
							commandSender.sendMessage("Head Handler works!");
							return false;
						})
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setUsage("Sub1 command handler test")
								.setGfmCommandHandler((commandSender, strings) -> {
									commandSender.sendMessage("Sub1 Handler works!");
									return false;
								})
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setUsage("Sub2 command handler test")
										.setGfmCommandHandler((commandSender, strings) -> {
											commandSender.sendMessage("Sub2 Handler works!");
											return false;
										})
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setUsage("Sub3 command handler test")
												.setGfmCommandHandler((commandSender, strings) -> {
													commandSender.sendMessage("Sub3 Handler works!");
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
		GfmSubCommand permissionSub1A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub1A")
				.setUsage("Sub1A command permission test")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("Sub1A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub1A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand permissionSub1D = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub1D")
				.setUsage("Sub1D command permission test")
				.setPermission("testplugin.commands.sub1")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("Sub1D - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub1D - Has permission");
					return false;
				})
				.build();
		GfmSubCommand permissionSub2A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub2A")
				.setUsage("Sub2A command permission test")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("Sub2A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub2A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand permissionSub2D = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub2D")
				.setUsage("Sub2D command permission test")
				.setPermission("testplugin.commands.sub2")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("Sub2D - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub2D - Has permission");
					return false;
				})
				.build();
		GfmSubCommand permissionSub3A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub3A")
				.setUsage("Sub3A command permission test")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("Sub3A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub3A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand permissionSub3D = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub3D")
				.setUsage("Sub3D command permission test")
				.setPermission("testplugin.commands.sub3")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("Sub3D - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub3D - Has permission");
					return false;
				})
				.build();

		permissionSub2A.addGfmSubCommand(permissionSub3A);
		permissionSub2A.addGfmSubCommand(permissionSub3D);
		permissionSub2D.addGfmSubCommand(permissionSub3A);
		permissionSub2D.addGfmSubCommand(permissionSub3D);

		permissionSub1A.addGfmSubCommand(permissionSub2A);
		permissionSub1A.addGfmSubCommand(permissionSub2D);
		permissionSub1D.addGfmSubCommand(permissionSub2A);
		permissionSub1D.addGfmSubCommand(permissionSub2D);

		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmpermissionA")
						.setUsage("HeadA command permission test")
						.isPermissionBlockGfmSubCommands(false)
						.setNoPermissionMessage("HeadA - No permission")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("HeadA - Has permission");
							return false;
						})
						.addGfmSubCommand(permissionSub1A)
						.addGfmSubCommand(permissionSub1D)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmpermissionD")
						.setUsage("HeadD command permission test")
						.setPermission("testplugin.commands.head")
						.isPermissionBlockGfmSubCommands(false)
						.setNoPermissionMessage("HeadD - No permission")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("HeadD - Has permission");
							return false;
						})
						.addGfmSubCommand(permissionSub1A)
						.addGfmSubCommand(permissionSub1D)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmdefaultpermission")
						.setUsage("Head command default permission message test")
						.setPermission("testplugin.commands.default")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("Head - Has permission");
							return false;
						})
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.setUsage("Sub1 command default permission message test")
								.setPermission("testplugin.commands.default")
								.setGfmCommandHandler((commandSender, strings) -> {
									commandSender.sendMessage("Sub1 - Has permission");
									return false;
								})
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.setUsage("Sub2 command default permission message test")
										.setPermission("testplugin.commands.default")
										.setGfmCommandHandler((commandSender, strings) -> {
											commandSender.sendMessage("Sub2 - Has permission");
											return false;
										})
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.setUsage("Sub3 command default permission message test")
												.setPermission("testplugin.commands.default")
												.setGfmCommandHandler((commandSender, strings) -> {
													commandSender.sendMessage("Sub3 - Has permission");
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

		GfmSubCommand bPermissionSub1A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub1A")
				.setUsage("Sub1A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub1A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub1A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand bPermissionSub1D = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub1D")
				.setUsage("Sub1D command permission test")
				.setPermission("testplugin.commands.sub1")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub1D - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub1D - Has permission");
					return false;
				})
				.build();
		GfmSubCommand bPermissionSub2A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub2A")
				.setUsage("Sub2A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub2A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub2A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand bPermissionSub2D = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub2D")
				.setUsage("Sub2D command permission test")
				.setPermission("testplugin.commands.sub2")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub2D - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub2D - Has permission");
					return false;
				})
				.build();
		GfmSubCommand bPermissionSub3A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub3A")
				.setUsage("Sub3A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub3A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub3A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand bPermissionSub3D = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub3D")
				.setUsage("Sub3D command permission test")
				.setPermission("testplugin.commands.sub3")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub3D - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub3D - Has permission");
					return false;
				})
				.build();

		bPermissionSub2A.addGfmSubCommand(bPermissionSub3A);
		bPermissionSub2A.addGfmSubCommand(bPermissionSub3D);
		bPermissionSub2D.addGfmSubCommand(bPermissionSub3A);
		bPermissionSub2D.addGfmSubCommand(bPermissionSub3D);

		bPermissionSub1A.addGfmSubCommand(bPermissionSub2A);
		bPermissionSub1A.addGfmSubCommand(bPermissionSub2D);
		bPermissionSub1D.addGfmSubCommand(bPermissionSub2A);
		bPermissionSub1D.addGfmSubCommand(bPermissionSub2D);

		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmblocksubcommandA")
						.setUsage("HeadA command permission test")
						.isPermissionBlockGfmSubCommands(true)
						.setNoPermissionMessage("HeadA - No permission")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("HeadA - Has permission");
							return false;
						})
						.addGfmSubCommand(bPermissionSub1A)
						.addGfmSubCommand(bPermissionSub1D)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmblocksubcommandD")
						.setUsage("HeadD command permission test")
						.setPermission("testplugin.commands.head")
						.isPermissionBlockGfmSubCommands(true)
						.setNoPermissionMessage("HeadD - No permission")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("HeadD - Has permission");
							return false;
						})
						.addGfmSubCommand(bPermissionSub1A)
						.addGfmSubCommand(bPermissionSub1D)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmtabcomplete")
						.addTabCompleteArg("sub1tab1", "sub1tab2", "sub1tab3")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.addTabCompleteArg("sub2tab1", "sub2tab2", "sub2tab3")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.addTabCompleteArg("sub3tab1", "sub3tab2", "sub3tab3")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.addTabCompleteArg("sub4tab1", "sub4tab2", "sub4tab3")
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
						.setName("gfmdefaulttabcomplete")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1h")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub11")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.build()
								)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub21")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub12")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.build()
										)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub22")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.build()
										)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub32")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
														.setName("sub13")
														.setGfmCommandHandler((commandSender, strings) -> false)
														.build()
												)
												.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
														.setName("sub23")
														.setGfmCommandHandler((commandSender, strings) -> false)
														.build()
												)
												.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
														.setName("sub33")
														.setGfmCommandHandler((commandSender, strings) -> false)
														.build()
												)
												.build()
										)
										.build()
								)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub31")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.build()
								)
								.build()
						)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub2h")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.build()
						)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub3h")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.build()
						)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmpermissiontabcomplete")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.isPermissionBlockTabComplete(true)
								.setName("sub1h")
								.setPermission("testplugin.commands.sub1")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.isPermissionBlockTabComplete(true)
										.setName("sub11")
										.setPermission("testplugin.commands.sub1")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.build()
								)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.isPermissionBlockTabComplete(true)
										.setName("sub21")
										.setPermission("testplugin.commands.sub2")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.isPermissionBlockTabComplete(true)
												.setName("sub12")
												.setPermission("testplugin.commands.sub1")
												.isPermissionBlockGfmSubCommands(true)
												.setGfmCommandHandler((commandSender, strings) -> false)
												.build()
										)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.isPermissionBlockTabComplete(true)
												.setName("sub22")
												.setPermission("testplugin.commands.sub2")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.build()
										)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.isPermissionBlockTabComplete(true)
												.setName("sub32")
												.setPermission("testplugin.commands.sub3")
												.setGfmCommandHandler((commandSender, strings) -> false)
												.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
														.isPermissionBlockTabComplete(true)
														.setName("sub13")
														.setPermission("testplugin.commands.sub1")
														.setGfmCommandHandler((commandSender, strings) -> false)
														.build()
												)
												.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
														.isPermissionBlockTabComplete(true)
														.setName("sub23")
														.setPermission("testplugin.commands.sub2")
														.setGfmCommandHandler((commandSender, strings) -> false)
														.build()
												)
												.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
														.isPermissionBlockTabComplete(true)
														.setName("sub33")
														.setPermission("testplugin.commands.sub3")
														.setGfmCommandHandler((commandSender, strings) -> false)
														.build()
												)
												.build()
										)
										.build()
								)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.isPermissionBlockTabComplete(true)
										.setName("sub31")
										.setPermission("testplugin.commands.sub3")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.build()
								)
								.build()
						)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.isPermissionBlockTabComplete(true)
								.setName("sub2h")
								.setPermission("testplugin.commands.sub2")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.build()
						)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.isPermissionBlockTabComplete(true)
								.setName("sub3h")
								.setPermission("testplugin.commands.sub3")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.build()
						)
						.build()
		);
		commands.add(
				(GfmHeadCommand) new GfmHeadCommand.builder()
						.setName("gfmplayertabcomplete")
						.isTabCompletePlayer(true)
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub1")
								.isTabCompletePlayer(true)
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub2")
										.isTabCompletePlayer(true)
										.setGfmCommandHandler((commandSender, strings) -> false)
										.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
												.setName("sub3")
												.isTabCompletePlayer(true)
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
