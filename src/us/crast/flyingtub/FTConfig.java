package us.crast.flyingtub;

import java.io.File;

public final class FTConfig {
	public static final String FLYINGTUB_VERSION = "0.1";
	
    private final double horizontal;
	private final double vertical;
	private final boolean prevent_damage;

	public FTConfig(FlyingTub plugin) {
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
	public final double verticalSpeed() {
		return this.vertical;
	}
	
	public final boolean preventDamage() {
		return this.prevent_damage;
	}
}
