package us.crast.flyingtub;

import java.io.File;

public final class FTConfig {
	public static final String FLYINGTUB_VERSION = "0.1";
	
    private double horizontal;
	private double vertical;
	private boolean prevent_damage;

	public FTConfig(FlyingTub plugin) {
		File dataFolder = plugin.getDataFolder();
		dataFolder.mkdir();

		File configFile = new File(dataFolder, "config.yml");

		if (!configFile.exists()) {
			plugin.saveDefaultConfig();
		}
		this.horizontal = plugin.getConfig().getDouble("speed.horizontal", 20);
		this.vertical = plugin.getConfig().getDouble("speed.vertical", 20);
		this.prevent_damage = plugin.getConfig().getBoolean("prevent_damage", false);
	}

	public double horizontalSpeed() {
		return this.horizontal;
	}
	public double verticalSpeed() {
		return this.vertical;
	}
	
	public boolean preventDamage() {
		return this.prevent_damage;
	}
}
