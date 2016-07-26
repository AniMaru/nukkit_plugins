package mocha.plugin.mc;

import java.util.LinkedHashMap;
import java.util.Random;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import me.onebone.economyapi.EconomyAPI;

public class MCrandoms extends PluginBase implements Listener{
	public boolean warOn = false;
	public Config DB,config,player;

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		DB = new Config(getDataFolder()+"/DB.json",Config.JSON);
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("money", 10000);
		map.put("delay", 5);
		config = new Config(getDataFolder()+"/Config.yml",Config.JSON,map);
		player = new Config(getDataFolder()+"/player.yml",Config.YAML);
	}
	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		super.onDisable();
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MCOHA-EULA:"+pd.getWebsite());
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equals("전쟁시작")){
			if(!warOn){
				this.warOn = true;
				for(Player player : getServer().getOnlinePlayers().values()){
					player.getInventory().addItem(this.getRandomSword());
					player.getInventory().addItem(this.getRendomHelmet());
					player.getInventory().addItem(this.getRandomArmor());
					player.getInventory().addItem(this.getRandomPants());
					player.getInventory().addItem(this.getRandomBoots());
					DB.set(player.getName(), true);
					this.player.set(player.getName(), 0);
					save();
				}
				getServer().broadcastMessage(TextFormat.AQUA+"[알림]랜덤으로 무기가 지급되었습니다.");
			}
			else{
				getServer().broadcastMessage(TextFormat.AQUA+"[알림]이미 랜덤무기전쟁이 시작되었습니다.");
			}
		}
		if(command.getName().equals("전쟁종료")){
			getServer().broadcastMessage(TextFormat.AQUA+"[알림]랜덤무기전쟁이 관리자에 의해 강제로 종료되었습니다.");
			this.warOn = false;
		}
		return super.onCommand(sender, command, label, args);
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		event.setKeepInventory(true);
		Player player = event.getEntity();
		String name = player.getName();
		this.player.remove(name);
		save();
		if(warOn){
			this.getServer().broadcastMessage(TextFormat.RED+"남은사람");
			for(String s : this.player.getKeys()){
				this.getServer().broadcastMessage(TextFormat.RED+s);
			}
			if(this.player.getKeys().size() == 1){
				for(String s:this.player.getKeys()){
					getServer().broadcastMessage(TextFormat.LIGHT_PURPLE+"랜덤무기전쟁이 끝났습니다. 우승자:"+s);
					this.getServer().getPlayer(s).sendMessage(TextFormat.AQUA+"[알림]당신이 우승하셨습니다.");
					this.getServer().getPlayer(s).sendMessage(TextFormat.AQUA+"[알림]우승상금으로 "+config.getInt("money")+"원을 얻으셨습니다.");
					EconomyAPI.getInstance().addMoney(s, config.getInt("money"));
					EconomyAPI.getInstance().saveConfig();
					EconomyAPI.getInstance().saveDefaultConfig();
					this.player.remove(s);
				}
				for(String s:DB.getKeys()){
					this.DB.remove(s);
				}
				warOn = false;
				for(Player p : getServer().getOnlinePlayers().values()){
					p.getInventory().clearAll();
				}
			}
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(warOn){
			this.getServer().broadcastMessage(TextFormat.RED+"남은사람");
			for(String s : this.player.getKeys()){
				this.getServer().broadcastMessage(TextFormat.RED+s);
			}
			if(DB.get(player.getName()) == null){
				player.getInventory().addItem(this.getRandomSword());
				player.getInventory().addItem(this.getRendomHelmet());
				player.getInventory().addItem(this.getRandomArmor());
				player.getInventory().addItem(this.getRandomPants());
				player.getInventory().addItem(this.getRandomBoots());
				DB.set(player.getName(), true);
				this.player.set(player.getName(), 0);
				save();
				player.sendMessage(TextFormat.AQUA+"[알림]랜덤으로 무기가 지급되었습니다.");
			}
			else{
				player.sendMessage(TextFormat.RED+"남은사람 "+this.player.getKeys().size()+"명");
			}
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		String name = player.getName();
		this.player.remove(name);
		save();
		if(warOn){
			if(this.player.getKeys().size() == 1){
				for(String s:this.player.getKeys()){
					getServer().broadcastMessage(TextFormat.LIGHT_PURPLE+"랜덤무기전쟁이 끝났습니다. 우승자:"+s);
					this.getServer().getPlayer(s).sendMessage(TextFormat.AQUA+"[알림]당신이 우승하셨습니다.");
					EconomyAPI.getInstance().addMoney(s, config.getInt("money"));
					EconomyAPI.getInstance().saveConfig();
					EconomyAPI.getInstance().saveDefaultConfig();
					this.player.remove(s);
				}
				for(String s:DB.getKeys()){
					this.DB.remove(s);
				}
				warOn = false;
				for(Player p : getServer().getOnlinePlayers().values()){
					p.getInventory().clearAll();
				}
			}
		}
	}
	public Item getRandomSword(){
		Random random = new Random();
		int i = random.nextInt(100);
		if(i<30)
			return Item.get(Item.WOODEN_SWORD);
		else if(i<50)
			return Item.get(Item.STONE_SWORD);
		else if(i<70)
			return Item.get(Item.IRON_SWORD);
		else if(i<90)
			return Item.get(Item.DIAMOND_SWORD);
		else
			return Item.get(Item.AIR);
	}
	public Item getRendomHelmet(){
		Random random = new Random();
		int i = random.nextInt(100);
		if(i<30)
			return Item.get(Item.LEATHER_CAP);
		else if(i<50)
			return Item.get(Item.CHAIN_HELMET);
		else if(i<70)
			return Item.get(Item.IRON_HELMET);
		else if(i<90)
			return Item.get(Item.DIAMOND_HELMET);
		else
			return Item.get(Item.AIR);
	}
	public Item getRandomArmor(){
		Random random = new Random();
		int i = random.nextInt(100);
		if(i<30)
			return Item.get(Item.LEATHER_TUNIC);
		else if(i<50)
			return Item.get(Item.CHAIN_CHESTPLATE);
		else if(i<70)
			return Item.get(Item.IRON_CHESTPLATE);
		else if(i<90)
			return Item.get(Item.DIAMOND_CHESTPLATE);
		else
			return Item.get(0);
	}
	public Item getRandomPants(){
		Random random = new Random();
		int i = random.nextInt(100);
		if(i<30)
			return Item.get(Item.LEATHER_PANTS);
		else if(i<50)
			return Item.get(Item.CHAIN_LEGGINGS);
		else if(i<70)
			return Item.get(Item.IRON_LEGGINGS);
		else if(i<90)
			return Item.get(Item.DIAMOND_LEGGINGS);
		else
			return Item.get(0);
	}
	public Item getRandomBoots(){
		Random random = new Random();
		int i = random.nextInt(100);
		if(i<30)
			return Item.get(Item.LEATHER_BOOTS);
		else if(i<50)
			return Item.get(Item.CHAIN_BOOTS);
		else if(i<70)
			return Item.get(Item.IRON_BOOTS);
		else if(i<90)
			return Item.get(Item.DIAMOND_BOOTS);
		else
			return Item.get(0);
	}
	public void save(){
		DB.save();
		this.player.save();
	}
}	