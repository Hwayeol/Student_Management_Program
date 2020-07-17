package java_project;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {
	
	private String id;		//�й�
	private String name;	//�̸�
	private int score;		//����

	public Student(String id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	//��ü ������ ���ڿ��� ǥ��
	public String toString() {
		return id+":"+name+":"+score;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}

}

