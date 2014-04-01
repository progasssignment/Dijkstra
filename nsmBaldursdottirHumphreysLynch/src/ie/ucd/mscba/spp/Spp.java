package ie.ucd.mscba.spp;

import java.util.Scanner;

import ie.ucd.mscba.graph.Edge;
import ie.ucd.mscba.graph.EdgeWeightedGraph;
import ie.ucd.mscba.graph.In;

public class Spp {
	private int n;
	private int r;
	public double D[];
	EdgeWeightedGraph G;
	Heap<Integer> heap = new Heap<Integer>();

	public Spp() {
		String inFile = "testStream.txt";
		String flag;

		In in = new In(inFile);
		flag = in.readString();
		n = in.readInt();
		in.readLine();
		if (!flag.equals("S"))
		{
			System.out.println("Input in wrong format");
			System.exit(0);
		}
		InitAdjList(in);
	}

	public void InitAdjList(In in) {
		String wArcs;
		String adjList;
		int numEdges;

		wArcs = in.readAll();
		numEdges = wArcs.split(System.getProperty("line.separator")).length;
		adjList = Integer.toString(n) + " " + numEdges + " " + wArcs;
		//System.out.println("Num " + numEdges);
		//System.out.println(adjList);

		In aList = new In(new Scanner(adjList));
		G = new EdgeWeightedGraph(aList);
		System.out.println(G.toString());		
	}

	public void TarjanSPathTree() {
		int p [] = new int [n];
		D = new double [n];

		initialise(r,p,D);
		heap.makeHeap(n);
		heap.insert(r);
		while (heap.size() > 0)
		{
			int u = heap.deleteMin();
			for (Edge e : G.adj[u]){
				int v = e.other(r);
				double w = e.weight();
				if (D[v] > D[u] + w)
				{
					D[v] = D[u] + w;
					p[v] = u;
				}
				if (v == 0)// IMPLEMENT v not in heap)
				{
					heap.insert(v);
				}
				else {
					heap.siftUp(v);
				}
			}
		}
	}

	public void initialise(int r, int p[], double D[])
	{
		for (int i = 0; i < n; i++)
			D[i] = Double.POSITIVE_INFINITY;

		D[r] = 0;
		//p[r] = null;
		for (Edge e : G.adj[r]){
			int i = e.other(r);
			double w = e.weight();
			D[i] = w;
			p[i] = r;
		}
	}	

}
