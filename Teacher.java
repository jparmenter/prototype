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
	private jaklUtilities utility = new jaklUtilities();
	//public static Vector<Class> classList;

	public Teacher(int _id, String _name, String _pass)
	{
		super(_id, _name, _pass);
		status = 't';
		classList = new Vector<Integer>(); // might as well instanciate it
	}

	public void createQuiz()
	{
		return;
	}

	public void createStudents(int id, String name, String pass, int classIdToAdd)
	{
		utility.writeStudent(id,name,pass,'s');
	}

	public String toString()
	{
		return "\n\nTeacher: " + super.toString();
	}

	public void addClass(Class c)
	{
		//ADD
	}
}
