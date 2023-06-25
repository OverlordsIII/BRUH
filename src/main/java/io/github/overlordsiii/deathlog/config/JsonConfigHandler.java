package io.github.overlordsiii.deathlog.config;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import io.github.overlordsiii.BRUH;
import io.github.overlordsiii.deathlog.DeathLogEntry;

public class JsonConfigHandler {
    public List<DeathLogEntry> entries = new ArrayList<>();

    final Path deathLogEntriesPath;

    private final static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .create();

    public JsonConfigHandler(Path targetPath) {
        this.deathLogEntriesPath = targetPath.resolve("deathlog.json");
    }

    public void load() throws IOException {
        if (!Files.exists(deathLogEntriesPath)) {
            save();
        }
        JsonArray array = JsonParser.parseReader(Files.newBufferedReader(deathLogEntriesPath)).getAsJsonArray();

        for (JsonElement jsonElement : array) {
            JsonObject object = jsonElement.getAsJsonObject();

            DeathLogEntry entry = GSON.fromJson(object, DeathLogEntry.class);
            if (!entries.contains(entry)) {
                entries.add(entry);
            }
        }
    }

    public void initialize() {
        try {
            load();
            save();
        } catch (IOException e) {
            BRUH.LOGGER.error("Error while initializing user configs for server!");
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            save();
            load();
        } catch (IOException e) {
            BRUH.LOGGER.error("Error while reloading user configs for server!");
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        JsonArray array = new JsonArray();

        for (DeathLogEntry entry : entries) {
            JsonObject object = GSON.toJsonTree(entry).getAsJsonObject();

            array.add(object);
        }

        Files.writeString(deathLogEntriesPath, GSON.toJson(array));
    }




}
