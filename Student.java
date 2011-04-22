import java.util.Vector;

public class Student extends User
{
	private int id;
	private String name;
	private String pass;
	//private Vector<Integer> classList;
	//private Vector<Integer> grades;
	private char status;

	public Student(int _id, String _name, String _pass)
	{
		super(_id,_name,_pass);
		status = 's';
		classList = new Vector<Integer>(); // might as well instanciate it
		//grades = null;
	}

	//public void modifyGrade(int classNmbr, int grade)
	///{
		//int oldGrade = grades.get(--classNmbr);
		//oldGrade = grade;
	//}

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

	public void addClass(int classId)
	{
		if (classList == null)
		{
			classList = new Vector<Integer>();
		}

		classList.add(classId);
		//if (status == 's')
		//	grades.add(0);
	}

	/* getClass is a function in java have to name it something else
	public Vector<Class> getClasses()
	{
		return c;
	}*/

	public String toString()
	{
		return "\n\nStudent: " + super.toString();
	}
}
