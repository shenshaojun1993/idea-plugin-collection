plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.intellij.platform")
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2025.1")
        instrumentationTools()
    }
}

intellijPlatform {
    pluginConfiguration {
        id = "com.ssj.copypath"
        name = "Copy Path with Line Number"
        version = "1.0.0"
        description = """
            快速复制当前文件路径和选中行号到剪贴板。
            支持多种格式：完整路径、相对路径、带行号、不带行号。
        """.trimIndent()
        vendor {
            name = "SSJ"
        }
        ideaVersion {
            sinceBuild = "251"
            untilBuild = provider { null }
        }
    }
}

tasks {
    buildSearchableOptions {
        enabled = false
    }
}
