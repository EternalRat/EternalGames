package fr.games.eternalgames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.games.eternalgames.listener.ListenerBlocks;
import fr.games.eternalgames.listener.ListenerJoin;
import fr.games.eternalgames.listener.ListenerMoving;

public class EternalGames extends JavaPlugin {
	
	private GStates states;
	private List<Player> Players = new ArrayList<Player>();
	public Listener movements = new ListenerMoving(this);
	public PluginManager pmm = Bukkit.getServer().getPluginManager();
	public List<Player> frozen = new ArrayList<Player>();
	public String Chasseur = null;
	
	@Override
	public void onEnable() {
		System.out.println("Ready to play at EternalGames !");
		Listener listen = new ListenerJoin(this);
		PluginManager pm = getServer().getPluginManager();
		Listener blocks = new ListenerBlocks();
		PluginManager pmB = getServer().getPluginManager();
		pmB.registerEvents(blocks, this);
		pm.registerEvents(listen, this);
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