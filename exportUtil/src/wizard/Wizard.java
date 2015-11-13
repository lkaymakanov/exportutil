package wizard;


import helpfun.HelpFunctions;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.is_bg.ltf.db.common.ConnectionProperties;
import net.is_bg.ltf.db.common.DBConfig;
import net.is_bg.ltf.db.common.impl.logging.LogFactorySystemOut;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import swing.utils.WizardButtons;
import wizard.factories.ConnectionFactory;
import wizard.factories.ElapsedTimerFactory;
import wizard.factories.LogFactory;
import wizard.factories.VisitFactory;
import wizard.pages.ConnectionPage;
import wizard.pages.ExportPage;
import wizard.pages.GuiWorker;
import wizard.pages.GuiWorkerInterface;
import wizard.pages.Page;
import wizard.pages.PageCallBack;
import wizard.pages.UserObjectPage;
import wizard.pages.UsersPage;
import export.common.DaoInterface;
import export.common.DataBaseObjectsOrcl;
import export.common.DataBaseObjectsPostgre;
import export.common.DataObjectsInterface;
import export.common.DataObjectsInterface.OBJEC_TYPE;
import export.common.User;
import export.common.UserObject;
import export.oracle.dao.DaoOrcl;
import export.postgre.dao.DaoPostgre;
import file.utils.FileSaver;


public class Wizard extends JFrame implements  DaoInterface, GuiWorkerInterface {
	
	

	private static final long serialVersionUID = 1782579312920885244L;
	private final static String ENCODING = "UTF-8";    //set the file encoding
	//private final static char BOM = '\ufeff';            //BOM SYmbol
	
	CardLayout card;
	JPanel wizpanel;
	
	//action listener 
	ActionListenHandler wizListener;
	
	
	
	//the pages
	ConnectionPage connPage;
	UsersPage  userPage;
	UserObjectPage userObjects;
	ExportPage exPage;
	
	
	
	//wizard sizes
	Dimension dLittle = new Dimension(400, 260);
	Dimension dBig = new Dimension(700, 600);
	Dimension dBtabs = new Dimension(450, 490);
	
	//the handler of page buttons click
	PageCallBackHandler pageCallBack;
	
	//the navigation buttons
	WizardButtons buttons = new WizardButtons();
	
	//navigation button panel
	JPanel buttonp = new JPanel();
	
	//the list with pages in wizard
	List<Page> pages = new ArrayList<Page>();
	
	
	//create    daos
	DaoOrcl     daoOrcl;
	DaoPostgre  daoPostgre;
	
	//objects interface reference
	DataObjectsInterface datObjInterface;
	
	//user object implements
	DataBaseObjectsOrcl    dbObjectsOrcl;
	DataBaseObjectsPostgre dbObjectsPostgre; 
			
	//the wizard itself
	private static Wizard wizard = null;
	
	//reference to selected user
	User usr;
	
	
	//public  DBTYPE dbType = DBTYPE.ORCL;
	
	//WHEHER WE SAFE ALL IN IN ONE FILE OR EACH OBJECT IN SEPARATE FILE
	static final String   FILE_NAME = "usr_obj_";
	
	
	//constructor
	public Wizard() {
		// TODO Auto-generated constructor stub
		super("LAK Export Util UTF-8 ");
	}

	//let it be a singleton pattern
	public static Wizard getWizard(){
		if(wizard == null) wizard = new Wizard();
		return wizard;
	}
		
	
	
