package export.oracle.statements.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import export.common.DataObjectsInterface.OBJEC_TYPE;
import export.common.User;
import export.common.UserObject;

public class SelectChekcedObjectForExportOrcl extends SelectSqlStatement{
	/*
	 * select t.name, t.type, t.text, 
	   decode(t.line, 1,'create or replace ', '' ) ||  t.text
	   from user_source t  
	   where  1=1 
	   and t.name = 'TAXVALUATION';
	 */
	private String ObjName = "";
	private User user;
	private OBJEC_TYPE type;
	
	List<IAbstractModel> result = new ArrayList<IAbstractModel>();
	
	public SelectChekcedObjectForExportOrcl(User user, String objName) {
		// TODO Auto-generated constructor stub
		this.ObjName = objName;
		this.user = user;
	}
	
	public SelectChekcedObjectForExportOrcl(User user, String objName, OBJEC_TYPE type) {
		// TODO Auto-generated constructor stub
		this.ObjName = objName;
		this.user = user;
		this.type = type;
	}
	
	

	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		String sql = "   select --t.name, t.type, t.text, \n"+
			   "   decode(t.line, 1,'create or replace ', '' ) ||  t.text definiton "+
			   "   from all_source t  "+ 
			   "   where  1=1 ";
			   if(type != null){sql +=" and t.type = "+type.getType()+" ";}
			   sql +="   and upper(t.name)  = upper('"+ ObjName + "') " +
			   "   and upper(t.owner) = upper('"+ user.getUsrName() +"')";
		return sql;
	}
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		UserObject obj = new UserObject();
		obj.setObjname(ObjName);
		while(rs.next()){
			obj.getObjectDefinition().append(rs.getString("definiton"));
		}
		result.add(obj);
	}

	public List<IAbstractModel> getResult() {
		return result;
	}
	
	
}
