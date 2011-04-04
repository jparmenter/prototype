/* Team Name: Jakl
*  Teacher class
*  Last Modified By: Jeremy Parmenter
*  Last Modified On: 4/1/11
*/

/*
*	TODO: create quiz?
*/


public class Teacher extends User
{

	public Teacher(int _id, String _name, String _pass)
	{
		super(_id, _name, _pass, 't');
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
}
