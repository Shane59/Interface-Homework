/*
 * Shinya Aoi
 * InterfaceHW
 * 05/17/2018
 */

/**
 * Driver for testing.
 */
public class Driver {
    public static void main(String[] args) {
        //Testing the expense class.
        ExpenseAccount<Bill> accounts = new ExpenseAccount<>();
        accounts.add(new Bill(new Money(42,49), new Date(3,5,2015), "Cellphone Bill"));
        accounts.add(new Bill(new Money(12000), new Date(9,28, 2018), "Tuition"));

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
        System.out.println();

        accounts.forEach(k->k.setPaid(new Date(10,25, 2019)));
        System.out.println("Updated accounts:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
        accounts.write("Bills.txt");

        ExpenseAccount<Bill> accounts2 = new ExpenseAccount<>();
        accounts2.read("Bills.txt");
        for (int i = 0; i < accounts2.size() ; i++) {
            System.out.println(accounts2.get(i));
        }

        //checking the compareTo method.
        System.out.println();
        Money money = new Money(10,00);
        Money money1 = new Money(10,95);
        System.out.println("money is: "+money);
        System.out.println("money1 is: "+money1);
        System.out.println(money.compareTo(money1));//-1
        System.out.println(money1.compareTo(money));//1
        System.out.println();

        Date date = new Date(3,9,2017);
        Date date1 = new Date(10,1, 2017);
        System.out.println("date is: "+date);
        System.out.println("date1 is: "+date1);
        System.out.println(date.compareTo(date1));//-1
        System.out.println(date1.compareTo(date));//1
        System.out.println();

        Bill bill = new Bill(money,date,"cell");
        Bill bill2 = new Bill(money1, date1, "Shinya");
        System.out.println("bill is: "+bill);
        System.out.println("bill2 is: "+bill2);
        System.out.println(bill.compareTo(bill2));//-1
        System.out.println(bill2.compareTo(bill));//1
        System.out.println();

        //checking the clone methods.
        Money money3 = money.clone();
        System.out.println(money3);//10.00
        System.out.println();

        Bill bill3 = bill.clone();
        System.out.println(bill3);//10.00 3/9/2017 "cell"
        System.out.println();

        Date date2 = date.clone();
        System.out.println(date2);//3/9/2017
    }
}

