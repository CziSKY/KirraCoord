package me.skymc.kirracoord

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.*
import taboolib.expansion.createHelper
import taboolib.platform.util.sendLang

@Suppress("SpellCheckingInspection")
@CommandHeader(name = "KirraCoord", aliases = ["coord", "coords"])
object Commands {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val set = subCommand {
        dynamic(commit = "coordName") {
            execute<Player> { sender, _, context ->
                suggestion<Player> { _, _ ->
                    Loader.coords.map { it.name }
                }
                if (Loader.getCoordByName(context) != null) {
                    sender.sendLang("command-coord-already-exists")
                    return@execute
                }
                val coord = Coord(context, sender.location)
                Loader.addCoord(coord)
                sender.sendLang("command-create-succ", context)
            }
        }
        execute<Player> { sender, _, _ ->
            sender.sendLang("command-args-is-empty")
        }
        execute<CommandSender> { sender, _, _ ->
            sender.sendLang("command-wrong-type-sender")
        }
    }

    @CommandBody
    val del = subCommand {
        dynamic(commit = "coordName") {
            suggestion<Player> { _, _ ->
                Loader.coords.map { it.name }
            }
            execute<Player> { sender, _, context ->
                val coord = Loader.getCoordByName(context)
                if (coord == null) {
                    sender.sendLang("command-coord-not-exists")
                    return@execute
                }
                Loader.delCoord(coord)
                sender.sendLang("command-del-succ", context)
            }
        }
        execute<Player> { sender, _, _ ->
            sender.sendLang("command-arg-is-empty")
        }
        execute<CommandSender> { sender, _, _ ->
            sender.sendLang("command-wrong-type-sender")
        }
    }

    @CommandBody
    val list = subCommand {
        execute<CommandSender> { sender, _, _ ->
            val list = Loader.coords.map { it.name }
            sender.sendLang("command-list-coord", list, list.size)
        }
    }

    @CommandBody(aliases = ["tp"])
    val teleport = subCommand {
        dynamic(commit = "coordName") {
            suggestion<Player> { _, _ ->
                Loader.coords.map { it.name }
            }
            execute<Player> { sender, _, argument ->
                val coord = Loader.getCoordByName(argument)
                if (coord == null) {
                    sender.sendLang("command-coord-not-exists")
                    return@execute
                }
                sender.teleport(coord.loc)
                sender.sendLang("command-teleport-succ", sender.name, argument)
            }
            dynamic("playerName") {
                suggestion<CommandSender> { _, _ ->
                    Bukkit.getOnlinePlayers().map { it.name }
                }
                execute<CommandSender> { sender, context, _ ->
                    val str = context.get(1)
                    val coord = Loader.getCoordByName(context.get(1))
                    if (coord == null) {
                        sender.sendLang("command-coord-not-exists")
                        return@execute
                    }
                    val player = Bukkit.getPlayer(context.get(2))
                    if (player == null) {
                        sender.sendLang("command-player-not-exists")
                        return@execute
                    }
                    player.teleport(coord.loc)
                    sender.sendLang("command-teleport-succ", player, str)
                }
            }
        }
    }

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, _, _ ->
            Loader.i()
            sender.sendLang("command-reload-succ")
        }
    }
}