package wizard.factories;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;
import net.is_bg.ltf.db.common.interfaces.logging.ILogFactory;
import swing.utils.GraphicsConsole;
import file.utils.FileHandle;

public class LogFactory implements ILogFactory{

		private GraphicsConsole console;
		
		public LogFactory(GraphicsConsole console, FileHandle file) {
			// TODO Auto-generated constructor stub
			this.console = console;
		}
		
		@Override
		public ILog getLog(Class<?> arg0) {
			// TODO Auto-generated method stub
			return new LogGraphicsConsole(console);
		}
		
		
}
