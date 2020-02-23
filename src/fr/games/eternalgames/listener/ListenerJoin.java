package fr.games.eternalgames.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.games.eternalgames.EternalGames;
import fr.games.eternalgames.GStates;
import fr.games.eternalgames.tasks.GAutoStart;

public class ListenerJoin implements Listener {
	
	private EternalGames main;
	
	public ListenerJoin(EternalGames main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.sendTitle("Welcome", "HunterGames made by EternalRat", 10, 80, 20);
		Location spawn = new Location(player.getWorld(), 91.815, 4, -37.031, 90f, -0.9f);
		player.teleport(spawn);
		player.getInventory().clear();
		player.setFoodLevel(20);
		player.setHealth(20);
		
		int count = 20 - main.getPlayers().size();
		
		Bukkit.broadcastMessage(count + "players missing for the game to start.");
		
		if (!main.isState(GStates.WAITING)) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage("The game already started.");
			e.setJoinMessage(null);
			return;
		}
		
		if (!main.getPlayers().contains(player)) {
			main.getPlayers().add(player);
			main.frozen.add(player);
		}
		player.setGameMode(GameMode.ADVENTURE);
		e.setJoinMessage("§7[§cEternalGames§7]§r " + player.getName() + " has joined the game. <" + main.getPlayers().size() + "/10>");
		
		if (main.isState(GStates.WAITING) && main.getPlayers().size() >= 10) {
			//TP MANQUANT
			GAutoStart start = new GAutoStart(main);
			start.runTaskTimer(main, 0, 20);
			main.setState(GStates.PREPARATION);
		}
	}
}