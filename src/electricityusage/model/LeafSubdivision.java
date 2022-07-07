/*
*LeafSubdivision
*leafs contain power consumption and categories
*is part of the composite pattern
*/
package electricityusage.model;
import java.util.*;
import electricityusage.control.*;
import electricityusage.view.*;


public class LeafSubdivision implements Subdivision
{
    private String name;
    private int position;//what level in the tree it is
    private Power power;
 
    //intialise name and power categories
    public LeafSubdivision(String inName)
    {
        position = 0;
        name = inName;
        power = new Power();
   }    

    @Override
    public String getName() 
    {
        return name;
    }
    @Override
    public Subdivision getNode(String s)
    {
        Subdivision node = null;
        if(s.equals(name))
        {
            node = this;
        }
        return node;
    }


    @Override 
    public String getFileFormat()
    {
        return name + power.csvFormat();
    }

    @Override 
    public boolean hasNode(String s)
    {
        return (s.equals(name));
    }

    @Override
    public String display() 
    {
        return name;
    }

    @Override
    public void updatePosition(int ii)
    {
        position = position + ii;
    }

    @Override
    public int getPosition()
    {
        return position;
    }


    //set power for a category
    public void setPower(String s, Double d)
    {
        power.setPower(s,d);
    }

    public boolean hasKey(String s)
    {    
        return power.hasKey(s);
    }

    @Override
    public Power getTotalPower()
    { 
        return power;
    }
}






