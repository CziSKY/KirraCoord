package me.skymc.kirracoord

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

@Suppress("SpellCheckingInspection")
object Loader {

    val coords = mutableListOf<Coord>()

    @Awake(LifeCycle.ENABLE)
    fun i() {
        coords.clear()
        val section = KirraCoord.coordFile.getKeys(false)
        section.forEach {
            val id = it
            val loc = KirraCoord.coordFile.getString("$it.loc") ?: return@forEach
            coords += Coord(id, loc.parseToLoc() ?: return@forEach)
            printToConsole("已加载点: $id")
        }
    }

    fun getCoordByName(name: String) = coords.find { it.name == name }

    fun addCoord(coord: Coord) {
        coords += coord
        KirraCoord.coordFile["${coord.name}.loc"] = coord.loc.parseToString()
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