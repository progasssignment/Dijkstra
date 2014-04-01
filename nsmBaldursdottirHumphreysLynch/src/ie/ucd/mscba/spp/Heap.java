package ie.ucd.mscba.spp;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap <T extends Comparable<T>> {

	Spp instaceSpp = new Spp();
	double [] D = instaceSpp.D;
	private ArrayList<T> S;

	public Heap() {
		S = new ArrayList<T>();
	}

	public void siftUp(int i)
	{
		int p = i / 2; //(i-1)/2
		T item = S.get(i);
		T parent = S.get(p);
		if (D[(Integer) parent] > (D[(Integer) item]))
		{
			swap(i,p);
			siftUp(p);
		}
	}

	public void siftDown(int i, int size)
	{
		int c = 2*i; // 2*i + 1
		if (c < size && D[(Integer) S.get(c+1)] < D[(Integer) S.get(c)])
			c++;
		if (c < size && D[(Integer) S.get(i)] > D[(Integer) S.get(c)])
		{
			swap(i,c);
			siftDown(c,size);
		}
	}

	public void insert(T x)
	{
		S.add(x);
		siftUp(S.size());
	}

	public T deleteMin() throws NoSuchElementException {
		if (S.size() == 0) {
			throw new NoSuchElementException();
		}
		if (S.size() == 1) {
			return S.remove(0);
		}
		T min = S.get(0);
		S.set(0, S.remove(S.size()-1));
		siftDown(0,S.size()-1);
		return min;
	}

	public void makeHeap(int n)
	{
		for(int i = n/2; i >= 0; i--)
		{
			siftDown(i,n);
		}
	}

	public void swap(int i, int j) {
		T swap = S.set(i-1,S.get(j-1));
		S.set(j-1,swap);
	}
	
	public int size() {
		return S.size();
	}

}
