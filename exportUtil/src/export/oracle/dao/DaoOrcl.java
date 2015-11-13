package export.oracle.dao;


import java.util.List;

import net.is_bg.ltf.db.common.AbstractMainDao;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import export.common.DataObjectsInterface.OBJEC_TYPE;
import export.common.User;
import export.oracle.statements.users.SelectChekcedObjectForExportOrcl;
import export.oracle.statements.users.SelectUserObjectsOrcl;
import export.oracle.statements.users.SelectUsersOrcl;

public class DaoOrcl extends AbstractMainDao{

	public DaoOrcl(IConnectionFactory c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	
	//select oracle users
	public List<IAbstractModel>  getUser(){
		SelectUsersOrcl usersel = new SelectUsersOrcl();
		execute(usersel);
		return usersel.getResult();
	}
	
	
	//select Object for user 
	public List<IAbstractModel>  getUserObjects(String user){
		SelectUserObjectsOrcl sel= new SelectUserObjectsOrcl(user);
		execute(sel);
		return sel.getResult();
	}
	
	
	
	//select Object for user & type
	public List<IAbstractModel>  getUserObjects(String user, OBJEC_TYPE type){
		SelectUserObjectsOrcl sel= new SelectUserObjectsOrcl(user, type);	
		execute(sel);
		return sel.getResult();
	}
	
	
	//select definiton for user object
	public List<IAbstractModel>  getUserObjects(User user, String Objname){
	    SelectChekcedObjectForExportOrcl sel= new SelectChekcedObjectForExportOrcl(user, Objname);	
		execute(sel);
		return sel.getResult();
	}

	//select definiton for user object
	public List<IAbstractModel> getUserObjects(User user, String objName,
			OBJEC_TYPE objtype) {
		// TODO Auto-generated method stub
		SelectChekcedObjectForExportOrcl sel= new SelectChekcedObjectForExportOrcl(user, objName, objtype);	
		execute(sel);
		return sel.getResult();
	}

}
