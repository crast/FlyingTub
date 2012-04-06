package us.crast.flyingtub;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FlyingTub extends JavaPlugin {
	private FTConfig config;
	public PluginManager pluginManager;
	private Logger log;

	public void onEnable() {
	    this.log = getLogger();
		this.config = new FTConfig(this);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new TubFlightListener(this), this);
		
		if (config.preventDamage()) {
			pm.registerEvents(new DamageListener(), this);
		}

		log.info(String.format("FlyingTub version %s disabled", FTConfig.FLYINGTUB_VERSION));
	}

	public void onDisable() {
		log.info("Disabling FlyingTub: Tubs can no longer fly :(");
	}

	public FTConfig getFTConfig() {
		return this.config;
	}
}
