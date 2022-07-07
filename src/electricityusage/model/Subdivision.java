/*******
*Subdivision, composite patter, CompositeSubdivision and leafSubdivision
*inherit from this
*/
package electricityusage.model;
import java.util.*;
import java.io.*;
import electricityusage.control.*;
import electricityusage.view.*;


public interface Subdivision
{
    String getName();
    String display();
    int getPosition();
    void updatePosition(int ii);
    boolean hasNode(String s);
    Subdivision getNode(String s);
    Power getTotalPower();
    String getFileFormat();
}


