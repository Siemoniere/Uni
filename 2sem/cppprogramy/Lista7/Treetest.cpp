#include<iostream>
#include "Treee.cpp"
using namespace std;
/**
 * @brief Funkcja główna do testowania klasy Tree.
 * 
 * @return int 
 */
int main(){
    Tree<int> tree;
    tree.add(23);
    tree.add(8);
    tree.add(54);
    tree.add(27);
    tree.add(102);
    tree.add(25);
    tree.add(38);
    tree.add(63);
    tree.add(200);
    if(tree.search(54)){
        cout<<"Jest 54"<<endl;
    } else cout<<"Nie ma 54"<<endl;
    tree.draw();
    cout<<endl;
    tree.deletee(54);
    cout<<"Usuwam 54"<<endl;
    if(tree.search(54)){
        cout<<"Jest 54"<<endl;
    } else cout<<"Nie ma 54"<<endl;
    tree.draw();
    return 0;
}
