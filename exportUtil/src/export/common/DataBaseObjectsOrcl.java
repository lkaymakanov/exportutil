package export.common;

import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import wizard.AbstractModel;

public class DataBaseObjectsOrcl implements DataObjectsInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4524372976475987320L;
	DaoInterface dao;
	
	//set the daos
	public DataBaseObjectsOrcl(DaoInterface dao){
		this.dao = dao;
	}

	
	//in each method choose appropriate DAO
	@Override
	public List<IAbstractModel> getUsers() {
		// TODO Auto-generated method stub
		return dao.getOracleDao().getUser();
	}

	@Override
	public List<IAbstractModel> getUserObject(AbstractModel user, String objName) {
		// TODO Auto-generated method stub
		return dao.getOracleDao().getUserObjects(((User)user), objName);
	}

	@Override
	public List<IAbstractModel> getUserObjects(AbstractModel user) {
		// TODO Auto-generated method stub
		return dao.getOracleDao().getUserObjects(((User)user).getUsrName());
	}
	
	





	@Override
	public List<IAbstractModel> getUserObject(AbstractModel user, OBJEC_TYPE objName) {
		// TODO Auto-generated method stub
		if(objName == OBJEC_TYPE.SCHEMA) return new ArrayList<IAbstractModel>();
		return dao.getOracleDao().getUserObjects(((User)user).getUsrName(), objName);
	}

	
	private StringBuffer getPackage(AbstractModel user,
			String objName){
		List<IAbstractModel> l = getUserObject(user, objName, OBJEC_TYPE.PACK_HEAD);
		if(l == null || l.size() ==0 || l.get(0) == null) return new StringBuffer();
		return ((UserObject)l.get(0)).getObjectDefinition();
	}
	
	
	private StringBuffer getPackageBody(AbstractModel user,
			String objName){
		List<IAbstractModel> l = getUserObject(user, objName, OBJEC_TYPE.PACK_BODY);
		if(l == null || l.size() ==0 || l.get(0) == null) return new StringBuffer();
		return ((UserObject)l.get(0)).getObjectDefinition();
	}
	

	@Override
	public List<IAbstractModel> getUserObject(AbstractModel user,
			String objName, OBJEC_TYPE objtype) {
		
		if(objtype == null) return dao.getOracleDao().getUserObjects((User)user, objName);
		
		//we want packages
		if(objtype == OBJEC_TYPE.PACK){
			List<IAbstractModel> l = new ArrayList<IAbstractModel>();
			UserObject obj = new UserObject();
			obj.setObjname(objName);
			
			StringBuffer bufhead =  getPackage(user, objName);
			StringBuffer  packBody =  getPackageBody(user, objName);
			StringBuffer  def = bufhead.append("\n/\n").append(packBody).append("\n/\n");
			
			obj.getObjectDefinition().append(def);
			l.add(obj);
			
			return l;
		}
		
		// TODO Auto-generated method stub
		return  dao.getOracleDao().getUserObjects(((User)user), objName, objtype);
	}



   

}
