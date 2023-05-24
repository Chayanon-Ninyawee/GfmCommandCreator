package me.garfieldcmix.gfmcommandcreator;

import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmCommand;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmHeadCommand;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GfmCommandCreator {
	public static void register(JavaPlugin plugin, List<GfmHeadCommand> commands) {
		for (GfmHeadCommand command : commands) {
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
		private final GfmHeadCommand gfmHeadCommand;

		private CmdExecutor(GfmHeadCommand gfmHeadCommand) {
			this.gfmHeadCommand = gfmHeadCommand;
		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if (args.length == 0 || this.gfmHeadCommand.getGfmSubCommands().isEmpty()) {
				if (this.gfmHeadCommand.getPermission() == null) {
					this.gfmHeadCommand.executeCommand(sender, args);
					return true;
				}

				if (sender.hasPermission(this.gfmHeadCommand.getPermission())) {
					this.gfmHeadCommand.executeCommand(sender, args);
					return true;
				}

				sender.sendMessage(this.gfmHeadCommand.getNoPermissionMessage());
				return true;
			}

			if (this.gfmHeadCommand.getPermission() != null) {
				if (this.gfmHeadCommand.isPermissionBlockGfmSubCommands()) {
					if (!sender.hasPermission(this.gfmHeadCommand.getPermission())) {
						sender.sendMessage(this.gfmHeadCommand.getNoPermissionMessage());
						return true;
					}
				}
			}

			boolean isThereNextSubCommand = true;
			boolean isThereAnyCorrectSubCommand = true;
			GfmCommand lastSubCommand = this.gfmHeadCommand;
			String[] lastSubArgs = args;

			while(isThereNextSubCommand && isThereAnyCorrectSubCommand) {
				isThereAnyCorrectSubCommand = false;

				for (GfmSubCommand gfmSubCommand : lastSubCommand.getGfmSubCommands()) {
					if (!lastSubArgs[0].equalsIgnoreCase(gfmSubCommand.getName())) continue;

					String[] subArgs = Arrays.copyOfRange(lastSubArgs, 1, lastSubArgs.length);

					if (subArgs.length == 0 || gfmSubCommand.getGfmSubCommands().isEmpty()) {
						if (gfmSubCommand.getPermission() == null) {
							gfmSubCommand.executeCommand(sender, subArgs);
							return true;
						}

						if (sender.hasPermission(gfmSubCommand.getPermission())) {
							gfmSubCommand.executeCommand(sender, subArgs);
							return true;
						}

						sender.sendMessage(gfmSubCommand.getNoPermissionMessage());
						return true;
					}

					if (gfmSubCommand.getPermission() != null) {
						if (gfmSubCommand.isPermissionBlockGfmSubCommands()) {
							if (!sender.hasPermission(gfmSubCommand.getPermission())) {
								sender.sendMessage(gfmSubCommand.getNoPermissionMessage());
								return true;
							}
						}
					}

					isThereNextSubCommand = !gfmSubCommand.getGfmSubCommands().isEmpty();
					isThereAnyCorrectSubCommand = true;
					lastSubCommand = gfmSubCommand;
					lastSubArgs = subArgs;
				}

			}
			return true;
		}
	}

	private static class CmdTabCompleter implements TabCompleter {
		private final GfmHeadCommand gfmHeadCommand;

		private CmdTabCompleter(GfmHeadCommand gfmHeadCommand) {
			this.gfmHeadCommand = gfmHeadCommand;
		}

		@Override
		public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
			if (args.length == 0) {
				if (this.gfmHeadCommand.isTabComplete()) {
					if (!this.gfmHeadCommand.getTabCompleteArgs().isEmpty()) {
						return new ArrayList<>(this.gfmHeadCommand.getTabCompleteArgs().get(0));
					}
					if (this.gfmHeadCommand.isTabCompletePlayer()) {
						return sender.getServer().getOnlinePlayers().stream()
								.map(Player::getName)
								.collect(Collectors.toCollection(ArrayList::new));
					}
					if (this.gfmHeadCommand.getGfmSubCommands().isEmpty()) {
						return Collections.emptyList();
					}
					return this.gfmHeadCommand.getGfmSubCommands().stream()
							.filter(gfmSubCommand -> !gfmSubCommand.isPermissionBlockTabComplete() || sender.hasPermission(gfmSubCommand.getPermission()))
							.map(GfmSubCommand::getName)
							.collect(Collectors.toCollection(ArrayList::new));
				}
				return Collections.emptyList();
			}

			boolean isThereNextSubCommand = true;
			boolean isThereAnyCorrectSubCommand = true;
			GfmCommand lastSubCommand = this.gfmHeadCommand;
			String[] lastSubArgs = args;

			while(isThereNextSubCommand && isThereAnyCorrectSubCommand) {
				isThereAnyCorrectSubCommand = false;

				for (GfmSubCommand gfmSubCommand : lastSubCommand.getGfmSubCommands()) {
					if (!lastSubArgs[0].equalsIgnoreCase(gfmSubCommand.getName())) continue;

					String[] subArgs = Arrays.copyOfRange(lastSubArgs, 1, lastSubArgs.length);

					if (subArgs.length == 0) {
						if (gfmSubCommand.isTabComplete()) {
							if (!gfmSubCommand.getTabCompleteArgs().isEmpty()) {
								return new ArrayList<>(gfmSubCommand.getTabCompleteArgs().get(0));
							}
							if (gfmSubCommand.isTabCompletePlayer()) {
								return sender.getServer().getOnlinePlayers().stream()
										.map(Player::getName)
										.collect(Collectors.toCollection(ArrayList::new));
							}
							if (gfmSubCommand.getGfmSubCommands().isEmpty()) {
								return Collections.emptyList();
							}
							return gfmSubCommand.getGfmSubCommands().stream()
									.filter(subCommand -> !subCommand.isPermissionBlockTabComplete() || sender.hasPermission(subCommand.getPermission()))
									.map(GfmSubCommand::getName)
									.collect(Collectors.toCollection(ArrayList::new));
						}
						return Collections.emptyList();
					}

					isThereNextSubCommand = !gfmSubCommand.getGfmSubCommands().isEmpty();
					isThereAnyCorrectSubCommand = true;
					lastSubCommand = gfmSubCommand;
					lastSubArgs = subArgs;
				}
			}
			return Collections.emptyList();
		}
	}
}
