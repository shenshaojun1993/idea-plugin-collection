# IDEA Plugin Collection

IntelliJ IDEA 插件集合。

## 插件列表

### [copy-path-plugin](./copy-path-plugin)

快速复制当前文件路径和选中行号到剪贴板。

**功能特性：**
- 复制完整路径和行号（`Ctrl+Alt+Shift+C`）
- 复制仅文件路径
- 复制相对路径和行号
- 复制仅相对路径
- 复制后显示通知气泡

**输出格式：**
```
C:/path/to/file.ts:L5-L10
```

## 开发环境

- JDK 17+
- IntelliJ IDEA 2025.1+

## 构建

```bash
# 构建所有插件
./gradlew build

# 构建指定插件
./gradlew :copy-path-plugin:buildPlugin

# 运行测试 IDEA 实例
./gradlew :copy-path-plugin:runIde
```

## 安装

1. 构建插件：`./gradlew :copy-path-plugin:buildPlugin`
2. 在 `copy-path-plugin/build/distributions/` 目录找到 zip 文件
3. IDEA → Settings → Plugins → ⚙️ → Install Plugin from Disk → 选择 zip

## 添加新插件

1. 创建新目录，如 `new-plugin/`
2. 在 `settings.gradle.kts` 中添加 `include("new-plugin")`
3. 参考 `copy-path-plugin` 的结构创建配置文件
