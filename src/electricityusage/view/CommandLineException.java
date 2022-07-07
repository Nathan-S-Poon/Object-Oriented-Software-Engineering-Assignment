/*
*thrown if command line is given incorrect arguments
*
*/
package electricityusage.view;
import electricityusage.model.*;
import electricityusage.control.*;
import java.util.*;
public class CommandLineException extends Exception
{
    public CommandLineException(String message, Throwable cause)
    {
        super(message, cause);
    }
    public CommandLineException(String message)
    {
        super(message);
    }



}







