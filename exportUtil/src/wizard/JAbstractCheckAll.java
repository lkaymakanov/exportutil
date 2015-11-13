package wizard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;


public abstract  class JAbstractCheckAll extends JCheckBox{

	private static final long serialVersionUID = -4129003001096273309L;
	Object obj;
	
	
	public JAbstractCheckAll(Object o){
		
		//register listener
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isSelected()){
					OnCkeck(obj);    //check
				}
				else {
					OnUnCheck(obj);      //Uncheck
				}
			}
		});
		
	}	
	public abstract void OnCkeck(Object o);
	public abstract void OnUnCheck(Object o);
	
}
