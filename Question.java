import java.util.*;

public class Question
{
	private String title;
	private Vector<Answer> answer;

	public Question(String _title, Vector<Answer> _answer)
	{
		title = _title;
		answer = _answer;
	}

	// Only one answer
	public boolean grade(String a)
	{
		Answer temp;
		int i = 0;
		do
		{
			temp = answer.get(i++);
		}while ((a.compareTo(temp.getStmt()) != 0) || (i < answer.size()));

		if (a.compareTo(temp.getStmt()) != 0)	 
			return false;

		return (temp.getAns());
	}
	
	public Vector<Answer> getAnswer()
	{
		return answer;
	}

	public String getTitle()
	{
		return title;
	}
	
	public String toString()
	{
		String s = title + "\n\n";
		
		for (int i = 0; i < answer.size(); i++)
			s += i+1 + "  " + answer.get(i) + "\n";

		return s;
	}
}
