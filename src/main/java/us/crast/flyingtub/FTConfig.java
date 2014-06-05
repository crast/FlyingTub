package us.crast.flyingtub;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public final class FTConfig {
	public static final String FLYINGTUB_VERSION = "0.3";

    private double horizontal;
	private double vertical;
    private double exitedHorizontal;
    private double exitedVertical;
	private boolean prevent_damage;
    private final Logger logger;

	public FTConfig(FlyingTub plugin) {
	    this.logger = plugin.getLogger();
		File dataFolder = plugin.getDataFolder();
		dataFolder.mkdir();

		File configFile = new File(dataFolder, "config.yml");

		if (!configFile.exists()) {
			plugin.saveDefaultConfig();
		}
        configure(plugin.getConfig());
	}

    private void configure(ConfigurationSection section) {
        this.horizontal = this.exitedHorizontal = section.getDouble("speed.horizontal");
        this.vertical = this.exitedVertical = section.getDouble("speed.vertical");
        this.prevent_damage = section.getBoolean("prevent_damage");
        if (section.getBoolean("empty_speed.enabled")) {
            this.exitedHorizontal = section.getDouble("empty_speed.horizontal");
            this.exitedVertical = section.getDouble("empty_speed.vertical");
        }
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

    public double exitedHorizontalSpeed() { return this.exitedHorizontal; }

    public double exitedVerticalSpeed() { return this.exitedVertical; }

    public Logger getLogger() {
        return logger;
    }
    
    public void copyToYaml(FileConfiguration config) {
        config.set("speed.horizontal", this.horizontal);
        config.set("speed.vertical", this.vertical);
    }
}
