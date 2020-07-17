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

class JPanel_add extends JFrame implements FocusListener, KeyListener, ActionListener {
	private JTextField tf_id = new JTextField(20);
	private JTextField tf_name = new JTextField(20);
	private JTextField tf_score = new JTextField(20);
	private JTextArea ta = new JTextArea(7, 20);
	private JPanel p = new JPanel();
	private StudentDAO dao;
	
	private JButton bt_ok = new JButton("등록");
	
	JPanel_add(){
		dao = new StudentDAO();
		this.formDesign();
		this.eventHandler();
		this.setSize(300, 350);
		this.setTitle("입력화면");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	private void formDesign() {
		this.add(p);
		p.add(new JLabel("입력후 <Enter>키를 입력하세요"));
		p.add(tf_id);
		p.add(tf_name);
		p.add(tf_score);
		p.add(new JScrollPane(ta));
		
		p.add(bt_ok);
		
		tf_name.setEditable(false); 	//초기화면에는 비홠성화
		tf_score.setEditable(false);	//초기화면에는 비활성화
		
		bt_ok.setEnabled(false);
		
	}
	private void eventHandler() {
		tf_id.addFocusListener(this);
		tf_name.addFocusListener(this);
		tf_score.addFocusListener(this);
		
		tf_score.addKeyListener(this);
		
		bt_ok.addActionListener(this);
		
	}
	public static void main(String[] args) {
		new JPanel_add();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt_ok) {
			dao.insert(tf_id.getText(), tf_name.getText(), Integer.parseInt(tf_score.getText()));
			tf_id.setText("");
			tf_name.setText("");
			tf_score.setText("");
			//추가적으로 다른 버튼을 활성화
			
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
//		if(e.getKeyCode() == e.VK_ENTER) {
//			
//			ta.append("입력된 정보:"+tf_id.getText()+":"+tf_name.getText()+""+tf_score.getText());
//		}
//		
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
		if(e.getSource().equals(tf_id)) {
			ta.setText("ID릉 입력하세요"+'\n');
		}
		else if(e.getSource().equals(tf_name)) {
			ta.setText("이름을 입력하세요"+'\n');
		}
		else if(e.getSource().equals(tf_score)) {
			ta.setText("성적을 입력하세요"+'\n');
		}
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(tf_id)) {
			if(tf_id.getText() == "") {
				ta.setText("ID가 입력되지 않았습니다."+'\n');
			}else {
				tf_name.setEditable(true);
			}
		}
		else if(e.getSource().equals(tf_name)) {
			if(tf_name.getText() == "")
				ta.setText("이름이 없습니다."+'n');
			else
				tf_score.setEditable(true);
		}
		else if(e.getSource().equals(tf_score)) {
			if(tf_score.getText() == "") 
				ta.setText("성적이 없습니다." + '\n');
			else {
				//등록 버튼 활성화
				//등록버튼이 포크스를 갖도록 한다.
				
				ta.append(tf_id.getText()+tf_name.getText()+tf_score.getText());
				ta.setText("입력이 완료!!");
				bt_ok.setEnabled(true);
				bt_ok.requestFocus();
			}
		}
		
	}
}
