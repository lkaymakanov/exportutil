package wizard.pages;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import swing.utils.JAbstractCombo;

import export.common.User;

public class UsersPage extends Page{
	private static final long serialVersionUID = -4402227634851266336L;
	private JComboUsers  combousrs = new JComboUsers();

	
	
	
	public UsersPage() {
		// TODO Auto-generated constructor stub
		initPage();
	}
	
	
	
	
	@Override
	protected void initPage() {
		// TODO Auto-generated method stub+
		JPanel p = new JPanel();
		
		JLabel labelselUser = new JLabel("Please select User  ");
		Font f = new Font("Ariral Bold", Font.BOLD, 12);
		labelselUser.setFont(f);
		
		p.add(labelselUser);
		
		p.add(combousrs);
		//add label
		//add(labelselUser, BorderLayout.CENTER);
		
		//add the users combo
		add(p, BorderLayout.CENTER);
		
	}
	
	
	public class JComboUsers extends JAbstractCombo{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7169515950218040664L;

		@Override
		public String getValToShow(int index) {
			// TODO Auto-generated method stub
			Object o = getSelObject();
			if(o == null)   return "No Items";
			
			//show the users in combo
			return ((User)getSelObject()).getUsrName();
		}
	}
	

	public JComboUsers getCombousrs() {
		return combousrs;
	}



	
	
	
}
