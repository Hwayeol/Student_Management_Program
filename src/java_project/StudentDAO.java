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
	
	private String id;			// 학생 id
	private String name;		//학생 이름
	private int score;			//학생 성적

	public StudentDAO() {
		al = new ArrayList<Student>();
	}
	
	
	//1.등록
	public void insert(String id, String name, int score) {
		
		load();
		
		for(int i = 0; i < al.size(); i++) {
			Student stu = (Student)al.get(i);
			if(al.contains(al.get(i))) {
				JOptionPane.showMessageDialog(null, "이미 등록된 아이디!");
				return;
			}
		}
		
		Student stu = new Student(id, name, score);
		
		al.add(stu);
					
		save();
		JOptionPane.showMessageDialog(null, "등록 완료!");
		
	}
	//2. 조회
	public void inquire() {
		
		load();
		this.add(p);
		this.setTitle("조회 화면");
		this.setSize(300, 280);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		p.add(new JScrollPane(ta));
		
		if(al.isEmpty()) {
			JOptionPane.showMessageDialog(null, "데이터 없음!");
			return;
		}
		
		else {
			for(int i = 0; i < al.size(); i++) {
				ta.append("ID : " + al.get(i).getId() 
						+ " 이름 : " + al.get(i).getName() 
						+ " 성적 : " + al.get(i).getScore() + "\n");				
			}
		}
			
	}
	
	//3.수정
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
			JOptionPane.showMessageDialog(null, "수정할 아이디 없음!");
			return;
		}
		
	}
	
	//4.삭제
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
			JOptionPane.showMessageDialog(null, "삭제 완료!");
			save();
		} else {
			JOptionPane.showMessageDialog(null, "삭제할 아이디 없음!");
			return;
		}
	}

	//5. 성적순으로 정렬
	public void sortScore() {
		
		load();

		Collections.sort(al, new ScoreSort());
		
		this.add(p1);
		this.setTitle("조회(성적순) 화면");
		this.setSize(300, 280);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		p1.add(new JScrollPane(ta1));

		
		if(al == null) {
			JOptionPane.showMessageDialog(null, "데이터 없음!");
		}
		
		for(int i = 0; i <al.size(); i++) {
			
			ta1.setText("ID : " + al.get(i).getId() 
							+ " 이름 : " + al.get(i).getName() 
							+ " 성적 : " + al.get(i).getScore());
		}
	}

	//6. 이름순으로 정렬
	public void sortName() {
		
		load();
		Collections.sort(al);
		
		this.add(p2);
		this.setTitle("조회(성적순) 화면");
		this.setSize(300, 280);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		p2.add(new JScrollPane(ta2));
		
		if(al == null) {
			JOptionPane.showMessageDialog(null, "데이터 없음!");
		}
		
		for(int i = 0; i <al.size(); i++) {
			ta2.setText("ID : " + al.get(i).getId() 
							+ " 이름 : " + al.get(i).getName() 
							+ " 성적 : " + al.get(i).getScore());
		}
	}
	
	//7. 저장	
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
	
	//8.불러오기
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
		//내림차순 정렬
		return stu2.getScore() - stu1.getScore();
	}
	
}
