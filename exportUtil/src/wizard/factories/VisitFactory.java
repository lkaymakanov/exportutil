package wizard.factories;

import net.is_bg.ltf.db.common.impl.visit.VisitEmpty;
import net.is_bg.ltf.db.common.interfaces.visit.IVisit;
import net.is_bg.ltf.db.common.interfaces.visit.IVisitFactory;

public class VisitFactory  implements IVisitFactory{

	@Override
	public IVisit getVist() {
		// TODO Auto-generated method stub
		return new VisitEmpty();
	}

}
