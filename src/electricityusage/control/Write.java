/*********************
*inherits Output
*gets file write implementation
***********************/

package electricityusage.control;
import electricityusage.view.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;


public class Write implements Output
{
    @Override
    public String getChoice(Subdivision tree, String s)
    {
        try
        {
            FileHandler.writeFile(tree, s);
        }
        catch(FileInputException e)
        {
            System.out.println(e.getMessage());
        }
        return "";
    }

}
