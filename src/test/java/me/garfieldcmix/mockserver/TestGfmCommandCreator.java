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

import java.util.HashMap;
import java.util.UUID;

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
	public void testHeadCommandUsage() {
		Command command = plugin.getCommand("gfmusage");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("Usage works!");

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("Usage works!");
	}

	@Test
	public void testHeadCommandDefaultUsage() {
		Command command = plugin.getCommand("gfmdefaultusage");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"));
	}

	@Test
	public void testHeadCommandHandler() {
		Command command = plugin.getCommand("gfmhandler");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("Handler works!");

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("Handler works!");
	}

	@Test
	public void testHeadCommandPermission() {
		Command command = plugin.getCommand("gfmpermission");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid("No permission naja");

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("If you have permission, it works! But if you don't, ...why?");
	}

	@Test
	public void testHeadCommandDefaultPermission() {
		Command command = plugin.getCommand("gfmdefaultpermission");
		assert command != null;

		command.execute(playerNoPermission, command.getName(), new String[]{});
		playerNoPermission.assertSaid(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"));

		command.execute(playerHasPermission, command.getName(), new String[]{});
		playerHasPermission.assertSaid("If you have permission, it works! But if you don't, ...why?");
	}

	// TODO: Do the test for subCommand first/second/third iteration.
}
