package me.chengzhify.autoJoinVoiceGroup;

import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.JoinGroupEvent;
import de.maxhenkel.voicechat.api.events.LeaveGroupEvent;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

import static org.apache.logging.log4j.LogManager.getLogger;

public class VoicechatImpl implements VoicechatPlugin {
    public static VoicechatServerApi voiceServerApi;
    private static final AutoJoinVoiceGroup instance = AutoJoinVoiceGroup.getInstance();
    private static final boolean voiceLog = instance.getConfig().getBoolean("voice-group-console-log");
    @Override
    public String getPluginId() {
        return "AutoJoinVoiceGroup";
    }

    @Override
    public void initialize(VoicechatApi api) {

    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onVoiceServerStart);
    }

    private void onVoiceServerStart(VoicechatServerStartedEvent event) {
        voiceServerApi = event.getVoicechat();
        System.out.println(voiceServerApi);
        getLogger().info("[VoiceChat] API 已获取，可以创建语音频道了！");
    }

    public static UUID createGroup(String name, String team) {
        Group g = voiceServerApi.groupBuilder()
                .setPersistent(true)
                .setName(name + team) // The name of the group
                .setType(Group.Type.OPEN)
                .build();
        if (voiceLog) {
            getLogger().info("[VoiceGroup] " + name + team + " 语音组已创建");
        }
        return g.getId();
    }
}