/*
*The main class
*Is the start of the program
*/
import electricityusage.model.*;
import electricityusage.control.*;
import electricityusage.view.*;
import java.util.*;

public class CityElectricApp
{
    public static void main(String[] args)
    {
        try
        {
            Controller control = new Controller();
            UI.processInput(args, control);
        }
        catch(CommandLineException e)  
        {
            System.out.println(e.getMessage());
        }


    }

}





