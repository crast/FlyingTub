package us.crast.flyingtub;

import java.util.logging.Logger;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.util.Vector;

public final class TubFlightListener implements Listener {

	@SuppressWarnings("unused")
	private final Logger log;
	private final FTConfig config;
    private Vector desiredVelocityMod;

	public TubFlightListener(FlyingTub plugin) {
		this.log = plugin.getLogger();
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
		//log.info("vehicle enter");
		fixCart(event.getVehicle());
	}
	
	@EventHandler
	public final void onVehicleCreate(VehicleCreateEvent event) {
		//log.info("vehicle create");
		fixCart(event.getVehicle());
	}
	
	private void fixCart(Vehicle cart) {
		if (cart.getType() == EntityType.MINECART) {
            ((Minecart)cart).setFlyingVelocityMod(desiredVelocityMod);
		}
	}
}
