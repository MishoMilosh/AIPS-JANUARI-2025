import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cards
{
    public static void tarotCards(SLL<Card> firstPart,SLL<Card> secondPart)
    {
        // prv del prva delete - > insertlast vtor del
        SLLNode<Card> prvaprvdel = firstPart.getFirst();
        secondPart.insertLast(prvaprvdel.element);
        firstPart.delete(prvaprvdel);
        // zavrseno
        // vtor del prva karta delete - > insertlast prv del
        SLLNode<Card> prvavtordel = secondPart.getFirst();
        firstPart.insertLast(prvavtordel.element);
        secondPart.delete(prvavtordel);
        // zavrseno
        // pretposledna karta od prv del - > insert na sredina na vtor del
        //
        SLLNode<Card> dvizi = firstPart.getFirst();
        SLLNode<Card> temp = null;
        int pretposledna = 0;
        pretposledna = firstPart.length() - 1;
        int brojac = 0;
        while(dvizi != null)
        {
            brojac++;
            if(brojac == pretposledna)
            {
                temp = dvizi;
                break;
            }
            dvizi = dvizi.succ;
        }
        firstPart.delete(temp);
        // do tuka e najdena pretposledna karta od prv del i e izbrisana od istiot
        // od tuka nadole se bara sredina na vtor del i se vmetnuva pretposlednata karta od prviot del
        SLLNode<Card> dvizi2 = secondPart.getFirst();
        int sredina = 0;
        int brojac2 = 1;
        if(secondPart.length() % 2 == 0)
        {
            sredina = secondPart.length() / 2;
        }else{
            sredina = secondPart.length() / 2 + 1;
        }
        while(dvizi2 != null)
        {
            if(brojac2 == sredina)
            {
                secondPart.insertAfter(temp.element, dvizi2);
                break;
            }
            dvizi2 = dvizi2.succ;
            brojac2++;
        }
        // zavrseno
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        SLL<Card> prvDel = new SLL<>();
        SLL<Card> vtorDel = new SLL<>();

        for(int i=0;i<6;i++)
        {
            int id = input.nextInt();
            int rank = input.nextInt();
            Card c1 = new Card(id,rank);
            prvDel.insertLast(c1);
        }
        for(int j=0;j<6;j++)
        {
            int id = input.nextInt();
            int rank = input.nextInt();
            Card c2 = new Card(id,rank);
            vtorDel.insertLast(c2);
        }
        tarotCards(prvDel,vtorDel);
        System.out.println(prvDel.toString());
        System.out.println(vtorDel.toString());
    }
}

class Card
{
    private int id;
    private int rank;

    public Card(int id, int rank)
    {
        this.id=id;
        this.rank=rank;
    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLLNode<E>
{
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

    public SLLNode<E> getFirst() {
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