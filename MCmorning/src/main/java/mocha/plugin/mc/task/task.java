package mocha.plugin.mc.task;

import cn.nukkit.Server;
import cn.nukkit.scheduler.Task;

public class task extends Task{
	@Override
	public void onRun(int arg0) {
		Server.getInstance().dispatchCommand(Server.getInstance().getConsoleSender(), "time set day");
		Server.getInstance().broadcastMessage("아침이 되었습니다.");
	}
}