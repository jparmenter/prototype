import java.util.*;
import java.io.*;
import java.sql.*;

public class jaklUtilities
{
	private String url = "jdbc:postgresql://68.98.104.148:5432/test"; //NEED TO CHANGE

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

	public void writeUser(int id, String name, String pass, char acctType)
	{
		 try
		 {
			// Load the JDBC driver.
			//java.lang.Class.forName("org.postgresql.Driver");

			// Establish the connection to the database
			//String url = "jdbc:postgresql://localhost:5432/jakl";
			Connection conn = DriverManager.getConnection(url,"postgres","pass");

			//This is how you write. just create a statement and execute a sql query.

			Statement st = conn.createStatement();
			st.execute("INSERT INTO users\nVALUES\n(" + id + ", '" + name + "', '" + pass + "'" + acctType + "');");

			conn.close();

		}

		 catch (Exception e)
		 {
		   System.out.println("Got an exception! ");
		   System.out.println(e.getMessage());
		 }
	}

	public void showUsers()
	{
		try
		{
			//java.lang.Class.forName("org.postgresql.Driver");

			//String url = "jdbc:postgresql://localhost:5432/jakl";
			Connection conn = DriverManager.getConnection(url,"postgres","jakl");

		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT * FROM users");
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
			//rsmd.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void writeClass(int id, String name, String description)
	{
		 try
		 {
			Connection conn = DriverManager.getConnection(url,"postgres","jakl");

			//This is how you write. just create a statement and execute a sql query.

			Statement st = conn.createStatement();
			st.execute("INSERT INTO classes\nVALUES\n(" + id + ", '" + name + "', '" + description + "', NULL, NULL);");

			conn.close();

		}

		 catch (Exception e)
		 {
		   System.out.println("The course was not saved successfully");
		   System.out.println(e.getMessage());
		 }
	}

	public void showClasses(int teacherId)
	{
		try
		{
		   Connection conn = DriverManager.getConnection(url,"postgres","jakl");

		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT * FROM classes WHERE id=" + teacherId + "");
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
			//rsmd.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("FFFAAIIIILLLLZZZZZZZZZZ");
			System.out.println(e.getMessage());
		}
	}

	public User checkLogin(int id, String pass)
	{
		try
		{
			//java.lang.Class.forName("org.postgresql.Driver");

			//String url = "jdbc:postgresql://localhost:5432/jakl";
			Connection conn = DriverManager.getConnection(url,"postgres","pass");

		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT id FROM users");
		   ResultSetMetaData rsmd = rs.getMetaData();
		   int numberOfColumns = rsmd.getColumnCount();

		   	while (rs.next())
		   	{
		   		for (int i = 1; i <= numberOfColumns; i++)
		   		{
		   			System.out.print("   Col " + i + ":  ");
		   			System.out.println(rs.getString(i));

		   			if(rs.getInt(i) == id);
		   				System.out.println("wtf do i do from here");
		   		}
			}

			rs.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

		return null;

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