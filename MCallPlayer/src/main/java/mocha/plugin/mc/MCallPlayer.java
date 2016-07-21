package mocha.plugin.mc;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityShootBowEvent;
import cn.nukkit.event.entity.ExplosionPrimeEvent;
import cn.nukkit.event.inventory.CraftItemEvent;
import cn.nukkit.event.inventory.InventoryOpenEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class MCallPlayer extends PluginBase implements Listener{
	public Config ap;
	
	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		ap = new Config(getDataFolder()+"/AllPlayerDB.json",Config.JSON);
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
			if(command.getName().equals("올플")){
				
				if(args[0].equals("전체")){
					ap.set("move", true);
					ap.set("chat", true);
					ap.set("drop", true);
					ap.set("command", true);
					ap.set("break", true);
					ap.set("place", true);
					ap.set("fight", true);
					ap.set("explode", true);
					ap.set("bow", true);
					ap.set("craft", true);
					ap.set("inventory", true);
					ap.set("damage", true);
					sender.sendMessage(TextFormat.AQUA+"[알림]모든 행위를 제어합니다.");
					this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 모든행위가 제어됩니다.");
					save();
				}
				else if(args[0].equals("전체풀기")){
					ap.set("move", false);
					ap.set("chat", false);
					ap.set("drop", false);
					ap.set("command", false);
					ap.set("break", false);
					ap.set("place", false);
					ap.set("fight", false);
					ap.set("explode", false);
					ap.set("bow", false);
					ap.set("craft", false);
					ap.set("inventory", false);
					ap.set("damage", false);
					sender.sendMessage(TextFormat.AQUA+"[알림]모든 행위의 제어를 풉니다.");
					this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 모든행위의 제어를 풉니다.");
					save();
				}
				else if(args[0].equals("움직임")){
					if(ap.getBoolean("move")){
						ap.set("move", false);
						save();
					}
					else{
						ap.set("move", true);
						save();
					}
					if(ap.getBoolean("move")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 움직임이 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 움직임의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("채팅")){
					if(ap.getBoolean("chat")){
						ap.set("chat", false);
						save();
					}
					else{
						ap.set("chat", true);
						save();
					}
					if(ap.getBoolean("chat")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 채팅이 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 채팅의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("드롭")){
					if(ap.getBoolean("drop")){
						ap.set("drop", false);
						save();
					}
					else{
						ap.set("drop", true);
						save();
					}
					if(ap.getBoolean("drop")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 아이템 떨어뜨리기가 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 아이템 떨어뜨리기의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("명령어")){
					if(ap.getBoolean("command")){
						ap.set("command", false);
						save();
					}
					else{
						ap.set("command", true);
						save();
					}
					if(ap.getBoolean("command")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 모든 명령어가 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 명령어 입력의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("블럭부수기")){
					if(ap.getBoolean("break")){
						ap.set("break", false);
						save();
					}
					else{
						ap.set("break", true);
						save();
					}
					if(ap.getBoolean("break")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 블럭 부수기가 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 블럭 부수기의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("블럭설치")){
					if(ap.getBoolean("place")){
						ap.set("place", false);
						save();
					}
					else{
						ap.set("place", true);
						save();
					}
					if(ap.getBoolean("place")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 블럭 설치가 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 블럭 설치의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("싸움")){
					if(ap.getBoolean("fight")){
						ap.set("fight", false);
						save();
					}
					else{
						ap.set("fight", true);
						save();
					}
					if(ap.getBoolean("fight")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 싸움이 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 싸움의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("데미지")){
					if(ap.getBoolean("damage")){
						ap.set("damage", false);
						save();
					}
					else{
						ap.set("damage", true);
						save();
					}
					if(ap.getBoolean("damage")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 데미지가 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 데미지의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("활")){
					if(ap.getBoolean("bow")){
						ap.set("bow", false);
						save();
					}
					else{
						ap.set("bow", true);
						save();
					}
					if(ap.getBoolean("bow")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 활이 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 활의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("폭발")){
					if(ap.getBoolean("explode")){
						ap.set("explode", false);
						save();
					}
					else{
						ap.set("explode", true);
						save();
					}
					if(ap.getBoolean("explode")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 폭발이 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 폭발의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("만들기")){	
					if(ap.getBoolean("craft")){
						ap.set("craft", false);
						save();
					}
					else{
						ap.set("craft", true);
						save();
					}
					if(ap.getBoolean("craft")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 만들기가 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 만들기의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("인벤열기")){	
					if(ap.getBoolean("inventory")){
						ap.set("inventory", false);
						save();
					}
					else{
						ap.set("inventory", true);
						save();
					}
					if(ap.getBoolean("inventory")){
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 인벤토리 열기가 제어됩니다.");
					}
					else{
						this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님에 의해 인벤토리 열기의 제어가 풀립니다.");
					}
				}
				else if(args[0].equals("킥")){
					for(Player player : this.getServer().getOnlinePlayers().values()){
						player.kick("you are kicked by "+sender.getName());
					}
				}
				else if(args[0].equals("밴")){
					for(Player player : this.getServer().getOnlinePlayers().values()){
						this.getServer().getNameBans().addBan(player.getName(), "you are Banned by "+sender.getName(), null, sender.getName());
					}
				}
				else if(args[0].equals("킬")){
					for(Player player : this.getServer().getOnlinePlayers().values()){
						player.kill();
						this.getServer().broadcastMessage(TextFormat.AQUA+"[알림]모든 사람이 "+sender.getName()+" 님에 의해 죽었습니다.");
					}
				}
				else if(args[0].equals("티피")){
					try{
						Player sendPlayer = (Player)sender;
						for(Player player : this.getServer().getOnlinePlayers().values()){
							Position pos = new Position(sendPlayer.getX(),sendPlayer.getY(),sendPlayer.getZ());
							player.teleport(pos);
							this.getServer().broadcastMessage(TextFormat.AQUA+"[알림]모두가 "+sender.getName()+" 에게로 티피되었습니다.");
						}
					}catch(Exception e){
						sender.sendMessage(TextFormat.RED+"게임 안에서만 쓸 수 있는 명령어 입니다.");
					}
				}
				else if(args[0].equals("아이템주기")){
					try{
						Item item=Item.fromString(args[1]);
						if(item.getId() == 0){
							sender.sendMessage(TextFormat.AQUA+"아이템코드가 잘못 되었습니다.");
						}
						else{
							for(Player player : this.getServer().getOnlinePlayers().values()){
								player.getInventory().addItem(new Item(item.getId(), item.getDamage(), Integer.parseInt(args[2])));
							}
							this.getServer().broadcastMessage(TextFormat.AQUA+"[서버]"+sender.getName()+"님이 모두에게 "+item.getName()+"를 "+args[2]+" 만큼 지금하셨습니다. 인벤토리를 확인 해 보세요.");
							
						}
					}catch (Exception e) {
						sender.sendMessage(TextFormat.AQUA+"/올플 아이템주기 <아이템 코드> <수량>");
					}
				}
				else if(args[0].equals("디오피")){
					for(Player player : this.getServer().getOnlinePlayers().values()){
						if(player.isOp()){
							player.setOp(false);
							player.sendMessage(TextFormat.GRAY+"You are no longer op!");
						}
						this.getServer().broadcastMessage(TextFormat.RED+"[주의]"+sender.getName()+"가 모두에게 디오피 하였습니다.");
					}
				}
				else{
					allPlayerError(sender, Integer.parseInt(args[0]));
				}
			}
		}catch(Exception e){
			allPlayerError(sender, 1);
			sender.sendMessage(TextFormat.AQUA+"[알림]/올플 <페이지>");
		}
		return super.onCommand(sender, command, label, args);
	}
	@EventHandler
	public void onMove(PlayerMoveEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("move")){
			event.setCancelled();
			event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 움직임이 제어되고 있습니다");
			}
		}
	}
	@EventHandler
	public void onChat(PlayerChatEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("chat")){
			event.setCancelled();
			event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 채팅이 금지되고 있습니다.");
			}
		}
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("drop")){
			event.setCancelled();
			event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 아이템을 떨어뜨릴 수 없습니다.");
			}
		}
	}
	@EventHandler
	public void onBow(EntityShootBowEvent event){
		try{
			Player player = (Player)event.getEntity();
			if(!player.isOp()){
				if(ap.getBoolean("bow")){
					event.setCancelled();
					player.sendMessage(TextFormat.AQUA+"[알림]서버에 의해 활을 쏘실 수 없습니다.");
				}
			}	
		}catch (Exception e) {
			event.setCancelled();
		}
	}
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("command")){
			event.setCancelled();
			event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 명령어 입력이 금지되고 있습니다.");
			}
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("break")){
				event.setCancelled();
				event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 블럭 부수기가 금지되고 있습니다.");
			}	
		}
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("place")){
				event.setCancelled();
				event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 블럭 설치가 금지되고 있습니다.");
			}
		}
	}
	@EventHandler
	public void onPvp(EntityDamageByEntityEvent event){
		try{
			Player player = (Player)event.getDamager();
			if(!player.isOp()){
				if(ap.getBoolean("fight")){
					event.setCancelled();
					player.sendMessage(TextFormat.AQUA+"[알림]서버에 의해 싸움이 금지되고 있습니다.");
				}
			}
		}catch(Exception e){
			if(ap.getBoolean("damage")){
				event.setCancelled();
			}
		}
	}
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		event.setCancelled();
	}
	@EventHandler
	public void onExplode(ExplosionPrimeEvent event){
		if(ap.getBoolean("explode")){
			event.setCancelled();
		}
	}
	@EventHandler
	public void onCraft(CraftItemEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("craft")){
				event.setCancelled();
				event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 만들기가 금지되고 있습니다.");
			}
		}
	}
	@EventHandler
	public void onOpenInventiry(InventoryOpenEvent event){
		if(!event.getPlayer().isOp()){
			if(ap.getBoolean("inventory")){
				event.setCancelled();
				event.getPlayer().sendMessage(TextFormat.AQUA+"[알림]서버에 의해 인벤토리 열기가 금지되고 있습니다.");
			}
		}
	}
	public void allPlayerError(CommandSender sender,int x){
		if(x == 1){
			String[] str = {"/올플 전체 - 모든 설정을 ON 으로 바꿉니다","/올플 전체풀기 - 모든 설정을 OFF 으로 바꿉니다","/올플 움직임 - 모든 플레이어가 움직이지 못하게 합니다 한번더 입력시 ON","/올플 채팅 - 모든 플레이어가 채팅을 못하게 됩니다 한번더 입력시 ON","/올플 드롭 - 모든 플레이어가 아이템을 버리지 못합니다 한번더 입력시 ON"};
			sender.sendMessage(TextFormat.BLUE+"=== 올플 명령어 사용법(1/5) ===");
			for(int i=0;i<str.length;i++){
				sender.sendMessage(TextFormat.BLUE+str[i]);
			}
		}
		else if(x == 2){
			String[] str = {"/올플 섭취 - 모든 플레이어가 아이템 섭취를 못합니다 한번더 입력시 ON","/올플 명령어 - 모든 플레이어가 명령어를 못치게 됩니다 한번더 입력시 ON","/올플 블럭부수기 - 모든 플레이어가 블럭을 못 부수게 합니다 한번더 입력시 ON","/올플 블럭설치 - 모든 플레이어가 블럭을 못 설치하게 합니다 한번더 입력시 ON","/올플 싸움 - 모든 플레이어가 싸움을 못하게 합니다 한번더 입력시 ON"};
			sender.sendMessage(TextFormat.BLUE+"=== 올플 명령어 사용법(2/5) ===");
			for(int i=0;i<str.length;i++){
				sender.sendMessage(TextFormat.BLUE+str[i]);
			}
		}
		else if(x == 3){
			String[] str = {"/올플 폭발 - 서버에 폭발 자국이 안생기게 합니다 한번더 입력시 ON","/올플 활 - 모든 플레이어가 활을 못쏘게 합니다 한번더 입력시 ON","/올플 만들기 - 모든 플레이어가 아이템을 못만들게 합니다 한번더 입력시 ON","/올플 인벤열기 - 모든 플레이어가 인벤을 못열게 합니다 한번더 입력시 ON","/올플 데미지 - 모든 플레이어가 데미지 제한을 받습니다 한번더 입력시 ON"};
			sender.sendMessage(TextFormat.BLUE+"=== 올플 명령어 사용법(3/5) ===");
			for(int i=0;i<str.length;i++){
				sender.sendMessage(TextFormat.BLUE+str[i]);
			}
		}
		else if(x == 4){
			String[] str = {"/올플 킥 - 모든 플레이어가 킥을 당합니다","/올플 벤 - 모든 플레이어가 벤을 당합니다","/올플 킬 - 모든 플레이어가 죽습니다","/올플 티피 - 모든 플레이어가 자신에게 티피합니다","/올플 아이템주기 <아이템> <수량> - 모든 플레이어에게 아이템을 줍니다"};
			sender.sendMessage(TextFormat.BLUE+"=== 올플 명령어 사용법(4/5) ===");
			for(int i=0;i<str.length;i++){
				sender.sendMessage(TextFormat.BLUE+str[i]);
			}
		}
		else if(x == 5){
			String[] str = {"/올플 디오피 - 모든 플레이어를 디오피 합니다","/올플 <페이지> - 올플레이어에 대한 명령어를 봅니다"};
			sender.sendMessage(TextFormat.BLUE+"=== 올플 명령어 사용법(5/5) ===");
			for(int i=0;i<str.length;i++){
				sender.sendMessage(TextFormat.BLUE+str[i]);
			}
		}
		else{
			String[] str = {"/올플 전체 - 모든 설정을 ON 으로 바꿉니다","/올플 전체풀기 - 모든 설정을 OFF 으로 바꿉니다","/올플 움직임 - 모든 플레이어가 움직이지 못하게 합니다 한번더 입력시 ON","/올플 채팅 - 모든 플레이어가 채팅을 못하게 됩니다 한번더 입력시 ON","/올플 드롭 - 모든 플레이어가 아이템을 버리지 못합니다 한번더 입력시 ON"};
			sender.sendMessage(TextFormat.BLUE+"=== 올플 명령어 사용법(1/5) ===");
			for(int i=0;i<str.length;i++){
				sender.sendMessage(TextFormat.BLUE+str[i]);
			}
		}
	}
	public void save(){
		ap.save();
	}
	public boolean isDouble(String args){
		try{
			Double.parseDouble(args);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}