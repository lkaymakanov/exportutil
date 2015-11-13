package wizard.factories;

import swing.utils.GraphicsConsole;
import net.is_bg.ltf.db.common.impl.logging.LogSystemOut;
import net.is_bg.ltf.db.common.interfaces.logging.ILog;


public class LogGraphicsConsole implements  ILog{
	GraphicsConsole grConsole;
	ILog sysout = new LogSystemOut();
	
	public   LogGraphicsConsole(GraphicsConsole grConsole) {
		this.grConsole = grConsole;
	}
	
	@Override
	public void debug(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		
		sysout.debug(arg0);
	}

	@Override
	public void debug(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		
		sysout.debug(arg0 , arg1);
	}

	@Override
	public void error(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.error(arg0);
	}

	@Override
	public void error(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.error(arg0, arg1);
	}

	@Override
	public void fatal(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.fatal(arg0);
	}

	@Override
	public void fatal(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.fatal(arg0, arg1);
	}

	@Override
	public void info(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.info(arg0);
	}

	@Override
	public void info(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.info(arg0, arg1);
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trace(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.trace(arg0);
	}

	@Override
	public void trace(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
		sysout.trace(arg0, arg1);
	}

	@Override
	public void warn(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
	}

	@Override
	public void warn(Object arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		if(arg0 == null)grConsole.writeln(null);
		else grConsole.writeln(arg0.toString());
	}

}
