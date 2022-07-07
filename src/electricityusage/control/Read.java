/*
*inherits Input
*file reading
*/

package electricityusage.control;
import electricityusage.view.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;


public class Read implements Input
{
    @Override
    public Subdivision getChoice(String s)
    {
        Subdivision tree = null;
        try
        {
            tree = FileHandler.readFile(s);
        }
        catch(FileInputException e)
        {
            System.out.println("File invalid: " + e.getMessage());
        } 
        return tree;
    }

}
