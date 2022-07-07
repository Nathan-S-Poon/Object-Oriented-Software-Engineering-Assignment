/*
*inherits Input
*generates a random tree of depth 1-5
*/

package electricityusage.control;
import electricityusage.view.*;
import electricityusage.model.*;
import java.util.*;
import java.io.*;


public class Generate implements Input
{
    @Override
    public Subdivision getChoice(String s)
    {
        Subdivision tree;
        CompositeSubdivision newComposite = null;
        LeafSubdivision newLeaf;
        String curComposite = "root";
        int children;
        boolean firstChild;
        int ID = 0; //unique name for every new node
        int type; //random: 0 for a leaf, 1 for a composite
        //generate random number for depth
        Random rand = new Random();
        int depth = rand.nextInt(5);  
        //root node generation
        CompositeSubdivision root = new CompositeSubdivision("root");
        int i = 0;
        while(i < depth)
        {    
            children = rand.nextInt(4) + 2;
            int j = 0;
            firstChild = true;
            //construct next child node and other nodes on same level
            while(j < children&&(i < depth))//create random number of children
            {
                if(firstChild)
                {
                    firstChild = false;
                    newComposite = new CompositeSubdivision("city"
                                                         +Integer.toString(ID));
                    ID++;
                    i++;
                }
                else
                {
                    Subdivision newNode = null;
                    type = rand.nextInt(2);
                    switch(type)
                    {
                        case 0:
                            newLeaf = new LeafSubdivision("city"+Integer.toString(ID));
                            generatePower(newLeaf);
                            newNode = newLeaf;
                            ID++;
                        break;
                        case 1:
                            newNode = new CompositeSubdivision("city"+Integer.toString(ID));
                            ID++;
                        break;
                    }
                    newComposite.addNode(newComposite.getName(), newNode);
                }
                j++;
            } 
            //add new node to current tree
            root.addNode(curComposite, newComposite);//add new nodes to last composite
            curComposite = newComposite.getName();//make new node composite
        }
        tree = root; 
        return tree;
    }

    //randomly assigns power consumption between 0 - 1000.0
    public void generatePower(LeafSubdivision node)
    {
        Random rand = new Random();
        int store;
        double[] arr = new double[8];
        for(int ii = 0; ii < 8; ii++)
        {
            arr[ii] = rand.nextDouble();
            store = (int)(arr[ii]*10000);
            arr[ii] = ((double)store)/10;
        }
        node.setPower("dm", arr[0]);
        node.setPower("da", arr[1]);
        node.setPower("de", arr[2]);
        node.setPower("em", arr[3]);
        node.setPower("ea", arr[4]);
        node.setPower("ee", arr[5]);
        node.setPower("h", arr[6]);
        node.setPower("s", arr[7]);
    }

}
