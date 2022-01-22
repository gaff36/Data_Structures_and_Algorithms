import java.util.*;

/** A class for calling Dijkstra's algorithm.
 *  @author Koffman and Wolfgang
*/

public class DijkstrasAlgorithm {

  /** Custom Dijkstra’s Shortest-Path algorithm with requested properties.
      @param graph The weighted graph to be searched
      @param start The start vertex
      @param pred Output array to contain the predecessors
                  in the shortest path
      @param dist Output array to contain the distance
                  in the shortest path
      @param selection1 for determining which Edge property will be used for algorithm(0: weight, 1: distance, 2: time, 3: quality)
      @param selection2 for determining which associative operation will be used for algorithm
   */
  public static void dijkstrasAlgorithm(Graph graph,int start,int[] pred,double[] dist, int selection1, int selection2)                                       
  {
    int numV = graph.getNumV();
    HashSet < Integer > vMinusS = new HashSet < Integer > (numV);
    // Initialize V–S.
    for (int i = 0; i < numV; i++) {
      if (i != start) {
        vMinusS.add(i);
      }
    }

    if(selection1 == 0)
    {
     // Initialize pred and dist.
      for (int v : vMinusS) {
       pred[v] = start;
       dist[v] = graph.getEdge(start, v).getWeight();
       System.out.println(graph.getEdge(start, v).getWeight());
      }

      System.out.println("\n\n1");
       for(int i = 0; i < graph.getNumV(); i++)System.out.println(pred[i]);
       System.out.println("2");
      for(int i = 0; i < graph.getNumV(); i++)System.out.println(dist[i]);


      // Main loop
     while (vMinusS.size() != 0) {
        // Find the value u in V–S with the smallest dist[u].
        double minDist = Double.POSITIVE_INFINITY;
       int u = -1;
      for (int v : vMinusS) {
        if (dist[v] < minDist) {
          minDist = dist[v];
          u = v;
        }

        }
       // Remove u from vMinusS.
        vMinusS.remove(u);

        // Update the distances.
        for (int v : vMinusS) {
         if ( graph.isEdge(u, v)) {
           double weight = graph.getEdge(u, v).getWeight();
           if (func(dist[u], weight, selection2) < dist[v]) {
              dist[v] = func(dist[u], weight, selection2);
             pred[v] = u;
           }
         }
        }
      }
    }
    else if(selection1 == 1)
    {
     for (int v : vMinusS) {
       pred[v] = start;
       if(graph.isEdge(start, v))
       dist[v] = graph.getEdge(start, v).getDistance();
      else dist[v] = Double.POSITIVE_INFINITY;
    }
      
      // Main loop
     while (vMinusS.size() != 0) {
        // Find the value u in V–S with the smallest dist[u].
        double minDist = Double.POSITIVE_INFINITY;
       int u = -1;
      for (int v : vMinusS) {
        if (dist[v] < minDist) {
          minDist = dist[v];
          u = v;
        }

        }
       // Remove u from vMinusS.
        vMinusS.remove(u);

        // Update the distances.
        for (int v : vMinusS) {
         if ( graph.isEdge(u, v)) {
           double distance = graph.getEdge(u, v).getDistance();
           if (func(dist[u], distance, selection2) < dist[v]) {
              dist[v] = func(dist[u], distance, selection2);
             pred[v] = u;
           }
         }
        }
      }
    }
    else if(selection1 == 2)
    {

     // Initialize pred and dist.
      for (int v : vMinusS) {
       pred[v] = start;
        if(graph.isEdge(start, v))
       dist[v] = graph.getEdge(start, v).getTime();
      else dist[v] = Double.POSITIVE_INFINITY;
      }
      // Main loop
     while (vMinusS.size() != 0) {
        // Find the value u in V–S with the smallest dist[u].
        double minDist = Double.POSITIVE_INFINITY;
       int u = -1;
      for (int v : vMinusS) {
        if (dist[v] < minDist) {
          minDist = dist[v];
          u = v;
        }

        }
       // Remove u from vMinusS.
        vMinusS.remove(u);

        // Update the distances
        for (int v : vMinusS) {
         if ( graph.isEdge(u, v)) {
           double time = graph.getEdge(u, v).getTime();
           if (func(dist[u], time, selection2) < dist[v]) {
              dist[v] = func(dist[u], time, selection2);
             pred[v] = u;
           }
         }
        }
      }
    }
    else if(selection1 == 3)
    {
     // Initialize pred and dist.
      for (int v : vMinusS) {
       pred[v] = start;
       if(graph.isEdge(start, v))
       dist[v] = graph.getEdge(start, v).getQuality();
      else dist[v] = Double.POSITIVE_INFINITY;
      }
      // Main loop
     while (vMinusS.size() != 0) {
        // Find the value u in V–S with the smallest dist[u].
        double minDist = Double.POSITIVE_INFINITY;
       int u = -1;
      for (int v : vMinusS) {
        if (dist[v] < minDist) {
          minDist = dist[v];
          u = v;
        }

        }
       // Remove u from vMinusS.
        vMinusS.remove(u);

        // Update the distances.
        for (int v : vMinusS) {
         if ( graph.isEdge(u, v)) {
           double quality = graph.getEdge(u, v).getQuality();
           if (func(dist[u], quality, selection2) < dist[v]) {
              dist[v] = func(dist[u], quality, selection2);
             pred[v] = u;
           }
         }
        }
      }
    }
    else
    {
      System.out.println("INVALID SELECTION");
    }
  }

/** A method for returning bunch of associative operations.
 *  @param a double
 *  @param b double
 *  @param selection int
 *  @return func result double
 */
  private static double func(double a, double b, int selection)
  {
    double result = -1;   
    if(selection == 0) result = (a + b);
    else if(selection == 1) result = (a * b);
    else if(selection == 2) result = (a * a) + (b * b);
    else if(selection == 3) result = a / (a + b);
    else result = (a + b + (a * b));
    return  result; 
  }

}
