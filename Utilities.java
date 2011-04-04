import java.util.*;
package utilities;

public class Utilities
{
	public Utilities()
	{
	}

	public String generatePass()
	{
		Long.toHexString(Double.doubleToLongBits(Math.random()));
	} 
}
