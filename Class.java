import java.util.*;

public class Class {
	private int id;
	private String title;
	private String desc;
	private Teacher teacher;
	private Vector<Quiz> quiz;
	private Vector<User> roster;
	
	public Class(int _id, String _title, String _desc, Teacher _teacher)
	{
		id = _id;
		title = _title;
		desc = _desc;
		teacher = _teacher;
		quiz = null;
		roster = null; 
	}

	public void changeDesc(String nDesc)
	{
		desc = nDesc;
	}
	
	public void addQuiz(Quiz _quiz)
	{
		if (quiz == null)
			quiz = new Vector<Quiz>();

		quiz.add(_quiz);
	}

	public void addStudent(User student)
	{
		if (roster == null)
			roster = new Vector<User>();

		roster.add(student);
	}

	public void changeTeacher(Teacher newTeacher)
	{
		int i = 0;
		Vector<Class> c = teacher.getClasses();
		Class rmClass;

		do
		{
			rmClass = c.get(i);
		}
		while(id != rmClass.getId());

		c.remove(i);
		teacher = newTeacher;	
	}

	public Teacher getTeacher()
	{
		return teacher;
	}

	public Vector<Quiz> getQuiz()
	{
		return quiz;
	}

	public Vector<User> getRoster()
	{
		return roster;
	}

	public int getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getDesc()
	{
		return desc;
	}

	public String toString()
	{
		return "ID: " + id + "\tCourse: " + title;
	}
}
