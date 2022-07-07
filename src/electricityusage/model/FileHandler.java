/*
*File I/O implementation
*
*/    
package electricityusage.model;
import java.util.*;
import java.io.*;

import electricityusage.control.*;
import electricityusage.view.*;

public class FileHandler
{


    /**************************************
    *readFile
    *returns tree  
    *Based on code from Dave Cooper AddressBookApp (accessed 17 March 2021)
    **************************************/
    public static Subdivision readFile(String inFilename) throws FileInputException
    {
        Subdivision tree = null;      
        CompositeSubdivision pTree = null;
        FileInputStream fileStrm = null;
        InputStreamReader rdr; 
        BufferedReader bufRdr; 
        int lineNum; 
        String line; 
        boolean leafIsRoot = false;
        try  
        { 
            fileStrm  = new FileInputStream(inFilename); 
            rdr = new InputStreamReader(fileStrm); 
            bufRdr = new BufferedReader(rdr); 
     
            lineNum = 0; 
            line = bufRdr.readLine(); 
            while (line != null)  
            { 
                if(leafIsRoot)//if try to add more nodes after leaf root
                {
                    throw new FileInputException("Root node is a leaf, " + 
                                                "Cannot add anymore nodes");
                }
                lineNum++; 
                String[] arr = line.split(","); 

                switch(arr.length)
                {
                    case 1://root node
                        if(lineNum > 1)
                        {
                            throw new FileInputException("Two root nodes");
                        }
                        pTree = new CompositeSubdivision(arr[0]);  
                    break;
                    case 2://Composite node
                        processComposite(arr, pTree);
                    break;
                    default:
                        if(arr.length <= 10)
                        {
                            if(lineNum == 1)//if leaf node is the root
                            {
                                LeafSubdivision leaf = new LeafSubdivision(arr[0]);
                                processPower(leaf, arr);
                                tree = leaf;
                                leafIsRoot = true;
                            }
                            else
                            {
                                processLeaf(arr, pTree);
                            }
                        }
                        else
                        {
                            throw new FileInputException("invalid file format");
                        }
                }
                line = bufRdr.readLine(); 
            } 
                if(!leafIsRoot)
                {
                    tree = pTree;
                }
                fileStrm.close(); 
        } 
        catch (IOException e)  
        { 
            if (fileStrm != null)  
            { 
                try  
                { 
                    fileStrm.close(); 
                }
                catch (IOException ex2)  
                {} 
            } 
            //System.out.println("Error in file processing: " + e.getMessage()); 
            throw new FileInputException("File not found");
        }
        return tree; 
    } 

 
    private static void processComposite(String[] arr, CompositeSubdivision tree) throws FileInputException
    {
        //check if composite exists
        if(!tree.hasNode(arr[1]))
        {
            throw new FileInputException("Composite node doesn't exists");
        }
        //check if node is actually able to add
        if(!(tree.getNode(arr[1]) instanceof CompositeSubdivision))
        {
            throw new FileInputException("can't add node to this node");
        }
        if(tree.hasNode(arr[0]))//check for duplicate 
        {
            throw new FileInputException("node already exists");
        }
        CompositeSubdivision newNode = new CompositeSubdivision(arr[0]);
        tree.addNode(arr[1], newNode);
    }

    private static void processLeaf(String[] arr, CompositeSubdivision tree) throws FileInputException
    {
        if(tree.hasNode(arr[0]))//check for duplicate 
        {
            throw new FileInputException("node already exists");
        }
        LeafSubdivision newNode = new LeafSubdivision(arr[0]);
        //check if Composite exists
        if(!tree.hasNode(arr[1]))
        {
            throw new FileInputException("Composite node doesn't exists");
        } 
        if(!(tree.getNode(arr[1]) instanceof CompositeSubdivision))
        {
            throw new FileInputException("can't add node to this node");
        }
        //get power 
        processPower(newNode, arr);
        tree.addNode(arr[1], newNode);

    }

    /************************************
    *ProcessPower
    *processes array of power into leaf node
    ***********************************/
    private static void processPower(LeafSubdivision newNode, String[] arr) throws 
                                    FileInputException
    {
        //check power input
        for(int ii = 2; ii < arr.length; ii++)
        { 
            String[] powerArr = arr[ii].split("=");
            if(powerArr.length != 2)
            {
                throw new FileInputException("invalid power format");
            }
            //put in category
            if(!newNode.hasKey(powerArr[0]))
            {
                throw new FileInputException("not a valid power category");
            }
            try
            {
                newNode.setPower(powerArr[0], Double.parseDouble(powerArr[1])); 
            }
            catch(IllegalArgumentException e)
            {
                throw new FileInputException("power not a double", e);   
            }
        }
    }

    /***********************************
    *writeFile
    *Based on code from Dave Cooper AddressBookApp (accessed 17 March 2021)
    ***********************************/
    public static void writeFile(Subdivision tree, String fileName) throws FileInputException
    {
        FileOutputStream fileStrm= null; 
        PrintWriter pw; 
   
        try  
        { 
            fileStrm = new FileOutputStream(fileName); 
            pw = new PrintWriter(fileStrm); 

            pw.println(tree.getFileFormat());

            pw.close();
        } 
        catch (IOException e) 
        { 
            if (fileStrm != null) 
            { 
                try  
                {  
                    fileStrm.close();  
                }  
                catch (IOException ex2) 
                {}   
            }  
         //System.out.println("Error in writing to file: " + e.getMessage()); 
            throw new FileInputException("Error with file writing"); 
        } 
    } 
 




}

