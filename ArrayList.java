/*
 * Shinya Aoi
 * CSS 143A
 * Data Structures: ArrayList-like Structures, Stacks, and Queues
 * InterfaceHW
 * 05/17/2018
 */

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * ArrayList class is a class run like an ArrayList.
 * This class contains insert, remove, size, toString
 * isEmpty, indexOf, equals, get, and resizeArray methods.
 * This class implements Iterable class.
 * Extra credit:  ArrayList implement the Iterable interface.
 */
public class ArrayList<T> implements Iterable<T>{
    protected Object[] arr = new Object[10];
    protected int numOfElements = 0;

    /**
     * This method adds an item which is type T.
     * @param item which is type T.
     */
    public void add(T item){
        if(arr.length==numOfElements) resizeArray();
        arr[numOfElements] = item;
        numOfElements++;
    }

    /**
     * First, This method checks if an array is full or not. If it is full
     * it calls resizeArray method to increase size of the array. Then, move the array
     * one index to the right to make a space by using the System.arraycopy. And
     * assign the object to the index of array. Finally, increase numOfElements by one.
     * @param object that will be insert in an existing array.
     * @param index where to put an object.
     */
    public void insert(T object, int index){//make a method for resizing the array
        if(arr.length==numOfElements) resizeArray();
        System.arraycopy(arr, index, arr,index+1, arr.length-index-1);
        arr[index] = object;
        numOfElements++;
    }

    /**
     * This method removes an object in an array of the index and returns the object.
     * @param index where to remove an object.
     * @return the object that has been removed.
     */
    public T remove(int index){
        numOfElements--;
        T remove = (T)arr[index];
        System.arraycopy(arr, index+1, arr, index, numOfElements-index);
        return remove;
    }

    /**
     * This method returns to the size of the array which is numOfElements.
     * @return numOfElements.
     */
    public int size(){
        return numOfElements;
    }

    /**
     * Override toString that prints out an array.
     * @return retVal.
     */
    @Override
    public String toString() {//when do i use this?
        String retVal = "";
        for(int i=0; i<numOfElements; i++){
            if(i==0){
                retVal = retVal+arr[i];
                continue;
            }
            retVal = retVal+ ","+ arr[i];
        }
        return retVal;
    }

    /**
     * This method checks if an array is empty or not.
     * @return true if it is empty.
     */
    public boolean isEmpty(){
        return numOfElements == 0;
    }

    /**
     * This methods returns an index of the object. It returns the index of that object
     * that has been passed as an argument, but it returns -1 if it does not match.
     * @param object that would match with an existing object.
     * @return index. If there is no object that matches, it returns -1.
     */
    public int indexOf(Object object){
        for(int i=0; i<numOfElements; i++){
            if(arr[i].equals(object)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Override that checks if the object is the same as the argument(object).
     * Also checks if it is empty or not.
     * @param other object that is passed, then change it to that
     * @return true if they are the same object.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof ArrayList)) return false;
        ArrayList that = (ArrayList) other;
        if(this.numOfElements==that.numOfElements){
            for(int i=0;i<numOfElements;i++){
                if(!this.arr[i].equals(that.arr[i])) return false;
            }
        }
        else return false;

        return true;
    }

    /**
     * This method gets an object of the index.
     * @param index of the array. Throw exception if the index is valid.
     * @return the object of the index.
     */
    public T get(int index){
        if(index>=numOfElements||index<0)
            throw new ArrayIndexOutOfBoundsException();
        return (T)arr[index];
    }

    /**
     * This method increases the size of the array.
     */
    public void resizeArray(){
        Object[] newArr = new Object[arr.length+100];
        System.arraycopy(arr,0,newArr,0,arr.length);
        arr = newArr;
    }

    /**
     * Override Iterator method.
     * @return the method of ArrayIterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Override forEach method that iterates through the array.
     * @param action
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < numOfElements ; i++) {
            action.accept((T)arr[i]);
        }
    }
    /**
     * Iterator class to iterate the Array list.
     */
    protected class ArrayListIterator<J extends T> implements Iterator{
        private int current;

        /**
         * Set current = 0.
         */
        ArrayListIterator(){
            this.current = 0;
        }

        /**
         * This method to check if there is next element.
         * @return true if there is a next element.
         */
        @Override
        public boolean hasNext() {
            return current<numOfElements;
        }

        /**
         * This method to return the next element of array
         * @return
         */
        @Override
        public J next() {
            return (J)arr[numOfElements++];
        }
    }
}
