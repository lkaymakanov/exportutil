package export.postgre.statements.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import export.common.User;

public class SelectUsersPostgre extends SelectSqlStatement{

	List<IAbstractModel> result = new ArrayList<IAbstractModel>();
	
	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		return " select  t.usesysid, t.usename  from pg_user t  order by t.usename" ;
	}
	
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		while (rs.next()) {
			
			User pg = new User();
			pg.setUsrName(rs.getString("usename"));
			pg.setId(Long.valueOf(rs.getString("usesysid")));
			
			result.add(pg);
		}
	}


	public List<IAbstractModel> getResult() {
		return result;
	}

	
}
