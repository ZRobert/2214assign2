import lists.*;


public class Main {

	  //you can use the following initializations to debug your implementations
    public static void main(String[] args) {		 
		   
		  CircularLinkedList<String> dls = new CircularLinkedList<String>(false);
		
		  //adds these elements to the list:
		  // badcbadcba	  
		  for(int i = 0; i < 4; i++)
		  {
		  	//	System.out.println(i);	
				if(i%4 == 0)
					dls.addToRear("b");
				if(i%4 == 1)
					dls.addToRear("c");
				if(i%4 == 2)
					dls.addToRear("d");
				if(i%4 == 3)
					dls.addToFront("e");

			}
			
			
			dls.addBefore("a","b");
			dls.addAfter("f","e");
			
			System.out.println("Pre initial sort:\n" + dls);
			dls.descendingSort();
			System.out.println("Post initial sort:\n" + dls);
			/*
			//MoveUp MoveDown Test
			dls.moveUp("f");
			dls.moveDown("a");
			System.out.println("List should still be descending:\n" + dls);
			dls.moveDown("f");
			dls.moveDown("d");
			dls.moveUp("a");
			dls.moveUp("c");
			System.out.println("f, d move down; a, c move up:\n" + dls);
			*/

			
			//Contains and Size Test
			/*
			System.out.println("Contains z? " + dls.contains("z"));
			System.out.println("Contains a? " + dls.contains("a"));
			System.out.println("Contains f? " + dls.contains("f"));
			System.out.println("Size: " + dls.size());
			*/
			//Remove First/Last Test
			/*
			System.out.println("remove first called: "+dls.removeFirst());
			System.out.println("remove last called: " + dls.removeLast());
			System.out.println(dls);
			*/
			
			//Slide Test
			/*
			dls.slide(2);
			System.out.println("Two Slide: \n" + dls);
			dls.slide(-2);
			System.out.println("Neg Two Slide: \n" + dls);
			*/
			
			//Remove Target Test
			/*
			dls.remove("a");
			dls.remove("f");
			dls.remove("d");
			System.out.println("Removed a, f, d: \n" + dls);
			*/
			
			//Shuffle & ascendingSort Test
			/*		   
			dls.shuffle();
			System.out.println("Shuffle! \n" + dls);
			
			dls.addToFront("9");
			dls.addToRear("9");
			System.out.println("Added 9's to the front and rear... should not be sorted \n" + dls);
			
			dls.ascendingSort();
			System.out.println("Ok... should be sorted in ascending order now\n" + dls);
			*/
			/*
			//addBefore/After Test
			dls.slide(1); 	//shake off isSorted without shuffle
			dls.slide(-1);
			System.out.println("Pre-add before/after test (fedcba): \n"+ dls);
			dls.remove("a");
			dls.remove("f");
			dls.remove("d");
			dls.remove("c");
			dls.addBefore("f","e");
			dls.addAfter("a","b");
			System.out.println("After add before/after test (feba):\n" + dls);
			*/
			/*
			//Prevailing ascending sort over descending sort test
			//Tests to see if ascending sort will overcome descending sort
			dls.descendingSort();
			System.out.println("Descending Sort: \n" + dls);
			dls.remove("f");
			dls.remove("e");
			dls.addToFront("b");
			dls.addToFront("a");
			System.out.println("Prevailing sort test:\n" + dls);
			 */
			/*
			//Opposite prevailing sort test
			//tests to make sure that the correct sort is performed (ascending)
			dls.ascendingSort();
			System.out.println("Ascending Sort: \n" + dls);
			dls.remove("a");
			dls.remove("b");
			dls.addToRear("b");
			dls.addToRear("a");
			System.out.println("Prevailing sort test:\n" + dls);
			*/
			//Clear Test
			dls.clear();
			System.out.println("List after clear: " + dls);       
	 }
   
}