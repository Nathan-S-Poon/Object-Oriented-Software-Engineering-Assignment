/*
*inherits Output
*Returns tree and power consumption
*/

package electricityusage.control;
import electricityusage.view.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;


public class Display implements Output
{
    @Override
    public String getChoice(Subdivision tree, String s)
    {
        String result =  "Display\n" + tree.display() + 
                         "\nTotal power\n" + tree.getTotalPower().toString(); 
        return result;
    }

}
