package export.common;

import wizard.AbstractModel;


public class UserObject extends AbstractModel{


	private static final long serialVersionUID = -6918380949861314466L;
	
	private boolean selected = true;
	private String Objname;
	private String ObjType;
	private String Owner;
	
	private String ObjectNameSpace;
	private StringBuffer objectDefinition = new StringBuffer();
	
	
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getObjname() {
		return Objname;
	}
	public void setObjname(String objname) {
		Objname = objname;
	}
	public String getObjType() {
		return ObjType;
	}
	public void setObjType(String objType) {
		ObjType = objType;
	}
	public StringBuffer getObjectDefinition() {
		return objectDefinition;
	}
	public String getObjectNameSpace() {
		return ObjectNameSpace;
	}
	public void setObjectNameSpace(String objectNameSpace) {
		ObjectNameSpace = objectNameSpace;
	}
	
}
