/* Team Name: Jakl
*  Teacher class
*  Last Modified By: Jeremy Parmenter
*  Last Modified On: 4/1/11
*/

/*
*	TODO: create quiz?
*/

import java.util.*;

public class Teacher extends User
{

	public static Vector<Class> classList;

	public Teacher(int _id, String _name, String _pass)
	{
		super(_id, _name, _pass, 't');
		classList = new Vector<Class>();
	}

	public void createQuiz()
	{
		return;
	}

	public User createStudents(int id, String name, String pass)
	{
		User student = new User(id, name, pass);
		return student;
	}

	public String toString()
	{
		return "\n\nTeacher: " + super.toString();
	}

	public void addClass()
	{
		//ADD
	}
}
