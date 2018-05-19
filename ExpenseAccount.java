/*
 * Shinya Aoi
 * HW interface
 * 05/17/2018
 */

import java.io.*;

/**
 * This class extends the ArrayList class.
 * You should remove the limit on the number of bills that can
 * be placed in an account by making your ArrayList dynamically resize itself.
 */
public class ExpenseAccount<T extends Bill> extends ArrayList<T> implements Serializable{

    /**
     * Override the add method from ArrayList class.
     * This method also sorts the obj into an existing array.
     * @param obj which is passed as an argument.
     */
    @Override
    public void add(T obj){
        if(arr.length==numOfElements) resizeArray();
        for (int i = 0; i <numOfElements ; i++) {
            if(obj.compareTo((Bill)arr[i])<0){
                super.insert(obj, i);
                return;
            }
        }
        arr[numOfElements++] = obj;
    }

    /**
     * Override the get method.
     * @param index of the array. Throw exception if the index is valid.
     * @return the clone of object
     */
    @Override
    public T get(int index){
        if(index>numOfElements || index<0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T)super.get(index).clone();
    }

    /**
     * Write an object into a file,
     * @param file that you want to put.
     */
    public void write(String file){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(this);
        }catch (IOException e){

        }
    }

    /**
     * Read an object from a file.
     * @param file
     */
    public void read(String file){
        try{
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
            ExpenseAccount<Bill> account = (ExpenseAccount)read.readObject();
            this.arr = account.arr;
            this.numOfElements = account.numOfElements;

        }catch (IOException e){
            System.out.println(file+" was not found!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class was not found!");
        }
    }
}
