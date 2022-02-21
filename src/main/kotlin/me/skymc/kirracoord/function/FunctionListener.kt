package me.skymc.kirracoord.function

import me.skymc.kirracoord.CoordTeleportEvent
import me.skymc.kirracoord.Loader
import me.skymc.kirracoord.getRegionByLoc
import org.bukkit.Bukkit
import taboolib.common.platform.event.SubscribeEvent

object FunctionListener {

    private val isWorldGuardPluginEnabled by lazy {
        Bukkit.getPluginManager().isPluginEnabled("WorldGuard")
    }

    @SubscribeEvent
    fun e(e: CoordTeleportEvent) {
        if (!isWorldGuardPluginEnabled) return
        val player = e.player
        val loc = player.location
        val region = getRegionByLoc(loc)
        if (region != null) {
            if (Loader.disableRegions.contains(region)) {
                e.isCancelled = true
            }
        }
    }
}