/*
*CompositeSubdivision - inherits from Subdivision
*can contain other subdivision objects 
*
*/
package electricityusage.model;
import java.util.*;
import electricityusage.control.*;
import electricityusage.view.*;



public class CompositeSubdivision implements Subdivision
{
    private Set<Subdivision> tree;
    private String name;
    private int position;

    public CompositeSubdivision(String inName)
    {
        position = 0;
        name = inName;
        tree = new HashSet<>();
    }
    @Override
    public String getName()
    {
        return name;
    }

    public void addNode(String inName, Subdivision node)
    {
        CompositeSubdivision curPNode;
        if(name.equals(inName))
        {
            node.updatePosition(position + 1);
            tree.add(node);
        }
        else
        {
            for(Subdivision curNode : tree)
            {   
                if(curNode instanceof CompositeSubdivision)
                {
                    curPNode = (CompositeSubdivision)curNode;
                    curPNode.addNode(inName, node);
                }
            }
        }
    } 

    @Override
    public Subdivision getNode(String s)
    {
        Subdivision found = null;
        if(s.equals(name))
        {
            found = this;
        }
        else
        {
            for(Subdivision node : tree)
            {    
                if(node.getNode(s) != null)
                {
                    found = node.getNode(s); 
                }  
            }
        }
        return found;
    }


    @Override
    public boolean hasNode(String s)
    {
        boolean found = false;
        if(s.equals(name))
        {
            found = true;
        }
        else
        {
            for(Subdivision node : tree)
            {    
                if(node.hasNode(s))
                {
                    found = true; 
                }  
            }
        }
        return found;
    }

    @Override
    public String display()
    {
        String result = name;
        String childStr = "";
        String curNodeStr = "";
        String spaces = "";
        Subdivision newNode;
        //check if there are any children
        if(!tree.isEmpty())
        {
            Iterator<Subdivision> iter = tree.iterator();
            while(iter.hasNext())
            {
                spaces = "\n";
                newNode = iter.next();
                //increment spaces based on level
                for(int ii = 0; ii < newNode.getPosition(); ii++)
                {   
                    spaces = spaces + "  ";
                }   
                curNodeStr = spaces;
                
                curNodeStr = spaces + newNode.display();
              
                childStr = childStr + curNodeStr;
            }
        } 
        return name + childStr;
    }

    @Override
    public void updatePosition(int ii)
    {
        position = position + ii;
        for(Subdivision node : tree)
        {
            node.updatePosition(node.getPosition() + 1);
        }
    }

    @Override
    public int getPosition()
    {
        return position;
    }




    //returns total power of all leafs
    @Override
    public Power getTotalPower()
    {
        Power newPower = new Power(); 
        for(Subdivision node : tree)
        {
            newPower.addPower(node.getTotalPower());        
        }
        return newPower;
    } 



    @Override
    public String getFileFormat()
    {
        String output = "";
         
        //get all children
        for(Subdivision node : tree) 
        {   //split to allow this name to be inserted
            String[] arr = node.getFileFormat().split(":");
            if(arr.length == 1)//if Composite with no child nodes
            {
                output = output + arr[0] + "," + name;
            }
            else
            {
                output = output + arr[0] + "," + name + arr[1];
            }
        }
        if(position != 0)//if not the root
        {
            output = name + ":" + "\n" + output;
        }
        else
        {
            output = name + "\n" + output;
            output = output.trim();
        }
        return output;
    }

}
















