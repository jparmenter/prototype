import java.util.*;
import java.io.*;

public class jaklUtilities
{
	public jaklUtilities()
	{
	}

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
