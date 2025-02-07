import javax.xml.stream.FactoryConfigurationError;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Glavna
{
    public static void orders(SLL<Order> active, SLL<Order> shipping)
    {
        SLLNode<Order> dvizi = active.getFirst();
        SLLNode<Order> temp = null;
        int priority = Integer.MAX_VALUE;
        while(dvizi != null)
        {
            if(dvizi.element.getPriority() <= priority)
            {
                priority = dvizi.element.getPriority();
                temp = dvizi;
            }
            dvizi = dvizi.succ;
        }
        active.delete(temp);
        shipping.insertLast(temp.element);

        SLLNode<Order> dvizi2 = active.getFirst();
        SLLNode<Order> temp2 = null;
        int priority2 = Integer.MAX_VALUE;
        while(dvizi2 != null)
        {
            if(dvizi2.element.getPriority() <= priority2)
            {
                priority2 = dvizi2.element.getPriority();
                temp2 = dvizi2;
            }
            dvizi2 = dvizi2.succ;
        }
        active.delete(temp2);
        shipping.insertLast(temp2.element);
    }

    public static void main(String[] args)
    {
        SLL<Order> active = new SLL<>();
        SLL<Order> shipping = new SLL<>();
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int m = input.nextInt();
        for(int i=0;i<n;i++)
        {
            int id = input.nextInt();
            int product = input.nextInt();
            int priority = input.nextInt();
            Order nov = new Order(id,product,priority);
            active.insertLast(nov);
        }
        for(int i=0;i<m;i++)
        {
            int id = input.nextInt();
            int product = input.nextInt();
            int priority = input.nextInt();
            Order nov2 = new Order(id,product,priority);
            shipping.insertLast(nov2);
        }

        orders(active,shipping);
        System.out.println(active.toString());
        System.out.println(shipping.toString());
    }
}

class Order
{
    private int id;
    private int product;
    private int priority;

    public Order(int id, int product, int priority) {
        this.id = id;
        this.product = product;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getProduct() {
        return product;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString()
    {
        return String.valueOf(id);
    }

}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first ==node){
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst()
    {
        return first;
    }



    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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


}
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
