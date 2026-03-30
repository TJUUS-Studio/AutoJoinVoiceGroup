package me.chengzhify.autoJoinVoiceGroup;

import de.maxhenkel.voicechat.api.VoicechatConnection;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(AutoJoinVoiceGroup.getInstance(), () -> {
            event.getPlayer().sendMessage("§e§lTJUUS WEEKLY §8» §7正在尝试将你连接至全局语音频道...");
        }, 20L); // 延迟3秒执行，确保玩家已连接到语音服务器
        Bukkit.getScheduler().runTaskLaterAsynchronously(AutoJoinVoiceGroup.getInstance(), () -> {
            VoicechatConnection connection = VoicechatImpl.voiceServerApi.getConnectionOf(event.getPlayer().getUniqueId());
            if (connection == null) {
                event.getPlayer().sendMessage("§e§lTJUUS WEEKLY §8» §c连接失败! 请检查你的 SimpleVoiceChat 模组是否正确启用!");
                return;
            }
            connection.setGroup(VoicechatImpl.voiceServerApi.getGroup(AutoJoinVoiceGroup.uuid));
            event.getPlayer().sendMessage("§e§lTJUUS WEEKLY §8» §a你已成功连接至全局语音频道!");
        }, 20L * 3L); // 延迟3秒执行，确保玩家已连接到语音服务器
    }
}
