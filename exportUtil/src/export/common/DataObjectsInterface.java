package export.common;

import java.io.Serializable;
import java.util.List;

import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import wizard.AbstractModel;



public interface DataObjectsInterface extends Serializable{
	
	public enum DBTYPE{ORCL, POSTGRE};
	
	//the type of objects we are interested in - procedure, function & package
	public enum OBJEC_TYPE{
		
		PROC("'PROCEDURE'"), 
		FUN("'FUNCTION'"),
	    PACK("'PACKAGE'"),
	    PACK_HEAD("'PACKAGE'"),
	    PACK_BODY("'PACKAGE BODY'"),
	    SCHEMA(null);
		
		private String type ;
		
	    
	    OBJEC_TYPE(String s){
			type = s;
		}
	    
	    public String getType(){
	    	return type;
	    }
	};

	//get the data base users  
	public List<IAbstractModel> getUsers();
	
	
	//get userObjects this gets only objects names + definition
	public List<IAbstractModel> getUserObject(AbstractModel user, String objName);
	
	
	public List<IAbstractModel> getUserObject(AbstractModel user, String objName,  OBJEC_TYPE objtype);
	
	
	//get userObjects this gets only objects names + definition
	public List<IAbstractModel> getUserObject(AbstractModel user, OBJEC_TYPE objName);
	
	//get userObjects - this gets only objects names
	public List<IAbstractModel> getUserObjects(AbstractModel user);
	
}
