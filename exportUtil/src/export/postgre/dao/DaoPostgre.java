package export.postgre.dao;


import java.util.List;

import net.is_bg.ltf.db.common.AbstractMainDao;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import export.common.User;
import export.postgre.statements.users.SelectCheckedObjectsForExportPostgre;
import export.postgre.statements.users.SelectUserObjectsPostgre;
import export.postgre.statements.users.SelectUsersPostgre;

public class DaoPostgre extends AbstractMainDao{

	public DaoPostgre(IConnectionFactory c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	//select postgre users
	public List<IAbstractModel>  getUser(){
		SelectUsersPostgre usersel = new SelectUsersPostgre();
		execute(usersel);
		return usersel.getResult();
	}
		
	//select postgre user objects
	public List<IAbstractModel>  getUserObjects(User user){
		SelectUserObjectsPostgre  sel = new SelectUserObjectsPostgre(user);
		execute(sel);
		return sel.getResult();
	}
	
	
	//select definition for user object
	public List<IAbstractModel>  getUserObjects(User user, String object){
		SelectCheckedObjectsForExportPostgre  sel = new SelectCheckedObjectsForExportPostgre(user, object);
		execute(sel);
		return sel.getResult();
	}
	
	
}
