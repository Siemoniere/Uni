/**
 * Klasa Tree reprezentuje drzewo binarne.
 *
 * @param <T> typ elementów w drzewie, który musi implementować Comparable
 */
public class Tree <T extends Comparable<T>> {

    private TreeElem<T> root;
    /**
     * Konstruktor tworzy puste drzewo.
     */
    public Tree (){
        root = null;
    }
    /**
     * Dodaje element do drzewa.
     *
     * @param elem element do dodania
     */
    public void add(T elem){
        root = insert(elem, root);
    }
    private TreeElem<T> insert(T elem, TreeElem<T> e){
        if(e == null) {
            return new TreeElem<T>(elem);
        }
        if(elem.compareTo(e.elem)<0){
            e.left = insert(elem, e.left);
        } else if (elem.compareTo(e.elem)>0){
            e.right = insert(elem, e.right);
        } return e;
    }
    /**
     * Usuwa element z drzewa.
     *
     * @param elem element do usunięcia
     */
    public void delete(T elem){
        root = del(elem, root);
    }
    private TreeElem<T> del(T elem, TreeElem<T> e){
    if (e == null) return null;

    if (elem.compareTo(e.elem) < 0) {
        e.left = del(elem, e.left);
    } else if (elem.compareTo(e.elem) > 0) {
        e.right = del(elem, e.right);
    } else {
        if (e.left == null && e.right == null) {
            return null;
        } else if (e.left == null) {
            return e.right;
        } else if (e.right == null) {
            return e.left;
        } else {
            TreeElem<T> smallest = findSmallest(e.right);
            e.elem = smallest.elem;
            e.right = del(smallest.elem, e.right);
        }
    }
    return e;
}

private TreeElem<T> findSmallest(TreeElem<T> e) {
    while (e.left != null) {
        e = e.left;
    }
    return e;
}
    /**
     * Szuka elementu w drzewie.
     *
     * @param elem element do wyszukania
     * @return true, jeśli element jest obecny w drzewie, w przeciwnym razie false
     */
    public boolean search(T elem){
        return exists(elem, root);
    }
    private boolean exists(T elem, TreeElem<T> e){
        if (e == null) return false;
        if (elem.compareTo(e.elem) ==0) return true;
        if (elem.compareTo(e.elem)<0){
            return exists(elem, e.left);
        } else return exists(elem, e.right);
    }
    /**
     * Zwraca reprezentację tekstową drzewa.
     *
     * @return drzewo jako String
     */
    public String toString() {
        return toS(root);
    }
    private String toS(TreeElem<T> e){
        if (e != null) return "("+e.elem+":"+toS(e.left)+":"+toS(e.right)+")";
        return "()";
    }
}