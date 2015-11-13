package wizard.pages;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import swing.utils.GraphicsConsole;


public class ExportPage extends Page{

	
	private static final long serialVersionUID = 804499724151598457L;
	
	
	ButtonGroup fileRadioGrp = new ButtonGroup();
	
	
	//oracle or postgre connections radios
	JRadioButton onefile = new JRadioButton("One File");
	JRadioButton filePerObject = new JRadioButton("File per Object");
	GraphicsConsole console ;
	public ExportPage(){
		initPage();
	}
	
	@Override
	protected void initPage() {
		// TODO Auto-generated method stub
		
		//init console
		console = new GraphicsConsole(this, new Color(0,0,0), new Color(0,255,0), new Dimension(420, 350)); 
		
		//add radio buttons to group
		fileRadioGrp.add(filePerObject);
		fileRadioGrp.add(onefile);
		
		//check file per object radio
		filePerObject.setSelected(true);
		
		//add to page panel
		add(onefile);
		add(filePerObject);
	}



	public JRadioButton getOnefile() {
		return onefile;
	}

	public JRadioButton getFilePerObject() {
		return filePerObject;
	}

	public GraphicsConsole getConsole() {
		return console;
	} 

}
