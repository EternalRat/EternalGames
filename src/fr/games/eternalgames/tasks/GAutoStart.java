package fr.games.eternalgames.tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.games.eternalgames.EternalGames;
import fr.games.eternalgames.GStates;

public class GAutoStart extends BukkitRunnable {
	
	public int timer = 10;
	private EternalGames main;

	public GAutoStart(EternalGames main) {
		this.main = main;
	}

	@Override
	public void run() {
		if (main.Chasseur == null)
			main.Chasseur = main.getPlayers().get((int)(Math.random() * main.getPlayers().size())).getName();
		for (Player pl : main.getPlayers()) {
			pl.setLevel(timer);
			pl.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(timer, 1));
			main.pmm.registerEvents(main.movements, main);
		}
		if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
			Bukkit.broadcastMessage("The game will start in " + timer + "s.");
		if (timer == 0) {
			for (Player pl : main.frozen) {
				main.frozen.remove(pl);
				if (pl.getName() == main.Chasseur) {
					pl.sendTitle("Hunter", "The game has started.", 10, 80, 20);
					ItemStack item = new ItemStack(Material.CROSSBOW, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName("Supra Deaths");
					meta.setUnbreakable(true);
					List<String> lore = new ArrayList<String>();
					lore.add("The definition of god himself.");
					lore.add("The special crossbow for the Hunter God.");
					meta.setLore(lore);
					meta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
					item.setItemMeta(meta);
					pl.getInventory().setItemInMainHand(item);
				} else {
					pl.sendTitle("Rabbits", "Try to live.", 10, 80, 20);
					pl.addPotionEffect(PotionEffectType.SPEED.createEffect(99999, 3));
					pl.addPotionEffect(PotionEffectType.JUMP.createEffect(99999, 8));
				}
			}
			main.setState(GStates.CHASE);
			GChase cycle = new GChase(main);
			
			cycle.runTaskTimer(main, 0, 20);
			cancel();
		}
		timer--;
	}

}
