/*
*Interface that controller classes inherit
*implement strategy pattern for input/create tree
*reference: Worksheet 2 submitted 
*/
package electricityusage.control;
import electricityusage.view.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;



public interface Input
{
    Subdivision getChoice(String s);
}


