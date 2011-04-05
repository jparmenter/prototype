/* Team Name: Jakl
*  Prototype main class
*  Last Modified By: Jeremy Parmenter
*  Last Modified On: 4//11
*/

/*
*	TODO: 
*		Creating Quiz
*		IO Handling 	
*			-login() - load users/classes.
*				When a user loads all of the classes files 
*				(and the quizzes) need to be initialized as well
*			-create(char userType) - save users
*			-takeQuiz - saves user cause of changed grade
*			-createQuiz - savesClass
*			-createClass - saves class
*			-viewClass - saves class
*			-Modify Roster - saves class
*			-Modify Teacher - saves class
*		Exception handling
*		Modify Roster
*		Modify Teacher	
*		View Grades - for a student or list all grades for students
*		**ADDITION FUNCTIONALITY FOR OBJECTS:
*		Quiz Check CreateQuiz info
*		User to hanle grading
*/
import java.util.*;

public class Prototype
{
	public static Scanner keyboard;
	// What Our file system would do
	public static Vector<Admin> a;
	public static Vector<Teacher> t;
	public static Vector<User> s;
	// So we know what is logged in
	public static Admin currA;
	public static User currS;
	public static Teacher currT;

	public static void main (String [] args)
	{
		keyboard = new Scanner(System.in);
		a = new Vector<Admin>();
		t = new Vector<Teacher>();
		s = new Vector<User>();
		Admin admin = new Admin(1234, "Jeremy", "password");
		Teacher teacher = new Teacher(1111, "Bob", "software");
		User student = new User(2222, "James", "progit");
		Class nClass = new Class(272111, "CSE 110 Intro to Java", "This class teaches you java", teacher);
		admin.addClass(nClass);
		student.addClass(nClass);
		teacher.addClass(nClass);
		a.add(admin);
		s.add(student);
		t.add(teacher);
		Vector<Answer> answers = new Vector<Answer>();
		Answer ans = new Answer("2009", false);
		answers.add(ans);
		ans = new Answer("2010", false);
		answers.add(ans);
		ans = new Answer("2011", true);
		answers.add(ans);
		Vector<Question> question = new Vector<Question>();
		Question q = new Question("What year is it?", answers);
		question.add(q);
		Quiz quiz1 = new Quiz("Quiz 1", question);
		nClass.addQuiz(quiz1);

		currA = null;
		currS = null;
		currT = null;

		login();
	}

	public static void login()
	{
		int id = 0, option = 0;
		String pass = "";
		String error;

		System.out.println("\tLog Screen\n");

		do {
			System.out.print("Enter a number to go\n" +
				"\t(1) Login\n"  + "\t(2) Exit\n" + "\nEnter: ");

			option = keyboard.nextInt();

			if (option == 1)
			{
				//Log In
				System.out.print("\tID: ");
				id = keyboard.nextInt();
				System.out.print("\tPassword: ");
				pass = keyboard.next();

				/* this would do processing reading name of Text files. N or O(1) find for file
				* Such as naming all of the text files based id and see if the text files have
				* said id

				char test = (Integer.toString(id)).charAt(0);
				if (test == '1')
				{
					// tempA = openAdmin
					if (tempA != null)
					{
						if (pass.compareTo(tempA.getPass()) == 0)
							currA = tempA;
						else
							System.out.println("\nError: Incorrect Password\n");
					}
					else
						System.out.println("\nError: Incorrect Id\n");
				}
				else if (test == '2')
				{
					// tempT = openTeacher
					if (tempT != null)
					{
						if (pass.compareTo(tempT.getPass()) == 0)
							currT = tempT;
						else
							System.out.println("\nError: Incorrect Password\n");
					}
					else
						System.out.println("\nError: Incorrect Id\n");
				}
				else if (test == '3')
				{
					// tempS = openStudent
					if (tempS != null)
					{
						if (pass.compareTo(tempS.getPass()) == 0)
							currS = tempS;
						else
							System.out.println("\nError: Incorrect Password\n");
					}
					else
						System.out.println("\nError: Incorrect Id\n");
				}*/
				

				if (a != null)
					for (int i = 0; i < a.size(); i++)
					{
						Admin tempA = a.get(i);
						
						if ((tempA.getId() == id) && (pass.compareTo(tempA.getPass()) == 0))
							currA = tempA;
					}

				if (t != null)
					for (int i = 0; i < t.size(); i++)
					{
						Teacher tempT = t.get(i);
						if ((tempT.getId() == id) && (pass.compareTo(tempT.getPass()) == 0))
							currT = tempT;
					}

				if (s != null)
					for (int i = 0; i < s.size(); i++)
					{
						User tempS = s.get(i);
						if ((tempS.getId() == id) && (pass.compareTo(tempS.getPass()) == 0))
							currS = tempS;
					}
				if ((currA != null) || (currT != null) || (currS != null))
					mainMenu();
				else
					System.out.println("\nError: Enter correct id or password\n");
			}
			else if (option != 2)
				 System.out.println("\nEnter a correct option\n");

		} while (option != 2);

	}

