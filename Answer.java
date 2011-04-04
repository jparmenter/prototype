
public class Answer
{
	private String stmt;
	private boolean ans;

	public Answer(String _stmt, boolean _ans)
	{
		stmt = _stmt;
		ans = _ans;
	}	

	public String getStmt()
	{
		return stmt;
	}

	public Boolean getAns()
	{
		return ans;
	}

	public String toString()
	{
		return stmt;
	}
}
