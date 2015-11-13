package wizard.factories;

import java.sql.Connection;

import net.is_bg.ltf.db.common.ConnectionProperties;
import net.is_bg.ltf.db.common.impl.DataSourceConnectionFactoryDrManager;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;

public class ConnectionFactory  implements IConnectionFactory{

		
		private ConnectionProperties  pr;
		
	    public	ConnectionFactory(){
		
		}
		

		public ConnectionProperties getProperties() {
			return pr;
		}

		public void setProperties(ConnectionProperties pr) {
			this.pr = pr;
		}


		@Override
		public Connection getConnection() {
			// TODO Auto-generated method stub
			return new DataSourceConnectionFactoryDrManager(pr).getConnection();
		}
		
}
