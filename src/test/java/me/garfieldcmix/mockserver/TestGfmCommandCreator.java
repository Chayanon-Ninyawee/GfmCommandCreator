package me.garfieldcmix.mockserver;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.garfieldcmix.testplugin.TestPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGfmCommandCreator {
	private ServerMock server;
	private TestPlugin plugin;

	private PlayerMock playerNoPermission;
	private PlayerMock playerHasPermission;

	@BeforeEach
	public void setUp() {
		server = MockBukkit.mock();
		plugin = MockBukkit.load(TestPlugin.class);

		playerNoPermission = server.addPlayer("NoPermissionBoi");

		playerHasPermission = server.addPlayer("HasPermissionBoi");
	}

	@AfterEach
	public void tearDown() {
		// Stop the mock server
		MockBukkit.unmock();
	}

	@Test
	@DisplayName("Test Head Command Usage")
	public void testHeadCommandUsage() {
		Command command = plugin.getCommand("gfmusage");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("Head Usage works!");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("Head Usage works!");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub1 Command Usage")
	public void testSubICommandUsage() {
		Command command = plugin.getCommand("gfmusage");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1"});
		playerNoPermission.assertSaid("Sub1 Usage works!");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1"});
		playerHasPermission.assertSaid("Sub1 Usage works!");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub2 Command Usage")
	public void testSubIICommandUsage() {
		Command command = plugin.getCommand("gfmusage");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1", "sub2"});
		playerNoPermission.assertSaid("Sub2 Usage works!");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1", "sub2"});
		playerHasPermission.assertSaid("Sub2 Usage works!");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub3 Command Usage")
	public void testSubIIICommandUsage() {
		Command command = plugin.getCommand("gfmusage");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1", "sub2", "sub3"});
		playerNoPermission.assertSaid("Sub3 Usage works!");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1", "sub2", "sub3"});
		playerHasPermission.assertSaid("Sub3 Usage works!");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test All Command Default Usage")
	public void testCommandDefaultUsage() {
		Command command = plugin.getCommand("gfmdefaultusage");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));
		playerHasPermission.assertNoMoreSaid();

		for (String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			command.execute(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));
			playerNoPermission.assertNoMoreSaid();

			command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerHasPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));
			playerNoPermission.assertNoMoreSaid();
		}
	}


	@Test
	@DisplayName("Test Head CommandHandler")
	public void testHeadCommandHandler() {
		Command command = plugin.getCommand("gfmhandler");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("Head Handler works!");
		playerNoPermission.assertSaid("Head command handler test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("Head Handler works!");
		playerHasPermission.assertSaid("Head command handler test");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub1 CommandHandler")
	public void testSubICommandHandler() {
		Command command = plugin.getCommand("gfmhandler");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1"});
		playerNoPermission.assertSaid("Sub1 Handler works!");
		playerNoPermission.assertSaid("Sub1 command handler test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1"});
		playerHasPermission.assertSaid("Sub1 Handler works!");
		playerHasPermission.assertSaid("Sub1 command handler test");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub2 CommandHandler")
	public void testSubIICommandHandler() {
		Command command = plugin.getCommand("gfmhandler");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1", "sub2"});
		playerNoPermission.assertSaid("Sub2 Handler works!");
		playerNoPermission.assertSaid("Sub2 command handler test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1", "sub2"});
		playerHasPermission.assertSaid("Sub2 Handler works!");
		playerHasPermission.assertSaid("Sub2 command handler test");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub3 CommandHandler")
	public void testSubIIICommandHandler() {
		Command command = plugin.getCommand("gfmhandler");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1", "sub2", "sub3"});
		playerNoPermission.assertSaid("Sub3 Handler works!");
		playerNoPermission.assertSaid("Sub3 command handler test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1", "sub2", "sub3"});
		playerHasPermission.assertSaid("Sub3 Handler works!");
		playerHasPermission.assertSaid("Sub3 command handler test");
		playerHasPermission.assertNoMoreSaid();
	}


	@Test
	@DisplayName("Test Head Command Permission")
	public void testHeadCommandPermission() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.head", true);


		Command command = plugin.getCommand("gfmpermissionA");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("HeadA - Has permission");
		playerNoPermission.assertSaid("HeadA command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("HeadA - Has permission");
		playerHasPermission.assertSaid("HeadA command permission test");
		playerHasPermission.assertNoMoreSaid();


		command = plugin.getCommand("gfmpermissionD");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("HeadD - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("HeadD - Has permission");
		playerHasPermission.assertSaid("HeadD command permission test");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub1 Command Permission")
	public void testSubICommandPermission() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.sub1", true);


		Command command = plugin.getCommand("gfmpermissionA");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1a"});
		playerNoPermission.assertSaid("Sub1A - Has permission");
		playerNoPermission.assertSaid("Sub1A command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a"});
		playerHasPermission.assertSaid("Sub1A - Has permission");
		playerHasPermission.assertSaid("Sub1A command permission test");
		playerHasPermission.assertNoMoreSaid();


		command = plugin.getCommand("gfmpermissionD");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d"});
		playerNoPermission.assertSaid("Sub1D - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1d"});
		playerHasPermission.assertSaid("Sub1D - Has permission");
		playerHasPermission.assertSaid("Sub1D command permission test");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub2 Command Permission")
	public void testSubIICommandPermission() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.sub2", true);


		Command command = plugin.getCommand("gfmpermissionA");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1a", "sub2a"});
		playerNoPermission.assertSaid("Sub2A - Has permission");
		playerNoPermission.assertSaid("Sub2A command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2a"});
		playerHasPermission.assertSaid("Sub2A - Has permission");
		playerHasPermission.assertSaid("Sub2A command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2a"});
		playerNoPermission.assertSaid("Sub2A - Has permission");
		playerNoPermission.assertSaid("Sub2A command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1d", "sub2a"});
		playerHasPermission.assertSaid("Sub2A - Has permission");
		playerHasPermission.assertSaid("Sub2A command permission test");
		playerHasPermission.assertNoMoreSaid();


		command = plugin.getCommand("gfmpermissionD");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2d"});
		playerNoPermission.assertSaid("Sub2D - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1d", "sub2d"});
		playerHasPermission.assertSaid("Sub2D - Has permission");
		playerHasPermission.assertSaid("Sub2D command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1a", "sub2d"});
		playerNoPermission.assertSaid("Sub2D - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2d"});
		playerHasPermission.assertSaid("Sub2D - Has permission");
		playerHasPermission.assertSaid("Sub2D command permission test");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub3 Command Permission")
	public void testSubIIICommandPermission() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.sub3", true);


		Command command = plugin.getCommand("gfmpermissionA");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1a", "sub2a", "sub3a"});
		playerNoPermission.assertSaid("Sub3A - Has permission");
		playerNoPermission.assertSaid("Sub3A command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2a", "sub3a"});
		playerHasPermission.assertSaid("Sub3A - Has permission");
		playerHasPermission.assertSaid("Sub3A command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2d", "sub3a"});
		playerNoPermission.assertSaid("Sub3A - Has permission");
		playerNoPermission.assertSaid("Sub3A command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2d", "sub3a"});
		playerHasPermission.assertSaid("Sub3A - Has permission");
		playerHasPermission.assertSaid("Sub3A command permission test");
		playerHasPermission.assertNoMoreSaid();


		command = plugin.getCommand("gfmpermissionD");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2d", "sub3d"});
		playerNoPermission.assertSaid("Sub3D - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1d", "sub2d", "sub3d"});
		playerHasPermission.assertSaid("Sub3D - Has permission");
		playerHasPermission.assertSaid("Sub3D command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2a", "sub3d"});
		playerNoPermission.assertSaid("Sub3D - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2d", "sub3d"});
		playerHasPermission.assertSaid("Sub3D - Has permission");
		playerHasPermission.assertSaid("Sub3D command permission test");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test All Command Default Permission Message")
	public void testCommandDefaultPermission() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.default", true);

		Command command = plugin.getCommand("gfmdefaultpermission");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("Head - Has permission");
		playerHasPermission.assertSaid("Head command default permission message test");
		playerHasPermission.assertNoMoreSaid();

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			command.execute(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));
			playerNoPermission.assertNoMoreSaid();

			command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerHasPermission.assertSaid(testSubCommand.substring(0, 1).toUpperCase() + testSubCommand.substring(1).toLowerCase() + " - Has permission");
			playerHasPermission.assertSaid(testSubCommand.substring(0, 1).toUpperCase() + testSubCommand.substring(1).toLowerCase() + " command default permission message test");
			playerHasPermission.assertNoMoreSaid();
		}
	}

	@Test
	@DisplayName("Test Sub1 Permission Block Subcommand")
	public void testSubIPermissionBlockSubCommand() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.head", true);

		Command command = plugin.getCommand("gfmblocksubcommandA");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1a"});
		playerNoPermission.assertSaid("Sub1A - Has permission");
		playerNoPermission.assertSaid("Sub1A command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a"});
		playerHasPermission.assertSaid("Sub1A - Has permission");
		playerHasPermission.assertSaid("Sub1A command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d"});
		playerNoPermission.assertSaid("Sub1D - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1d"});
		playerHasPermission.assertSaid("Sub1D - No permission");
		playerHasPermission.assertNoMoreSaid();


		command = plugin.getCommand("gfmblocksubcommandD");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1a"});
		playerNoPermission.assertSaid("HeadD - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a"});
		playerHasPermission.assertSaid("Sub1A - Has permission");
		playerHasPermission.assertSaid("Sub1A command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d"});
		playerNoPermission.assertSaid("HeadD - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1d"});
		playerHasPermission.assertSaid("Sub1D - No permission");
		playerHasPermission.assertNoMoreSaid();
	}

	@Test
	@DisplayName("Test Sub2 Permission Block Subcommand")
	public void testSubIIPermissionBlockSubCommand() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.head", true);

		Command command = plugin.getCommand("gfmblocksubcommandA");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1a", "sub2a"});
		playerNoPermission.assertSaid("Sub2A - Has permission");
		playerNoPermission.assertSaid("Sub2A command permission test");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2a"});
		playerHasPermission.assertSaid("Sub2A - Has permission");
		playerHasPermission.assertSaid("Sub2A command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2d"});
		playerNoPermission.assertSaid("Sub1D - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2d"});
		playerHasPermission.assertSaid("Sub2D - No permission");
		playerHasPermission.assertNoMoreSaid();


		command = plugin.getCommand("gfmblocksubcommandD");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2a"});
		playerNoPermission.assertSaid("HeadD - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2a"});
		playerHasPermission.assertSaid("Sub2A - Has permission");
		playerHasPermission.assertSaid("Sub2A command permission test");
		playerHasPermission.assertNoMoreSaid();

		command.execute(playerNoPermission, command.getName(), new String[]{"sub1d", "sub2a"});
		playerNoPermission.assertSaid("HeadD - No permission");
		playerNoPermission.assertNoMoreSaid();

		command.execute(playerHasPermission, command.getName(), new String[]{"sub1a", "sub2d"});
		playerHasPermission.assertSaid("Sub2D - No permission");
		playerHasPermission.assertNoMoreSaid();
	}


	@Test
	@DisplayName("Test TabComplete")
	public void testTabComplete() {
		Command command = plugin.getCommand("gfmtabcomplete");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		assertEquals(Arrays.asList("headtab1", "headtab2", "headtab3"), command.tabComplete(playerNoPermission, command.getName(), new String[]{}));
		assertEquals(Arrays.asList("headtab1", "headtab2", "headtab3"), command.tabComplete(playerHasPermission, command.getName(), new String[]{}));

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			assertEquals(Arrays.asList(testSubCommand+"tab1", testSubCommand+"tab2", testSubCommand+"tab3"), command.tabComplete(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0])));
			assertEquals(Arrays.asList(testSubCommand+"tab1", testSubCommand+"tab2", testSubCommand+"tab3"), command.tabComplete(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0])));
		}
	}

	@Test
	@DisplayName("Test Default TabComplete")
	public void testDefaultTabComplete() {
		Command command = plugin.getCommand("gfmdefaulttabcomplete");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1h", "sub21", "sub32");
		List<String> testedSubCommands = new ArrayList<>();

		assertEquals(Arrays.asList("sub1h", "sub2h", "sub3h"), command.tabComplete(playerNoPermission, command.getName(), new String[]{}));
		assertEquals(Arrays.asList("sub1h", "sub2h", "sub3h"), command.tabComplete(playerHasPermission, command.getName(), new String[]{}));

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			assertEquals(Arrays.asList("sub1"+testSubCommand.charAt(3), "sub2"+testSubCommand.charAt(3), "sub3"+testSubCommand.charAt(3)), command.tabComplete(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0])));
			assertEquals(Arrays.asList("sub1"+testSubCommand.charAt(3), "sub2"+testSubCommand.charAt(3), "sub3"+testSubCommand.charAt(3)), command.tabComplete(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0])));
		}
	}

	@Test
	@DisplayName("Test Player TabComplete")
	public void testPlayerTabComplete() {
		Command command = plugin.getCommand("gfmplayertabcomplete");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		assertEquals(server.getOnlinePlayers().stream().map(PlayerMock::getName).collect(Collectors.toList()), command.tabComplete(playerNoPermission, command.getName(), new String[]{}));
		assertEquals(server.getOnlinePlayers().stream().map(PlayerMock::getName).collect(Collectors.toList()), command.tabComplete(playerHasPermission, command.getName(), new String[]{}));

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			assertEquals(server.getOnlinePlayers().stream().map(PlayerMock::getName).collect(Collectors.toList()), command.tabComplete(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0])));
			assertEquals(server.getOnlinePlayers().stream().map(PlayerMock::getName).collect(Collectors.toList()), command.tabComplete(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0])));
		}
	}

	@Test
	@DisplayName("Test Permission TabComplete")
	public void testPermissionTabComplete() {
		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("testplugin.commands.sub1", true);
		pperms.setPermission("testplugin.commands.sub3", true);

		Command command = plugin.getCommand("gfmpermissiontabcomplete");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1h", "sub21", "sub32");
		List<String> testedSubCommands = new ArrayList<>();

		assertEquals(Collections.emptyList(), command.tabComplete(playerNoPermission, command.getName(), new String[]{}));
		assertEquals(Arrays.asList("sub1h", "sub3h"), command.tabComplete(playerHasPermission, command.getName(), new String[]{}));

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			assertEquals(Collections.emptyList(), command.tabComplete(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0])));
			assertEquals(Arrays.asList("sub1"+testSubCommand.charAt(3), "sub3"+testSubCommand.charAt(3)), command.tabComplete(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0])));
		}
	}
}
