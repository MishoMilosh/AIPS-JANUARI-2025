import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void sorting(DLL<Integer> list1, DLL<Integer> list2) {
        // Step 1: Merge both lists into list3
        DLLNode<Integer> dviziList1 = list1.getFirst();
        DLLNode<Integer> dviziList2 = list2.getFirst();
        DLL<Integer> list3 = new DLL<>();

        while (dviziList1 != null) {
            list3.insertLast(dviziList1.element);
            dviziList1 = dviziList1.succ;
        }

        while (dviziList2 != null) {
            list3.insertLast(dviziList2.element);
            dviziList2 = dviziList2.succ;
        }

        // Step 2: Sort list3 in descending order
        DLLNode<Integer> element = list3.getFirst();
        while (element != null) {
            DLLNode<Integer> maxNode = element;
            DLLNode<Integer> dviziList3 = element.succ;

            while (dviziList3 != null) {
                if (dviziList3.element > maxNode.element) {
                    maxNode = dviziList3;
                }
                dviziList3 = dviziList3.succ;
            }

            // Swap elements of outer and maxNode
            if (element != maxNode) {
                int temp = element.element;
                element.element = maxNode.element;
                maxNode.element = temp;
            }

            element = element.succ;
        }

        System.out.println(list3);

        // Step 3: Sort list3 in ascending order
        element = list3.getFirst();
        while (element != null) {
            DLLNode<Integer> minNode = element;
            DLLNode<Integer> inner = element.succ;

            while (inner != null) {
                if (inner.element < minNode.element) {
                    minNode = inner;
                }
                inner = inner.succ;
            }

            // Swap elements of outer and minNode
            if (element != minNode) {
                int temp = element.element;
                element.element = minNode.element;
                minNode.element = temp;
            }

            element = element.succ;
        }

        System.out.println(list3);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        DLL<Integer> list1 = new DLL<Integer>();
        DLL<Integer> list2 = new DLL<Integer>();
        for(int i = 0; i < m; i++) {
            list1.insertLast(sc.nextInt());
        }
        for(int i = 0; i < n;i++)
        {
            list2.insertLast(sc.nextInt());
        }
        sorting(list1, list2);
    }
}
class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if(after==last){
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if(before == first){
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node){
        if(node==first){
            deleteFirst();
            return node.element;
        }
        if(node==last){
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp + "<->";
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void izvadiDupliIPrebroj(){

    }
}
