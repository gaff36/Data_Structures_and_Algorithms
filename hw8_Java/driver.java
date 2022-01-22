import java.util.*;

/** driver class
 *  @author Emre Sezer
*/
public class driver
{
	public static void main(String [] argc)
	{
		Scanner myObj = new Scanner(System.in);

		System.out.println("TESTING PART 1");

		int [] pred;
		double [] dist;
		DijkstrasAlgorithm dj = new DijkstrasAlgorithm();

		AbstractGraph d = new ListGraph(8, true);
		AbstractGraph d2 = new MatrixGraph(8, true);

		d.insert(new Edge(0,1,2,2,2,2));
		d.insert(new Edge(0,3,4,4,4,4));
		d.insert(new Edge(0,4,4,4,4,4));
		d.insert(new Edge(1,2,1,1,1,1));
		d.insert(new Edge(1,4,1,1,1,1));
		d.insert(new Edge(4,2,2,2,2,2));
		d.insert(new Edge(4,6,4,4,4,4));
		d.insert(new Edge(4,7,10,10,10,10));
		d.insert(new Edge(3,6,2,2,2,2));
		d.insert(new Edge(6,7,4,4,4,4));
		d.insert(new Edge(2,5,11,11,11,11));
		d.insert(new Edge(5,7,5,5,5,5));
		d.insert(new Edge(4,5,20,20,20,20));

		d2.insert(new Edge(0,1,2,2,2,2));
		d2.insert(new Edge(0,3,4,4,4,4));
		d2.insert(new Edge(0,4,4,4,4,4));
		d2.insert(new Edge(1,2,1,1,1,1));
		d2.insert(new Edge(1,4,1,1,1,1));
		d2.insert(new Edge(4,2,2,2,2,2));
		d2.insert(new Edge(4,6,2,2,2,2));
		d2.insert(new Edge(4,7,10,10,10,10));
		d2.insert(new Edge(3,6,2,2,2,2));
		d2.insert(new Edge(6,7,4,4,4,4));
		d2.insert(new Edge(2,5,11,11,11,11));
		d2.insert(new Edge(5,7,5,5,5,5));
		d2.insert(new Edge(4,5,20,20,20,20));


		pred = new int [d.getNumV()];
		dist = new double [d.getNumV()];
		
		dj.dijkstrasAlgorithm(d, 0, pred, dist, 1, 2);

		System.out.println("Using Distance as Edge Property and (a * a) + (b * b) as operation With ListGraph: ");

		System.out.println("PRED ARRAY: ");
		for(int i : pred) System.out.println(i);

		System.out.println("\nDIST ARRAY: \n");
		for(double j : dist) System.out.println(j);

		System.out.println("Using Time as Edge Property and a / (a + b) as operation With ListGraph: ");	

		pred = new int [d.getNumV()];
		dist = new double [d.getNumV()];
		
		dj.dijkstrasAlgorithm(d, 0, pred, dist, 2, 3);

		System.out.println("PRED ARRAY: ");
		for(int i : pred) System.out.println(i);

		System.out.println("\nDIST ARRAY: \n");
		for(double j : dist) System.out.println(j);

		System.out.println("Using Quality as Edge Property and (a + b + (a * b)) as operation With ListGraph: ");	

		pred = new int [d.getNumV()];
		dist = new double [d.getNumV()];
		
		dj.dijkstrasAlgorithm(d, 0, pred, dist, 3, 4);

		System.out.println("PRED ARRAY: ");
		for(int i : pred) System.out.println(i);

		System.out.println("\nDIST ARRAY: \n");
		for(double j : dist) System.out.println(j);


		

		/////////////////////////////////////////////////////////////////////////////////		
		
		pred = new int [d2.getNumV()];
		dist = new double [d2.getNumV()];
		
		dj.dijkstrasAlgorithm(d2, 0, pred, dist, 1, 2);

		System.out.println("Using Distance as Edge Property and (a * a) + (b * b) as operation With MatrixGraph: ");

		System.out.println("PRED ARRAY: ");
		for(int i : pred) System.out.println(i);

		System.out.println("\nDIST ARRAY: \n");
		for(double j : dist) System.out.println(j);

		System.out.println("Using Time as Edge Property and a / (a + b) as operation With MatrixGraph: ");	

		pred = new int [d2.getNumV()];
		dist = new double [d2.getNumV()];
		
		dj.dijkstrasAlgorithm(d2, 0, pred, dist, 2, 3);

		System.out.println("PRED ARRAY: ");
		for(int i : pred) System.out.println(i);

		System.out.println("\nDIST ARRAY: \n");
		for(double j : dist) System.out.println(j);

		System.out.println("Using Quality as Edge Property and (a + b + (a * b)) as operation With MatrixGraph: ");	

		pred = new int [d2.getNumV()];
		dist = new double [d2.getNumV()];
		
		dj.dijkstrasAlgorithm(d2, 0, pred, dist, 3, 4);

		System.out.println("PRED ARRAY: ");
		for(int i : pred) System.out.println(i);

		System.out.println("\nDIST ARRAY: \n");
		for(double j : dist) System.out.println(j);			


		myObj.nextLine();

		System.out.println("TESTING PART 3");
		System.out.println("Using Graph With Size Equals to 10 to Calcute Importance of Each Vertex in the Graph");

		AbstractGraph gr = new ListGraph(10, false);
		
		gr.insert(new Edge(0,2));
		gr.insert(new Edge(0,1));
		gr.insert(new Edge(1,3));
		gr.insert(new Edge(3,4));
		gr.insert(new Edge(4,6));
		gr.insert(new Edge(7,8));
		gr.insert(new Edge(4,5));
		gr.insert(new Edge(6,7));
		gr.insert(new Edge(8,9));
		gr.insert(new Edge(7,9));
		gr.insert(new Edge(2,4));
		gr.insert(new Edge(0,3));

		ArrayList <Double> al = gr.importance();

		for(int i = 0; i < al.size(); i++)	System.out.println("Vertex " + i + ") " + al.get(i));

		System.out.println("\nUsing Graph With Size Equals to 5 to Calcute Importance of Each Vertex in the Graph\n");	

		AbstractGraph gr2 = new ListGraph(5, false);
		
		gr2.insert(new Edge(0,1));
		gr2.insert(new Edge(1,2));
		gr2.insert(new Edge(1,3));
		gr2.insert(new Edge(2,3));
		gr2.insert(new Edge(2,4));
		gr2.insert(new Edge(3,4));
	

		al = gr2.importance();

		for(int i = 0; i < al.size(); i++)	System.out.println("Vertex " + i + ") " + al.get(i));	

		myObj.nextLine();

		System.out.println("TESTING PART 2");	

		ArrayList <AbstractGraph> graphs1 = new ArrayList <AbstractGraph>();
		for(int i = 0; i < 10; i++) graphs1.add(new ListGraph(1000, false));
		ArrayList <AbstractGraph> graphs2 = new ArrayList <AbstractGraph>();
		for(int i = 0; i < 10; i++) graphs2.add(new ListGraph(2000, false));
		ArrayList <AbstractGraph> graphs3 = new ArrayList <AbstractGraph>();
		for(int i = 0; i < 10; i++) graphs3.add(new ListGraph(5000, false));
		ArrayList <AbstractGraph> graphs4 = new ArrayList <AbstractGraph>();
		for(int i = 0; i < 10; i++) graphs4.add(new ListGraph(10000, false));

		int avg = 0;	
		int total = 0;	
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 1000; j++)
			{
				int rand1 = (int)(Math.random() * 1000);
				int rand2 = (int)(Math.random() * 1000);
				graphs1.get(i).insert(new Edge(rand1, rand2));
			}
		}
		for(int i = 5; i < 10; i++)
		{
			for(int j = 0; j < 250; j++)
			{
				int rand1 = (int)(Math.random() * 1000);
				int rand2 = (int)(Math.random() * 1000);
				graphs1.get(i).insert(new Edge(rand1, rand2));
			}
		} 


		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 2000; j++)
			{
				int rand1 = (int)(Math.random() * 2000);
				int rand2 = (int)(Math.random() * 2000);
				graphs2.get(i).insert(new Edge(rand1, rand2));
			}
		}
		for(int i = 5; i < 10; i++)
		{
			for(int j = 0; j < 500; j++)
			{
				int rand1 = (int)(Math.random() * 2000);
				int rand2 = (int)(Math.random() * 2000);
				graphs2.get(i).insert(new Edge(rand1, rand2));
			}
		} 


		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5000; j++)
			{
				int rand1 = (int)(Math.random() * 5000);
				int rand2 = (int)(Math.random() * 5000);
				graphs3.get(i).insert(new Edge(rand1, rand2));
			}
		}
		for(int i = 5; i < 10; i++)
		{
			for(int j = 0; j < 1250; j++)
			{
				int rand1 = (int)(Math.random() * 5000);
				int rand2 = (int)(Math.random() * 5000);
				graphs3.get(i).insert(new Edge(rand1, rand2));
			}
		} 


		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 10000; j++)
			{
				int rand1 = (int)(Math.random() * 10000);
				int rand2 = (int)(Math.random() * 10000);
				graphs4.get(i).insert(new Edge(rand1, rand2));
			}
		}
		for(int i = 5; i < 10; i++)
		{
			for(int j = 0; j < 2500; j++)
			{
				int rand1 = (int)(Math.random() * 10000);
				int rand2 = (int)(Math.random() * 10000);
				graphs4.get(i).insert(new Edge(rand1, rand2));
			}
		} 

		double avg1 = 0.0;
		long startTime1 = 0;
		long endTime1 = 0;
		long totalTime1 = 0;

		double avg2 = 0.0;
		long startTime2 = 0;
		long endTime2 = 0;
		long totalTime2 = 0;
	
		for(int i = 0; i < 10; i++)
		{
			startTime1 = System.nanoTime();
			int m = graphs1.get(i).nofccBreadthFirstSearch();
			endTime1 = System.nanoTime();
			totalTime1 += (endTime1 - startTime1);

			startTime2 = System.nanoTime();
			int n = graphs1.get(i).nofccDepthFirstSearch();
			endTime2 = System.nanoTime();
			totalTime2 += (endTime2 - startTime2);
		}	

		avg1 = (double) totalTime1 / 10.0;
		avg2 = (double) totalTime2 / 10.0;

		System.out.println("AVG BREADFIRST RESULT FOR SIZE 1000: " + avg1);
		System.out.println("AVG DEPTHFIRST RESULT FOR SIZE 1000: " + avg2);

		avg1 = 0.0;
		avg2 = 0.0;
		totalTime1 = 0;
		totalTime2 = 0;

		for(int i = 0; i < 10; i++)
		{
			startTime1 = System.nanoTime();
			int m = graphs2.get(i).nofccBreadthFirstSearch();
			endTime1 = System.nanoTime();
			totalTime1 += (endTime1 - startTime1);

			startTime2 = System.nanoTime();
			int n = graphs2.get(i).nofccDepthFirstSearch();
			endTime2 = System.nanoTime();
			totalTime2 += (endTime2 - startTime2);
		}	

		avg1 = (double) totalTime1 / 10.0;
		avg2 = (double) totalTime2 / 10.0;

		System.out.println("AVG BREADFIRST RESULT FOR SIZE 2000: " + avg1);
		System.out.println("AVG DEPTHFIRST RESULT FOR SIZE 2000: " + avg2);

		avg1 = 0.0;
		avg2 = 0.0;
		totalTime1 = 0;
		totalTime2 = 0;

		for(int i = 0; i < 10; i++)
		{
			startTime1 = System.nanoTime();
			int m = graphs3.get(i).nofccBreadthFirstSearch();
			endTime1 = System.nanoTime();
			totalTime1 += (endTime1 - startTime1);

			startTime2 = System.nanoTime();
			int n = graphs3.get(i).nofccDepthFirstSearch();
			endTime2 = System.nanoTime();
			totalTime2 += (endTime2 - startTime2);
		}	

		avg1 = (double) totalTime1 / 10.0;
		avg2 = (double) totalTime2 / 10.0;

		System.out.println("AVG BREADFIRST RESULT FOR SIZE 5000: " + avg1);
		System.out.println("AVG DEPTHFIRST RESULT FOR SIZE 5000: " + avg2);

		avg1 = 0.0;
		avg2 = 0.0;
		totalTime1 = 0;
		totalTime2 = 0;

		for(int i = 0; i < 10; i++)
		{
			startTime1 = System.nanoTime();
			int m = graphs4.get(i).nofccBreadthFirstSearch();
			endTime1 = System.nanoTime();
			totalTime1 += (endTime1 - startTime1);

			startTime2 = System.nanoTime();
			int n = graphs4.get(i).nofccDepthFirstSearch();
			endTime2 = System.nanoTime();
			totalTime2 += (endTime2 - startTime2);
		}	

		avg1 = (double) totalTime1 / 10.0;
		avg2 = (double) totalTime2 / 10.0;

		System.out.println("AVG BREADFIRST RESULT FOR SIZE 10000: " + avg1);
		System.out.println("AVG DEPTHFIRST RESULT FOR SIZE 10000: " + avg2);

		avg1 = 0.0;
		avg2 = 0.0;
		totalTime1 = 0;
		totalTime2 = 0;


	}
}