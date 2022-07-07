/*
*Interface that controller classes inherit
*implement strategy pattern for output/display
*reference: Worksheet 2 submitted 
*/
package electricityusage.control;
import electricityusage.view.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;



public interface Output
{
    String getChoice(Subdivision tree, String s);
    
}




