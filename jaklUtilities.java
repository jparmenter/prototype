import java.util.*;
import java.io.*;
import java.sql.*;

public class jaklUtilities
{
	private String url = "jdbc:postgresql://68.98.104.148:5432/jitt";

	public jaklUtilities()
	{
		try
		{
			java.lang.Class.forName("org.postgresql.Driver");
		}
		catch(Exception e)
		{
			System.out.println("This should never happen");
		}
	}

	public void writeAdmin(int id, String name, String pass, char acctType)
	{
		 try
		 {
			// Load the JDBC driver.
			//java.lang.Class.forName("org.postgresql.Driver");

			// Establish the connection to the database
			//String url = "jdbc:postgresql://localhost:5432/jakl";
			Connection conn = DriverManager.getConnection(url,"postgres","jakl");

			//This is how you write. just create a statement and execute a sql query.

			Statement st = conn.createStatement();
			st.execute("INSERT INTO \"user\"\nVALUES\n(" + id + ", '" + name + "', '" + pass + "', '" + acctType + "');");

			conn.close();

		}

		 catch (Exception e)
		 {
		   System.out.println("Got an exception! ");
		   System.out.println(e.getMessage());
		 }
	}


	public void writeTeacher(int id, String name, String pass, char acctType)
		{
			 try
			 {
				// Load the JDBC driver.
				//java.lang.Class.forName("org.postgresql.Driver");

				// Establish the connection to the database
				//String url = "jdbc:postgresql://localhost:5432/jakl";
				Connection conn = DriverManager.getConnection(url,"postgres","jakl");

				//This is how you write. just create a statement and execute a sql query.

				Statement st = conn.createStatement();
				st.execute("INSERT INTO \"user\"\nVALUES\n(" + id + ", '" + name + "', '" + pass + "', '" + acctType + "');");

				conn.close();

			}

			 catch (Exception e)
			 {
			   System.out.println("Got an exception! ");
			   System.out.println(e.getMessage());
			 }
	}

	public void writeStudent(int id, String name, String pass, char acctType)
		{
			 try
			 {
				Connection conn = DriverManager.getConnection(url,"postgres","jakl");

				//This is how you write. just create a statement and execute a sql query.

				Statement st = conn.createStatement();
				st.execute("INSERT INTO \"user\"\nVALUES\n(" + id + ", '" + name + "', '" + pass + "', '" + acctType + "');");
				conn.close();
			}
			 catch (Exception e)
			 {
			   System.out.println("Got an exception! ");
			   System.out.println(e.getMessage());
			 }
		}


	public User openUser(int id)
		{
			String strUser = null;
			String strPass = null;
			String strType = null;
			Character charType;
			int numClasses;
			try
			{
				//java.lang.Class.forName("org.postgresql.Driver");

				//String url = "jdbc:postgresql://localhost:5432/jakl";
				Connection conn = DriverManager.getConnection(url,"postgres","jakl");

			   Statement stmt = conn.createStatement();
			   ResultSet user = stmt.executeQuery("SELECT username from \"user\" WHERE id=" + id);
			   ResultSetMetaData rsmd1 = user.getMetaData();
			   while (user.next())
			   {
				   strUser = user.getString(1);
			   }
			   user.close();
			   ResultSet pass = stmt.executeQuery("SELECT pass from \"user\" WHERE id=" + id);
			   ResultSetMetaData rsmd2 = pass.getMetaData();
			   while (pass.next())
			   {
				   strPass = pass.getString(1);
			   }
				pass.close();

			   ResultSet type = stmt.executeQuery("SELECT acctype from \"user\" WHERE id=" + id);
			   ResultSetMetaData rsmd3 = type.getMetaData();
			   while (type.next())
			   {
				   strType = type.getString(1);
			   }
			   type.close();
				//SELECT array_to_string(ARRAY[class], ',') FROM "user" WHERE id=9999
			   ResultSet classes = stmt.executeQuery("SELECT array_to_string(ARRAY[class], ',') from \"user\" WHERE id=" + id);
			   ResultSetMetaData rsmd4 = classes.getMetaData();
			   int numRows = rsmd4.getColumnCount();
			   numClasses = classes.getInt(1);
			   int[] tempArray = new int[5];
			   //classes.next();
			   for(int temp_index = 1; temp_index <= numRows; temp_index++)
			   {
				   //System.out.println(classes.getInt(temp_index));
				   tempArray[temp_index-1] = classes.getInt(temp_index);
			   }
			   conn.close();

			charType = strType.charAt(0);
			if (charType == 't')
			{
				Teacher teach = new Teacher(id, strUser, strPass);
				return teach;
			}
			else if (charType == 'a')
			{
				Admin admin = new Admin(id, strUser, strPass);
				return admin;
			}

			else
			{
				if(numClasses > 0)
				{
				Student student = new Student(id,strUser,strPass, tempArray);
				return student;
				}
				else
				{
				Student student = new Student(id, strUser,strPass);
				return student;
				}
			}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				return null;
			}

		}

