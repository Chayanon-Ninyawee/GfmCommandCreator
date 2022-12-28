package me.garfieldcmix.gfmcommandcreator;

import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmCommand;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class GfmCommandCreator {
	public static void register(JavaPlugin plugin, List<GfmCommand> commands) {
		for (GfmCommand command : commands) {


			CmdExecutor cmdExecutor = new CmdExecutor(command);
			CmdTabCompleter cmdTabCompleter = new CmdTabCompleter(command);

			plugin.getServer().getPluginManager().registerEvents(cmdExecutor, plugin);
			plugin.getCommand(command.getName()).setExecutor(cmdExecutor);
			if (command.isTabComplete()) {
				plugin.getCommand(command.getName()).setTabCompleter(cmdTabCompleter);
			}
		}
	}

	private static class CmdExecutor implements Listener, CommandExecutor {
		private final GfmCommand gfmCommand;

		private CmdExecutor(GfmCommand gfmCommand) {
			this.gfmCommand = gfmCommand;
		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if (!(args.length > 0)) {
				if (this.gfmCommand.isPermissionNull()) {
					if (!this.gfmCommand.getGfmCommandHandler().execute(sender, args)) {
						sender.sendMessage(this.gfmCommand.getUsage());
					}
					return true;
				}

				if (sender.hasPermission(this.gfmCommand.getPermission())) {
					if (!this.gfmCommand.getGfmCommandHandler().execute(sender, args)) {
						sender.sendMessage(this.gfmCommand.getUsage());
					}
					return true;
				}

				sender.sendMessage(this.gfmCommand.getNoPermissionMessage());
				return true;
			}

			if (!this.gfmCommand.isPermissionNull() && this.gfmCommand.isPermissionBlockGfmSubCommands()) {
				if (!sender.hasPermission(this.gfmCommand.getPermission())) {
					sender.sendMessage(this.gfmCommand.getNoPermissionMessage());
					return true;
				}
			}

			for (GfmSubCommand gfmSubCommand : this.gfmCommand.getGfmSubCommands()) {
				if (args[0].equalsIgnoreCase(gfmSubCommand.getName())) {
					if (gfmSubCommand.isPermissionNull()) {
						if (!gfmSubCommand.getGfmCommandHandler().execute(sender, args)) {
							sender.sendMessage(gfmSubCommand.getUsage());
						}
						return true;
					}

					if (sender.hasPermission(gfmSubCommand.getPermission())) {
						if (!gfmSubCommand.getGfmCommandHandler().execute(sender, args)) {
							sender.sendMessage(gfmSubCommand.getUsage());
						}
						return true;
					}

					sender.sendMessage(gfmSubCommand.getNoPermissionMessage());
					return true;
				}
			}
			sender.sendMessage(this.gfmCommand.getUsage());
			return true;
		}
	}

	private static class CmdTabCompleter implements TabCompleter {
		private final GfmCommand gfmCommand;

		private CmdTabCompleter(GfmCommand gfmCommand) {
			this.gfmCommand = gfmCommand;
		}

		@Override
		public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
			if (this.gfmCommand.isTabCompleteEmpty()) {
				if (args.length != 1) {
					return null;
				}

				List<String> gfmSubCommandVisibleNames = new ArrayList<>();
				for (GfmSubCommand gfmSubCommand : this.gfmCommand.getGfmSubCommands()) {
					if (gfmSubCommand.isTabComplete()) {
						if (!gfmSubCommand.isPermissionNull() && gfmSubCommand.isTabCompleteToPermission()) {
							if (!sender.hasPermission(gfmSubCommand.getPermission())) {
								continue;
							}
						}
						gfmSubCommandVisibleNames.add(gfmSubCommand.getName());
					}
				}

				return createReturnList(gfmSubCommandVisibleNames, args[0]);
			}

			for(int i = 0; i < this.gfmCommand.getTabCompleteArgs().size(); i++) {
				if (args.length == i+1) {
					return createReturnList(this.gfmCommand.getTabCompleteArgs().get(i), args[i]);
				}
			}

			return null;
		}

		private List<String> createReturnList(List<String> list, String string) {
			if (string.length() == 0) {
				return list;
			}

			String input = string.toLowerCase(Locale.ROOT);
			List<String> returnList = new LinkedList<>();

			for (String item : list) {
				if (item.toLowerCase(Locale.ROOT).contains(input)) {
					returnList.add(item);

					if (returnList.size() >= 40) {
						break;
					}
				} else if (item.equalsIgnoreCase(input)) {
					return Collections.emptyList();
				}
			}

			return returnList;
		}
	}
}
