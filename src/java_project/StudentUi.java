package java_project;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;
import java_project.*;

public class StudentUi extends JFrame implements FocusListener, KeyListener, ActionListener {
	private static final Object CENTER = null;
	private Scanner scan;
	private StudentDAO dao;	
	
	//메인
	private JPanel p = new JPanel();
	
	private JLabel jlabel = new JLabel("1505101");
	private JLabel jlabe2 = new JLabel("박기태");

	private JButton insert = new JButton("등록");
	private JButton search = new JButton("검색");
	private JButton re_insert = new JButton("수정");
	private JButton del = new JButton("삭제");
	private JButton sort_score = new JButton("정렬(성적순)");
	private JButton sort_name = new JButton("정렬(이름순)");
	private JButton save = new JButton("저장");
	private JButton load = new JButton("불러오기");
	
	public StudentUi() {
		scan = new Scanner(System.in);
		dao = new StudentDAO();		
		
		this.setTitle("학생 성적 처리 프로그램");
		this.formDesign();
		this.eventHandler();
		this.setSize(500, 350);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void formDesign () {	
		add(p);
		p.setLayout(new GridLayout(5,4,20, 20));
		p.add(insert);
		p.add(search);
		p.add(re_insert);
		p.add(del);
		p.add(sort_score);
		p.add(sort_name);
		p.add(save);
		p.add(load);
		p.add(jlabel,CENTER);
		p.add(jlabe2,CENTER);
		
	}
	public void eventHandler() {
		
		//등록
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new JPanel_add();
				dao.load();
			}});
		
		//조회
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.inquire();
			}
			
		});
		
		//수정
		re_insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dao.load();
				new JPanel_update();
				dao.load();
			}
			
		});
		
		//삭제
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new JPanel_remove();
				dao.load();
			}
			
		});
		
		//성적순 정렬
		sort_score.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.sortScore();
			}
			
		});
		
		//이름순 정렬
		sort_name.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.sortName();
			}
			
		});
		
		//저장
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.save();
				JOptionPane.showMessageDialog(null, "저장 완료!");
				dao.load();
			}
			
		});
		
		//불러오기
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "불러오기 완료!");
				dao.load();
			}
			
		});
	}
	
	public static void main(String[] args) {
		new StudentUi();		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
