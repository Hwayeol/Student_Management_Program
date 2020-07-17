package java_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StudentDAO extends JFrame{
	
	private JTextArea ta = new JTextArea(10, 20);
	private JTextArea ta1 = new JTextArea(10, 20);
	private JTextArea ta2 = new JTextArea(10, 20);
	private JPanel p = new JPanel();
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	
	private ArrayList<Student> al;
	
	private String id;			// �л� id
	private String name;		//�л� �̸�
	private int score;			//�л� ����

	public StudentDAO() {
		al = new ArrayList<Student>();
	}
	
	
	//1.���
	public void insert(String id, String name, int score) {
		
		load();
		
		for(int i = 0; i < al.size(); i++) {
			Student stu = (Student)al.get(i);
			if(al.contains(al.get(i))) {
				JOptionPane.showMessageDialog(null, "�̹� ��ϵ� ���̵�!");
				return;
			}
		}
		
		Student stu = new Student(id, name, score);
		
		al.add(stu);
					
		save();
		JOptionPane.showMessageDialog(null, "��� �Ϸ�!");
		
	}
	//2. ��ȸ
	public void inquire() {
		
		load();
		this.add(p);
		this.setTitle("��ȸ ȭ��");
		this.setSize(300, 280);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		p.add(new JScrollPane(ta));
		
		if(al.isEmpty()) {
			JOptionPane.showMessageDialog(null, "������ ����!");
			return;
		}
		
		else {
			for(int i = 0; i < al.size(); i++) {
				ta.append("ID : " + al.get(i).getId() 
						+ " �̸� : " + al.get(i).getName() 
						+ " ���� : " + al.get(i).getScore() + "\n");				
			}
		}
			
	}
	
	//3.����
	public void update(String id) {
		
		load();
		
		boolean found = false;
		int tempIndex = 0;
		
		for(int i = 0; i < al.size(); i++) {
			Student stu = (Student)al.get(i);
			if(id.equals(stu.getId())) {
				found = true;
				tempIndex = i;
				break;
			}
		}
		
		if(found) {
			al.remove(tempIndex);
			new JPanel_add();
			save();
		} else {
			JOptionPane.showMessageDialog(null, "������ ���̵� ����!");
			return;
		}
		
	}
	
	//4.����
	public void delete(String id) {
		
		load();
		
		boolean found = false;
		int tempIndex = 0;
		for(int i = 0; i < al.size(); i++) {
			Student stu = (Student)al.get(i);
			if(id.equals(stu.getId())) {
				found = true;
				tempIndex = i;
				break;
			}
		}
		
		if(found) {
			al.remove(tempIndex);
			JOptionPane.showMessageDialog(null, "���� �Ϸ�!");
			save();
		} else {
			JOptionPane.showMessageDialog(null, "������ ���̵� ����!");
			return;
		}
	}

	//5. ���������� ����
	public void sortScore() {
		
		load();

		Collections.sort(al, new ScoreSort());
		
		this.add(p1);
		this.setTitle("��ȸ(������) ȭ��");
		this.setSize(300, 280);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		p1.add(new JScrollPane(ta1));

		
		if(al == null) {
			JOptionPane.showMessageDialog(null, "������ ����!");
		}
		
		for(int i = 0; i <al.size(); i++) {
			
			ta1.setText("ID : " + al.get(i).getId() 
							+ " �̸� : " + al.get(i).getName() 
							+ " ���� : " + al.get(i).getScore());
		}
	}

	//6. �̸������� ����
	public void sortName() {
		
		load();
		Collections.sort(al);
		
		this.add(p2);
		this.setTitle("��ȸ(������) ȭ��");
		this.setSize(300, 280);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		p2.add(new JScrollPane(ta2));
		
		if(al == null) {
			JOptionPane.showMessageDialog(null, "������ ����!");
		}
		
		for(int i = 0; i <al.size(); i++) {
			ta2.setText("ID : " + al.get(i).getId() 
							+ " �̸� : " + al.get(i).getName() 
							+ " ���� : " + al.get(i).getScore());
		}
	}
	
	//7. ����	
	public void save() {
		OutputStream out = null;
		ObjectOutputStream oos = null;
		
		try {
			out = new FileOutputStream("member.txt");
			oos = new ObjectOutputStream(out);
			oos.writeObject(al);			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				}catch(Exception e) {}
			}
			
			if(oos != null) {
				try {
					oos.close();
				}catch(Exception e) {}
			}
		}
	}
	
	//8.�ҷ�����
	public void load() {
		InputStream in = null;
		ObjectInputStream ois = null;
		ArrayList<Student> list = null;
		
		try {
			in = new FileInputStream("member.txt");
			ois = new ObjectInputStream(in);
			list = (ArrayList<Student>) ois.readObject();
			
			Iterator<Student> it = list.iterator();
			while(it.hasNext()) {
				al.add(it.next());
			}

		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				}catch(Exception e) {}
			}
			
			if(ois != null) {
				try {
					ois.close();
				}catch(Exception e) {}
			}
		}
	}

}
	
class ScoreSort implements Comparator<Student> {

	@Override
	public int compare(Student stu1, Student stu2) {
		//�������� ����
		return stu2.getScore() - stu1.getScore();
	}
	
}
