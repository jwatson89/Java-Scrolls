package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PauseMenu extends JFrame {

	private JButton resume,save,load;
	private PlayScreen ps;
	private MainMenu mm;
	
	public PauseMenu(PlayScreen ps , MainMenu mm) {
		resume = new JButton("Resume");
		save = new JButton("Save");
		load = new JButton("Load");
		this.ps=ps;
		this.mm=mm;
		init();
	}
	
	public void init() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		resume.addActionListener(new ButtonListener());
		save.addActionListener(new ButtonListener());
		load.addActionListener(new ButtonListener());
		add(resume);
		add(save);
		add(load);
		setAlwaysOnTop(true);
		setSize(600,100);
		this.setResizable(false);
		this.setMenuBar(null);
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton tmp = (JButton) e.getSource();
			
			if(tmp == resume) {
				ps.setFocusable(true);
				setVisible(false);
				ps.requestFocus();
				
			}
			else if(tmp == save) {
				PlayScreen.save(ps);
			}
			else if (tmp == load) {
				PlayScreen pstmp;
				pstmp= PlayScreen.load();
				if(pstmp!=null) {
					ps =pstmp;
					mm.setPlayScreen(ps);
				}
			}
			
		}
		
		
	}
	
	public void pause() {
		setVisible(true);
		requestFocus();
		ps.setFocusable(false);
	}

	public void setPlayScreen(PlayScreen ps2) {
		this.ps=ps2;
		
	}
	
}
