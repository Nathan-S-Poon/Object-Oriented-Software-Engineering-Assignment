/**
*Stores a power object that is stored in the leaf nodes
*
*/
package electricityusage.model;
import java.util.*;
import electricityusage.control.*;
import electricityusage.view.*;


public class Power
{

    private HashMap<String,Double> categories;

    public Power()
    {
        categories = new HashMap<String,Double>();
        categories.put("dm", 0.0);
        categories.put("da", 0.0);
        categories.put("de", 0.0);
        categories.put("em", 0.0);
        categories.put("ea", 0.0);
        categories.put("ee", 0.0);
        categories.put("h", 0.0);
        categories.put("s", 0.0);
    }

    //set power for a category
    public void setPower(String s, Double d)
    {
        categories.replace(s,d);
    }

    //add on more power from another power object
    public void addPower(Power p)
    {
        double newPower;
        for(String key : categories.keySet())
        {
            newPower = categories.get(key) + p.getPower(key);
            setPower(key, newPower);
        }
    }

    public String csvFormat()
    {
        String output = ":";
        for(String key : categories.keySet())
        {
            output = output + "," + key + "=" + categories.get(key);
        } 
        output = output + "\n";
        return output;
    }


    public double getPower(String key)
    {
        return categories.get(key);
    }

    public boolean hasKey(String s)
    {    
        return categories.containsKey(s);
    }

    //returns formated string of each power
    public String toString()
    {
        String result;
        result = "Weekday morning   : " + categories.get("dm") + "\n" +
                 "Weekday afternoon : " + categories.get("da") + "\n" +
                 "Weekday evening   : " + categories.get("de") + "\n" +
                 "Weekend morning   : " + categories.get("em") + "\n" +
                 "Weekend afternoon : " + categories.get("ea") + "\n" +
                 "Weekend evening   : " + categories.get("ee") + "\n" +
                 "Heatwave          : " + categories.get("h") + "\n" +
                 "Special event     : " + categories.get("s") + "\n";

        return result;
    }


} 









