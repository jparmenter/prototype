/* Team Name: Jakl
*  User class
*  Last Modified By: Jeremy Parmenter
*  Last Modified On: 4/1/11
*/

/*
*	TODO: adding grades and class?
*/

import java.util.Vector;

public class User 
{
	private int id;
	private String name;
	private String pass;
	private Vector<Class> c;
	private Vector<Integer> grades;
	private char status; 

	public User(int _id, String _name, String _pass)
	{
		id = _id;
		name = _name;
		pass = _pass;
		status = 's';
		c = null;
		grades = new Vector<Integer>();
	}

	public User(int _id, String _name, String _pass, char _status)
	{
		id = _id;
		name = _name;
		pass = _pass;
		status = _status;
		c = null;
		grades = null;
	}
	
	public void modifyGrade(int classNmbr, int grade)
	{
		int oldGrade = grades.get(--classNmbr);
		oldGrade = grade;
	}

	// Need to know if the change pass was successful
	public boolean changePass(String oldPass, String newPass)
	{
		
		if((pass.compareTo(oldPass)) == 0)
		{
			pass = newPass; 
			return true;
		}
		return false;
	}

	public void addClass(Class _c)
	{
		if (c == null)
			c = new Vector<Class>();

		c.add(_c);
		if (status == 's')
			grades.add(0);	
	}

	// getClass is a function in java have to name it something else
	public Vector<Class> getClasses()
	{
		return c;
	} 
	
	public String getName()
	{
		return name;
	}

	public int getId()
	{
		return id;
	}
	
	public String getPass()
	{
		return pass;
	}

	public char getStatus()
	{
		return status;
	}
	public String toString()
	{
		return "\n\nID: " + id + "\nName: " + name + "\n";
	}
}
