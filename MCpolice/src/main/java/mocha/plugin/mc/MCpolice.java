package mocha.plugin.mc;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class MCpolice extends PluginBase implements Listener{
	public Config policeDB;

	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		policeDB = new Config(getDataFolder()+"/policeDB.json",Config.JSON);
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
		try{
			if(command.getName().equals("경찰추가")){
				sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"을 경찰로 임명하였습니다.");
				policeDB.set(args[0], args[0]);
				save();
			}
			else if(command.getName().equals("경찰삭제")){
				sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"을 경찰에서 해임하였습니다.");
				policeDB.remove(args[0]);
				save();
			}
		}catch(Exception e){
			String blue = TextFormat.BLUE;
			sender.sendMessage(blue+"=== 경찰 관리 ===");
			sender.sendMessage(blue+"/경찰추가 <이름>");
			sender.sendMessage(blue+"/경찰삭제 <이름>");
		}
		if(command.getName().equals("경찰")){
			try{
				if(!sender.getName().equals(policeDB.get(sender.getName()))){
					sender.sendMessage(TextFormat.RED+"[알림]당신은 경찰이 아닙니다.");
				}
				else if(sender.getName().equals(policeDB.get(sender.getName()))){
					if(args[0].equals("밴")){
						sender.sendMessage(TextFormat.AQUA+"[알림]"+args[1]+"을 밴하였습니다.");
						Player player = this.getServer().getPlayer(args[1]);
						player.kick(TextFormat.RED+"경찰에 의해 밴 되셨습니다.");
						player.setBanned(true);
					}
					else if(args[0].equals("킥")){
						Player player =this.getServer().getPlayer(args[1]);
						sender.sendMessage(TextFormat.AQUA+"[알림]"+args[1]+"을 킥하였습니다.");
						player.kick(TextFormat.RED+"경찰에 의해 킥 되셨습니다.");
					}
					else if(args[0].equals("말하기")){
						this.getServer().broadcastMessage(TextFormat.LIGHT_PURPLE+"[경찰:"+sender.getName()+"]"+args[1]);
					}
					else if(args[0].equals("tp")){
						Player player = this.getServer().getPlayer(args[1]);
						Player sendPlayer = (Player)sender;
						Position pos = new Position(player.getX(),player.getY(),player.getZ());
						sender.sendMessage(TextFormat.AQUA+"[알림]"+args[1]+"에게로 TP 하셨습니다.");
						sendPlayer.teleport(pos);
					}
				}
			}catch(Exception e){
				String b = TextFormat.BLUE;
				sender.sendMessage(b+"=== 경찰 ===");
				sender.sendMessage(b+"/경찰 밴 (대상)");
				sender.sendMessage(b+"/경찰 킥 (대상)");
				sender.sendMessage(b+"/경찰 말하기 (문자열)");
				sender.sendMessage(b+"/경찰 tp (대상)");
			}
		}
		return super.onCommand(sender, command, label, args);
	}
	public void save(){
		policeDB.save();
	}
}	