import java.util.*;

public class Test3
{

public static void main(String[] args)
{

	Collection collection = new ArrayList();

	Integer[] tablica= {2,4,6,8,10};


	collection.addAll(Arrays.asList(tablica));



	System.out.println("zwykłe wywołanie"+collection);
	print(collection.iterator()) ;
}

public static void print(Iterator it)
{
	while(it.hasNext())
	{	
	System.out.println(it.next());
	
	}
}





}
