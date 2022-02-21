@file:Suppress("SpellCheckingInspection")

package me.skymc.kirracoord

import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.bukkit.Bukkit
import org.bukkit.Location

fun printToConsole(str: String) = Bukkit.getConsoleSender().sendMessage("[KirraCoord] $str")

fun getAllRegions(): List<ProtectedRegion> {
    return mutableListOf<ProtectedRegion>().also { regionList ->
        WorldGuard.getInstance().platform.regionContainer.loaded.forEach {
            regionList += it.regions.values
        }
    }
}

fun getRegionByLoc(loc: Location): String? {
    getAllRegions().forEach {
        if (it.contains(loc.blockX, loc.blockY, loc.blockZ)) return it.id
    }
    return null
}