	public static void mainMenu()
	{
		int option = 0;
		String s = "\n\tMain Menu\n\n";

		s += "Hello, ";

		if (currA != null)
			s += currA.getName();
		else if (currT != null)
			s += currT.getName();
		else if (currS != null)
			s += currS.getName();


		s += "\n\nEnter the following to view:\n" + "\t(1) Logout\n" +
			"\t(2) Class\n" + "\t(3) Account Management\n";

		if (currA != null)
		{
			s += "\t(4) Create Admin\n" +
				"\t(5) Create Teacher\n" +
				"\t(6) Create Class\n";
		}

		s += "\nEnter a number: ";

		do
		{

			System.out.print(s);
			option = keyboard.nextInt();

			if (currA != null)
				switch(option)
				{
					case 2:
						classMenu();
						break;
					case 3:
						accountManagement();
						break;
					case 4:
						create('a');
						break;
					case 5:
						create('t');
						break;
					case 6:
						classCreator();
						break;
					default:
						System.out.println("\nEnter a correct option\n");
						break;
				}
			else
				switch(option)
				{
					case 2:
						classMenu();
						break;
					case 3:
						accountManagement();
						break;
					default:
						System.out.println("\nEnter a correct option\n");
						break;
				}

		} while (option != 1);

		currA = null;
		currT = null;
		currS = null;
	}

	public static void create(char userType)
	{
		if (userType == 'a')
			System.out.println("\nCreate Admin\n");

		if (userType == 't')
			System.out.println("\nCreate Teacher\n");

		if (userType == 's')
			System.out.println("\nCreate Student\n");


		System.out.print("\tName: ");
		String name = keyboard.next();
		System.out.print("\tID: ");
		int id = keyboard.nextInt();

		if (userType == 'a')
		{
			System.out.print("\tPassword: ");
			String pass = keyboard.next();
			Admin admin = new Admin(id, name, pass);
			a.add(admin);//Save Admin
			System.out.println("\nAdmin Added\n");
		}
		else
		{
			String pass = Long.toHexString(Double.doubleToLongBits(Math.random())); // Our Random pass generator*
			if (userType == 't')
			{
				Teacher teacher = new Teacher(id, name, pass);
				System.out.print(teacher);
				t.add(teacher); //Save Teacher
				System.out.println("\nTeacher Added Pass: " + (teacher.getPass()) + "\n");
			}

			if (userType == 's')
			{
				User student = new User(id, name, pass);
				s.add(student); //Save Student
				System.out.println("\nStudent Added Pass: " + (student.getPass()) + "\n");
			}
		}


	}

