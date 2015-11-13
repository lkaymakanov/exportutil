package wizard.factories;

import net.is_bg.ltf.db.common.impl.timer.ElapsedTimer;
import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimer;
import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimerFactory;

public class ElapsedTimerFactory implements IElaplsedTimerFactory{

	@Override
	public IElaplsedTimer getElapsedTimer() {
		// TODO Auto-generated method stub
		return new ElapsedTimer();
	}

}
