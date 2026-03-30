package me.chengzhify.autoJoinVoiceGroup;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.UUID;

public final class AutoJoinVoiceGroup extends JavaPlugin {
    public static UUID uuid = UUID.randomUUID();
    private static AutoJoinVoiceGroup instance;
    public static final Logger LOGGER = LogManager.getLogger("AutoJoinVoiceGroup");
    @Nullable
    private VoicechatImpl voicechatPlugin;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        if (!getServer().getPluginManager().isPluginEnabled("voicechat")) {
            getLogger().severe("voicechat not found! Disabling...");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);
            if (service != null) {
                voicechatPlugin = new VoicechatImpl();
                service.registerPlugin(voicechatPlugin);
                LOGGER.info("Successfully registered voicegroup plugin");
            } else {
                LOGGER.info("Failed to register voicegroup plugin");
            }
        }
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            uuid = VoicechatImpl.createGroup("全局", "语音频道");
        }, 60L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (voicechatPlugin != null) {
            getServer().getServicesManager().unregister(voicechatPlugin);
        }
        instance = null;
    }

    public static AutoJoinVoiceGroup getInstance() {
        return instance;
    }
}
