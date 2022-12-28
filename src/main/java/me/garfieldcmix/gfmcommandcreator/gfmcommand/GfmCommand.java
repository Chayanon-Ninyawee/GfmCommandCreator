package me.garfieldcmix.gfmcommandcreator.gfmcommand;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

import java.util.*;
import java.util.stream.Collectors;

public class GfmCommand {
	@Getter private final String name;
	@Getter private final String usage;
	@Getter private final List<List<String>> tabCompleteArgs;
	@Getter private final boolean isTabCompleteEmpty;
	@Getter private final boolean isTabComplete;
	@Getter private final Permission permission;
	@Getter private final boolean isPermissionNull;
	@Getter private final String noPermissionMessage;
	@Getter private final boolean isPermissionBlockGfmSubCommands;
	@Getter private final GfmCommandHandler gfmCommandHandler;
	@Getter private final List<GfmSubCommand> gfmSubCommands;


	private GfmCommand(Builder builder) {
		this.name = builder.name;
		this.usage = builder.usage;
		this.tabCompleteArgs = builder.tabCompleteArgs;
		this.isTabCompleteEmpty = builder.tabCompleteArgs.size() == 0;
		this.isTabComplete = builder.isTabComplete;
		this.permission = builder.permission;
		this.isPermissionNull = builder.permission == null;
		this.noPermissionMessage = builder.noPermissionMessage;
		this.isPermissionBlockGfmSubCommands = builder.isPermissionBlockGfmSubCommands;
		this.gfmCommandHandler = builder.gfmCommandHandler;
		this.gfmSubCommands = builder.gfmSubCommands;
	}

	public static class Builder {
		String name;
		String usage = ChatColor.translateAlternateColorCodes('&', "&cInvalid command!");
		List<List<String>> tabCompleteArgs = new ArrayList<>();
		boolean isTabComplete = true;
		Permission permission;
		String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!");
		boolean isPermissionBlockGfmSubCommands = true;
		GfmCommandHandler gfmCommandHandler = (sender, args) -> true;
		List<GfmSubCommand> gfmSubCommands = new ArrayList<>();

		/**
		 * Must have this before build GfmCommand.
		 * @param name The thing that sender need to type to execute this command.
		 */
		public Builder setName(final String name) {
			this.name = name;
			return this;
		}

		/**
		 * Default: {@code ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"); }
		 * @param usage The thing to send to sender when return false in GfmCommandHandler.
		 */
		public Builder setUsage(final String usage) {
			this.usage = usage;
			return this;
		}

		/**
		 * Set all tabCompletes for this GfmCommand. <br>
		 * **This will override tabComplete for GfmSubCommand**
		 * @param tabCompleteArgs All tabCompletes that you want sender to see when tab.
		 */
		public Builder setTabCompleteArgs(final List<List<String>> tabCompleteArgs) {
			this.tabCompleteArgs = tabCompleteArgs;
			return this;
		}

		/**
		 * Add one tabComplete to this GfmCommand. If add another tabComplete, it will be for next arg. <br>
		 * **This will override tabComplete for GfmSubCommand**
		 * @param tabCompleteArg one tabComplete that you want sender to see when tab.
		 */
		public Builder addTabCompleteArg(final List<String> tabCompleteArg) {
			this.tabCompleteArgs.add(tabCompleteArg);
			return this;
		}

		/**
		 * Add one tabComplete to this GfmCommand. If add another tabComplete, it will be for next arg. <br>
		 * **This will override tabComplete for GfmSubCommand**
		 * @param tabCompleteArg one tabComplete that you want sender to see when tab.
		 */
		public Builder addTabCompleteArg(final String... tabCompleteArg) {
			this.tabCompleteArgs.add(Arrays.asList(tabCompleteArg));
			return this;
		}

		/**
		 * Default: {@code true } <br>
		 * @param isTabComplete Show this GfmCommand in tabComplete.
		 */
		public Builder isTabComplete(final boolean isTabComplete) {
			this.isTabComplete = isTabComplete;
			return this;
		}

		/**
		 * Default: {@code true }
		 * @param isPermissionBlockGfmSubCommands Allow sender to execute GfmSubCommand of this GfmCommand
		 *                                              , even if sender has no permission to execute this GfmCommand.
		 */
		public Builder isPermissionBlockGfmSubCommands(final boolean isPermissionBlockGfmSubCommands) {
			this.isPermissionBlockGfmSubCommands = isPermissionBlockGfmSubCommands;
			return this;
		}

		/**
		 * Default: {@code ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"); }
		 * @param noPermissionMessage The message sender get when have no permission to use this GfmSubCommand.
		 */
		public Builder setNoPermissionMessage(final String noPermissionMessage) {
			this.noPermissionMessage = noPermissionMessage;
			return this;
		}

		/**
		 * @param permission The permission sender needs to have to execute this GfmSubCommand.
		 */
		public Builder setPermission(final String permission) {
			this.permission = new Permission(permission);
			return this;
		}

		/**
		 * Use lambda expression for easier programming. <br>
		 * (sender, args) -> { your code }
		 * @param gfmCommandHandler The thing to run when sender successfully execute this GfmSubCommand.
		 */
		public Builder setGfmCommandHandler(final GfmCommandHandler gfmCommandHandler) {
			this.gfmCommandHandler = gfmCommandHandler;
			return this;
		}

		/**
		 * Set all GfmSubCommands.
		 * @param gfmSubCommands All GfmSubCommands that you want to set.
		 */
		public Builder setGfmSubCommands(final List<GfmSubCommand> gfmSubCommands) {
			this.gfmSubCommands = gfmSubCommands;
			return this;
		}

		/**
		 * Add one GfmSubCommand.
		 * @param subCommand One GfmSubCommand that you want to add.
		 */
		public Builder addGfmSubCommand(final GfmSubCommand subCommand) {
			this.gfmSubCommands.add(subCommand);
			return this;
		}

		/**
		 * Use to build GfmCommand.
		 * @throws RuntimeException If you don't set name before build.
		 */
		public GfmCommand build() {
			List<String> gfmSubCommandName = this.gfmSubCommands.stream()
					.map(GfmSubCommand::getName)
					.collect(Collectors.toCollection(ArrayList::new));
			gfmSubCommandName = gfmSubCommandName.stream()
					.map(String::toLowerCase)
					.collect(Collectors.toCollection(ArrayList::new));
			Set<String> nonDuplicateGfmSubCommandName = new HashSet<>(gfmSubCommandName);

			if (gfmSubCommandName.size() > nonDuplicateGfmSubCommandName.size()) {
				throw new RuntimeException("There is duplicate GfmSubCommand!");
			}

			if (this.name == null) {
				throw new RuntimeException("There is no name for GfmCommand!");
			}

			return new GfmCommand(this);
		}
	}
}
