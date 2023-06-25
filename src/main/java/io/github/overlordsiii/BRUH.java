package io.github.overlordsiii;

import io.github.overlordsiii.command.DeathLogCommand;
import io.github.overlordsiii.config.BRUHConfig;
import io.github.overlordsiii.deathlog.config.JsonConfigHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

public class BRUH implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger("BRUH");

	public static final BRUHConfig CONFIG;

	public static final ConfigManager<BRUHConfig> CONFIG_MANAGER;

	public static final JsonConfigHandler DEATH_LOG_CONFIG = new JsonConfigHandler(FabricLoader.getInstance().getConfigDir());

	static {
		CONFIG_MANAGER = (ConfigManager<BRUHConfig>) AutoConfig.register(BRUHConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(BRUHConfig.class).getConfig();
		DEATH_LOG_CONFIG.initialize();
	}

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			DeathLogCommand.register(dispatcher);
		});
	}
}
