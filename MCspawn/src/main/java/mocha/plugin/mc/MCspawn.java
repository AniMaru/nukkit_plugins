package mocha.plugin.mc;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class MCspawn extends PluginBase implements Listener{
	public Config message;
	
	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		message =  new Config(getDataFolder()+"/message.yml", Config.YAML);
		message.set("SpawnMessage", TextFormat.AQUA+"[알림]스폰으로 이동하셨습니다.");
		message.save();
	}
	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		super.onDisable();
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MCOHA-EULA:"+pd.getWebsite());
		message.save();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().toLowerCase().equals("spawn")){
			if(!sender.isPlayer()){
				sender.sendMessage(TextFormat.RED+"[ERROR]게임 안에서만 사용가능한 명령어입니다.");
			}
			else{
				Player player = (Player)sender;
				Level level = player.getLevel();
				player.teleport(level.getSpawnLocation());
				player.sendMessage(message.getString("SpawnMessage"));
			}
		}
		return super.onCommand(sender, command, label, args);
	}
}	