package me.garfieldcmix.testplugin.commands;

import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmHeadCommand;
import me.garfieldcmix.gfmcommandcreator.gfmcommand.GfmSubCommand;

public class TestHandler {
	public static GfmHeadCommand gfmHandler() {
		return (GfmHeadCommand) new GfmHeadCommand.builder()
				.setName("gfmhandler")
				.setUsage("Head command handler test")
				.setGfmCommandHandler((commandSender, strings) -> {
					commandSender.sendMessage("Head Handler works!");
					return false;
				})
				.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
						.setName("sub1")
						.setUsage("Sub1 command handler test")
						.setGfmCommandHandler((commandSender, strings) -> {
							commandSender.sendMessage("Sub1 Handler works!");
							return false;
						})
						.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
								.setName("sub2")
								.setUsage("Sub2 command handler test")
								.setGfmCommandHandler((commandSender, strings) -> {
									commandSender.sendMessage("Sub2 Handler works!");
									return false;
								})
								.addGfmSubCommand((GfmSubCommand) new GfmSubCommand.builder()
										.setName("sub3")
										.setUsage("Sub3 command handler test")
										.setGfmCommandHandler((commandSender, strings) -> {
											commandSender.sendMessage("Sub3 Handler works!");
											return false;
										})
										.build()
								)
								.build()
						)
						.build()
				)
				.build();
	}
}
