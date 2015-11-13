package wizard.pages;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import export.common.UserObject;

import wizard.JAbstractCheckAll;
import wizard.datatable.JObjectTable;



public class UserObjectPage extends Page {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5386176858014746524L;
	
	//the object tabs for procedures, function, packages, schemas
	JTabbedPane tabs = new JTabbedPane();
	
	
	//panels for each tab
	JPanel pack = new JPanel();
	JPanel fun = new JPanel();
	JPanel proc = new JPanel();
	JPanel sch = new JPanel();
	

	//the tables in each panel
	JObjectTable packTbl ;
	JObjectTable funTbl ;
	JObjectTable procTbl;
	JObjectTable schTbl;
	
	//check in each tab
	JAbstractCheckAll ckPack;
	JAbstractCheckAll ckFun;
	JAbstractCheckAll ckProc;
	JAbstractCheckAll ckSchema;
	
	public UserObjectPage(){
		initPage();
	}

	@Override
	protected void initPage() {
		// TODO Auto-generated method stub
		//lay outs
		
		
		JPanel paneLabel = new JPanel();
		//JPanel panelTabs = new JPanel();
		
		
		//pack.setVisible(false);

		//setlay out
		//panelTabs.setLayout(new GridLayout(1, 1));
		
		//add label to label panel
		paneLabel.add(new JLabel("Please select Objects To export"));
		//tabs.setLayout(new GridLayout(1, 1));
		
		//add tabs
		tabs.addTab("Packages", null, pack,  "Packages");
		tabs.addTab("Functions", null, fun,  "Functions");
		tabs.addTab("Procedures", null, proc, "Procedures");
		tabs.addTab("Schemas", null, sch, "Schemas");
		
		
		tabs.setTabPlacement(JTabbedPane.TOP);
		
		//add tabs to tabpanel panel
		//panelTabs.add(tabs);
		
		//add data tables to panels
		packTbl = new JObjectTable(pack);
		funTbl = new JObjectTable(fun);
		schTbl = new JObjectTable(sch);
		procTbl = new JObjectTable(proc);
		
		//set layout
		setLayout(new GridLayout(1,1));
		
		//add label & tabs to page panel
		//add(paneLabel, BorderLayout.NORTH);
		//add(panelTabs,BorderLayout.CENTER);
		add(tabs);
		
		//init select all check boxes
		initChecks();
		
		//add checks to panel
		pack.add(ckPack);
		fun.add(ckFun);
		sch.add(ckSchema);
		proc.add(ckProc);
		
	}

	public JObjectTable getPackTbl() {
		return packTbl;
	}

	public JObjectTable getFunTbl() {
		return funTbl;
	}

	public JObjectTable getProcTbl() {
		return procTbl;
	}

	public JObjectTable getSchTbl() {
		return schTbl;
	}

	public JTabbedPane getTabs() {
		return tabs;
	}

	
	//init select all check boxes
	private void initChecks(){
	 
	  //functions
	  if(ckFun == null)
	  ckFun	= new JAbstractCheckAll(this) {
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			@Override
			public void OnUnCheck(Object o) {
				// TODO Auto-generated method stub
				System.out.println("Entered in ckFun OnUnCheck");
				checAll(getFunTbl().getModel().getData(), false);
				getFunTbl().updateUI();
			}
			
			@Override
			public void OnCkeck(Object o) {
				// TODO Auto-generated method stub
				System.out.println("Entered in ckFun OnCheck");
				checAll(getFunTbl().getModel().getData(), true);
				getFunTbl().updateUI();
			}
		};
		
	   //packages
	   if(ckPack == null)
	   ckPack  = new JAbstractCheckAll(this) {
			/**
		 * 
		 */
			
		private static final long serialVersionUID = 1L;

			@Override
			public void OnUnCheck(Object o) {
				// TODO Auto-generated method stub
				checAll(getPackTbl().getModel().getData(), false);
				getPackTbl().updateUI();
			}
			
			@Override
			public void OnCkeck(Object o) {
				// TODO Auto-generated method stub
				checAll(getPackTbl().getModel().getData(), true);
				getPackTbl().updateUI();
			}
		};
		
		//procedures
		if(ckProc == null)
		ckProc = new JAbstractCheckAll(this) {
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			@Override
			public void OnUnCheck(Object o) {
				// TODO Auto-generated method stub
				checAll(getProcTbl().getModel().getData(), false);
				getProcTbl().updateUI();
			}
			
			@Override
			public void OnCkeck(Object o) {
				// TODO Auto-generated method stub
				checAll(getProcTbl().getModel().getData(), true);
				getProcTbl().updateUI();
			}
		};
		
		//schemas
		if(ckSchema == null) 
			ckSchema = new JAbstractCheckAll(this) {
			/**
		 * 
		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void OnUnCheck(Object o) {
				// TODO Auto-generated method stub
				checAll(getSchTbl().getModel().getData(), false);
				getSchTbl().updateUI();
			}
			
			@Override
			public void OnCkeck(Object o) {
				// TODO Auto-generated method stub
				checAll(getSchTbl().getModel().getData(), true);
				getSchTbl().updateUI();
			}
		};
		
		//set check texts
		ckFun.setText("Select All");
		ckPack.setText("Select All");
		ckSchema.setText("Select All");
		ckProc.setText("Select All");
	}
	
	//mark or un_mark everything
	private void checAll(Object [][] arr, boolean b){
		if(arr == null)  return ;
		
		for(int i =0 ; i < arr.length; i++){
			UserObject ob =  (UserObject)arr[i][0];
			if(ob == null) continue;
			ob.setSelected(b);
		}
	}
	
	public  void setSelectedAll(){
		ckFun.setSelected(true);
		ckPack.setSelected(true);
		ckSchema.setSelected(true);
		ckProc.setSelected(true);
	}
}
