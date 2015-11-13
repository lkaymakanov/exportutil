package wizard.datatable;

import java.util.List;

import javax.swing.JPanel;

import swing.utils.JAbstractColumn;
import swing.utils.JAbstractTable;

import export.common.UserObject;




public class JObjectTable extends JAbstractTable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4095133053710184495L;
	
	//the columns
	private static JAbstractColumn col [] ={
			new JAbstractColumn("Selected", 10, true),
			new JAbstractColumn("Object Name", 100, false)
	};
	
	//constructors
	public JObjectTable(JPanel panel, List<JAbstractColumn> cols) {
		super(panel, cols, null);
		// TODO Auto-generated constructor stub
	}
	
	public JObjectTable(JPanel panel){
		super(panel, col, null);	
	}


	@Override
	public MyTableModel getTableModel() {
		// TODO Auto-generated method stub
		if(model == null)
		model =  new MyTableModel() {
			
			private static final long serialVersionUID = 5754362546676344183L;

			@Override
			public void setValueAt(Object value, int row, int col) {
				// TODO Auto-generated method stub
				if(model.getData() == null) return ;
	            if (DEBUG) {
	                System.out.println("Setting value at " + row + "," + col
	                                   + " to " + value
	                                   + " (an instance of "
	                                   + value.getClass() + ")");
	            }
	 
	            //get user Object
	            UserObject usrObject =  ((UserObject)model.getData()[row][0]);
	            
	           // ((UserObject)data[row][0]). = value;
	            if(col == 0)  {usrObject.setSelected(Boolean.valueOf(String.valueOf(value)));}
	            if(col == 1)  {usrObject.setObjname(String.valueOf(value));}
	            fireTableCellUpdated(row, col);
	 
	            if (DEBUG) {
	                System.out.println("New value of data:");
	                printDebugData();
	            }
			};
			
			@Override
			protected void printDebugData() {
				// TODO Auto-generated method stub
				int numRows = getRowCount();
	            // int numCols = getColumnCount();
	 
	            if(model.getData() == null) return;
	            for (int i=0; i < numRows; i++) {
	                System.out.print("    row " + i + ":");
	                //for (int j=0; j < numCols; j++) {
	                    System.out.print("  " + ((UserObject)model.getData()[i][0]).isSelected() + " " +  ((UserObject)model.getData()[i][0]).getObjname());
	                //}
	                System.out.println();
	            }
	            System.out.println("--------------------------");
				
			};
			
			
			@Override
			public Object getValueAt(int row, int col) {
				// TODO Auto-generated method stub
				if(model.getData() == null) return null;
	        	
	        	UserObject usrobj = (UserObject)model.getData()[row][0];
	        	if(col == 0) {return usrobj.isSelected();}
	        	if(col == 1) {return usrobj.getObjname();}
	        	
	            return null;
			}

			@Override
	        @SuppressWarnings("unchecked")
			public Class getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }
		};
		return model;
	}

}
