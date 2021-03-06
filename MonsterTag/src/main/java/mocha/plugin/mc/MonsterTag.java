/******************************
@author mocha127
@date 2016.7.24
******************************/
package mocha.plugin.mc;

import java.util.LinkedHashMap;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntitySpawnEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class MonsterTag extends PluginBase implements Listener{
	public Config tagDB; //Config 파일을 사용하기위해 선언

	@SuppressWarnings("deprecation")//사용을 권장하지 않음
	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();//plugin description 불러오기
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		getDataFolder().mkdirs();
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("Zombie","§a좀비");
		map.put("Silverfish","§a좀벌레");
		map.put("Blaze","§c블레이즈");
		map.put("Skeleton","§a스켈레톤");
		map.put("IronGolem","§a수호자");
		map.put("SnowGolem", "§f눈사람");
		map.put("Wolf","§a늑대");
		map.put("Ocelot","§b오셀롯");
		map.put("Rabbit","§a토끼");
		map.put("Cow","§a소");
		map.put("Pig","§a돼지");
		map.put("Sheep","§a양");
		map.put("Chicken","§b닭");
		map.put("ZombiePigman","§a좀비피그맨");
		map.put("Creeper","§a크리퍼");
		map.put("Spider","§a거미");
		map.put("Slime","§a젤리");
		map.put("EnderMan","§a엔더맨");
		map.put("CaveSpider","§a거미");
		map.put("Gast","§a젤리");
		map.put("MagmaCube", "§a마그마큐브");
		map.put("MooshRoom", "§e버섯소");
		tagDB = new Config(getDataFolder()+"/tagDB.yml",Config.YAML,map);//사용을 권장하지 않음
	}
	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		super.onDisable();
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MCOHA-EULA:"+pd.getWebsite());
		tagDB.save();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length<2){
			sender.sendMessage(TextFormat.BLUE+"/monstertag <몬스터> <칭호>");
		}
		else{
			switch (args[0].toLowerCase()) {
			case "좀비":
			case "zombie":
			case "z":
				tagDB.set("Zombie", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Zombie의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "silverfish":
			case "silver":
			case "좀벌레":
				tagDB.set("Silverfish", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Silverfish의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "skeleton":
			case "s":
			case "스켈레톤":
				tagDB.set("Blaze", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Blaze의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "blaze":
			case "b":
			case "블레이즈":
				tagDB.set("Skeleton", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Skeleton의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "irongolem":
			case "ig":
			case "골렘":
				tagDB.set("IronGolem", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]IronGolem의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "wolf":
			case "w":
			case "늑대":
			case "개":
				tagDB.set("Wolf", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Wolf의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "ocelot":
			case "o":
			case "오셀롯":
				tagDB.set("Ocelot", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Ocelot의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "rabbit":
			case "r":
			case "토끼":
				tagDB.set("Rabbit", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Rabbit의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "cow":
			case "c":
			case "소":
				tagDB.set("Cow", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Cow의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "pig":
			case "p":
			case "돼지":
				tagDB.set("Pig", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Pig의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "sheep":
			case "sh":
			case "양":
				tagDB.set("Sheep", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Sheep의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "chicken":
			case "ch":
			case "닭":
				tagDB.set("Chicken", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Chicken의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "zombiepigman":
			case "zp":
			case "좀비피그맨":
			case "좀비남자":
			case "돼지좀비":
				tagDB.set("ZombiePigman", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]ZombiePigman의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "snowgolem":
			case "sg":
			case "스노우골렘":
			case "눈골렘":
			case "눈사람":
				tagDB.set("SnowGolem", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]SnowGolem의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "creeper":
			case "cr":
			case "크리퍼":
				tagDB.set("Creeper", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Creeper의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "spider":
			case "sp":
			case "거미":
				tagDB.set("Spider", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Spider의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "slime":
			case "sl":
			case "슬라임":
				tagDB.set("Slime", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Slime의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "enderman":
			case "em":
			case "e":
			case "ender":
			case "엔더맨":
			case "엔더":
				tagDB.set("EnderMan", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]EnderMan의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "cavespider":
			case "cs":
			case "동굴거미":
				tagDB.set("CaveSpider", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]CaveSpider의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "gast":
			case "g":
			case "가스트":
				tagDB.set("Gast", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]Gast의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "mooshroom":
			case "mr":
			case "버섯소":
			case "버섯":
			case "좀비소":
				tagDB.set("MooshRoom", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]MooshRoom의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			case "magmacube":
			case "m":
			case "mm":
			case "마그마큐브":
				tagDB.set("MagmaCube", args[1]);
				sender.sendMessage(TextFormat.AQUA+"[알림]MagmaCube의 칭호를 "+args[1]+"으로 변경하였습니다.");
				tagDB.save();
				break;
			default:
				sender.sendMessage(TextFormat.BLUE+"/monstertag <몬스터> <칭호>");
				sender.sendMessage(TextFormat.YELLOW+"몬스터 종류");
				sender.sendMessage("Zombie,Silverfish,Blaze,Skeleton");
				sender.sendMessage("IronGolem,Wolf,Ocelot,Rabbit");
				sender.sendMessage("Cow,Pig,Sheep,Chicken,MooshRoom");
				sender.sendMessage("ZombiePigman,Creeper,Spider,Slime");
				sender.sendMessage("EnderMan,CaveSpider,Gast,MagmaCube");
				break;
			}
		}
		return super.onCommand(sender, command, label, args);
	}
	@EventHandler //nukkit 이벤트를 받아오기위한 애너테이션
	public void onSpawn(EntitySpawnEvent event){
		Entity entity = event.getEntity();
		event.getEntity().setNameTagVisible();
		if(!event.isHuman()){
			if(event.getType() == 10){
				entity.setNameTag(this.tagDB.getString("Chicken"));
			}
			else if(event.getType() == 32){
				entity.setNameTag(this.tagDB.getString("Zombie"));		
			}
			else if(event.getType() == 39){
				entity.setNameTag(this.tagDB.getString("Silverfish"));
			}
			else if(event.getType() == 43){
				entity.setNameTag(this.tagDB.getString("Blaze"));
			}
			else if(event.getType() == 34){
				entity.setNameTag(this.tagDB.getString("Skeleton"));
			}
			else if(event.getType() == 20){
				entity.setNameTag(this.tagDB.getString("IronGolem"));
			}
			else if(event.getType() == 14){
				entity.setNameTag(this.tagDB.getString("Wolf"));
			}
			else if(event.getType() == 18){
				entity.setNameTag(this.tagDB.getString("Rabbit"));
			}
			else if(event.getType() == 22){
				entity.setNameTag(this.tagDB.getString("Ocelot"));
			}
			else if(event.getType() == 11){
				entity.setNameTag( this.tagDB.getString("Cow"));
			}
			else if(event.getType() == 12){
				entity.setNameTag( this.tagDB.getString("Pig"));
			}
			else if(event.getType() == 13){
				entity.setNameTag( this.tagDB.getString("Sheep"));
			}
			else if(event.getType() == 36){
				entity.setNameTag( this.tagDB.getString("ZombiePigman"));
			}
			else if(event.getType() == 21){
				entity.setNameTag( this.tagDB.getString("SnowGolem"));
			}
			else if(event.getType() == 33){
				entity.setNameTag( this.tagDB.getString("Creeper"));
			}
			else if(event.getType() == 35){
				entity.setNameTag( this.tagDB.getString("Spider"));
			}
			else if(event.getType() == 37){
				entity.setNameTag( this.tagDB.getString("Slime"));
			}
			else if(event.getType() == 38){
				entity.setNameTag( this.tagDB.getString("EnderMan"));
			}
			else if(event.getType() == 40){
				entity.setNameTag( this.tagDB.getString("CaveSpider"));
			}
			else if(event.getType() == 41){
				entity.setNameTag( this.tagDB.getString("Gast"));
			}
			else if(event.getType() == 42){
				entity.setNameTag( this.tagDB.getString("MagmaCube"));
			}
			else if(event.getType() == 16){
				entity.setNameTag( this.tagDB.getString("MooshRoom"));
			}
		}
	}
}	