/**
 * Klasa TreeElem reprezentuje pojedynczy element w drzewie binarnym.
 *
 * @param <T> typ elementu, który musi implementować Comparable
 */
public class TreeElem<T extends Comparable<T>>{
    T elem;
    TreeElem<T> left;
    TreeElem<T> right;
    /**
     * Konstruktor tworzy nowy element drzewa.
     *
     * @param elem wartość elementu
     */
    TreeElem(T elem){
        this.elem = elem;
        left = null;
        right = null;
    }
}
