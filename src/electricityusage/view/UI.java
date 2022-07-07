/*
*Takes in and processes command line arguments
*sends array to controller - order is type1,input,type2,input
*/
package electricityusage.view;
import electricityusage.control.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;
public class UI
{
    //processes command line input and checks if valid
    /*
    *Errors: invalid num args, no filename, g/r not first
    *no g/r, no d/w, d and w, g and r
    *g/r must be before d/w
    */
    public static void processInput(String[] args, Controller inControl)
                                    throws CommandLineException
    {
       String[] array = new String[4];
       Controller control = inControl;
       int pos = 0;//where the next argument should be
       //check for max output
       if(args.length > 1 && args.length <= 4)
       { 
           if(args[0].equals("-r"))
           { 
               if(args[1].equals("-g"))
               {
                   throw new CommandLineException("can't have -r followed by -g");
               }
               
               //try reading in file
               pos = 2;//-d/-w will be in position
               //control.doChoice(args[0], args[1]); 
               array[0] = args[0];
               array[1] = args[1];
           }
           else if(args[0].equals("-g"))
           {
               if(args[1].equals("-r"))
               {
                   throw new CommandLineException("can't have -g followed by -r");
               }
               //generate random tree
               //control.doChoice(args[0]);
               array[0] = args[0];
               array[1] = "";
               pos = 1;
           }
           else
           {
               throw new CommandLineException("first argument needs to be -r"
                                              + " or -g");
           }
           if(args.length > pos)//check if -d/-w argument exists
           {
               //second arguments
               if(args[pos].equals("-d"))
               {
                   if(args.length > pos+1)
                   {
                       throw new CommandLineException("can't have input after -d");
                   }
                   //control.doChoice(args[pos]);
                   array[2] = args[pos];
                   array[3] = "";
               }
               else if(args[pos].equals("-w"))
               {
                   if(args.length < pos+2)//if there is no file name
                   {
                       throw new CommandLineException("Need a file name for output");
                   } 
                   //write to file
                   //control.doChoice(args[pos], args[pos++]);   
                   array[2] = args[pos];
                   pos++;
                   if(args[pos].equals("-d"))
                   {
                       throw new CommandLineException("can't have -d after -w");
                   }
                   array[3] = args[pos]; 
               }
               else 
               {
                   throw new CommandLineException("Next input must be -w/-d");
               }
           }
           else
           {
               throw new CommandLineException("not enough arguments, need -w/-d");
           }
        }
        else
        {
            throw new CommandLineException("wrong number of arguments");
        }
        //send validated input to controller        
        System.out.println(control.doOption(array));
    }

}


