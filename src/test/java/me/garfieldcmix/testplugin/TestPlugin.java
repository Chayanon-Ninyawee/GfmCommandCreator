package me.garfieldcmix.testplugin;

import me.garfieldcmix.testplugin.commands.TestCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class TestPlugin extends JavaPlugin {
    public TestPlugin() {
        super();
    }

    protected TestPlugin(JavaPluginLoader loader, PluginDescriptionFile descriptionFile, File dataFolder, File file) {
        super(loader, descriptionFile, dataFolder, file);
    }

    @Override
    public void onEnable() {
        TestCommand.register(this);
    }

    @Override
    public void onDisable() {

    }

}
