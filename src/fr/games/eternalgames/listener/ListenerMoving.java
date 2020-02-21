package fr.games.eternalgames.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.games.eternalgames.EternalGames;

public class ListenerMoving implements Listener {

	private EternalGames main;
	
	public ListenerMoving(EternalGames main) {
		this.main = main;
	}
	
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (main.frozen.contains(p)) {
			e.setTo(e.getFrom());
		}
	}

}