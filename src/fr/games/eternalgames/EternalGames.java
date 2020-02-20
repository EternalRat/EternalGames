package fr.games.eternalgames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.games.eternalgames.listener.ListenerJoin;

public class EternalGames extends JavaPlugin {
	
	private GStates states;
	private List<Player> Players = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		if (Bukkit.getServer().getName() == "ChaseRun") {
			Listener listen = new ListenerJoin(this);
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(listen, this);
			System.out.println("Ready to play at EternalGames !");
		}
	}
	
	public void setState(GStates states) {
		this.states = states;
	}
	
	public boolean isState(GStates states) {
		return this.states == states;
	}
	
	public List<Player> getPlayers() {
		return Players;
	}
}
