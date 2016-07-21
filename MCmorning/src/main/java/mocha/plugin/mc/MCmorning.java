package mocha.plugin.mc;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import mocha.plugin.mc.task.task;

public class MCmorning extends PluginBase implements Listener{

	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		super.onEnable();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(TextFormat.GREEN +pd.getName()+" v"+pd.getVersion()+" LOADING ///");
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MOCHA-EULA:"+pd.getWebsite());
		Task task = new task();
		this.getServer().getScheduler().scheduleDelayedRepeatingTask(task, 0, 6000);
	}
	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		super.onDisable();
		this.getLogger().info(TextFormat.DARK_RED + "본 플러그인은 MOCHA-EULA 최종 사용자 라이센스를 사용중입니다.");
		this.getLogger().info(TextFormat.DARK_RED + "MCOHA-EULA:"+pd.getWebsite());
	}
}	