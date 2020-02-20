package fr.games.eternalgames.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
		player.getInventory().clear();
		player.setFoodLevel(20);
		player.setHealth(20);
		
		if (!main.isState(GStates.WAITING)) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage("The game already started.");
			return;
		}
		
		if (!main.getPlayers().contains(player)) main.getPlayers().add(player);
		player.setGameMode(GameMode.ADVENTURE);
		
		if (main.isState(GStates.WAITING) && main.getPlayers().size() == 20) {
			GAutoStart start = new GAutoStart(main);
			main.setState(GStates.PREPARATION);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
	}
}
