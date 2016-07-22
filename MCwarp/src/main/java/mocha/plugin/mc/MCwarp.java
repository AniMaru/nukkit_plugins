package mocha.plugin.mc;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

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
		warps = new Config(getDataFolder()+"/warps.json",Config.JSON);
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
		if(command.getName().equals("워프추가")){
			try{
				if(!sender.isPlayer()){
					sender.sendMessage(TextFormat.RED+"[오류]게임 안에서만 사용 할 수 있습니다.");
				}
				else if(args.length == 0){
					sender.sendMessage(TextFormat.AQUA+"[오류]워프이름을 입력하세요.");
				}
				else{
					Player player = (Player)sender;
					LinkedHashMap<String,Object> map = new LinkedHashMap<>();
					map.put(args[0], new Object[]{player.getX(),player.getY(),player.getZ(),player.getLevel().getFolderName()});
					warps.set(args[0], map);
					save();
					sender.sendMessage(TextFormat.AQUA+"[알림]워프 "+args[0]+"(이)가 생성되었습니다.");
				}
			}catch(Exception e){
				sender.sendMessage(TextFormat.BLUE+"/워프추가 <워프이름>");
			}
		}
		if(command.getName().equals("워프목록")){
				try{
					if(args.length == 0){
					sender.sendMessage(TextFormat.AQUA+"=====[워프목록]=====");
					for(String s : warps.getKeys()){
						sender.sendMessage(s);
					}
					sender.sendMessage(TextFormat.YELLOW+"/워프목록 <워프이름>:워프위치보기");
					}
					else{
						LinkedTreeMap<String, Object> list = (LinkedTreeMap<String, Object>)warps.get(args[0],new LinkedTreeMap<String,Object>());
						List<Object> val = (List<Object>)list.get(args[0]);
						double x,y,z;
						x = toDouble(val.get(0));
						y = toDouble(val.get(1));
						z = toDouble(val.get(2));
						String levelName = val.get(3).toString();
						sender.sendMessage(TextFormat.LIGHT_PURPLE+"["+args[0]+"]");
						sender.sendMessage(TextFormat.LIGHT_PURPLE+"X:"+(int)x);
						sender.sendMessage(TextFormat.LIGHT_PURPLE+"Y:"+(int)y);
						sender.sendMessage(TextFormat.LIGHT_PURPLE+"Z:"+(int)z);
						sender.sendMessage(TextFormat.LIGHT_PURPLE+"world:"+levelName);
					}
				}catch(Exception e){
					sender.sendMessage(TextFormat.RED+"[오류]같은 이름의 워프를 발견할 수 없습니다.");
				}
		}
		if(command.getName().equals("워프삭제")){
			try{
				if(args.length == 0){
					sender.sendMessage(TextFormat.AQUA+"[오류]워프이름을 입력하세요.");
				}
				else{
					warps.remove(args[0]);
					save();
					sender.sendMessage(TextFormat.AQUA+"[알림]워프 "+args[0]+"(이)가 삭제되었습니다.");
				}
			}catch(Exception e){
				sender.sendMessage(TextFormat.BLUE+"/워프삭제 <워프이름>");
			}
		}
		if(command.getName().equals("워프")){
			if(!sender.isPlayer()){
				sender.sendMessage(TextFormat.RED+"[오류]게임 안에서만 사용 할 수 있습니다.");
			}
			else if(args.length == 0){
				sender.sendMessage(TextFormat.AQUA+"[오류]워프이름을 입력하세요.");
			}
			else{
				try{
					LinkedTreeMap<String, Object> list = (LinkedTreeMap<String, Object>)warps.get(args[0],new LinkedTreeMap<String,Object>());
					List<Object> val = (List<Object>)list.get(args[0]);
					double x,y,z;
					x = toDouble(val.get(0));
					y = toDouble(val.get(1));
					z = toDouble(val.get(2));
					String levelName = val.get(3).toString();
					Player player = (Player)sender;
					player.teleport(new Position(x,y,z,getServer().getLevelByName(levelName)));
					sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"로 워프하셨습니다.");
				}catch(Exception e){
					sender.sendMessage(TextFormat.RED+"[오류]같은 이름의 워프를 발견할 수 없습니다.");
				}
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