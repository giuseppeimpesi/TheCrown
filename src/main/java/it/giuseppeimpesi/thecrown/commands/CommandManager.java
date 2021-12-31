package it.giuseppeimpesi.thecrown.commands;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.giuseppeimpesi.thecrown.commands.subcommands.*;
import it.giuseppeimpesi.thecrown.manager.GameManager;
import it.giuseppeimpesi.thecrown.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.List;
import java.util.Map;

public class CommandManager implements CommandExecutor, TabCompleter {

    private GameManager manager;
    private Map<String, SubCommand> subCommands;

    public CommandManager(GameManager manager){
        this.manager = manager;
        subCommands = Maps.newHashMap();

        subCommands.put("start", new StartCommand(manager));
        subCommands.put("stop", new StopCommand(manager));
        subCommands.put("info", new InfoCommand(manager));
        subCommands.put("list", new ListCommand(manager));
        subCommands.put("join", new JoinCommand(manager));
        subCommands.put("leave", new LeaveCommand(manager));
        // manager.getPlayerConfig().reloadConfiguration();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo i giocatori possono utilizzare questo comando.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Utils.toColor("&7&m--------------------------\n"
                    + "&6&l• &7Running &6&lTheCrown &7by zFavourite\n\n"
                    + "&6&l> &e/thecrown start\n"
                    + "&6&l> &e/thecrown stop\n"
                    + "&6&l> &e/thecrown list\n"
                    + "&6&l> &e/thecrown info\n"
                    + "&6&l> &e/thecrown join\n"
                    + "&6&l> &e/thecrown leave\n"
                    + "&7&m--------------------------"));
            return true;
        }

        if (!subCommands.containsKey(args[0].toLowerCase())) {
            player.sendMessage(Utils.toColor("&6&lTheCrown &7Comando sconosciuto."));
            return false;
        }

        SubCommand subCommand = subCommands.get(args[0].toLowerCase());
        if (!player.hasPermission(subCommand.getPermission())) {
            player.sendMessage(Utils.toColor("&6&lTheCrown &7Permessi insufficienti."));
            return false;
        }

        subCommand.perform(player, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length > 1) return null;
        List<String> completitions = Lists.newArrayList();
        StringUtil.copyPartialMatches(args[0], subCommands.keySet(), completitions);
        return completitions;
    }

}
