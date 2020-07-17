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
	
	//����
	private JPanel p = new JPanel();
	
	private JLabel jlabel = new JLabel("1505101");
	private JLabel jlabe2 = new JLabel("�ڱ���");

	private JButton insert = new JButton("���");
	private JButton search = new JButton("�˻�");
	private JButton re_insert = new JButton("����");
	private JButton del = new JButton("����");
	private JButton sort_score = new JButton("����(������)");
	private JButton sort_name = new JButton("����(�̸���)");
	private JButton save = new JButton("����");
	private JButton load = new JButton("�ҷ�����");
	
	public StudentUi() {
		scan = new Scanner(System.in);
		dao = new StudentDAO();		
		
		this.setTitle("�л� ���� ó�� ���α׷�");
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
		
		//���
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new JPanel_add();
				dao.load();
			}});
		
		//��ȸ
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.inquire();
			}
			
		});
		
		//����
		re_insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dao.load();
				new JPanel_update();
				dao.load();
			}
			
		});
		
		//����
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new JPanel_remove();
				dao.load();
			}
			
		});
		
		//������ ����
		sort_score.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.sortScore();
			}
			
		});
		
		//�̸��� ����
		sort_name.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.sortName();
			}
			
		});
		
		//����
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dao.save();
				JOptionPane.showMessageDialog(null, "���� �Ϸ�!");
				dao.load();
			}
			
		});
		
		//�ҷ�����
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "�ҷ����� �Ϸ�!");
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
