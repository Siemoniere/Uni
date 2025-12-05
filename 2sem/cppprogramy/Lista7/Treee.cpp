#include <iostream>
#include <memory>
#include <string>
using namespace std;

template<typename T>
class TreeElem {
public:
    T element;
    TreeElem *left;
    TreeElem *right;

    TreeElem(T elem) : element(elem), left(nullptr), right(nullptr) {}
    ~TreeElem() {}
};

template<typename T>
class Tree {
public:
    Tree() : root(nullptr) {}
    ~Tree() {}
    void add(T elem);
    void deletee(T elem);
    bool search(T elem);
    string draw();

    TreeElem<T>* insert(T elem, TreeElem<T>* root);
    TreeElem<T>* del(T elem, TreeElem<T>* root);
    TreeElem<T>* findSmallest(TreeElem<T>* e);
    bool exists(T elem, TreeElem<T>* root);
    string dr(TreeElem<T>* root);

private:
    TreeElem<T>* root;
};

template <typename T>
void Tree<T>::add(T elem) {
    root = insert(elem, root);
}

template <typename T>
TreeElem<T>* Tree<T>::insert(T elem, TreeElem<T>* root) {
    if (root == nullptr) {
        return new TreeElem<T>(elem);
    } else if (elem < root->element) {
        root->left = insert(elem, root->left);
    } else if (elem > root->element) {
        root->right = insert(elem, root->right);
    }
    return root;
}

template <typename T>
void Tree<T>::deletee(T elem) {
    root = del(elem, root);
}

template <typename T>
TreeElem<T>* Tree<T>::del(T elem, TreeElem<T>* root) {
    if (root == nullptr) {
        return nullptr;
    }
    if (elem > root->element) {
        root->right = del(elem, root->right);
    } else if (elem < root->element) {
        root->left = del(elem, root->left);
    } else {
        if (root->left == nullptr) {
            TreeElem<T>* temp = root->right;
            delete root;
            return temp;
        } else if (root->right == nullptr) {
            TreeElem<T>* temp = root->left;
            delete root;
            return temp;
        } else {
            TreeElem<T>* smallest = findSmallest(root->right);
            root->element = smallest->element;
            root->right = del(smallest->element, root->right);
        }
    }
    return root;
}

template <typename T>
TreeElem<T>* Tree<T>::findSmallest(TreeElem<T>* e) {
    while (e->left != nullptr) {
        e = e->left;
    }
    return e;
}

template <typename T>
bool Tree<T>::search(T elem) {
    return exists(elem, root);
}

template <typename T>
bool Tree<T>::exists(T elem, TreeElem<T>* root) {
    if (root == nullptr) {
        return false;
    } else if (elem < root->element) {
        return exists(elem, root->left);
    } else if (elem > root->element) {
        return exists(elem, root->right);
    } else return true;
}

template <typename T>
string Tree<T>::draw() {
    return dr(root);
}

template <typename T>
string Tree<T>::dr(TreeElem<T>* root) {
    if (root == nullptr) {
        return "()";
    } else {
        cout << "(" << root->element << ":" << dr(root->left) << ":" << dr(root->right) << ")";
    }
    return "";
}




