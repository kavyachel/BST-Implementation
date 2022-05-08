
package a3;

public class Main {

   public static void main(String[] args){
   /*
    * You will test your own bst implementation in here.
    * Do what you wish to in Main, as we will not be running it.
    * The grader uses its own Main and calls the methods of your other classes.
    *
    * In order to test you should create BST objects, put data into them, 
    * take data out, look for values stored in it, checking size, and looking 
    * at the Nodes to see if they are all linked up correctly as a BST.
    *
    * The Tester class we give you as a suggested way to organize your tests 
    * and allow you to systematically build your ADT method by method, 
    * testing as you go, adding new tests as you add new code.
    * 
    * You will be able to comment in and out various tests easily as you work
    * as I have done below.
    *
    */
      BST bst = new BSTImpl();
      Tester tst = new Tester();
      BST nst = new BSTImpl();
      /*tst.emptyTree(bst);
      tst.insert(bst);
      tst.remove(bst);


      // etc...*/
      bst.insert(8);
      bst.insert(3);
      bst.insert(10);
      bst.insert(9);
      nst.insert(40);
      nst.insert(20);
      nst.insert(25);
      nst.insert(21);
      Print printer = new Print();
      printer.tree(bst);
      printer.tree(nst);
      nst.merge(bst);
      printer.tree(nst);
      //System.out.println(bst.findMin());
      //System.out.println(bst.getMaxLeafHeightDiff());
    }
}
