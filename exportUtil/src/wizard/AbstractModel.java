package wizard;

import net.is_bg.ltf.db.common.interfaces.IAbstractModel;

public class AbstractModel implements IAbstractModel{

	private long id;
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public long getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public void setIndex(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
