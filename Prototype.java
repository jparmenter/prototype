import java.util.*;

public class Prototype
{
	public static Scanner keyboard;
	public static jaklUtilities utility;
	public static User curr;
	public static Vector<User> u;


	public static void main (String [] args)
	{
		keyboard = new Scanner(System.in);
		//u = new Vector<User>();
		//User admin = new Admin(1234, "Jeremy", "password");
		//User teacher = new Teacher(1111, "Bob", "software");
		//User student = new Student(2222, "James", "progit");
		//Class nClass = new Class(272111, "CSE 110 Intro to Java", "This class teaches you java", teacher);
		//u.add(admin);
		//u.add(student);
		//u.add(teacher);
		//Vector<Answer> answers = new Vector<Answer>();
		//Answer ans = new Answer("2009", false);
		//answers.add(ans);
		//ans = new Answer("2010", false);
		//answers.add(ans);
		//ans = new Answer("2011", true);
		//answers.add(ans);
		//Vector<Question> question = new Vector<Question>();
		//Question q = new Question("What year is it?", answers);
		//question.add(q);
		//Quiz quiz1 = new Quiz("Quiz 1", question);
		//nClass.addQuiz(quiz1);
		utility = new jaklUtilities();

		curr = null;

		login();
	}

	public static void login()
	{
		String str = null;
		int id = 0, option = 0;
		String pass = "";
		String error;
		User temp;

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
				* said id */

				char test = (Integer.toString(id)).charAt(0);

				//DB version
				temp = utility.openUser(id);

				if ((temp != null) && (pass.compareTo(temp.getPass()) == 0))
					curr = temp;
				else
					System.out.println("\nError: Enter correct id or password\n");

				// w/o DB
				/*if (u != null)
					for (int i = 0; i < u.size(); i++)
					{
						temp = u.get(i);
						if ((temp.getId() == id) && (pass.compareTo(temp.getPass()) == 0))
							curr = temp;
					}*/



				if (curr != null)
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

		s += "Hello, " + curr.getName();


		s += "\n\nEnter the following to view:\n" + "\t(1) Logout\n" +
			"\t(2) Class\n" + "\t(3) Account Management\n";

		if (curr.getStatus() == 'a')
		{
			s += "\t(4) Create Admin\n" +
				"\t(5) Create Teacher\n" +
				"\t(6) Create Student\n" +
				"\t(7) Create Class\n";
		}

		s += "\nEnter a number: ";

		do
		{

			System.out.print(s);
			option = keyboard.nextInt();

			if (curr.getStatus() == 'a')
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
						create('s');
						break;
					case 7:
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

		curr = null;
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
			utility.writeAdmin(id, name, pass, 'a');
			System.out.println("\nAdmin Added\n");
		}
		else
		{
			String pass = Long.toHexString(Double.doubleToLongBits(Math.random())); // Our Random pass generator*
			if (userType == 't')
			{
				Teacher teacher = new Teacher(id, name, pass);
				//System.out.print(teacher);
				utility.writeTeacher(id, name, pass, 't');
				System.out.println("\nTeacher Added Pass: " + (teacher.getPass()) + "\n");
			}

			if (userType == 's')
			{
				Student student = new Student(id, name, pass);
				//u.add(student);
				utility.writeStudent(id, name, pass, 's'); //Write student should change
				System.out.println("\nStudent Added Pass: " + (student.getPass()) + "\n");
			}
		}


	}

	public static void classMenu()
		{
			String s = "\n\tClass Menu\n\n";
			//curr.showClassIds();
			int i, option = 0;

			//c = null;//curr.getClasses();

			if (curr.classList != null)
			{
				for (i = 0; i < curr.classList.size(); i++)
				{															//c.get(i) replaced with printTitle from table
					s += "\t(" + (i+1) + ") " + curr.showClassId(i) + "\n";
				}

				s += "\t(" + ++i + ") " + "Back\n" + "\nEnter Option or classId: ";

				do
				{
					System.out.print(s);
					option = keyboard.nextInt();

					if ((option > 0))
					{
						Class tempClass = utility.openClass(option);
						visitClass(tempClass);             //instanciate selected class here (open from db)
					}

				} while (option != i);

			}
			else
				System.out.println(s + "No classes to display\n");


		}


