package fr.games.eternalgames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import fr.games.eternalgames.EternalGames;
import fr.games.eternalgames.GStates;
import fr.games.eternalgames.listener.ListenerDeath;

public class GFinalChase extends BukkitRunnable {

	private EternalGames main;
	private int timer = 180;
	
	public GFinalChase(EternalGames main) {
		this.main = main;
	}

	@Override
	public void run() {
		
		if (timer == 0) {
			for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
				pl.sendTitle("Rabbits have won !", "", 10, 80, 20);
			}
		}
		
		Listener Kill = new ListenerDeath(main);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(Kill, main);
		if (main.getPlayers().size() == 1 && main.isState(GStates.FINAL_CHASE)) {
			for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
				pl.sendTitle("Hunter have won !", "", 10, 80, 20);
			}
			main.setState(GStates.END);
			Bukkit.shutdown();
		}
		
		timer--;
	}

}