	public static void classMenu()
	{
		String s = "\n\tClass Menu\n\n";
		int i, option = 0;
		Vector<Class> c = null;

		if (currA != null)
		{
			if (currA.getClasses() != null)
				c = currA.getClasses();

		}
		else if (currT != null)
		{
			if (currT.getClasses() != null)
				c = currT.getClasses();
		}
		else if (currS != null)
		{
			if (currS.getClasses() != null)
				c = currS.getClasses();
		}

		if (c != null)
		{
			for (i = 0; i < c.size(); i++)
			{
				s += "\t(" + (i+1) + ") " + c.get(i) + "\n";
			}

			s += "\t(" + ++i + ") " + "Back\n" + "\nEnter Option: ";

			do
			{
				System.out.print(s);
				option = keyboard.nextInt();

				if ((option > 0) && (option < i))
					visitClass(c.get(--option));

			} while (option != i);

		}
		else
			System.out.println(s + "No classes to display\n");


	}

	public static void visitClass(Class currClass)
	{
		int option, i = 1;
		String s = "\nWelcome to " + currClass.getTitle() + "\n\n" +
				"\t(" + i++ + ") " + "View Quizzes\n" +
				"\t(" + i++ + ") " + "View Grades\n";

		if (currA != null)
		{
			s += "\t(" + i++ + ") " + "Modify Roster\n" +
				"\t(" + i++ + ") " + "Change Teacher\n";

		}
		else if (currT != null)
		{
			s += "\t(" + i++ + ") " + "Modify Roster\n";
		}

		s += "\t(" + i + ") " + "Back\n" +
			"\nEnter an option: ";
		i = 5;

		do
		{
			System.out.print(s);
			option = keyboard.nextInt();

			switch(option)
			{
				case 1:
					listQuizzes(currClass);
					break;
				case 2:
					System.out.println("\nView Grades\n");
					break;
				case 3:
					if ((currA != null) || (currT != null))
						System.out.println("\nModify Roster\n");
					else
						option = 5;
					break;
				case 4:
					if (currA != null)
						System.out.println("\nChange Teacher\n");
					else if (currT != null)
						option = 5;
					else if (currS != null)
						System.out.println("\nEnter a correct option\n");

					break;
				case 5:
					if ((currT != null) || (currS != null))
						System.out.println("\nEnter a correct option\n");
					break;
				default:
					System.out.println("\nEnter a correct option\n");
			}

		} while (option != i);

	}

	public static void listQuizzes(Class c)
	{
		Vector<Quiz>  quizzes = c.getQuiz();
		String s = "\n\tQuiz List\n\n";
		int i, option = 0;

		if (quizzes != null)
		{
			for (i = 0; i < quizzes.size(); i++)
			{
				s += "\t(" + (i+1) + ") " + quizzes.get(i) + "\n";
			}

			s += "\t(" + ++i + ") Create a Quiz\n" + "\t(" + ++i +
				 ") Back\n" + "\nEnter a Quiz number to view or go Back: ";
		}
		else
		{
			i = 1;
			s += "\tNo Quizzes to display\n" + "\n\t(1) Back\n\n" +
				"Enter 1 to go back to Class Menu: ";
		}

		do
		{
			System.out.print(s);
			option = keyboard.nextInt();

			if ((option > 0) && (option < i-1))
				if (quizzes != null)
					viewQuiz(quizzes.get(--option), c);
			else if (option == (i-1))
				c = createQuiz(c);
			else if ((option != i || (option != (i-1))))
				System.out.println("\nEnter a correct option\n");
		} while (option != i);
	}

	public static Class createQuiz(Class c)
	{
		/*
			This function will create a quiz by
			asking for a title, how many questions
			running through a loop to get the amount of
			questions. 
			-Each question will ask for the 
			initial question and then how many answers.
			-The Answers will ask for the statement and 
			it its true or false. 
			-This function also needs to SAVE class AND
			update c.
		*/
		System.out.println("\nCreate Quiz\n");
		return c;
	} 

