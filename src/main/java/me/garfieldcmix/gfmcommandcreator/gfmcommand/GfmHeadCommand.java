package me.garfieldcmix.gfmcommandcreator.gfmcommand;

public class GfmHeadCommand extends GfmCommand {
	protected GfmHeadCommand(builder builder) {
		super(builder);
	}

	public static class builder extends GfmCommand.builder {

		@Override
		protected GfmCommand __build() {
			return new GfmHeadCommand(this);
		}
	}
}
