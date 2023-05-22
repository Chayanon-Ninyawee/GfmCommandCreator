package me.garfieldcmix.gfmcommandcreator.gfmcommand;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.*;
import java.util.stream.Collectors;

public abstract class GfmCommand {
	@Getter private final String name;
	@Getter private final String usage;
	@Getter private final boolean isTabComplete;
	@Getter private final List<Set<String>> tabCompleteArgs;
	@Getter	private final Permission permission;
	@Getter	private final String noPermissionMessage;
	@Getter	private final GfmCommandHandler gfmCommandHandler;
	@Getter private final Set<GfmSubCommand> gfmSubCommands;
	@Getter private final boolean isPermissionBlockGfmSubCommands;

	protected GfmCommand(builder builder) {
		this.name 								= builder.name;
		this.usage 								= builder.usage;
		this.isTabComplete						= builder.isTabComplete;
		this.tabCompleteArgs 					= builder.tabCompleteArgs;
		this.permission							= builder.permission;
		this.noPermissionMessage				= builder.noPermissionMessage;
		this.gfmCommandHandler					= builder.gfmCommandHandler;
		this.gfmSubCommands						= builder.gfmSubCommands;
		this.isPermissionBlockGfmSubCommands	= builder.isPermissionBlockGfmSubCommands;
	}

	public abstract static class builder {
		String name;
		String usage 							= ChatColor.translateAlternateColorCodes('&', "&cInvalid command!");
		boolean isTabComplete 					= true;
		List<Set<String>> tabCompleteArgs 		= new ArrayList<>();
		Permission permission;
		String noPermissionMessage 				= ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!");
		GfmCommandHandler gfmCommandHandler;
		Set<GfmSubCommand> gfmSubCommands		= new HashSet<>();
		boolean isPermissionBlockGfmSubCommands	= true;

		/**
		 * Must have this before build GfmSubCommand.
		 * @param name The thing that sender need to type to execute this command.
		 */
		public builder setName(final String name) {
			this.name = name;
			return this;
		}

		/**
		 * Default: {@code ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"); }
		 * @param usage The thing to send to sender when return false in GfmSubCommandHandler.
		 */
		public builder setUsage(final String usage) {
			this.usage = usage;
			return this;
		}

		/**
		 * Set all tabCompletes for this GfmSubCommand.
		 * @param tabCompleteArgs All tabCompletes that you want sender to see when tab.
		 */
		public builder setTabCompleteArgs(final List<Set<String>> tabCompleteArgs) {
			this.tabCompleteArgs = tabCompleteArgs;
			return this;
		}

		/**
		 * Add one tabComplete to this GfmSubCommand. If add another tabComplete, it will be for next arg.
		 * @param tabCompleteArg one tabComplete that you want sender to see when tab.
		 */
		public builder addTabCompleteArg(final Set<String> tabCompleteArg) {
			this.tabCompleteArgs.add(tabCompleteArg);
			return this;
		}

		/**
		 * Add one tabComplete to this GfmSubCommand. If add another tabComplete, it will be for next arg.
		 * @param tabCompleteArg one tabComplete that you want sender to see when tab.
		 */
		public builder addTabCompleteArg(final String... tabCompleteArg) {
			this.tabCompleteArgs.add(new HashSet<>(Arrays.asList(tabCompleteArg)));
			return this;
		}

		/**
		 * Clear all tabComplete to this GfmSubCommand.
		 */
		public builder clearTabCompleteArg() {
			this.tabCompleteArgs.clear();
			return this;
		}

		/**
		 * Default: {@code true } <br>
		 * **Will override isTabCompleteToPermission if set to false.**
		 * @param isTabComplete Show this GfmSubCommand in tabComplete.
		 */
		public builder isTabComplete(final boolean isTabComplete) {
			this.isTabComplete = isTabComplete;
			return this;
		}

		/**
		 * @param permission The permission sender needs to have to execute this GfmSubCommand.
		 */
		public builder setPermission(final String permission) {
			this.permission = new Permission(permission);
			return this;
		}

		/**
		 * Default: {@code ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"); }
		 * @param noPermissionMessage The message sender get when have no permission to use this GfmSubCommand.
		 */
		public builder setNoPermissionMessage(final String noPermissionMessage) {
			this.noPermissionMessage = noPermissionMessage;
			return this;
		}

		/**
		 * Use lambda expression for easier programming. <br>
		 * (sender, args) -> { your code }
		 * @param gfmCommandHandler The thing to run when sender successfully execute this GfmSubCommand.
		 */
		public builder setGfmCommandHandler(final GfmCommandHandler gfmCommandHandler) {
			this.gfmCommandHandler = gfmCommandHandler;
			return this;
		}

		/**
		 * Set all GfmSubCommands.
		 * @param gfmSubCommands All GfmSubCommands that you want to set.
		 */
		public builder setGfmSubCommands(final Set<GfmSubCommand> gfmSubCommands) {
			this.gfmSubCommands = gfmSubCommands;
			return this;
		}

		/**
		 * Add one GfmSubCommand.
		 * @param subCommand One GfmSubCommand that you want to add.
		 */
		public builder addGfmSubCommand(final GfmSubCommand subCommand) {
			this.gfmSubCommands.add(subCommand);
			return this;
		}

		/**
		 * Clear all GfmSubCommand to this GfmCommand.
		 */
		public builder clearGfmSubCommand() {
			this.gfmSubCommands.clear();
			return this;
		}

		/**
		 * Default: {@code true }
		 * @param isPermissionBlockGfmSubCommands Allow sender to execute GfmSubCommand of this GfmCommand
		 *                                              , even if sender has no permission to execute this GfmCommand.
		 */
		public builder isPermissionBlockGfmSubCommands(final boolean isPermissionBlockGfmSubCommands) {
			this.isPermissionBlockGfmSubCommands = isPermissionBlockGfmSubCommands;
			return this;
		}

		protected abstract GfmCommand __build();

		/**
		 * Use to build GfmCommand.
		 * @throws RuntimeException If you don't set name before build or if you have duplicate GfmSubCommand.
		 */
		public GfmCommand build() {
			List<String> gfmSubCommandName = this.gfmSubCommands.stream()
					.map(GfmCommand::getName)
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

			return __build();
		}
	}

	public void executeCommand(CommandSender sender, String[] args) {
		if (!this.getGfmCommandHandler().execute(sender, args)) {
			sender.sendMessage(this.getUsage());
		}
	}
}






