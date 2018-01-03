Circular LinkedList

A circular linked list is essentially a singly linked list in which the next pointer of the tail node is set to point to the head node of the linked list rather than set to null.

the CircularLinklistApp reads an input file, and and manipulates the data in a cycle.
 

The definition of each method you have to implement in the CircularLinkedList class:
 void clear() – Remove all values from the list, creating an empty list.
 int size() – Return the number of data values currently in the list.
 boolean isEmpty() – Return true if the list is empty; otherwise return false.
 AnyType get(int index) – Return the data value at position index in the list.
 AnyType set(int index, AnyType newValue) – Return the old data value at position index and replace it with the data value newValue.
 void add(int index, AnyType newValue) – Add data value newValue at the position specified by index; shifting to the right by one position all the values from position index to the end of the list.
 AnyType remove(int index) – Remove and return the data value at position index in the list.
 void rotate() – Moves the value at the head of the list to the tail of the list without removing and adding anything to the list.
 Node<AnyType> getNode(int index, int lower, int upper) – Return the pointer to the node at position index in the list.
