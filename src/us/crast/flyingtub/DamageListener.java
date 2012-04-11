package us.crast.flyingtub;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageListener implements Listener {
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() != DamageCause.FALL) return;
		if (event.getEntity().getType() == EntityType.PLAYER) {	
			Player player = (Player) event.getEntity();
			if(player.isInsideVehicle() && player.getVehicle().getVelocity().length() > 0.01) {					
				event.setCancelled(true);
			}
		}
	}
}
