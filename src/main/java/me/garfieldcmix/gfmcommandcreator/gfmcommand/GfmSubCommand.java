package me.garfieldcmix.gfmcommandcreator.gfmcommand;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GfmSubCommand {
	@Getter private final String name;
	@Getter private final String usage;
	@Getter private final List<List<String>> tabCompleteArgs;
	@Getter private final boolean isTabCompleteEmpty;
	@Getter private final boolean isTabComplete;
	@Getter private final boolean isTabCompleteToPermission;
	@Getter private final Permission permission;
	@Getter private final boolean isPermissionNull;
	@Getter private final String noPermissionMessage;
	@Getter private final GfmCommandHandler gfmCommandHandler;

	private GfmSubCommand(Builder builder) {
		this.name = builder.name;
		this.usage = builder.usage;
		this.tabCompleteArgs = builder.tabCompleteArgs;
		this.isTabCompleteEmpty = builder.tabCompleteArgs.size() == 0;
		this.isTabComplete = builder.isTabComplete;
		this.isTabCompleteToPermission = builder.isTabCompleteToPermission;
		this.permission = builder.permission;
		this.isPermissionNull = builder.permission == null;
		this.noPermissionMessage = builder.noPermissionMessage;
		this.gfmCommandHandler = builder.gfmSubCommandHandler;
	}

	public static class Builder {
		String name;
		String usage = ChatColor.translateAlternateColorCodes('&', "&cInvalid command!");
		List<List<String>> tabCompleteArgs = new ArrayList<>();
		boolean isTabComplete = true;
		boolean isTabCompleteToPermission = false;
		Permission permission;
		String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!");
		GfmCommandHandler gfmSubCommandHandler = (sender, args) -> true;

		/**
		 * Must have this before build GfmSubCommand.
		 * @param name The thing that sender need to type to execute this command.
		 */
		public Builder setName(final String name) {
			this.name = name;
			return this;
		}

		/**
		 * Default: {@code ChatColor.translateAlternateColorCodes('&', "&cInvalid command!"); }
		 * @param usage The thing to send to sender when return false in GfmSubCommandHandler.
		 */
		public Builder setUsage(final String usage) {
			this.usage = usage;
			return this;
		}

		/**
		 * Set all tabCompletes for this GfmSubCommand.
		 * @param tabCompleteArgs All tabCompletes that you want sender to see when tab.
		 */
		public Builder setTabCompleteArgs(final List<List<String>> tabCompleteArgs) {
			this.tabCompleteArgs = tabCompleteArgs;
			return this;
		}

		/**
		 * Add one tabComplete to this GfmSubCommand. If add another tabComplete, it will be for next arg.
		 * @param tabCompleteArg one tabComplete that you want sender to see when tab.
		 */
		public Builder addTabCompleteArg(final List<String> tabCompleteArg) {
			this.tabCompleteArgs.add(tabCompleteArg);
			return this;
		}

		/**
		 * Add one tabComplete to this GfmSubCommand. If add another tabComplete, it will be for next arg.
		 * @param tabCompleteArg one tabComplete that you want sender to see when tab.
		 */
		public Builder addTabCompleteArg(final String... tabCompleteArg) {
			this.tabCompleteArgs.add(Arrays.asList(tabCompleteArg));
			return this;
		}

		/**
		 * Default: {@code true } <br>
		 * **Will override isTabCompleteToPermission if set to false.**
		 * @param isTabComplete Show this GfmSubCommand in tabComplete.
		 */
		public Builder isTabComplete(final boolean isTabComplete) {
			this.isTabComplete = isTabComplete;
			return this;
		}

		/**
		 * Default: {@code false }
		 * @param isTabCompleteToPermission Show this GfmSubCommand in tabComplete if sender has permission.
		 */
		public Builder isTabCompleteToPermission(final boolean isTabCompleteToPermission) {
			this.isTabCompleteToPermission = isTabCompleteToPermission;
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
		 * Default: {@code ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission to use this command!"); }
		 * @param noPermissionMessage The message sender get when have no permission to use this GfmSubCommand.
		 */
		public Builder setNoPermissionMessage(final String noPermissionMessage) {
			this.noPermissionMessage = noPermissionMessage;
			return this;
		}

		/**
		 * Use lambda expression for easier programming. <br>
		 * (sender, args) -> { your code }
		 * @param gfmSubCommandHandler The thing to run when sender successfully execute this GfmSubCommand.
		 */
		public Builder setGfmSubCommandHandler(final GfmCommandHandler gfmSubCommandHandler) {
			this.gfmSubCommandHandler = gfmSubCommandHandler;
			return this;
		}

		/**
		 * Use to build GfmSubCommand.
		 * @throws RuntimeException If you don't set name before build.
		 */
		public GfmSubCommand build() {
			if (this.name == null) {
				throw new RuntimeException("There is no name for GfmCommand!");
			}

			return new GfmSubCommand(this);
		}
	}
}