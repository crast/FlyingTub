package us.crast.flyingtub;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FlyingTub extends JavaPlugin {
	private FTConfig config;
	public PluginManager pluginManager;
	private TubFlightListener listener;
	private Logger log;

	@Override
	public void onEnable() {
	    this.log = getLogger();
		this.config = new FTConfig(this);
		this.listener = new TubFlightListener(this);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.listener, this);
		
		if (config.preventDamage()) {
			pm.registerEvents(new DamageListener(), this);
		}
		getCommand("flyingtub").setExecutor(this);
		log.info(String.format("FlyingTub version %s enabled", FTConfig.FLYINGTUB_VERSION));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(String.format(
                    "%sFlyingTub: %sHorizontal=%s%f %sVertical=%s%f",
                    ChatColor.GOLD,
                    ChatColor.AQUA,
                    ChatColor.GREEN, this.config.horizontalSpeed(),
                    ChatColor.AQUA,
                    ChatColor.GREEN, this.config.verticalSpeed()
            ));
            return false;
        }
        if (args[0].equals("save")) {
            saveConfig();
            sender.sendMessage(ChatColor.RED + "Save not yet implemented");
        } else if (args.length != 2) {
            return false;
        } else {
            try {
                double parsedval = Double.parseDouble(args[1]);
                if (args[0].equals("horizontal")) {
                    this.config.setHorizontalSpeed(parsedval);
                } else if (args[0].equals("vertical")) {
                    this.config.setVerticalSpeed(parsedval);
                } else {
                    return false;
                }
                sender.sendMessage(String.format("%s%s is now: %s%f", ChatColor.GOLD, args[0], ChatColor.GREEN, parsedval));
            } catch (NumberFormatException e) {
                return false;
            }
            this.listener.setup();
        }
        return true;
	}

    @Override
	public void onDisable() {
		log.info("Disabling FlyingTub: Tubs can no longer fly :(");
	}

	public final FTConfig getFTConfig() {
		return this.config;
	}
}
