package fr.games.eternalgames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.games.eternalgames.EternalGames;
import fr.games.eternalgames.GStates;

public class GChase extends BukkitRunnable {

	private int timer = 600;
	private EternalGames main;
	
	public GChase(EternalGames main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		if (timer == 0) {
			for (Player pl : main.getPlayers()) {
				if (pl.getName().toString() != main.Chasseur) {
					pl.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(99999, 1));
				}
			}
		}
		timer--;
		Listener Kill = new ListenerDeath();
		if (main.getPlayers().size() == 2) {
			//TP MANQUANT
			main.setState(GStates.FINAL_CHASE);
		}
	}

}