	//Program Entry 
	public static void main(String []args){
		
		
		System.setProperty("file.encoding", ENCODING);
		
		//use the system style for GUI
	    final boolean  SYSTEM_LOOK_AND_FEEL = false;
		try {
			if(SYSTEM_LOOK_AND_FEEL)
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Wizard.getWizard();  //invoke this to initialize wizard reference
		wizard.init();
	}
	
	
	//set sizes & show wizard frame
	public void showWiz(){
		setSize(dLittle);
		setResizable(false);
		setVisible(true);
	}
	
	//init wizard
	private void init(){
		wizListener = new ActionListenHandler();
		
		wizpanel = new JPanel();
		
		card = new CardLayout();   //use card layout to switch panels
		wizpanel.setLayout(card);
		getContentPane().add(BorderLayout.CENTER, wizpanel);
		
		//this isn't probably the best GUI face made :)
	    getContentPane().add(BorderLayout.SOUTH,buttonp);
	    
	    //register buttons listener
	    buttons.AddActionListenter(wizListener);
	    
	    //init to first page
	    getCallBack().setStateFirstPage();
	    
	    //add the buttons to panel
		buttonp.add(buttons.getBtnBack());
		buttonp.add(buttons.getBtnConnect());
		buttonp.add(buttons.getBtnNext());
		
		
		//create pages
		connPage = new ConnectionPage();
		userPage = new UsersPage();
		exPage = new ExportPage();
		userObjects = new UserObjectPage();
		
		//add pages to page list
		pages.add(connPage);
		pages.add(userPage);
		pages.add(userObjects);	
		pages.add(exPage);
		
		//add pages to wizard
		for(int i = 0; i< pages.size() ; i++){
			wizpanel.add(String.valueOf(i), pages.get(i));
		}
		
		//init database objects interfaces
		dbObjectsOrcl = new DataBaseObjectsOrcl(this);
		dbObjectsPostgre = new DataBaseObjectsPostgre(this); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//init database factories
		DBConfig.initDBConfig(new LogFactory(exPage.getConsole(), null), new VisitFactory(), new ConnectionFactory(), new ElapsedTimerFactory());
		
		//init DAOS
		daoOrcl =    new DaoOrcl(DBConfig.getConnectionFactory());
		daoPostgre = new DaoPostgre(DBConfig.getConnectionFactory());
		
		//show the wizard
		showWiz();
	}
	
	//navigation button is pushed  -- handler
	public  void wizbuttonPushed(String btnName){
		
		//hadle connect
		if(btnName.equals("btnConnect")){
			btnConnectCliked();
		}
		
		//back button pushed
		if(btnName.equals("btnBack")){
			btnBackCliked();
		}
		
		
		//next button pushed
		if(btnName.equals("btnNext")){
			btnNextCliked();
		}
	}
	
	
	
	/**
	 * Set connection from properties.
	 * @param properties
	 */
	public static void setConnectionProperties(ConnectionProperties prop){
		getWizard().getConnPage().setDbName(prop.getName_to_display());
		((ConnectionFactory)DBConfig.getConnectionFactory()).setProperties(prop);
	}
	
	
	//handle btn connect
	private void btnConnectCliked(){
		System.out.println("btnConnect clicked... ");
		ConnectionProperties prop =  connPage.getConnectionProperties();
		
		//set the connection properties in connection factory
		setConnectionProperties(prop);
		
		//check if connection is postgre or oracle
		if(prop == null || prop.getUrl() == null){
			datObjInterface = null;
			return;
		}
		
		if(prop.getUrl().contains("@")){
			//oracle connection
			//dbType = DBTYPE.ORCL;
			datObjInterface = dbObjectsOrcl;			
		}
		else
		if(prop.getUrl().contains("//")){
			//postgre connection
			//dbType = DBTYPE.POSTGRE;
			datObjInterface = dbObjectsPostgre;
		}
		else{
			datObjInterface = null;
			return;
		}
		
		//select all ckboxes
		userObjects.setSelectedAll();
		
		//start get users  TASK
		GuiWorker wk = new GuiWorker(this, TASKS.GET_USERS);
		wk.execute();
			
	}
	

	//handle btn back
	private void btnBackCliked(){
		System.out.println("btn Back clicked... ");
		
		//we are on tabs
		if(Page.getCurrenPage() == 2){
			setSize(dLittle); //set small size
		}
		
		//goto previous page
		card.previous(wizpanel);
		
		//set state to previous page
		getCallBack().toPrevious();
	}
	
	//handle btn next
	private void btnNextCliked(){
		System.out.println("btn Next clicked... ");
		
		//we reached last page
		if(Page.getCurrenPage() == pages.size() - 1){
			System.out.println("Last Page... ");
			
			//DO THE EXPORT HERE
			//start export TASK
			GuiWorker wk = new GuiWorker(this, TASKS.EXPORT);
			wk.execute();
		}
		
		//we are on users page
		if(Page.getCurrenPage()  == 1){
			
			//set selectedall by default
			//userObjects.setSelectedAll();
			
			//start get user Objects TASK
			GuiWorker wk = new GuiWorker(this, TASKS.GET_USEROBJECTS);
			wk.execute();
		}
		
		//we are on usersObject page
		if(Page.getCurrenPage()  == 2){
			//goto export page
			goToNextPage();	
		}
}
	
	//now invoke in the thread routine
	private void goToNextPage(){
			//check for error first
			//if(isErr())  return;
	
			//goto next  page
			card.next(wizpanel);
			
			//set state to next page
			getCallBack().toNext();
	}

	
	//getters & setters
	public ActionListenHandler getWizListener() {		
		return wizListener;
	}


	public ConnectionPage getConnPage() {
		return connPage;
	}



	
	//pages call back implementation  class
	class PageCallBackHandler implements PageCallBack{
		
		//remove all buttons from panel
		private  void removeAllButtons(){
			buttons.getBtnConnect().setVisible(false);
			buttons.getBtnBack().setVisible(false);
			buttons.getBtnNext().setVisible(false);
		}
		
		//we go to first page
		private void setStateFirstPage(){
			removeAllButtons();
			
			//add only  connect button
			buttons.getBtnConnect().setVisible(true);
		}
		
		//we go to some page between first and last page
		private void setStateMiddlePage(){
			//remove connect  button
			removeAllButtons();
			buttons.getBtnBack().setVisible(true);
			buttons.getBtnNext().setVisible(true);
			buttons.getBtnNext().setText("Next>>");
		}
		
		//we go to last page
		private void setStateLastPage(){
			setStateMiddlePage();
			buttons.getBtnNext().setText("Export>>");
		}
		

		//go to next page
		@Override
		public void toNext() {
			// TODO Auto-generated method stub
			Page.nextPage();   //increase page counter
			if(Page.getCurrenPage() > 0 && Page.getCurrenPage() <  pages.size() - 1){
				setStateMiddlePage();
			}else{
				setStateLastPage();
			}
		}

		
		//go to previous page
		@Override
		public void toPrevious() {
			// TODO Auto-generated method stub
			Page.previousPage();   //decrease page counter
			if(Page.getCurrenPage() == 0){
				setStateFirstPage();
			}else{
				setStateMiddlePage();
			}
		}

	
		
	}
	

	//implementation of pages call back interface
	public  PageCallBackHandler getCallBack (){
		if(pageCallBack == null)  return new PageCallBackHandler();
		return pageCallBack;
	}




	//dao getters
	@Override
	public DaoOrcl getOracleDao() {
		// TODO Auto-generated method stub
		return daoOrcl;
	}



	//dao getters
	@Override
	public DaoPostgre getPostgreDao() {
		// TODO Auto-generated method stub
		return daoPostgre;
	}

	//GUI worker interface
	@Override
	public void getUsers() {
		System.out.println("get Users  worker started...");
		// TODO Auto-generated method stub
		//select the users from data base 
		List<IAbstractModel> users = datObjInterface.getUsers();

		//set users in user page
		userPage.getCombousrs().initCombo(users);
		
		
		//go to next page
		goToNextPage();
	}

	@Override
	public void getUserObjects() {
		// TODO Auto-generated method stub
		System.out.println("geUser objects worker started...");
		//get selected user
		
		usr = (User)userPage.getCombousrs().getSelObject();
		
		//return if null
		if(usr == null) return;
		
		//get user objects
		List<IAbstractModel> pack  =  datObjInterface.getUserObject(usr, OBJEC_TYPE.PACK);
		List<IAbstractModel> fun   =  datObjInterface.getUserObject(usr, OBJEC_TYPE.FUN);
		List<IAbstractModel> proc  =  datObjInterface.getUserObject(usr, OBJEC_TYPE.PROC);
		List<IAbstractModel> sch   =  datObjInterface.getUserObject(usr, OBJEC_TYPE.SCHEMA);
		
		//fill user objects in tab page 
		userObjects.getPackTbl().setTableDataFromObjecs(pack);
		userObjects.getFunTbl().setTableDataFromObjecs(fun);
		userObjects.getProcTbl().setTableDataFromObjecs(proc);
		userObjects.getSchTbl().setTableDataFromObjecs(sch);
		
		//set tab dimensions
		setSize(dBtabs);
		
		//go to next page
		goToNextPage();
	}

	@Override
	public void exportObjects() {
		// TODO Auto-generated method stub
		System.out.println("Export worker started...");
		
		//get data from grids
		Object [][] pack = userObjects.getPackTbl().getModel().getData();
		Object [][] proc = userObjects.getProcTbl().getModel().getData();
		Object [][] fun = userObjects.getFunTbl().getModel().getData();
		Object [][] sch = userObjects.getSchTbl().getModel().getData();
		
		//get selected user
		User user = (User)userPage.getCombousrs().getSelObject();
		
		//clear console
		exPage.getConsole().clear();
		
		
		List<IAbstractModel> packDef = exportUserObjects(user, pack, OBJEC_TYPE.PACK);
		List<IAbstractModel> procDef =  exportUserObjects(user, proc,OBJEC_TYPE.PROC);
		List<IAbstractModel> funDef = exportUserObjects(user, fun, OBJEC_TYPE.FUN);
		List<IAbstractModel> schDef = exportUserObjects(user, sch, OBJEC_TYPE.SCHEMA);
		 
		//loop through all checked objects
		if(exPage.getFilePerObject().isSelected()){
			//file per Object
			saveObjectsToFile(packDef, null, "export\\oracle\\"+ user.getUsrName()+ "_UTF_8" + "\\pck", ".pck");
			saveObjectsToFile(procDef, null, "export\\oracle\\"+ user.getUsrName()+ "_UTF_8" + "\\proc", ".prc");
			saveObjectsToFile(funDef, null, "export\\oracle\\"+ user.getUsrName() + "_UTF_8" + "\\fun", ".fnc");
			saveObjectsToFile(schDef, null, "export\\postgre\\"+ user.getUsrName()+ "_UTF_8" + "\\schema", ".sql");
		}else{
			//one file 
			funDef.addAll(procDef);
			funDef.addAll(packDef);
			funDef.addAll(schDef);
			
			saveObjectsToFile(funDef, null, "export\\all_in_one_UTF_8\\"+ user.getUsrName()+"\\objects", ".sql");
		}
		
		//export finished
		exPage.getConsole().writeln("\nEXPORT FINISHED...");
	
	}
	
	
	//get user object definitions 
	private List<IAbstractModel> exportUserObjects(User user, Object [][] data, OBJEC_TYPE type){
		if(data == null) return new  ArrayList<IAbstractModel>();
		
		
		List<IAbstractModel> l = new ArrayList<IAbstractModel>();
		for(int i = 0; i < data.length; i++){
			List<IAbstractModel> local;
			UserObject obj = (UserObject)data[i][0];
			
			if(obj == null || obj.isSelected() == false)  continue;
			exPage.getConsole().writeln("Exporting  " + obj.getObjname()+"...");
			local = datObjInterface.getUserObject(user, obj.getObjname(), type);
			
			//one file and it's function or proc
			if(/*exPage.getOnefile().isSelected() &&*/ (type == OBJEC_TYPE.PROC || type == OBJEC_TYPE.FUN )){
				 ((UserObject)local.get(0)).getObjectDefinition().append("/\n");
			}
			
			if(local != null && local.size() > 0)   l.addAll(local);
		}
		return l;
	}
	
	
	

	
	
	/**
	 * 
	 * 
	 * this is save to file section
	 * 
	 * 
	 * 
	 */
	//save to file
	public void saveObjectsToFile(List<IAbstractModel> objects, String basedirPath,  String subdir, String ext){
		if(objects == null) return;
		FileSaver fsaver;
		
		//use relative dir to executable
		if(basedirPath == null)  fsaver = new FileSaver(ext);
		else {
			fsaver = new FileSaver(basedirPath, false, ext);   //use absolute dir path
		}
		
		String helpSubdir = subdir;
		
		for(int i =0; i < objects.size(); i++){
			if(objects.get(i) == null) continue;
	
			
			UserObject obj = (UserObject)objects.get(i);
			
			
			exPage.getConsole().writeln("Saving  " + obj.getObjname() + " to file...");
			
			String finalename = "";
			
			
			//save to file
			if(exPage.getOnefile().isSelected()){
				finalename = FILE_NAME + HelpFunctions.formatDBDate(new Date());   //one file for all objects
			}
			else{
				finalename = obj.getObjname();  //file per object
			}
			//set pack dir
			if(obj.getObjectNameSpace()!= null && !obj.getObjectNameSpace().equals("")){
				helpSubdir = subdir + "\\" + obj.getObjectNameSpace();
			}
			try {
				//add BOM symbol at the beginnig if UTF-8 at the end of file
		 		if(ENCODING.equals("UTF-8")){
		 			/*
		 			FileHandle hf = fsaver.getHandleToFile(helpSubdir, finalename, true);
		 			addBomSymbol(hf.getFileOutStream(true));
		 			hf.release();*/
		 		}
		 		
				fsaver.save(helpSubdir, finalename, new String(obj.getObjectDefinition().toString().getBytes(ENCODING), ENCODING), true/*exPage.getOnefile().isSelected()*/);
		 		
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}

	

	@Override
	public void enableButtons(boolean b) {
		// TODO Auto-generated method stub
		buttons.Enable(b);
	}
	

}