	public static void viewQuiz(Quiz quiz, Class c)
	{
		System.out.println("\n" + quiz + "\n");
		String s;
		int option;
		if (currS != null)
			s = "\t(1) Take Quiz\n";
		else
			s = "\t(1) Modify Quiz\n";

		s += "\t(2) Back\n" + "\nEnter an option: ";

		do
		{
			System.out.print(s);
			option = keyboard.nextInt();
			if (option == 1)
			{
				if (currS != null)
					takeQuiz(quiz);
				else 
					modifyQuiz(quiz, c);

			}
			else if (option != 2)
				System.out.println("\nEnter a correct option\n");

		} while (option != 2);
	}

	public static void modifyQuiz(Quiz quiz, Class c)
	{
		/*This class will allow the teacher or admin
			-delete a quiz 
			-modify title 
			-answers
			**ADDITIONAL QUIZ FUNCTIONALITY NEEDED
		*/
		System.out.println("\nModify Quiz!\n");
	}

	public static void takeQuiz(Quiz quiz)
	{
		Vector<String> sAnswers = new Vector<String>();
		Vector<Question> question = quiz.getQuestion();
		System.out.println("\n" + quiz.getTitle() + "\n");
		String s;

		for (int i = 0; i < question.size(); i++)
		{
			s = ((question.get(i)).getTitle()) + "\n\n";
			Vector<Answer> answers = (((question.get(i)).getAnswer()));

			for (int j = 0; j < answers.size(); j++)
			{
				s += "\t(" + (j+1) + ") " + answers.get(j) + "\n";
			}
			s += "\nEnter your answer: ";
			System.out.print(s);
			int choice = keyboard.nextInt();
			sAnswers.add((answers.get(--choice)).getStmt());
		}

		int grade = quiz.grade(sAnswers); 
		System.out.println("\n" + grade + "\n");
		currS.modifyGrade(1, grade); //Needs to save student info after
	}

	public static void accountManagement()
	{
		int option = 0;
		boolean flag;

		do
		{
			System.out.println("Account Management\n");
			System.out.print("\t(1) Change Password\n" +
				"\t(2) Back\n" + "\nEnter: ");
			option = keyboard.nextInt();

			if (option == 1)
			{
				System.out.print("\nEnter Current Password: ");
				String oldPass = keyboard.next();
				System.out.print("Enter New Password: ");
				String newPass = keyboard.next();

				// Needs to save user after change
				if (currA != null)
					flag = currA.changePass(oldPass, newPass);
				else if (currT != null)
					flag = currT.changePass(oldPass, newPass);
				else
					flag = currS.changePass(oldPass, newPass);

				if (flag)
					System.out.println("\nPassword Change was successful");
				else
					System.out.println("\nCurrent Password is incorrect");
			}
			else if (option != 2)
				System.out.println("\nEnter a correct option.");
		}
		while (option != 2);
	}

	public static void modifyClass(Class c)
	{
	}


	public static void classCreator()
		{
			int id;
			String desc;
			String title;
			Teacher nTeacher = null;
			String s = "";

			System.out.println("\nCreate Class\n");

			System.out.print("Enter the courseID: ");
			id = keyboard.nextInt();
			System.out.print("Enter the Course Title: ");
			title = keyboard.next();
			System.out.print("Enter a Brief Course Description: ");
			desc = keyboard.next();

			if (t != null)
			{	
				do
				{
					System.out.println("\nPick a teacher\n");
		
					for (int j = 0; j < t.size(); j++)
					{
						nTeacher = t.get(j);
						s += "\t(" + (j+1) + ") " + nTeacher + "\n";
					}
		
					System.out.print(s + "\nEnter the Teacher ID: ");
					int tId = keyboard.nextInt();

					for (int j = 0; j < t.size(); j++)
					{
						if (tId == (t.get(j)).getId())
							nTeacher = t.get(j);
					}
				}
				while (nTeacher == null); 
			}
			else
				System.out.print("\nNo teachers found\n");
			
			if (nTeacher != null)
			{
				Class newClass = new Class(id, title, desc, nTeacher);
				currA.addClass(newClass); //Save class, admin, and teacher
				nTeacher.addClass(newClass); 
				System.out.println("\nClass Created\n");
			}
			else
				System.out.println("\nClass not created\n");
	}
}
