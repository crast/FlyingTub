package us.crast.flyingtub;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;

public final class FTConfig {
	public static final String FLYINGTUB_VERSION = "0.3-pre1";
	
    private double horizontal;
	private double vertical;
	private final boolean prevent_damage;
    private final Logger logger;

	public FTConfig(FlyingTub plugin) {
	    this.logger = plugin.getLogger();
		File dataFolder = plugin.getDataFolder();
		dataFolder.mkdir();

		File configFile = new File(dataFolder, "config.yml");

		if (!configFile.exists()) {
			plugin.saveDefaultConfig();
		}
		this.horizontal = plugin.getConfig().getDouble("speed.horizontal");
		this.vertical = plugin.getConfig().getDouble("speed.vertical");
		this.prevent_damage = plugin.getConfig().getBoolean("prevent_damage");
	}

	public final double horizontalSpeed() {
		return this.horizontal;
	}
	
	public final void setHorizontalSpeed(double horizontalSpeed) {
	    this.horizontal = horizontalSpeed;
	}
	
	public final double verticalSpeed() {
		return this.vertical;
	}
	
	public final void setVerticalSpeed(double verticalSpeed) {
	    this.vertical = verticalSpeed;
	}
	
	public final boolean preventDamage() {
		return this.prevent_damage;
	}

    public Logger getLogger() {
        return logger;
    }
    
    public void copyToYaml(FileConfiguration config) {
        config.set("speed.horizontal", this.horizontal);
        config.set("speed.vertical", this.vertical);
    }
}
