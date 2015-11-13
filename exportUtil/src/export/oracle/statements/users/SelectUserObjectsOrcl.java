package export.oracle.statements.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import export.common.DataObjectsInterface.OBJEC_TYPE;
import export.common.UserObject;

public class SelectUserObjectsOrcl extends SelectSqlStatement{
	
	String user;
	

	
	
	private OBJEC_TYPE objType = null;
	List<IAbstractModel> result = new ArrayList<IAbstractModel>();
	
	public SelectUserObjectsOrcl(String user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}
	
	
	public SelectUserObjectsOrcl(String user, OBJEC_TYPE objType) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.objType = objType;
	}

	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
	 String sql = " select t.owner, t.object_name, t.object_type "+
		" from  "+ 
		" all_objects t  where 1 = 1 "+
		" and upper(t.owner) = upper('"+ user+ "') ";
		
	   
	 	if(objType != null){
			sql+=" and t.object_type  in (" +  getObjectTypeString(objType) + ") " ;
	    }
	 	
		sql +=" order by t.object_type, t.object_name ";
		return sql;
	}

	
	private String getObjectTypeString(OBJEC_TYPE otype){
		if(objType == null) return null;
		if(objType == OBJEC_TYPE.FUN) return OBJEC_TYPE.FUN.getType();
		if(objType == OBJEC_TYPE.PACK) return OBJEC_TYPE.PACK.getType();
		if(objType == OBJEC_TYPE.PROC) return OBJEC_TYPE.PROC.getType();
		return null;
	}
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		while(rs.next()){
			UserObject obj = new UserObject();
			
			obj.setOwner(rs.getString("owner"));
			obj.setObjname(rs.getString("object_name"));
			//obj.setObjecttype(rs.getString("object_type"));
			
			result.add(obj);
			
		}
	}


	public List<IAbstractModel> getResult() {
		return result;
	}
	
	
}
