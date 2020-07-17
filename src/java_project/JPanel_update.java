package java_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class JPanel_update extends JFrame implements FocusListener, KeyListener, ActionListener {
	private JTextField tf_id = new JTextField(20);
	private JTextArea ta = new JTextArea(7, 20);
	private JPanel p = new JPanel();
	private StudentDAO dao;
	
	private JButton bt_ok = new JButton("등록");
	
	JPanel_update(){
		dao = new StudentDAO();
		this.formDesign();
		this.eventHandler();
		this.setSize(300, 150);
		this.setTitle("입력화면");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	private void formDesign() {
		this.add(p);
		
		p.add(tf_id);
		
		p.add(bt_ok);		
		
	}
	private void eventHandler() {
		tf_id.addFocusListener(this);
		
		bt_ok.addActionListener(this);
		
	}
	public static void main(String[] args) {
		new JPanel_update();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt_ok) {
			
			dao.update(tf_id.getText());
			tf_id.setText("");
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ENTER) {
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusGained(FocusEvent e) {
	
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		
	}
}
