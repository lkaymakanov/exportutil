package export.postgre.statements.users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import export.common.DataObjectsInterface.OBJEC_TYPE;
import export.common.User;
import export.common.UserObject;

public class SelectUserObjectsPostgre  extends SelectSqlStatement{

		User user;
		
		List<IAbstractModel> result = new ArrayList<IAbstractModel>();
		public SelectUserObjectsPostgre(User user) {
			// TODO Auto-generated constructor stub
			this.user = user;
		}
		
		
		public SelectUserObjectsPostgre(User user, OBJEC_TYPE objType) {
			// TODO Auto-generated constructor stub
			this(user);
		}

		@Override
		protected String getSqlString() {
			// TODO Auto-generated method stub
			String sql = "  select ns.nspname, ns.nspowner, ns.nspacl from pg_namespace ns "+
						 "	where 1=1  "+
						 "	and ns.nspowner = (select usesysid  "+
						 "	from pg_user "+
						 "	where 1=1 "+
						 "	and upper(usename) = upper('"+ user.getUsrName() + "')) order by nspname ";
			return sql;
		}

		/*
		private String getObjectTypeString(OBJEC_TYPE otype){
			if(objType == null) return null;
			if(objType == OBJEC_TYPE.FUN) return OBJEC_TYPE.FUN.getType();
			if(objType == OBJEC_TYPE.PACK) return OBJEC_TYPE.PACK.getType();
			if(objType == OBJEC_TYPE.PROC) return OBJEC_TYPE.PROC.getType();
			return null;
		}*/
		
		@Override
		protected void retrieveResult(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			while(rs.next()){
				UserObject obj = new UserObject();
				//obj.setNspname(rs.getString("nspname"));
				obj.setObjname(rs.getString("nspname"));
				obj.setOwner(rs.getString("nspowner"));
				//obj.setNspacl(rs.getString("nspacl"));
				
				result.add(obj);
			}
		}


		public List<IAbstractModel> getResult() {
			return result;
		}
		
		
	

}
