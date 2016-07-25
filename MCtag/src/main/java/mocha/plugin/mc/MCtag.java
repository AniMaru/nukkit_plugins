package mocha.plugin.mc;

import java.util.LinkedHashMap;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import me.onebone.economyapi.EconomyAPI;

public class MCtag extends PluginBase implements Listener{
	public Config tagDB;
	public EconomyAPI api;
	
	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdir();
		tagDB = new Config(getDataFolder()+"/tagDB.yml",Config.YAML);
		try{
			this.api = EconomyAPI.getInstance();
		}catch(Exception e){
			getServer().getLogger().error("원본님의 EconomyAPI플러그인이 있으면 칭호상점기능을 이용하실 수 있습니다.");
		}
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
		if(command.getName().toLowerCase().equals("칭호설정")){
			switch (args.length) {
			case 2:
				try{
					Player player = getServer().getPlayer(args[0]);
					LinkedHashMap<String, Object> link = (LinkedHashMap<String, Object>)tagDB.get(args[0]);
					link.put("tag", args[1]);
					link.put("chat-color", link.get("chat-color").toString());
					link.put("name", link.get("name").toString());
					tagDB.set(args[0], link);
					sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"의 칭호를 "+args[1]+"(으)로 변경했습니다.");
					save();
					player.setNameTag(args[1]+link.get("name"));
					break;
				}catch (Exception e) {
					sender.sendMessage(TextFormat.RED+"[오류]같은 이름의 플레이어를 찾을 수 없습니다.");
					break;
				}
			default:
				sender.sendMessage(TextFormat.YELLOW+"/칭호설정 <플레이어 이름> <칭호>");
				sender.sendMessage(TextFormat.YELLOW+"/닉네임 <플레이어 이름> <이름>");
				sender.sendMessage(TextFormat.YELLOW+"/채팅색 <플레이어 이름> <색코드>(§없이)");
				break;
			}
		}
		if(command.getName().toLowerCase().equals("닉네임")){
			switch (args.length) {
			case 2:
				try{
					Player player = getServer().getPlayer(args[0]);
					LinkedHashMap<String, Object> link = (LinkedHashMap<String, Object>)tagDB.get(args[0]);
					link.put("tag", link.get("tag").toString());
					link.put("chat-color", link.get("chat-color").toString());
					link.put("name", args[1]);
					tagDB.set(args[0], link);
					sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"의 이름를 "+args[1]+"(으)로 변경했습니다.");
					save();
					player.setNameTag(link.get("tag").toString()+args[1]);
					break;
				}catch (Exception e) {
					sender.sendMessage(TextFormat.RED+"[오류]같은 이름의 플레이어를 찾을 수 없습니다.");
					break;
				}
			default:
				sender.sendMessage(TextFormat.YELLOW+"/칭호설정 <플레이어 이름> <칭호>");
				sender.sendMessage(TextFormat.YELLOW+"/닉네임 <플레이어 이름> <이름>");
				sender.sendMessage(TextFormat.YELLOW+"/채팅색 <플레이어 이름> <색코드>(§없이)");
				break;
			}
		}
		if(command.getName().toLowerCase().equals("채팅색")){
			switch (args.length) {
			case 2:
				try{
					LinkedHashMap<String, Object> link = (LinkedHashMap<String, Object>)tagDB.get(args[0]);
					link.put("tag", link.get("tag").toString());
					link.put("chat-color", args[1]);
					link.put("name", link.get("name").toString());
					tagDB.set(args[0], link);
					sender.sendMessage(TextFormat.AQUA+"[알림]"+args[0]+"의 채팅색를 "+args[1]+"(으)로 변경했습니다.");
					save();
					break;
				}catch (Exception e) {
					sender.sendMessage(TextFormat.RED+"[오류]같은 이름의 플레이어를 찾을 수 없습니다.");
					break;
				}
			default:
				sender.sendMessage(TextFormat.YELLOW+"/칭호설정 <플레이어 이름> <칭호>");
				sender.sendMessage(TextFormat.YELLOW+"/닉네임 <플레이어 이름> <이름>");
				sender.sendMessage(TextFormat.YELLOW+"/채팅색 <플레이어 이름> <색코드>(§없이)");
				break;
			}
		}
	
		return super.onCommand(sender, command, label, args);
	}
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String name = player.getName();
		player.setNameTagVisible();
		
		try{
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)tagDB.get(name);
			player.setNameTag(map.get("tag").toString()+map.get("name").toString());
		}catch(Exception e){
			LinkedHashMap<String, Object> link = new LinkedHashMap<>();
			link.put("tag", TextFormat.YELLOW+"[유저]");
			link.put("chat-color","f");
			link.put("name","§f"+name);
			tagDB.set(name,link);
			player.sendMessage(TextFormat.AQUA+"칭호를 생성합니다.");
			save();
			player.setNameTag(TextFormat.YELLOW+"[유저]"+name);
		}
	}
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onChat(PlayerChatEvent event){
		event.setCancelled();
		Player player = event.getPlayer();
		String name = player.getName();
		String message = event.getMessage();
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)tagDB.get(name);
		getServer().broadcastMessage(map.get("tag").toString()+" "+map.get("name")+"§"+map.get("chat-color")+" > "+message);
	}
	@EventHandler
	public void onSignChange(SignChangeEvent event){
		Player player = event.getPlayer();
		
		if(event.getLine(0).equals("칭호상점")||event.getLine(0).equals("tagshop")||event.getLine(0).equals("§a[칭호상점]")){
			if(!player.isOp()){
				player.sendMessage(TextFormat.RED+"칭호상점 생성권한이 없습니다.");
				event.setCancelled();
			}
			else if(!isDouble(event.getLine(2))){
				player.sendMessage(TextFormat.RED+"칭호상점이 잘못 생성되었습니다.");
				event.setCancelled();
			}
			else{
				event.setLine(0, "§a[칭호상점]");
				player.sendMessage(TextFormat.AQUA+"칭호상점을 생성하였습니다.");
			}
		}
		if(event.getLine(0).equals("닉네임상점")||event.getLine(0).equals("nameshop")||event.getLine(0).equals("§c[닉네임상점]")){
			if(!player.isOp()){
				player.sendMessage(TextFormat.RED+"닉네임상점 생성권한이 없습니다.");
				event.setCancelled();
			}
			else if(!isDouble(event.getLine(2))){
				player.sendMessage(TextFormat.RED+"닉네임상점이 잘못 생성되었습니다.");
				event.setCancelled();
			}
			else{
				event.setLine(0, "§c[닉네임상점]");
				player.sendMessage(TextFormat.AQUA+"닉네임상점을 생성하였습니다.");
			}
		}
		if(event.getLine(0).equals("채팅색상점")||event.getLine(0).equals("colorshop")||event.getLine(0).equals("§e[채팅색상점]")){
			if(!player.isOp()){
				player.sendMessage(TextFormat.RED+"채팅색상점 생성권한이 없습니다.");
				event.setCancelled();
			}
			else if(!isDouble(event.getLine(2))){
				player.sendMessage(TextFormat.RED+"채팅색상점이 잘못 생성되었습니다.");
				event.setCancelled();
			}
			else{
				event.setLine(0, "§e[채팅색상점]");
				player.sendMessage(TextFormat.AQUA+"채팅색상점을 생성하였습니다.");
			}
		}
	}
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onTouch(PlayerInteractEvent event){
		if((event.getBlock().getId()!=63)&&(event.getBlock().getId()!=68))return;
		BlockEntity blockEntity = event.getBlock().getLevel().getBlockEntity(new Vector3(event.getBlock().getX(),event.getBlock().getY(),event.getBlock().getZ()));
		if(!(blockEntity instanceof BlockEntitySign))return;	
		BlockEntitySign sign = (BlockEntitySign)blockEntity;
		if(sign.getText()[0].equalsIgnoreCase("§a[칭호상점]")){
			try{
				if(api.myMoney(event.getPlayer())>Integer.parseInt(sign.getText()[2])){
					event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]칭호 "+sign.getText()[1]+"(을)를 구매하였습니다.");
					this.api.reduceMoney(event.getPlayer(), Integer.parseInt(sign.getText()[2]));
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)tagDB.get(event.getPlayer().getName());
					map.put("tag", sign.getText()[1]);
					map.put("chat-color", map.get("chat-color").toString());
					map.put("name", map.get("name").toString());
					tagDB.set(event.getPlayer().getName(), map);
					save();
					event.getPlayer().setNameTag(sign.getText()[1]+map.get("name").toString());
				}
				else{
					event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]돈이 부족합니다.");
				}
			}catch (Exception e){
				event.getPlayer().sendMessage(TextFormat.AQUA+"EconomyAPI플러그인을 인식할 수 없습니다.");
			}
			event.setCancelled(true);
		}
		else if(sign.getText()[0].equalsIgnoreCase("§c[닉네임상점]")){
			try{
				if(api.myMoney(event.getPlayer())>Integer.parseInt(sign.getText()[2])){
					event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]닉네임 "+sign.getText()[1]+"(을)를 구매하였습니다.");
					this.api.reduceMoney(event.getPlayer(), Integer.parseInt(sign.getText()[2]));
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)tagDB.get(event.getPlayer().getName());
					map.put("tag", map.get("tag").toString());
					map.put("chat-color", map.get("chat-color").toString());
					map.put("name", sign.getText()[1]);
					tagDB.set(event.getPlayer().getName(), map);
					save();
					event.getPlayer().setNameTag(map.get("tag").toString()+sign.getText()[1]);
				}
				else{
					event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]돈이 부족합니다.");
				}
			}catch (Exception e){
				event.getPlayer().sendMessage(TextFormat.AQUA+"EconomyAPI플러그인을 인식할 수 없습니다.");
			}
			event.setCancelled(true);
		}
		else if(sign.getText()[0].equalsIgnoreCase("§e[채팅색상점]")){
			try{
				if(api.myMoney(event.getPlayer())>Integer.parseInt(sign.getText()[2])){
					event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]채팅색 §"+sign.getText()[1]+"이 색 "+TextFormat.AQUA+"(을)를 구매하였습니다.");
					this.api.reduceMoney(event.getPlayer(), Integer.parseInt(sign.getText()[2]));
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)tagDB.get(event.getPlayer().getName());
					map.put("tag", map.get("tag").toString());
					map.put("chat-color", sign.getText()[1]);
					map.put("name", map.get("chat-color").toString());
					tagDB.set(event.getPlayer().getName(), map);
					save();
				}
				else{
					event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]돈이 부족합니다.");
				}
			}catch (Exception e){
				event.getPlayer().sendMessage(TextFormat.AQUA+"EconomyAPI플러그인을 인식할 수 없습니다.");
			}
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		if((event.getBlock().getId()!=63)&&(event.getBlock().getId()!=68))return;
		BlockEntity blockEntity = event.getBlock().getLevel().getBlockEntity(new Vector3(event.getBlock().getX(),event.getBlock().getY(),event.getBlock().getZ()));
		if(!(blockEntity instanceof BlockEntitySign))return;	
		BlockEntitySign sign = (BlockEntitySign)blockEntity;
		String m1 = sign.getText()[0];
		if(m1.equalsIgnoreCase("§a[칭호상점]")||m1.equalsIgnoreCase("§c[닉네임상점]")||m1.equalsIgnoreCase("§e[채팅색상점]")){
			if(!event.getPlayer().isOp()){
				event.setCancelled();
			}
			else{
				event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]상점을 삭제했습니다.");
			}
		}
	}
	public void save(){
		tagDB.save();
	}
	public boolean isDouble(String s){
		try{
			Double.parseDouble(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}