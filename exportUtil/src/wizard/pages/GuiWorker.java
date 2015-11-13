package wizard.pages;

import javax.swing.SwingWorker;
import wizard.pages.GuiWorkerInterface.TASKS;

public class GuiWorker extends SwingWorker<String, Void>{
	
	private GuiWorkerInterface  interf;
	private TASKS  task;

	
	//constructor
	public GuiWorker(GuiWorkerInterface i, TASKS task){
		interf = i;
		this.task = task;
	}
	
	//things done in background
	@Override
    public String doInBackground() {
		interf.enableButtons(false);
		doProcess();
        return "Done";
    }

	
	//do the actual work
	private  void doProcess(){
		System.out.println("Entered in doProcess...");
		if(task == TASKS.GET_USERS) interf.getUsers();
		if(task == TASKS.GET_USEROBJECTS) interf.getUserObjects();
		if(task == TASKS.EXPORT) interf.exportObjects();
	}
	
	
	
	//finished processing
    @Override
    public void done() {
        try {
        	System.out.println("Entered in done...");
        	//enable buttons on finish
        	interf.enableButtons(true);
        } 
        catch (Exception ignore) 
        {
        	
        }
    }
}