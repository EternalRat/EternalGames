package fr.games.eternalgames.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import fr.games.eternalgames.EternalGames;
import fr.games.eternalgames.GStates;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class ListenerDeath implements Listener {

	private EternalGames main;
	
	public ListenerDeath(EternalGames main2) {
		this.main = main2;
	}
	
	public void onGetDamage(EntityDamageByEntityEvent e) {
		if (main.getPlayers().size() > 2) {
			if (e.getDamager() instanceof Arrow && main.isState(GStates.CHASE)) {
				Player p = (Player)e.getEntity();
				
				e.setCancelled(true);
				p.sendMessage("You have lost !");
				MobDisguise mobDisguise = new MobDisguise(DisguiseType.PLAYER);
				mobDisguise.setEntity(p);
				mobDisguise.removeDisguise();
				main.getPlayers().remove(p);
				
				p.setGameMode(GameMode.SURVIVAL);
				
			} else
				e.setCancelled(true);
		} else {
			if (e.getDamager() instanceof Arrow && main.isState(GStates.FINAL_CHASE)) {
				Player p = (Player)e.getEntity();
				
				e.setCancelled(true);
				p.sendMessage("You have lost !");
				MobDisguise mobDisguise = new MobDisguise(DisguiseType.PLAYER);
				mobDisguise.setEntity(p);
				mobDisguise.removeDisguise();
				main.getPlayers().remove(p);
				
				p.setGameMode(GameMode.SURVIVAL);
			}
		}
	}

}
