package mocha.plugin.mc;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
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
					warps.set(args[0], new Vector3(player.getX(),player.getY(),player.getZ()));
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
					sender.sendMessage(TextFormat.YELLOW+"/워프목록 [워프이름]:워프의 위치보기");
				}
				else{
					Vector3 vector3 = (Vector3)warps.get(args[0]);
					sender.sendMessage(TextFormat.LIGHT_PURPLE+"["+args[0]+"]");
					sender.sendMessage(TextFormat.LIGHT_PURPLE+"X : "+vector3.getX());
					sender.sendMessage(TextFormat.LIGHT_PURPLE+"Y : "+vector3.getY());
					sender.sendMessage(TextFormat.LIGHT_PURPLE+"Z : "+vector3.getZ());
				}
			}catch (Exception e) {
				sender.sendMessage(TextFormat.RED+"[오류]같은 이름의 워프를 발결하지 못했습니다.");
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
					Player player = (Player)sender;
					Vector3 pos = (Vector3)warps.get(args[0]); 
					player.teleport(new Position(pos.getX(),pos.getY(),pos.getZ()));
					sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"로 워프하셨습니다.");
				}catch(Exception e){
					sender.sendMessage(TextFormat.AQUA+"[오류]같은 이름의 워프를 발견 할 수 없습니다.");
				}
			}
		}
		return super.onCommand(sender, command, label, args);
	}
	public void save(){
		warps.save();
	}
}	