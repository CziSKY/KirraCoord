package me.skymc.kirracoord

import org.bukkit.entity.Player

@Suppress("SpellCheckingInspection")
object KirraCoordAPI {

    fun tpCoord(player: Player, name: String): Boolean {
        val coord = Loader.getCoordByName(name) ?: return false
        return tpCoord(player, coord)
    }

    private fun tpCoord(player: Player, coord: Coord): Boolean {
        val isCancelled = CoordTeleportEvent(player, coord).call()
        if (isCancelled) {
            return false
        }
        player.teleport(coord.loc)
        return true
    }
}