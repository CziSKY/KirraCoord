package me.skymc.kirracoord

import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

@Suppress("SpellCheckingInspection")
class CoordTeleportEvent(val player: Player, val coord: Coord) : BukkitProxyEvent()