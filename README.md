# AutoJoinVoiceGroup

一个基于 Paper 的服务器插件：当玩家加入服务器后，自动尝试将其加入 Simple Voice Chat 的预设语音组。

## 功能说明

- 服务器启动后自动注册 Voice Chat API 插件。
- 在语音服务可用时创建一个持久化开放语音组。
- 玩家加入服务器后延迟执行自动入组，避免玩家尚未完成语音连接时加入失败。
- 在玩家聊天栏提示入组成功或失败状态。

## 运行环境

- Java 17
- Maven 3.8+
- Paper `1.21.4-R0.1-SNAPSHOT`（编译依赖）
- 服务端需安装 `Simple Voice Chat`（插件依赖名：`voicechat`）

## 构建

在项目根目录执行：

```bash
mvn clean package
```

构建完成后，可用插件位于：

- `target/autojoinvoicegroup-<version>.jar`

说明：`target/original-*.jar` 为 Shade 过程中的中间产物，通常不用于实际部署。

## 安装与使用

1. 将生成的插件 Jar 放入服务端 `plugins/` 目录。
2. 确保服务端已安装并启用 `Simple Voice Chat`。
3. 启动或重启服务器。
4. 玩家加入后插件会自动尝试将其加入默认语音组。

## 自动发布（GitHub Actions）

仓库已包含工作流：`.github/workflows/release.yml`。

触发方式：

- 当推送任意 tag 到 GitHub 时自动触发。

工作流会执行：

1. 启用 JDK 17 构建 Maven 项目。
2. 创建 GitHub Release。
3. 上传 `target/autojoinvoicegroup-*.jar`（并排除 `target/original-*.jar`）。

## 项目结构

- `src/main/java/me/chengzhify/autoJoinVoiceGroup/AutoJoinVoiceGroup.java`：主插件入口。
- `src/main/java/me/chengzhify/autoJoinVoiceGroup/VoicechatImpl.java`：Voice Chat API 对接与语音组创建。
- `src/main/java/me/chengzhify/autoJoinVoiceGroup/PlayerJoinListener.java`：玩家加入事件监听与自动入组逻辑。
- `src/main/resources/plugin.yml`：Paper 插件元数据配置。

## 许可证

本项目采用 GNU General Public License v3.0，详见 `LICENSE`。


