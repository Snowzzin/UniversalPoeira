package me.snow.poeira.commands;

import me.snow.poeira.Terminal;
import me.snow.poeira.managers.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PoeiraCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores validos podem executar isso.");
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getLabel().equalsIgnoreCase("poeiras")) {
            if(args.length == 0) {
                p.sendMessage("§eSuas poeiras: §f" + Terminal.plugin.getPoeirasAPI().getPoeiras(p));
                return true;
            }

            if(args[0].equalsIgnoreCase("shop") || args[0].equalsIgnoreCase("loja")) {

                p.openInventory(new InventoryManager().storePoeira(p));
                p.sendMessage("§aLoja de Poeiras aberta com sucesso.");
            }

            if (args[0].equalsIgnoreCase("enviar") || args[0].equalsIgnoreCase("pay")) {

                Player target;
                Integer quantia;

                try {
                    target = Bukkit.getPlayerExact(args[1]);
                } catch (Throwable ignored) {
                    p.sendMessage("§cO jogador alvo não pode ser nullo");
                    return true;
                }

                try {
                    quantia = Integer.parseInt(args[2]);
                } catch (Throwable ignored) {
                    p.sendMessage("§cA quantia não pode ser nulla");
                    return true;
                }

                if(Terminal.plugin.getPoeirasAPI().getPoeiras(p) < quantia) {
                    p.sendMessage("§cVocê não possui essa quantidade de Poeiras.");
                    return true;
                }

                Terminal.plugin.getPoeirasAPI().removePoeiras(p, quantia);
                Terminal.plugin.getPoeirasAPI().addPoeiras(target, quantia);
                p.sendMessage("§eVocê enviou §f" + quantia + " §epoeiras para o jogador §f" + target.getName());
                target.sendMessage("§eVocê recebeu §f" + quantia + " §epoeiras do jogador §f" + p.getName());
            }

                if (args[0].equalsIgnoreCase("adicionar") || args[0].equalsIgnoreCase("add")) {
                    if (p.hasPermission(Terminal.plugin.getConfig().getString("Permissões.Admin"))) {

                        Player target;
                        Integer quantia;

                        try {
                            target = Bukkit.getPlayerExact(args[1]);
                        } catch (Throwable ignored) {
                            p.sendMessage("§cO jogador alvo não pode ser nullo");
                            return true;
                        }

                        try {
                            quantia = Integer.parseInt(args[2]);
                        } catch (Throwable ignored) {
                            p.sendMessage("§cA quantia não pode ser nulla");
                            return true;
                        }

                        Terminal.plugin.getPoeirasAPI().addPoeiras(target, quantia);
                        p.sendMessage("§eVocê adicionou §f" + quantia + " §epoeiras para o jogador §f" + target.getName());
                    }else{
                        p.sendMessage("§cVocê não possui permissão.");
                        return true;
                    }
                }

                if (args[0].equalsIgnoreCase("remover")) {
                    if (p.hasPermission(Terminal.plugin.getConfig().getString("Permissões.Admin"))) {

                        Player target;
                        Integer quantia;

                        try {
                            target = Bukkit.getPlayerExact(args[1]);
                        } catch (Throwable ignored) {
                            p.sendMessage("§cO jogador alvo não pode ser nullo");
                            return true;
                        }

                        try {
                            quantia = Integer.parseInt(args[2]);
                        } catch (Throwable ignored) {
                            p.sendMessage("§cA quantia não pode ser nulla");
                            return true;
                        }

                        Terminal.plugin.getPoeirasAPI().removePoeiras(target, quantia);
                        p.sendMessage("§eVocê removeu §f" + quantia + " §epoeiras do jogador §f" + target.getName());
                    }else{
                        p.sendMessage("§cVocê não possui permissão.");
                        return true;
                    }
                }
        }
        return false;
    }
}
