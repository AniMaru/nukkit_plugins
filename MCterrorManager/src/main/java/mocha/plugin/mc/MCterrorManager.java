package mocha.plugin.mc;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class MCterrorManager extends PluginBase implements Listener{
	public Config BannedMsg,BannedItem;

	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		BannedMsg = new Config(getDataFolder()+"/BannedMsg.json", Config.JSON);
		BannedItem = new Config(getDataFolder()+"/BannedItem.json",Config.JSON);
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
			if(command.getName().equals("채팅")){
				if(args[0].equals("금칙어")){
					if(args[1].equals("추가")){
						sender.sendMessage(TextFormat.AQUA+"[알림]금칙어를 추가하셨습니다.");
						BannedMsg.set(String.valueOf(BannedMsg.getAll().values().size()), args[2]);
						save();
					}
					else if(args[1].equals("목록")){
						for(int i=0;i<BannedMsg.getAll().values().size();i++){
							sender.sendMessage(i+". "+BannedMsg.getString(Integer.toString(i))+",");
						}
					}
					else if(args[1].equals("삭제")){
						BannedMsg.remove(args[2]);
						sender.sendMessage(TextFormat.RED+"[알림]금칙어 "+BannedMsg.getString(args[2])+"를 삭제 하셨습니다.");
						save();
					}
					else {
						chatCmdError(sender);
					}
				}
				else if(args[0].equals("청소")){
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(" ");
					this.getServer().broadcastMessage(TextFormat.BLUE+sender.getName()+TextFormat.YELLOW+"님이채팅을 청소 하셨습니다.");
				}
				else{
					chatCmdError(sender);
				}
			}
		}catch(Exception e){
			chatCmdError(sender);
		}
		try{
			if(command.getName().equals("아이템")){
				if(args[0].equals("밴")){
					if(args[1].equals("추가")){
						sender.sendMessage(TextFormat.AQUA+"[알림]밴 아이템을 추가하셨습니다.");
						BannedItem.set(String.valueOf(BannedItem.getAll().values().size()), Integer.parseInt(args[2]));
					}
					else if(args[1].equals("삭제")){
						sender.sendMessage(TextFormat.AQUA+"[알림]밴 아이템을 삭제하셨습니다.");
						BannedItem.remove(args[2]);
					}
					else if(args[1].equals("목록")){
						for(int i=0;i<BannedItem.getAll().values().size();i++){
							sender.sendMessage(i+". "+BannedItem.getInt(String.valueOf(i)));
						}
					}
					
				}
				else{
					itemCmdError(sender);
				}
			}
		}catch(Exception e){
			itemCmdError(sender);
		}
		if(command.getName().equals("청소")){
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
		}
		return super.onCommand(sender, command, label, args);
	}
	@EventHandler
	public void onChat(PlayerChatEvent event){
		String[] filtMsg = new String[BannedMsg.getAll().values().size()];
		for(int i=0;i < BannedMsg.getAll().values().size();i++){
			filtMsg[i] = BannedMsg.getString(Integer.toString(i));
		}
		for(int i =0;i < filtMsg.length; i++){
			if(event.getMessage().contains(filtMsg[i])){
				chatKick(event);
			}
			
		}
		String message = event.getMessage();
		defaultKcik(event, message, "씨", "발");
		defaultKcik(event, message, "애", "미");
		defaultKcik(event, message, "야", "미");
		defaultKcik(event, message, "새", "끼");
		defaultKcik(event, message, "새", "기");
		defaultKcik(event, message, "자", "지");
		defaultKcik(event, message, "보", "지");
		defaultKcik(event, message, "불", "알");
		defaultKcik(event, message, "부", "랄");
		defaultKcik(event, message, "응", "니");
		defaultKcik(event, message, "씹", "알");
	}
	@EventHandler
	public void onTouch(PlayerInteractEvent event){
		Player player = event.getPlayer();
		String name = player.getName();
		int id = event.getItem().getId();
		if(id == 46 || id == 10 || id == 11 || id == 327 || id == 259) {
			event.setCancelled();
			this.getServer().broadcastMessage(TextFormat.RED+"[테러알림]"+name+"님이 테러를 시도하였습니다 !" );
			player.kick("테러 시도로 킥당하셨습니다.");	
		}
		for(int i= 0; i < BannedItem.getAll().values().size(); i++){
			if(id == BannedItem.getInt(String.valueOf(i))){
				event.setCancelled();
				player.sendMessage(TextFormat.RED+"[알림]사용이 금지된 아이템입니다.");
			}
		}
	}
	public void save(){
		BannedMsg.save();
		BannedItem.save();
	}
	public void chatKick(PlayerChatEvent event){
		event.setCancelled();
		event.getPlayer().kick(TextFormat.RED+"바른 말 쓰세욧;;");
	}
	public void chatCmdError(CommandSender sender){
		String blue = TextFormat.BLUE;
		sender.sendMessage(blue+"=== 채팅 명령어 사용법 ===");
		sender.sendMessage(blue+"/채팅 금칙어 <추가 (금칙어)|삭제(금칙어 번호)|목록>");
		sender.sendMessage(blue+"/채팅 청소");
	}
	public void defaultKcik(PlayerChatEvent event, String message,String x, String y){
		if(message.contains(x)&&message.contains(y)){
			chatKick(event);
		}
	}
	public void itemCmdError(CommandSender sender){
		String blue = TextFormat.BLUE;
		sender.sendMessage(blue+"=== 채팅 명령어 사용법 ===");
		sender.sendMessage(blue+"/아이템 밴  <추가 (아이템 코드)|삭제(아이템 목록 번호)|목록>");
	}
}	