package fr.games.eternalgames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.games.eternalgames.EternalGames;
import fr.games.eternalgames.GStates;
import fr.games.eternalgames.listener.ListenerDeath;

public class GChase extends BukkitRunnable {

	private int timer = 300;
	private EternalGames main;
	
	public GChase(EternalGames main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		if (timer == 0) {
			for (Player pl : main.getPlayers()) {
				if (pl.getName() != main.Chasseur) {
					pl.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(99999, 1));
				}
			}
		}
		timer--;
		Listener Kill = new ListenerDeath(main);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(Kill, main);
		if (main.getPlayers().size() == 2 && main.isState(GStates.CHASE)) {
			//TP MANQUANT
			main.setState(GStates.FINAL_CHASE);
			GFinalChase cycle = new GFinalChase(main);
			cycle.runTaskTimer(main, 0, 20);
		}
	}

}
