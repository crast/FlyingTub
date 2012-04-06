package us.crast.flyingtub;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.util.Vector;

public final class TubFlightListener implements Listener {

	private final FTConfig config;
    private Vector desiredVelocityMod;

	public TubFlightListener(FlyingTub plugin) {
		this.config = plugin.getFTConfig();
		this.setup();
	}
	
	private void setup() {
	    desiredVelocityMod = new Vector(
	            this.config.horizontalSpeed(), 
	            this.config.verticalSpeed(), 
	            this.config.horizontalSpeed()
	    );
	}
	
	@EventHandler
	public final void onVehicleEnter(VehicleEnterEvent event) {
		fixCart(event.getVehicle());
	}
	
	@EventHandler
	public final void onVehicleCreate(VehicleCreateEvent event) {
		fixCart(event.getVehicle());
	}
	
	private void fixCart(Vehicle cart) {
		if (cart.getType() == EntityType.MINECART) {
            ((Minecart)cart).setFlyingVelocityMod(desiredVelocityMod);
		}
	}
}
