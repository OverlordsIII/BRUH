package io.github.overlordsiii;

import io.github.overlordsiii.config.BRUHConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class BRUH implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger("BRUH");

	public static final BRUHConfig CONFIG;

	static {
		AutoConfig.register(BRUHConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(BRUHConfig.class).getConfig();
	}

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {

	}
}