	public void writeClass(int id, String name, String description, int teacher)
	{
		 try
		 {
			Connection conn = DriverManager.getConnection(url,"postgres","jakl");

			//This is how you write. just create a statement and execute a sql query.

			Statement st = conn.createStatement();
			st.execute("INSERT INTO \"class\"\nVALUES\n(" + id + ", '" + name + "', '" + description + "', NULL," + teacher + ")");

			conn.close();

		}

		 catch (Exception e)
		 {
		   System.out.println("The course was not saved successfully");
		   System.out.println(e.getMessage());
		 }
	}

	public Class openClass(int courseId)
	{
		 String title = null;
		 String desc = null;
		 int teachId;
		try
		{

			 Connection conn = DriverManager.getConnection(url,"postgres","jakl");
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM \"class\" WHERE id=" + courseId);
			 ResultSetMetaData rsmd = rs.getMetaData();
			 rs.next();
			 rs.next();
			 title = rs.getString(1);
			 rs.next();
			 desc = rs.getString(1);
			 rs.next();
			 teachId = rs.getInt(1);
			 rs.close();

			 conn.close();
			Class tClass = new Class(courseId, title, desc, teachId);
			return tClass;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

	}

/*
	public void showClasses(int teacherId)
	{
		try
		{
		   Connection conn = DriverManager.getConnection(url,"postgres","jakl");

		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT * FROM \"class\" WHERE id=" + teacherId);
		   ResultSetMetaData rsmd = rs.getMetaData();
		   int numberOfColumns = rsmd.getColumnCount();
		   int rowCount = 1;

		   	while (rs.next())
		   	{
		   		System.out.println("Line " + rowCount + ":");
		   		for (int i = 1; i <= numberOfColumns; i++)
		   		{
		   			System.out.print("   Col " + i + ":  ");
		   			System.out.println(rs.getString(i));
		   		}

		   		System.out.println("");
		   		rowCount++;
			}

			rs.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("FFFAAIIIILLLLZZZZZZZZZZ");
			System.out.println(e.getMessage());
		}
	}

*/
	public void addClass(int classId, int id)
	{
		try
		{
			Connection conn = DriverManager.getConnection(url,"postgres","jakl");
			Statement st = conn.createStatement();
			st.executeUpdate("UPDATE \"user\" SET class = class || ARRAY["+ classId + "] WHERE id=" + id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void changePass(int id, String user, String pass, String nPass)
	{
		String strPass = null;
		String newPass = nPass;
		try
		{

			Connection conn = DriverManager.getConnection(url,"postgres","jakl");

		   Statement stmt = conn.createStatement();
		   ResultSet oldPass = stmt.executeQuery("SELECT pass from \"user\" WHERE id=" + id);
		   oldPass.next();
		   strPass = oldPass.getString(1);
		   oldPass.close();
		   if (pass.equals(strPass))
		   {
			   stmt.executeUpdate("UPDATE \"user\" SET pass = \'" + newPass + "\' WHERE id=\'" + id + "\'");
			   System.out.println("Password change successful!");

		   }
		   else
		   {
			  System.out.println("Password change unsuccessful, please try again.");
		   }

		   conn.close();
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}








/*OLD CODE //////////////////////////////////////////////////////////////////////////////////////////////////





	public Admin openAdmin(int id)
	{
		String user = null;
		String pass = null;
			try {
			FileInputStream fstream = new FileInputStream("/home/alex/workspace/prototype/users/admin/" + id + ".txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));


			String strLine;
			//Read File Line By Line
			user = br.readLine();
			pass = br.readLine();
			//Close the input stream
			in.close();
			}catch (Exception e){//Catch exception if any
			return null;
			}

		Admin admin = new Admin(id, user, pass);
		return admin;
	}


	public Admin writeAdmin(int id, String user, String pass)
	{
		Writer writer = null;
		String text = null;
		System.out.println("Enter in data to be entered");
		Scanner scan = new Scanner(System.in);
		text = scan.nextLine();

		try {
		    //String text = "This is a text file";

		    File file = new File("/home/alex/workspace/prototype/users/admin/" + id + ".txt");
		    writer = new BufferedWriter(new FileWriter(file));
		    writer.write(text);
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
			}
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    }
	}
}
*/