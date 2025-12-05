#include <iostream>
#include <memory>
#include <string>
using namespace std;
/**
 * @brief Klasa reprezentująca element drzewa.
 * 
 * @tparam T Typ danych przechowywanych w elemencie drzewa.
 */
template<typename T>
class TreeElem {
private:
    T element;
    TreeElem *left;
    TreeElem *right;
public:
    /**
     * @brief Konstruktor klasy TreeElem.
     * 
     * @param elem Wartość elementu.
     */
    TreeElem(T elem) : element(elem), left(nullptr), right(nullptr) {}
    /**
     * @brief Destruktor klasy TreeElem.
     */
    ~TreeElem() {}
    /**
     * @brief Pobiera wartość elementu.
     * 
     * @return T Wartość elementu.
     */
    T getElement() { return element; }
    /**
     * @brief Pobiera wskaźnik na lewy poddrzewo.
     * 
     * @return TreeElem* Wskaźnik na lewy poddrzewo.
     */
    TreeElem* getLeft() { return left; }
    /**
     * @brief Pobiera wskaźnik na prawy poddrzewo.
     * 
     * @return TreeElem* Wskaźnik na prawy poddrzewo.
     */
    TreeElem* getRight() { return right; }
    /**
     * @brief Ustawia wartość elementu.
     * 
     * @param elem Nowa wartość elementu.
     */
    void setElement(T elem) { element = elem; }
    /**
     * @brief Ustawia wskaźnik na lewy poddrzewo.
     * 
     * @param leftElem Nowy wskaźnik na lewy poddrzewo.
     */
    void setLeft(TreeElem* leftElem) { left = leftElem; }
    /**
     * @brief Ustawia wskaźnik na prawy poddrzewo.
     * 
     * @param rightElem Nowy wskaźnik na prawy poddrzewo.
     */
    void setRight(TreeElem* rightElem) { right = rightElem; }
};

/**
 * @brief Klasa reprezentująca drzewo binarne.
 * 
 * @tparam T Typ danych przechowywanych w drzewie.
 */
template<typename T>
class Tree {
public:
    TreeElem<T> *root;
    /**
     * @brief Konstruktor klasy Tree.
     */
    Tree() : root(nullptr) {}
    /**
     * @brief Destruktor klasy Tree.
     */
    ~Tree() {}

    /**
     * @brief Dodaje element do drzewa.
     * 
     * @param elem Wartość elementu do dodania.
     */
    void add(T elem);
    
    /**
     * @brief Usuwa element z drzewa.
     * 
     * @param elem Wartość elementu do usunięcia.
     */
    void deletee(T elem);
    
    /**
     * @brief Sprawdza, czy element istnieje w drzewie.
     * 
     * @param elem Wartość elementu do wyszukania.
     * @return true Jeśli element istnieje.
     * @return false Jeśli element nie istnieje.
     */
    bool search(T elem);
    
    /**
     * @brief Rysuje drzewo.
     * 
     * @return string Reprezentacja drzewa w postaci stringu.
     */
    string draw();

    /**
     * @brief Wstawia element do drzewa.
     * 
     * @param elem Wartość elementu do wstawienia.
     * @param root Wskaźnik na korzeń drzewa.
     * @return TreeElem<T>* Wskaźnik na nowy korzeń drzewa.
     */
    TreeElem<T>* insert(T elem, TreeElem<T>* root);
    
    /**
     * @brief Usuwa element z drzewa.
     * 
     * @param elem Wartość elementu do usunięcia.
     * @param root Wskaźnik na korzeń drzewa.
     * @return TreeElem<T>* Wskaźnik na nowy korzeń drzewa.
     */
    TreeElem<T>* del(T elem, TreeElem<T>* root);
    
    /**
     * @brief Znajduje najmniejszy element w poddrzewie.
     * 
     * @param e Wskaźnik na poddrzewo.
     * @return TreeElem<T>* Wskaźnik na najmniejszy element.
     */
    TreeElem<T>* findSmallest(TreeElem<T>* e);
    
    /**
     * @brief Sprawdza, czy element istnieje w drzewie.
     * 
     * @param elem Wartość elementu do wyszukania.
     * @param root Wskaźnik na korzeń drzewa.
     * @return true Jeśli element istnieje.
     * @return false Jeśli element nie istnieje.
     */
    bool exists(T elem, TreeElem<T>* root);
    
    /**
     * @brief Rysuje drzewo.
     * 
     * @param root Wskaźnik na korzeń drzewa.
     * @return string Reprezentacja drzewa w postaci stringu.
     */
    string dr(TreeElem<T>* root);
};

template <typename T>
TreeElem<T>* Tree<T>::insert(T elem, TreeElem<T>* root) {
    if (root == nullptr) {
        return new TreeElem<T>(elem);
    } else if (elem < root->getElement()) {
        root->setLeft(insert(elem, root->getLeft()));
    } else if (elem > root->getElement()) {
        root->setRight(insert(elem, root->getRight()));
    }
    return root;
}

template <typename T>
TreeElem<T>* Tree<T>::del(T elem, TreeElem<T>* root) {
    if (root == nullptr) {
        return nullptr;
    }
    if (elem > root->getElement()) {
        root->setRight(del(elem, root->getRight()));
    } else if (elem < root->getElement()) {
        root->setLeft(del(elem, root->getLeft()));
    } else {
        if (root->getLeft() == nullptr) {
            TreeElem<T>* temp = root->getRight();
            delete root;
            return temp;
        } else if (root->getRight() == nullptr) {
            TreeElem<T>* temp = root->getLeft();
            delete root;
            return temp;
        } else {
            TreeElem<T>* smallest = findSmallest(root->getRight());
            root->setElement(smallest->getElement());
            root->setRight(del(smallest->getElement(), root->getRight()));
        }
    }
    return root;
}

template <typename T>
TreeElem<T>* Tree<T>::findSmallest(TreeElem<T>* e) {
    while (e->getLeft() != nullptr) {
        e = e->getLeft();
    }
    return e;
}

template <typename T>
bool Tree<T>::exists(T elem, TreeElem<T>* root) {
    if (root == nullptr) {
        return false;
    } else if (elem < root->getElement()) {
        return exists(elem, root->getLeft());
    } else if (elem > root->getElement()) {
        return exists(elem, root->getRight());
    } else return true;
}

template <typename T>
string Tree<T>::dr(TreeElem<T>* root) {
    if (root == nullptr) {
        return "()";
    } else {
        cout << "(" << root->getElement() << ":" << dr(root->getLeft()) << ":" << dr(root->getRight()) << ")";
    }
    return "";
}

template <typename T>
void Tree<T>::add(T elem) {
    root = insert(elem, root);
}

template <typename T>
void Tree<T>::deletee(T elem) {
    root = del(elem, root);
}

template <typename T>
bool Tree<T>::search(T elem) {
    return exists(elem, root);
}

template <typename T>
string Tree<T>::draw() {
    return dr(root);
}
