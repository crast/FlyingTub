package us.crast.flyingtub

import java.util.logging.Logger

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class FlyingTub extends JavaPlugin {
    var pluginManager: PluginManager = _
    private var ftConfig: FTConfig = _
    private var listener: TubFlightListener = _

    override def onEnable() {
        ftConfig = new FTConfig(this)
        listener = new TubFlightListener(this)

        val pm = getServer().getPluginManager();
        pm.registerEvents(listener, this);
        
        if (ftConfig.preventDamage()) {
            pm.registerEvents(new DamageListener(), this);
        }
        getCommand("flyingtub").setExecutor(this);
        getLogger().info(String.format("FlyingTub version %s enabled", FTConfig.FLYINGTUB_VERSION));
    }
    
    override def onDisable() {
        getLogger().info("Disabling FlyingTub: Tubs can no longer fly :(");
    }

    override def onCommand(sender: CommandSender , cmd: Command , commandLabel: java.lang.String, args: Array[java.lang.String]): Boolean = {
        if (args.length == 0) {
            sender.sendMessage("%sFlyingTub: %sHorizontal=%s%f %sVertical=%s%f".format(
                    ChatColor.GOLD,
                    ChatColor.AQUA,
                    ChatColor.GREEN, this.ftConfig.horizontalSpeed(),
                    ChatColor.AQUA,
                    ChatColor.GREEN, this.ftConfig.verticalSpeed()
            ));
            return false;
        }
        var parsedval = 0.0D
        if (args.length == 2) {
            parsedval = args(1).toDouble
        }
        args(0) match {
            case "save" => {
                this.ftConfig.copyToYaml(getConfig());
                saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Configuration Saved")
            }
            case "horizontal" => {
                this.ftConfig.setHorizontalSpeed(parsedval)
            }
            case "vertical" => {
                this.ftConfig.setVerticalSpeed(parsedval)
            }
            case _ => return false
        }
        if (args.length == 2) {
            sender.sendMessage("%s%s is now: %s%f".format(ChatColor.GOLD, args(0), ChatColor.GREEN, parsedval));
            listener.setup();
        }
        return true
    }

    def getFTConfig() {
        return ftConfig
    }
}
