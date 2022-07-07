/*
*Exceptions thrown in file reader
*/
package electricityusage.model;
import java.util.*;
import java.lang.*;
import electricityusage.control.*;
import electricityusage.view.*;


public class FileInputException extends Exception
{
    public FileInputException(String message) 
    {
        super(message);
    }

    public FileInputException(String message, Throwable cause) 
    {
        super(message, cause);
    }



}







