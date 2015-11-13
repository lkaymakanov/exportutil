package export.oracle.statements.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import export.common.User;

public class SelectUsersOrcl extends SelectSqlStatement{

	List<IAbstractModel> result = new ArrayList<IAbstractModel>();
	
	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		return " select user_id, username from all_users order by username";
	}
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		while (rs.next()) {
			
			User user = new User();
			user.setId(rs.getLong("user_id"));
			user.setUsrName(rs.getString("username"));
			
			result.add(user);
		}
	}

	public List<IAbstractModel> getResult() {
		return result;
	}

}