	public static void visitClass(Class currClass)
	{
		int option, i = 1;
		String s = "\nWelcome to " + currClass.getTitle() + "\n\n" +
				"\t(" + i++ + ") " + "Take Quiz\n" +
				"\t(" + i++ + ") " + "View Grades\n";
		if (curr.getStatus() == 'a')
		{
			s += "\t(" + i++ + ") " + "Modify Roster\n" +
				"\t(" + i++ + ") " + "Change Teacher\n";



		}
		else if (curr.getStatus() == 't')
		{
			s += "\t(" + i++ + ") " + "Add Student\n" +
				"\t(" + i++ + ") " + "Create Quiz\n";
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
					viewQuiz(currClass);
					break;
				case 2:
					System.out.println("View Grades");
					break;
				case 3:
					if (curr.getStatus() != 's')
						System.out.println("Modify Roster");
					else
						option = 5;
					break;
				case 4:
					if (curr.getStatus() == 'a')
						System.out.println("Change Teacher");
					else if(curr.getStatus() == 't')
						{
						createQuiz(currClass);
						option = 5;
						}
					break;
				case 5:
					if (curr.getStatus() != 'a')
						System.out.println("\nEnter a correct option\n");
					break;
				default:
					System.out.println("\nEnter a correct option\n");
			}

		} while (option != i);

	}

	/*public static void listQuizzes( classId)
	{
		String s = "\n\tQuiz List\n\n";
		int i, option = 0;

		if (quizzes != null)
		{
			for (i = 0; i < quizzes.size(); i++)
			{
				s += "\t(" + (i+1) + ") " + quizzes.get(i) + "\n";
			}

			s += "\t(" + ++i + ") Back\n" + "\nEnter a Quiz number to view or go Back: ";
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

			if ((option > 0) && (option < i))
					viewQuiz(utility.openQuiz(option));
			else
				System.out.println("\nEnter a correct option\n");
		} while (option != i);
	}*/

	public static void viewQuiz(Class currClass)
	{
		Quiz currQuiz;
		int option;

		for(int i = 0; i < currClass.getNumQuizes();i++)
		{
			System.out.println("\n" + currClass.showQuizId(i));
		}
		do
		{
		System.out.println("Enter the QuizID you would like to take:");
		option = keyboard.nextInt();


			currQuiz = utility.openQuiz(option);
			takeQuiz(currQuiz);

			/*System.out.print(s);
			option = keyboard.nextInt();
			if (option == 1)
			{
				if (curr.getStatus() == 's')
					takeQuiz(quiz);
				else
					System.out.println("\nModify Quiz!\n"); //modifyQuiz(quiz);

			}
			else if (option != 2)
				System.out.println("\nEnter a correct option\n");*/

		} while (option != 3);
	}

	public static void takeQuiz(Quiz currQuiz)
	{
		//Vector<String> sAnswers = new Vector<String>();
		//Vector<Question> question = quiz.getQuestion();
		System.out.println("\n" + currQuiz.getId() + "\n");
		String s;
		int choice = 0;

		for(int i = 0; i < currQuiz.numQuestions(); i++)
		{
			System.out.println(currQuiz.numQuestions() + " : " + i);
			System.out.println((currQuiz.getQuestion(i)) + "\n\n");
			System.out.print(currQuiz.getAnswer1(i) + "\n");
			System.out.print(currQuiz.getAnswer2(i) + "\n");
			System.out.print(currQuiz.getAnswer3(i) + "\n");
			System.out.print(currQuiz.getAnswer4(i) + "\n");

			System.out.println("\nEnter your answer: ");
			choice = keyboard.nextInt();
		}


		//int grade = quiz.grade(sAnswers);
		//System.out.println("\n" + grade + "\n");
		//curr.modifyGrade(1, grade);
	}

	public static void accountManagement()
	{
		int option = 0;

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

				utility.changePass(curr.getId(), curr.getName(), oldPass, newPass);
			}
			else if (option != 2)
				System.out.println("\nEnter a correct option.");
		}
		while (option != 2);
	}


	public static void createQuiz(Class currClass)
	{
		try{
			int quizLength;
			int index = 0;
			int quizId;
			int currentClass = currClass.getId();

			System.out.println("\nCreate Quiz\n");

			System.out.print("Enter the quiz Id:");
			quizId = keyboard.nextInt();
			System.out.print("Enter the number of questions in this quiz: ");
			quizLength = keyboard.nextInt();

			String[] theQuestions = new String[quizLength];
			String[] ans1Array = new String[quizLength];
			String[] ans2Array = new String[quizLength];
			String[] ans3Array = new String[quizLength];
			String[] ans4Array = new String[quizLength];
			String[] correctAnsArray = new String[quizLength];

			while(index < quizLength)
			{
			String questionHolder = "";
			String ans1Holder = "";
			String ans2Holder = "";
			String ans3Holder = "";
			String ans4Holder = "";
			String correctAnsHolder = "";

			System.out.print("Enter question" + (index+1) + ":");
			while(questionHolder.equals(""))
			{
				questionHolder = keyboard.nextLine();
			}
			System.out.print("Enter Answer 1:");

			while(ans1Holder.equals(""))
			{
				ans1Holder = keyboard.nextLine();
			}
			System.out.print("Enter Answer 2:");

			while(ans2Holder.equals(""))
			{
				ans2Holder = keyboard.nextLine();
			}
			System.out.print("Enter Answer 3:");

			while(ans3Holder.equals(""))
			{
				ans3Holder = keyboard.nextLine();
			}
			System.out.print("Enter Answer 4:");

			while(ans4Holder.equals(""))
			{
				ans4Holder = keyboard.nextLine();
			}
			System.out.print("Enter Correct Answer:");
			while(correctAnsHolder.equals(""))
			{
				correctAnsHolder = keyboard.nextLine();
			}

			theQuestions[index] = questionHolder;
			ans1Array[index] = ans1Holder;
			ans2Array[index] = ans2Holder;
			ans3Array[index] = ans3Holder;
			ans4Array[index] = ans4Holder;
			correctAnsArray[index] = correctAnsHolder;

			index++;
			}
			utility.writeQuiz(quizId, theQuestions, ans1Array, ans2Array, ans3Array, ans4Array, correctAnsArray, currentClass);
		}
		catch(Exception e)
		{
				System.out.println("catch");
				System.out.println(e.getMessage());
		}
	}

	public static void classCreator()
		{
			int id;
			String desc = "";
			String title;
			int nUser;

			System.out.println("\nCreate Class\n");

			System.out.print("Enter the courseID: ");
			id = keyboard.nextInt();
			System.out.print("Enter the Course Title: ");
			title = keyboard.next();
			System.out.print("Enter a Breif Course Description: ");
			while(desc.equals(""))
			{
				desc = keyboard.nextLine();
			}
			System.out.print("Enter the ID of the teacher teaching this course: ");
			nUser = keyboard.nextInt();

			User tempU = utility.openUser(nUser);
			if (tempU != null)
			{
				utility.writeClass(id, title, desc, nUser);
				System.out.println("\nClass Created\n");
			}
			else
			{
				System.out.println("\nClass not created\n");
			}

			/*if (t != null)
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
				System.out.print("\nNo teachers found\n");*/

	}
}