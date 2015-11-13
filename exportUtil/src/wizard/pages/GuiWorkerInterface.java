package wizard.pages;

public interface GuiWorkerInterface {

	public enum TASKS{
		GET_USERS,
		GET_USEROBJECTS,
		EXPORT
	};
	
	public void getUsers();
	public void getUserObjects();
	public void exportObjects();
	public void enableButtons(boolean b);
}
