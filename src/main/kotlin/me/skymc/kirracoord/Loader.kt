package me.skymc.kirracoord

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.configuration.util.getLocation
import taboolib.platform.util.toBukkitLocation

@Suppress("SpellCheckingInspection")
object Loader {

    val coords = mutableListOf<Coord>()

    val disableRegions = mutableListOf<String>()

    @Awake(LifeCycle.ENABLE)
    fun i() {
        coords.clear()
        disableRegions.clear()
        val section = KirraCoord.coordFile.getKeys(false)
        section.forEach {
            val id = it
            val loc = KirraCoord.coordFile.getLocation("$it.loc") ?: return@forEach
            coords += Coord(id, loc.toBukkitLocation())
            printToConsole("已加载点: $id")
        }
        KirraCoord.config.getStringList("disable-regions").forEach {
            disableRegions += it
            printToConsole("已加载禁用区域: $it")
        }
    }

    fun getCoordByName(name: String) = coords.find { it.name == name }

    fun addCoord(coord: Coord) {
        coords += coord
        KirraCoord.coordFile["${coord.name}.loc"] = coord.loc
        KirraCoord.coordFile.saveToFile(KirraCoord.coordFile.file)
        printToConsole("已添加点: ${coord.name}")
    }

    fun delCoord(coord: Coord) {
        coords -= coord
        KirraCoord.coordFile["${coord.name}.loc"] = null
        KirraCoord.coordFile.saveToFile(KirraCoord.coordFile.file)
        printToConsole("已删除点: ${coord.name}")
    }
}