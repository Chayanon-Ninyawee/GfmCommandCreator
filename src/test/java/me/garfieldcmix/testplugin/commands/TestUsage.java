package me.garfieldcmix.testplugin.commands;

import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmHeadCommand;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmSubCommand;

public class TestUsage {
	public static GfmHeadCommand gfmUsage() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
				.setName("gfmusage")
				.setUsage("Head Usage works!")
				.setGfmCommandHandler((commandSender, strings) -> false)
				.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
						.setName("sub1")
						.setUsage("Sub1 Usage works!")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub2")
								.setUsage("Sub2 Usage works!")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub3")
										.setUsage("Sub3 Usage works!")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.build()
								)
								.build()
						)
						.build()
				)
				.build();
	}

	public static GfmHeadCommand gfmDefaultUsage() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
				.setName("gfmdefaultusage")
				.setGfmCommandHandler((commandSender, strings) -> false)
				.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
						.setName("sub1")
						.setGfmCommandHandler((commandSender, strings) -> false)
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub2")
								.setGfmCommandHandler((commandSender, strings) -> false)
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub3")
										.setGfmCommandHandler((commandSender, strings) -> false)
										.build()
								)
								.build()
						)
						.build()
				)
				.build();
	}
}
