package me.skymc.kirracoord

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin
import java.io.File

@Suppress("SpellCheckingInspection", "MemberVisibilityCanBePrivate")
object KirraCoord : Plugin() {

    // test

    @Config
    lateinit var config: Configuration
        private set

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }

    val coordFile by lazy {
        Configuration.loadFromFile(File(plugin.dataFolder, "coords.yml"))
    }
}