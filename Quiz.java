import java.util.*;

public class Quiz
{
	private String title;
	private Vector<Question> question;

	public Quiz(String _title, Vector<Question> _question)
	{
		title = _title;
		question = _question;
	}

	public int grade(Vector<String> a)
	{
		int sum = 0;
		Question tempQ;
		String tempA;
		for (int i = 0; i < question.size(); i++)
		{
			tempQ = question.get(i);
			tempA = a.get(i);
			if (tempQ.grade(tempA))
				sum++;
		}

		return (sum / question.size());
	}

	public Vector<Question> getQuestion()
	{
		return question;
	}
	
	public String getTitle()
	{
		return title;
	}

	public String toString()
	{
		return title + "\t" + question.size() + " questions";
	}
}
