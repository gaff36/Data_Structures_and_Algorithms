import java.io.*;
import java.util.*;

/** Abstract base class for graphs. A graph is a set
*   of vertices and a set of edges. Vertices are
*   represented by integers from 0 to n - 1. Edges
*   are ordered pairs of vertices.
*   @author Koffman and Wolfgang
*/

public abstract class AbstractGraph
    implements Graph {

  ArrayList<ArrayList<Integer>> total;    

  // Data Fields
  /** The number of vertices */
  private int numV;

  /** Flag to indicate whether this is a directed graph */
  private boolean directed;

  // Constructor
  /** Construct a graph with the specified number of vertices
      and the directed flag. If the directed flag is true,
      this is a directed graph.
      @param numV The number of vertices
      @param directed The directed flag
   */
  public AbstractGraph(int numV, boolean directed) {
    this.numV = numV;
    this.directed = directed;
    total = new ArrayList<ArrayList<Integer>>();
  }

  // Accessor Methods
  /** Return the number of vertices.-
      @return The number of vertices
   */
  public int getNumV() {
    return numV;
  }

  /** Return whether this is a directed graph.
      @return true if this is a directed graph
   */
  public boolean isDirected() {
    return directed;
  }

  // Other Methods


/** Finds number of connected vertexes using BreadthFirstSearch
*   @return int
*/
  public int nofccBreadthFirstSearch()
  {
    
    ArrayList<Boolean> visited = new ArrayList<Boolean>();
    for(int i = 0; i < numV; i++) visited.add(false);

    BreadthFirstSearch bfs = new BreadthFirstSearch();
    int counter = 0;

    while(counter < numV)
    { 
      boolean check = false;  
      int[] finish = bfs.breadthFirstSearch(this, counter);

        for(int i = 0; i < numV; i++)
        {
          if(visited.get(i) == false && finish[i] != -1)
          {
            visited.set(i, true);
            check = true;
          }
        }
        if(check) visited.set(counter, true);

      counter++;
   }

   counter = 0;

    for(int i = 0; i < numV; i++)
    {   
     if(visited.get(i) == true) counter++;
    }
    return counter;

  }

/** Finds number of connected vertexes using DepthFirstSearch
*   @return int
*/
  public int nofccDepthFirstSearch()
  {
   int counter = 0;
    ArrayList<Boolean> visited = new ArrayList<Boolean>();
    for(int i = 0; i < numV; i++) visited.add(false);

      for(int i = 0; i < numV; i++)
      {
        
        DepthFirstSearch ds = new DepthFirstSearch(this, i);
        counter = 0;
        for(int y : ds.getFinishOrder())
        {
          if(y != i && y != -1)
          {
            visited.set(y, true);
            counter++;
          }
        }  

        if(counter >= 1)  visited.set(i, true);
      }

    counter = 0;
    for(int i = 0; i < numV; i++)
    {   
     if(visited.get(i) == true) counter++;
    }
    return counter;
    
  }


  ////////////////////////////////////////////////////////////////////////

/** Finds importance of the each vertex in the connected components in the graph.
*   @return  ArrayList<Double>
*/
  public ArrayList<Double> importance()
  {
      double importanceOfV = 0;
      ArrayList<Double> result = new ArrayList<Double>();
      int size = nofccBreadthFirstSearch();

      for(int t = 0; t < getNumV(); t++)
      {
        importanceOfV = 0;
        for(int s = 0; s < getNumV(); s++)
        {
          for(int d = 0; d < getNumV(); d++)
          {
            if(t != s && t != d && s != d)
            {
      
        boolean [] isVisited = new boolean[numV];
        for(int i = 0; i < numV; i++) isVisited[i] = false;

        ArrayList<Integer> pathList = new ArrayList<Integer>();
        
        // add source to path[]
        pathList.add(s);
 
        // Call recursive utility
        importanceUtil(s, d, isVisited, pathList);

        int minPath = 2147483647;   // MAX_INTEGER
        double totalPaths = 0.0;
        double requestedPaths = 0.0;
        for(int i = 0; i < total.size(); i++)
        { 
          if(total.get(i).size() < minPath) minPath = total.get(i).size();
        }

        for(int i = 0; i < total.size(); i++)
        {
    
           if(total.get(i).size() == minPath)
           {               
                 totalPaths++;
                if(total.get(i).contains(t))  requestedPaths++;              
           }
        }
        //System.out.println(total);
        //System.out.println("REQUESTED: " + requestedPaths);
        //System.out.println("TOTAL: " + totalPaths);

        importanceOfV += (double) requestedPaths / (double) totalPaths;

        total.clear();
      }
    }
  }
    result.add(importanceOfV / (size * size));

}
   return result;
    }
        
  
/** Support method for finding importance.This methods finds all of the possible paths from u to d.
*   @param  u Integer
*   @param  d Integer
*   @param  isVisited boolean[]
*   @param  localPathList ArrayList<Integer>
*/
  private void importanceUtil(Integer u, Integer d, boolean[] isVisited, ArrayList<Integer> localPathList)                                 
    {
        if (u.equals(d)) 
        {
            //System.out.println(localPathList);

            if(!total.contains(localPathList))
            {
              total.add(new ArrayList<Integer>());
             for(int i = 0; i < localPathList.size(); i++) total.get(total.size() - 1).add(localPathList.get(i));
           }
            
            // if match found then no need to traverse more till depth
            return;
        }
 
        // Mark the current node
        isVisited[u] = true;
 
        Iterator <Edge> iter = edgeIterator(u);
        while(iter.hasNext()) {
          Edge temp = iter.next();
          int i = temp.getDest();

            if (!isVisited[i]) {
                // store current node
                // in path[]
                localPathList.add(i);
                importanceUtil(i, d, isVisited, localPathList);
 
                // remove current node
                // in path[]
                int p = localPathList.indexOf(i);
                localPathList.remove(p);
            }
        }
 
        // Mark the current node
        isVisited[u] = false;
    }

        
}
