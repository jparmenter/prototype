/* Team Name: Jakl
*  Admin class
*  Last Modified By: Jeremy Parmenter
*  Last Modified On: 4/1/11
*/

/*
*	TODO: create class?
*/
public class Admin extends User
{
	public Admin(int _id, String _name, String _pass)
	{
		super(_id, _name, _pass);
		status = 'a';
	}

	public void createClass()
	{
		return;
	}

	public Admin createAdmin(int id, String name, String pass)
	{
		Admin admin = new Admin(id, name, pass);
		return admin;
	}

	public Teacher createTeacher(int id, String name, String pass)
	{
		Teacher teacher = new Teacher(id, name, pass);
		return teacher;
	}

	public String toString()
	{
		return "\n\nAdmin: " + super.toString();
	}
}
