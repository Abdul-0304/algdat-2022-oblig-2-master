package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    private Node<T> finnNode(int indeks){
        Node<T> current;
        if(indeks < antall/2){
            current = hode;
            for (int i = 0; i < indeks; i++){
                current = current.neste;
            }
            return current;
        }

        current = hale;
        for (int i = antall - 1; i > indeks; i--){
            current = current.forrige;
        }
        return current;
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    private void fraTilKontroll(int tabellengde, int fra, int til){
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");
        if (til > tabellengde)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + tabellengde + ")");
        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");

    }

    public DobbeltLenketListe(T[] a) {
        if (a == null){
            throw new NullPointerException("Tabellen a er null!"); // Hvis tabellen a er null kaster vi en Nullpointerexception;
        }

        if (a.length > 0){ //Hvis a sin lengde er større en 0

            int i = 0;
            while (i < a.length){ //går inn i en while loop

                if (a[i] != null){ //hv is en verdi ikke er lik null, setter vi den verdien til hode;
                    hode = new Node<>(a[i]);
                    antall++;
                    break;
                }
                i++; //hvis verdien er null så går vi aldri inn i if setningen og dermed videre i lista
            }

            hale = hode; //Vi deklarer at hale=hode

            if (hale != null){
                i++;        //Hvis hale ikke er lik null så hopper vi en indeks videre
                while (i < a.length){  //løkken kjører så lenge i er mindre enn a sin lengde

                    if (a[i] != null){        // Hvis veriden ikke er null, så går vi inn i if setningen.
                        hale = hale.neste = new Node<>(a[i],hale,null); //(hale.neste).forrige = hale; //hale = hale.neste;
                        antall++;
                    }

                    i++;
                }
            }
        }
    }

    public Liste<T> subliste(int fra, int til) {

        fraTilKontroll(antall,fra,til);

        Liste<T> liste = new DobbeltLenketListe<>();
        int tabellengde = til-fra;

        if (tabellengde < 1) return liste;

        Node<T> current = finnNode(fra);

        for (int i = fra; i < til; i++){ // int i = tabellengde; i > 0; i--
            liste.leggInn(current.verdi);
            current = current.neste;

        }

        return liste;

    }

    @Override
    public int antall() {
        return antall; // returnerer antall verdier
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        return antall == 0; //returnerer true eller false basert på om antall==0
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");
        //Oppgaven krever å sette en requireNonNull for å ikke tilate null verdier

        if (antall == 0)  hode = hale = new Node<>(verdi);  // tom liste

        else hale = hale.neste = new Node<>(verdi, hale, null);
        // legges bakerst, dette gjør at vi legger til verdier bakerst.


        antall++;                  // en mer i listen
        return true;               // vellykket innlegging

    } // Kilden til denne koden er fra kompendiet

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke bruk null-verdier!");

        indeksKontroll(indeks, true);

        if (indeks == 0){
            if(tom()) {
                hode = new Node<T>(verdi, null,null);
                hale = hode;
            }
            else {
                hode = hode.forrige = new Node<T>(verdi, null, hode);
            }
        }

        else if (indeks == antall){
            hale = hale.neste = new Node<T>(verdi, hale, null);
        } else {
            Node<T> m = finnNode(indeks-1);
            Node<T> j = new Node<T>(verdi,m,m.neste);
            m.neste.forrige = j;
            m.neste = j;

        }

        antall++;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {

            if (indeksTil(verdi) == -1) {
                return false;
            }
             return true;

    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);

         if (finnNode(indeks) != null){
             return finnNode(indeks).verdi;
         }
            return null;

    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) return -1;
            Node<T> indeks = hode;

            for (int i = 0; i < antall; i++){

                if (indeks.verdi.equals(verdi)){
                    return i;
                }
                indeks = indeks.neste;
            }

         return -1;

    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke skriv nullverdier");

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig
        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;
        p.verdi = nyverdi;
        endringer++;

        return gammelVerdi;

    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        if (tom()){
            System.out.println("Tom liste");
            throw new IndexOutOfBoundsException("");
        }

        if (indeks>antall-1 || indeks<0){
            indeksKontroll(indeks, false);

        }   else {
                if (indeks == 0) {

                    T verdi = finnNode(indeks).verdi;
                 Node<T> remove = finnNode(indeks);

                 Node<T> temp = finnNode(indeks + 1);
                    temp.forrige = null;
                    hode = temp;
                    remove.neste = null;

                    antall--;
                    endringer++;

                    return verdi;
            }else if (indeks == antall - 1) {

                T verdi = finnNode(indeks).verdi;
                Node<T> remove = finnNode(indeks);


                Node<T> temp = finnNode(indeks - 1);
                temp.neste = null;
                hale = temp;
                remove.forrige = null;

                antall--;
                endringer++;

                return verdi;

            } else {
                T verdi = finnNode(indeks).verdi;
                Node<T> remove = finnNode(indeks);

                Node<T> temp = finnNode(indeks - 1);
                Node<T> tempTo = finnNode(indeks + 1);
                temp.neste = tempTo;
                tempTo.forrige = temp;
                remove.neste = remove.forrige = null;

                antall--;
                endringer++;

                return verdi;
            }

        }
        return null;
    }



    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder p = new StringBuilder(); // Oppretter en stringbuilder p

        p.append('['); // p.append vil addere på noe foran p. eks String ut?="["

        if (!tom() && hode != null){ // Når lista ikke er tom og hode ikke er null går vi inn

            p.append(hode.verdi); // adderer på hoden sin verdi
            Node<T> current = hode.neste; // Vi setter current som hode sin neste

            while (current != null){  // tar med resten hvis det er noe mer

                p.append(',').append(' ').append(current.verdi); // EKS: ut += ", " + current.verdi
                current = current.neste;
            }
        }
        p.append(']'); // addere tilslutt en ]



        return p.toString();
    }

    public String omvendtString() {
        StringBuilder j = new StringBuilder();  // Oppretter en stringbuilder j

        j.append('['); // j.append vil addere på noe foran p. eks String ut?="["

        if (!tom() && hale != null) { // Når lista ikke er tom og HALE ikke er null går vi inn

            Node<T> current = hale; //Vi setter current som hale
            j.append(current.verdi); //Vi adderer på verdien til current, som vil si hale sin verdi på j
            current = current.forrige; // Current går bakover ved bruk av current sin forrige

            while (current != null){  // tar med resten hvis det er noe mer
                j.append(',').append(' ').append(current.verdi); // append addere følgende symboler og verdier
                current = current.forrige; // current settes til forgie node for hver loop

            }
        }
        j.append(']'); // Tilslutt legger vi til en ]



        return j.toString();
    } // Vi gjør mye av det samme fra tostring metoden men her staretr vi fra hale!

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


