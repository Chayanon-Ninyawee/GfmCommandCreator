package me.garfieldcmix.mockserver;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.garfieldcmix.testplugin.TestPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.permissions.PermissionAttachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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

		// Set playerHasPermission permission
		HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
		PermissionAttachment attachment = playerHasPermission.addAttachment(plugin);
		perms.put(playerHasPermission.getUniqueId(), attachment);
		PermissionAttachment pperms = perms.get(playerHasPermission.getUniqueId());
		pperms.setPermission("gfmtest.commands.gfmpermission", true);
	}

	@AfterEach
	public void tearDown() {
		// Stop the mock server
		MockBukkit.unmock();
	}

	@Test
	public void testCommandUsage() {
		Command command = plugin.getCommand("gfmusage");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("Usage works!");

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("Usage works!");

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			command.execute(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerNoPermission.assertSaid("Usage works!");

			command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerHasPermission.assertSaid("Usage works!");
		}
	}

	@Test
	public void testCommandDefaultUsage() {
		Command command = plugin.getCommand("gfmdefaultusage");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));

		for (String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			command.execute(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));

			command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerHasPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));
		}
	}

	@Test
	public void testCommandHandler() {
		Command command = plugin.getCommand("gfmhandler");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("Handler works!");
		playerNoPermission.nextMessage();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("Handler works!");
		playerHasPermission.nextMessage();

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			command.execute(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerNoPermission.assertSaid("Handler works!");
			playerNoPermission.nextMessage();

			command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerHasPermission.assertSaid("Handler works!");
			playerHasPermission.nextMessage();
		}
	}

	@Test
	public void testCommandPermission() {
		Command command = plugin.getCommand("gfmpermission");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("No permission naja");
		playerNoPermission.nextMessage();

		command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
		playerHasPermission.assertSaid("If you have permission, it works! But if you don't, ...why?");
		playerHasPermission.nextMessage();

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			command.execute(playerNoPermission, command.getName(), new String[]{});
			playerNoPermission.assertSaid("No permission naja");
			playerNoPermission.nextMessage();

			command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerHasPermission.assertSaid("If you have permission, it works! But if you don't, ...why?");
			playerHasPermission.nextMessage();
		}
	}

	@Test
	public void testCommandDefaultPermission() {
		Command command = plugin.getCommand("gfmdefaultpermission");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));
		playerNoPermission.nextMessage();

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("If you have permission, it works! But if you don't, ...why?");
		playerHasPermission.nextMessage();

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			command.execute(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));
			playerNoPermission.nextMessage();

			command.execute(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0]));
			playerHasPermission.assertSaid("If you have permission, it works! But if you don't, ...why?");
			playerHasPermission.nextMessage();
		}
	}

	@Test
	public void testTabComplete() {
		Command command = plugin.getCommand("gfmtabcomplete");
		assert command != null;

		List<String> testSubCommands = Arrays.asList("sub1", "sub2", "sub3");
		List<String> testedSubCommands = new ArrayList<>();

		assertEquals(Arrays.asList("tab1", "tab2", "tab3"), command.tabComplete(playerNoPermission, command.getName(), new String[]{}));
		assertEquals(Arrays.asList("tab1", "tab2", "tab3"), command.tabComplete(playerHasPermission, command.getName(), new String[]{}));

		for(String testSubCommand : testSubCommands) {
			testedSubCommands.add(testSubCommand);

			assertEquals(Arrays.asList("tab1", "tab2", "tab3"), command.tabComplete(playerNoPermission, command.getName(), testedSubCommands.toArray(new String[0])));
			assertEquals(Arrays.asList("tab1", "tab2", "tab3"), command.tabComplete(playerHasPermission, command.getName(), testedSubCommands.toArray(new String[0])));
		}
	}

	// TODO: Test TabComplete


}
