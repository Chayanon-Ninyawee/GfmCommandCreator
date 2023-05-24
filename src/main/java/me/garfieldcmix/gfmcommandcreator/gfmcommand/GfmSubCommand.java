package me.garfieldcmix.gfmcommandcreator.gfmcommand;

import lombok.Getter;
import lombok.Setter;

public class GfmSubCommand extends GfmCommand {
	@Getter private final boolean isPermissionBlockTabComplete;

	protected GfmSubCommand(builder builder) {
		super(builder);
		this.isPermissionBlockTabComplete = builder.isPermissionBlockTabComplete;
	}

	public static class builder extends GfmCommand.builder {
		boolean isPermissionBlockTabComplete 	= false;

		/**
		 * Default: {@code false }
		 * @param isPermissionBlockTabComplete Show this GfmSubCommand in tabComplete if sender has permission.
		 */
		public builder isPermissionBlockTabComplete(final boolean isPermissionBlockTabComplete) {
			this.isPermissionBlockTabComplete = isPermissionBlockTabComplete;
			return this;
		}

		@Override
		protected GfmCommand __build() {
			return new GfmSubCommand(this);
		}
	}
}
