import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Order {
    private String username;
    private String paymentmethod;
    private double totalprice;
    private ArrayList<Pair<Product,Integer>> products;


    //Needed to store Orders in Database


    public Order() {
    }

    public Order(String username, String paymentmethod, double totalprice, ArrayList<Pair<Product, Integer>> products) {
        this.username = username;
        this.paymentmethod = paymentmethod;
        this.totalprice = totalprice;
        this.products = products;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public boolean purchase(Cart c,String username){
        this.username=username;
        c.showCart();

        Scanner input = new Scanner(System.in);
        String s;
        System.out.println("Enter payement method");
        System.out.println("visa:v\npaypal:p");
        s = input.nextLine();
        while(true){
            while(!(s.equalsIgnoreCase("v") || s.equalsIgnoreCase("p"))) {
                System.out.println("Enter payement method");
                System.out.println("visa:v\npaypal:p");
                s = input.nextLine();
                if (!(s.equalsIgnoreCase("v") || s.equalsIgnoreCase("p"))) {
                    System.out.println("Enter valid payment method");
                    input.next();
                }
            }
            if(s.equalsIgnoreCase("v")){paymentmethod="visa";}
            else if(s.equalsIgnoreCase("p")){paymentmethod="pay pal";}

            setTotalprice(c.getTotalprice());

            //validation
            System.out.println(" confirm purchase press c ,return to cart press r ");
            s=input.nextLine();
            if(s.equals("c")){
                this.products=(ArrayList<Pair<Product,Integer>>) c.getProducts().clone();
                display();
                System.out.println("Successfully purchased");
                Database.addOrder(this);
                c.clearCart();
                return true;
            }
            else if(s.equals("r")) {
                return false;
            }
        }
    }
    public void display()
    {

        System.out.println("Customer name: "+this.username);
        for(Pair<Product,Integer> p:products)
        {
            System.out.println("Product Name: "+ p.getKey().getName()+" Price: "+p.getKey().getPrice() + " Number: "+p.getValue());
        }
        System.out.println(this);
    }

    //Added by Marwan
    @Override
    public String toString(){
        return "Payment Method: " + paymentmethod + " Total Price: " + totalprice;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    //add by Marwan
    public ArrayList<Pair<Product,Integer>> getProducts() {
        return products;
    }
}