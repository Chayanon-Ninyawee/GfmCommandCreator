package me.garfieldcmix.testplugin.commands;

import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmHeadCommand;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmSubCommand;

public class TestPermission {
	private static GfmSubCommand permissionSub1A() {
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

		return permissionSub1A;
	}
	private static GfmSubCommand permissionSub1D() {
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

		permissionSub1D.addGfmSubCommand(permissionSub2A);
		permissionSub1D.addGfmSubCommand(permissionSub2D);

		return permissionSub1D;
	}

	private static GfmSubCommand blockPermissionSub1A() {
		GfmSubCommand blockPermissionSub1A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub1A")
				.setUsage("Sub1A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub1A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub1A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand blockPermissionSub2A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub2A")
				.setUsage("Sub2A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub2A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub2A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand blockPermissionSub2D = (GfmSubCommand) new GfmSubCommand.builder()
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
		GfmSubCommand blockPermissionSub3A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub3A")
				.setUsage("Sub3A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub3A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub3A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand blockPermissionSub3D = (GfmSubCommand) new GfmSubCommand.builder()
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

		blockPermissionSub2A.addGfmSubCommand(blockPermissionSub3A);
		blockPermissionSub2A.addGfmSubCommand(blockPermissionSub3D);
		blockPermissionSub2D.addGfmSubCommand(blockPermissionSub3A);
		blockPermissionSub2D.addGfmSubCommand(blockPermissionSub3D);

		blockPermissionSub1A.addGfmSubCommand(blockPermissionSub2A);
		blockPermissionSub1A.addGfmSubCommand(blockPermissionSub2D);

		return blockPermissionSub1A;
	}
	private static GfmSubCommand blockPermissionSub1D() {
		GfmSubCommand blockPermissionSub1D = (GfmSubCommand) new GfmSubCommand.builder()
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
		GfmSubCommand blockPermissionSub2A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub2A")
				.setUsage("Sub2A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub2A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub2A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand blockPermissionSub2D = (GfmSubCommand) new GfmSubCommand.builder()
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
		GfmSubCommand blockPermissionSub3A = (GfmSubCommand) new GfmSubCommand.builder()
				.setName("sub3A")
				.setUsage("Sub3A command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("Sub3A - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Sub3A - Has permission");
					return false;
				})
				.build();
		GfmSubCommand blockPermissionSub3D = (GfmSubCommand) new GfmSubCommand.builder()
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

		blockPermissionSub2A.addGfmSubCommand(blockPermissionSub3A);
		blockPermissionSub2A.addGfmSubCommand(blockPermissionSub3D);
		blockPermissionSub2D.addGfmSubCommand(blockPermissionSub3A);
		blockPermissionSub2D.addGfmSubCommand(blockPermissionSub3D);

		blockPermissionSub1D.addGfmSubCommand(blockPermissionSub2A);
		blockPermissionSub1D.addGfmSubCommand(blockPermissionSub2D);

		return blockPermissionSub1D;
	}


	public static GfmHeadCommand gfmPermissionA() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
				.setName("gfmpermissionA")
				.setUsage("HeadA command permission test")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("HeadA - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("HeadA - Has permission");
					return false;
				})
				.addGfmSubCommand(permissionSub1A())
				.addGfmSubCommand(permissionSub1D())
				.build();
	}

	public static GfmHeadCommand gfmPermissionD() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
				.setName("gfmpermissionD")
				.setUsage("HeadD command permission test")
				.setPermission("testplugin.commands.head")
				.isPermissionBlockGfmSubCommands(false)
				.setNoPermissionMessage("HeadD - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("HeadD - Has permission");
					return false;
				})
				.addGfmSubCommand(permissionSub1A())
				.addGfmSubCommand(permissionSub1D())
				.build();
	}

	public static GfmHeadCommand gfmDefaultPermission() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
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
				.build();
	}

	public static GfmHeadCommand gfmBlockSubcommandA() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
				.setName("gfmblocksubcommandA")
				.setUsage("HeadA command permission test")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("HeadA - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("HeadA - Has permission");
					return false;
				})
				.addGfmSubCommand(blockPermissionSub1A())
				.addGfmSubCommand(blockPermissionSub1D())
				.build();
	}

	public static GfmHeadCommand gfmBlockSubcommandD() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
				.setName("gfmblocksubcommandD")
				.setUsage("HeadD command permission test")
				.setPermission("testplugin.commands.head")
				.isPermissionBlockGfmSubCommands(true)
				.setNoPermissionMessage("HeadD - No permission")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("HeadD - Has permission");
					return false;
				})
				.addGfmSubCommand(blockPermissionSub1A())
				.addGfmSubCommand(blockPermissionSub1D())
				.build();
	}
}
