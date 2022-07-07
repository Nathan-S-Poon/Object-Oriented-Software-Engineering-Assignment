/*
*Controller
*decides on what functions to call
*
*/
package electricityusage.control;
import electricityusage.view.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;

public class Controller
{
    //create tree
    private Subdivision tree;
    //create a hash of choices
    private HashMap<String, Input> in;
    private HashMap<String, Output> out;

    public Controller()
    {
        tree = null;
        //create choices
        Input random = new Generate();
        Output display = new Display(); 
        Input read = new Read();
        Output write = new Write();
        //store choices in hash
        in = new HashMap<String, Input>();
        out = new HashMap<String, Output>();
        in.put("-r", read);
        in.put("-g", random);
        out.put("-w", write);
        out.put("-d", display); 
    }

    public String doOption(String[] array)
    {
        String output = "";
        //get tree first
        tree = in.get(array[0]).getChoice(array[1]); 
        if(tree != null)
        {
            output = out.get(array[2]).getChoice(tree, array[3]);
        }
        return output;
    }



}



















