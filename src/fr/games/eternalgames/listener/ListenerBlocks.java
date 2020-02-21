package fr.games.eternalgames.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class ListenerBlocks implements Listener {

	public void onBreakBlocks(BlockDamageEvent block) {
		block.setCancelled(true);
	}
	
}