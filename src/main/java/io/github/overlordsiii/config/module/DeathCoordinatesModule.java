package io.github.overlordsiii.config.module;

import io.github.overlordsiii.api.Module;
import io.github.overlordsiii.config.DeathCoordinateMessageType;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class DeathCoordinatesModule implements Module, ConfigData {

	@Comment("Default whispers the coordinates to the user after they respawn. Can be set to BROADCAST to have the message sent to whole server")
	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	public DeathCoordinateMessageType msgType = DeathCoordinateMessageType.WHISPER;

	public boolean enabled = true;

	@Override
	public String getName() {
		return "Death Coordinates";
	}
}
