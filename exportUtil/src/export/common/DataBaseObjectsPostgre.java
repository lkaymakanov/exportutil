package export.common;

import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import wizard.AbstractModel;

public class DataBaseObjectsPostgre implements DataObjectsInterface{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DaoInterface dao;
	
	//set the daos
	public DataBaseObjectsPostgre(DaoInterface dao){
		this.dao = dao;
	}
	
	//in each method choose appropriate DAO
	
	@Override
	public List<IAbstractModel> getUsers() {
		// TODO Auto-generated method stub
		return dao.getPostgreDao().getUser();
	}

	@Override
	public List<IAbstractModel> getUserObject(AbstractModel user, String objName) {
		// TODO Auto-generated method stub
		return dao.getPostgreDao().getUserObjects((User)user, objName);
	}

	@Override
	public List<IAbstractModel> getUserObjects(AbstractModel user) {
		// TODO Auto-generated method stub
		return new ArrayList<IAbstractModel>();
	}
	



	@Override
	public List<IAbstractModel> getUserObject(AbstractModel user, OBJEC_TYPE objName) {
		// TODO Auto-generated method stub
		if(objName == OBJEC_TYPE.SCHEMA) return dao.getPostgreDao().getUserObjects(((User)user));
		return new ArrayList<IAbstractModel>();
	}

	@Override
	public List<IAbstractModel> getUserObject(AbstractModel user,
			String objName, OBJEC_TYPE objtype) {
		// TODO Auto-generated method stub
		return getUserObject(user, objName);
	}
	
}
