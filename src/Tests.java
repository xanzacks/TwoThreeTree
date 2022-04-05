import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;


public class Tests
{

    @Test
    public void singleNodeTree()
    {
        Tree<Integer> t = new Tree();
        t.insert(9);

        assertEquals(1, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));

        t.insert(15);
        assertEquals(2, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));
        assertEquals(2, t.size(15));
        assertEquals(0, t.size(18));

        t =  new Tree();
        t.insert(15);
        t.insert(9);
        assertEquals(2, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));
        assertEquals(2, t.size(15));
        assertEquals(0, t.size(18));

        assertEquals((Integer)9, t.get(0));
        assertEquals((Integer)15, t.get(1));


    }

    @Test
    public void oneSplitLeft()
    {
        Tree<Integer> t = new Tree();
        t.insert(9);
        t.insert(15);
        t.insert(1);

        t.size(15);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        t.get(1);
        assertEquals((Integer)1, t.get(0));
        assertEquals((Integer)9, t.get(1));
        assertEquals((Integer)15, t.get(2));

        assertEquals(3,t.size());
    }

    @Test
    public void oneSplitRight()
    {
        Tree<Integer> t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(15);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals((Integer)1, t.get(0));
        assertEquals((Integer)9, t.get(1));
        assertEquals((Integer)15, t.get(2));
        assertEquals(3,t.size());


    }

    @Test
    public void oneSplitMiddle()
    {
        Tree<Integer> t = new Tree();
        t.insert(1);
        t.insert(15);
        t.insert(9);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals((Integer)1, t.get(0));
        assertEquals((Integer)9, t.get(1));
        assertEquals((Integer)15, t.get(2));
        assertEquals(3,t.size());


    }


    @Test
    public void testDuplicates()
    {
        Tree<Integer> t = new Tree();
        t.insert(1);
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);

        assertEquals(7, t.size(9));
        assertEquals(3, t.size(4));
        assertEquals(3, t.size(15));

        assertEquals(0, t.size(12));
        assertEquals(1, t.size(13));
        assertEquals(0, t.size(14));
        assertEquals(0, t.size(19));
        assertEquals(1, t.size(20));
        assertEquals(0, t.size(21));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(0, t.size(6));
        assertEquals(1, t.size(7));
        assertEquals(0, t.size(8));

        assertEquals((Integer)1, t.get(0));
        assertEquals((Integer)4, t.get(1));
        assertEquals((Integer)7, t.get(2));
        assertEquals((Integer)9, t.get(3));
        assertEquals((Integer)13, t.get(4));
        assertEquals((Integer)15, t.get(5));
        assertEquals((Integer)20, t.get(6));
        assertEquals(7,t.size());



    }


    @Test
    public void selfDuplicates(){
        Tree<Integer> myTree = new Tree<Integer>();
        myTree.insert(1);
        myTree.insert(9);
        myTree.insert(7);
        myTree.insert(3);
        myTree.insert(8);
        myTree.insert(6);
        myTree.insert(2);
        myTree.insert(4);
        myTree.insert(0);
        myTree.insert(1);
        myTree.insert(10);
        myTree.insert(11);
        myTree.insert(7);
        myTree.insert(9);
        myTree.insert(12);

        myTree = new Tree<Integer>();
        myTree.insert(50);
        myTree.insert(35);
        myTree.insert(11);
        myTree.insert(8);
        myTree.insert(9);
        myTree.insert(24);
        myTree.insert(49);
        myTree.insert(36);
        myTree.insert(40);
        myTree.insert(26);
        myTree.insert(14);
        myTree.insert(13);
        myTree.insert(32);
        myTree.insert(33);
        myTree.insert(30);
        myTree.insert(28);
        myTree.insert(27);
        myTree.insert(26);
        myTree.insert(29);

        myTree = new Tree<Integer>();
        for (int i = 1; i < 15; i++) {
            myTree.insert(i);
        }

        myTree.get(8);
        for (int i = 1; i < 15; i++) {
            assertEquals((Integer)i, myTree.get(i - 1));
        }

        myTree = new Tree<Integer>();
        for (int i = 25; i > 0; i--) {
            myTree.insert(i);
        }

        myTree = new Tree<Integer>();
        for (int i = 25; i > 0; i--) {
            myTree.insert(25 + i);
            myTree.insert(25 - i);
        }
    }
}
