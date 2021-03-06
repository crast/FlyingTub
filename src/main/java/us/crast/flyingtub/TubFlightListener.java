package us.crast.flyingtub;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.util.Vector;

public final class TubFlightListener implements Listener {
	private final FTConfig config;
    private Vector desiredVelocityMod;
    private Vector exitedVelocityMod;

	public TubFlightListener(FlyingTub plugin) {
		this.config = plugin.getFTConfig();
		this.setup();
	}
	
	public void setup() {
	    desiredVelocityMod = new Vector(
	            this.config.horizontalSpeed(), 
	            this.config.verticalSpeed(), 
	            this.config.horizontalSpeed()
	    );
        exitedVelocityMod = new Vector(
                this.config.exitedHorizontalSpeed(),
                this.config.exitedVerticalSpeed(),
                this.config.exitedHorizontalSpeed()
        );
	}
	
	@EventHandler(ignoreCancelled=true)
	public final void onVehicleEnter(VehicleEnterEvent event) {
		fixCart(event.getVehicle());
	}
	
	@EventHandler(ignoreCancelled=true)
	public final void onVehicleCreate(VehicleCreateEvent event) {
		fixCart(event.getVehicle());
	}

	private void fixCart(Vehicle cart) {
		if (cart.getType() == EntityType.MINECART) {
            ((Minecart)cart).setFlyingVelocityMod(desiredVelocityMod);
		}
	}

    @EventHandler(ignoreCancelled=true)
    public final void onVehicleExit(VehicleExitEvent event) {
        Vehicle cart = event.getVehicle();
        if (cart.getType() == EntityType.MINECART) {
            ((Minecart) cart).setFlyingVelocityMod(exitedVelocityMod);
        }
    }
}
