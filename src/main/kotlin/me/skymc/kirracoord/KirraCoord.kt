package me.skymc.kirracoord

import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin
import java.io.File

@Suppress("SpellCheckingInspection", "MemberVisibilityCanBePrivate")
object KirraCoord : Plugin() {

    @Config
    lateinit var config: Configuration
        private set

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }

    val coordFile by lazy {
        val file = File(plugin.dataFolder, "coords.yml")
        if (!file.exists()) {
            file.createNewFile()
            Configuration.loadFromFile(file)
        } else {
            Configuration.loadFromFile(file)
        }
    }
}