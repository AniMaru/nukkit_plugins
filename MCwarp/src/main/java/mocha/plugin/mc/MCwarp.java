package mocha.plugin.mc;

import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class MCwarp extends PluginBase implements Listener{
	public Config warps;

	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		warps = new Config(getDataFolder()+"/warps.yml",Config.YAML);
	}
	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		super.onDisable();
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MCOHA-EULA:"+pd.getWebsite());
		save();
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().toLowerCase().equals("워프추가")){
			if(sender.isPlayer()){
				Player player = (Player)sender;
				switch (args.length) {
				case 0:
					sender.sendMessage(TextFormat.RED+"워프이름을 입력해 주세요.");
				default:
					sender.sendMessage(TextFormat.AQUA+"[알림]워프"+args[0]+"(이)가 추가되었습니다.");
					LinkedHashMap<String, Object> map = new LinkedHashMap<>();
					map.put("x", player.getX());
					map.put("y", player.getY());
					map.put("z", player.getZ());
					map.put("level", player.getLevel().getFolderName());
					warps.set(args[0], map);
					save();
					break;
				}
			}
			else{
				sender.sendMessage(TextFormat.RED+"게임안에서만 쓸 수있는 명령어 입니다.");
			}
		}
		if(command.getName().toLowerCase().equals("워프삭제")){
			switch (args.length) {
			case 0:
				sender.sendMessage(TextFormat.RED+"워프이름을 입력해 주세요.");
			default:
				if(warps.get(args[0]) == null){
					sender.sendMessage(TextFormat.RED+"워프 "+args[0]+"를 찾을 수 없습니다.");
				}
				else{
					warps.remove(args[0]);
					save();
					sender.sendMessage(TextFormat.AQUA+"[알림]워프"+args[0]+"를 삭제하였습니다.");
				}
				break;
			}
		}
		if(command.getName().toLowerCase().equals("워프")){
			if(sender.isPlayer()){
				Player player = (Player)sender;
				switch (args.length) {
				case 0:
					sender.sendMessage(TextFormat.RED+"워프이름을 입력해 주세요.");
					break;
				default:
					if(warps.get(args[0]) == null){
						sender.sendMessage(TextFormat.RED+"워프 "+args[0]+"를 찾을 수 없습니다.");
					}
					else{
						LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)warps.get(args[0]);
						double x,y,z;
						x = toDouble(map.get("x"));
						y = toDouble(map.get("y"));
						z = toDouble(map.get("z"));
						player.teleport(new Position(x,y,z,getServer().getLevelByName(map.get("level").toString())));
						sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"로 이동하셨습니다.");
					}
					break;
				}
			}
			else{
				sender.sendMessage(TextFormat.RED+"게임안에서만 쓸 수있는 명령어 입니다.");
			}
		}
		return super.onCommand(sender, command, label, args);
	}
	public void save(){
		warps.save();
	}
	public static double toDouble(Object obj) {
		if (obj instanceof Integer)
			return ((Integer) obj).doubleValue();
		if (obj instanceof Double)
			return ((Double) obj).doubleValue();
		if (obj instanceof String) {
			return Double.parseDouble(obj.toString());
		}
		return 0.0D;
	}
}	