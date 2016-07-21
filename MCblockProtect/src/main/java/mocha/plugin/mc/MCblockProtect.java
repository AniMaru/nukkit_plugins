package mocha.plugin.mc;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class MCblockProtect extends PluginBase implements Listener{
	public Config protectDB;

	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		protectDB = new Config(getDataFolder()+"/protectDB.json", Config.JSON);
		save();
	}
	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		super.onDisable();
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MCOHA-EULA:"+pd.getWebsite());
		save();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equals("보호")){
			if(protectDB.getBoolean("protect")){
				protectDB.set("protect", false);
				save();
			}
			else{
				protectDB.set("protect", true);
				save();
			}
			if(protectDB.getBoolean("protect")){
				this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]서버의 블럭 보호가 켜졌습니다.");
			}
			else{
				this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]서버의 블럭 보호가 꺼졌습니다.");
			}
		}
		return super.onCommand(sender, command, label, args);
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		if(protectDB.getBoolean("protect")){
			event.setCancelled();
			event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]지금은 서버에 블럭을 놓을 수 없습니다.");
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		if(protectDB.getBoolean("protect")){
			event.setCancelled();
			event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]지금은 서버의 블럭을 부술 수 없습니다.");
		}
	}
	public void save(){
		protectDB.save();
	}
}	