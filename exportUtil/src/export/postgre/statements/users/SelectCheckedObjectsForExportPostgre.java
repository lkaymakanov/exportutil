package export.postgre.statements.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import export.common.User;
import export.common.UserObject;

public class SelectCheckedObjectsForExportPostgre  extends SelectSqlStatement{
	
	/*
	 *select  array_to_string(array( select 
	 --'DROP FUNCTION ' || n.nspname || '.' || p.proname || '(' || oidvectortypes(proargtypes) || '); '   ||  
	pg_get_functiondef(p.oid) || ';' 
	
	from pg_proc p 
	join pg_namespace n on p.pronamespace = n.oid 
	where  1=1
	and n.nspowner = 168115
	and n.nspname in('advance_pkg' 
	)), '
	') definition 
	 */
	List<IAbstractModel> result = new ArrayList<IAbstractModel>();
	
	public List<IAbstractModel> getResult() {
		return result;
	}

	private String ObjName = "";
	private User usr;
	
	public SelectCheckedObjectsForExportPostgre(User usr, String objName) {
		// TODO Auto-generated constructor stub
		this.ObjName = objName;
		this.usr = usr;
	}
	
	

	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		return   
				
		 " select "+
         " 'DROP FUNCTION  IF EXISTS  ' || n.nspname || '.' || p.proname || '(' || oidvectortypes(proargtypes) || '); \n'   || "+
         "  pg_get_functiondef(p.oid) || ';' definition, "+
	     //"/*,'" + ObjName +"' || '.'||" +
	     " p.proname  prname "+
         " from pg_proc p "+
         " join pg_namespace n on p.pronamespace = n.oid "+
         " where  1=1 "+
         " and n.nspowner =  " + usr.getId()  +
         " and n.nspname in('"+ ObjName +"') " ;
				/*
				old stuff exports whole schema into one file
				" select  array_to_string(array( select \n"+
				  "'DROP FUNCTION  IF EXISTS  ' || n.nspname || '.' || p.proname || '(' || oidvectortypes(proargtypes) || '); \n'   || \n  "+
				  " pg_get_functiondef(p.oid) || ';' "+
				  " from pg_proc p "+
				  " join pg_namespace n on p.pronamespace = n.oid "+
				  " where  1=1 "+
				  " and n.nspowner =  "+ usr.getId() +
				  " and n.nspname in('"+ ObjName +"'"+
				  " )), '"+
				  " ') definition"; old version*/
	}
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		UserObject obj;
		while(rs.next()){
			obj = new UserObject();
			obj.setObjectNameSpace(ObjName);
			obj.setObjname(rs.getString("prname"));
			obj.getObjectDefinition().append(rs.getString("definition")+"\n");
			result.add(obj);
		}
	}

}
