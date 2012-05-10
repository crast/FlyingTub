package us.crast.flyingtub;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageListener implements Listener {
    // If we're going at least this fast, ignore damage.
    // We use the velocity squared because it avoids a square root
	private static final double MIN_SQUARE_VELOCITY = 0.0001;

    @EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() != DamageCause.FALL) return;
		Entity entity = event.getEntity();
		if (entity.getType() == EntityType.PLAYER) {
			if (entity.isInsideVehicle()) {
			    Entity vehicle = entity.getVehicle();
			    if (vehicle.getType() == EntityType.MINECART && vehicle.getVelocity().lengthSquared() > MIN_SQUARE_VELOCITY) {			
			        event.setCancelled(true);
			    }
			}
		}
	}
}
