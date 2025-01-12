import java.util.ArrayList;
import javafx.util.Pair;

    public class Cart {
    
        private double totalprice;
        private ArrayList<Pair<Product,Integer>> products = new ArrayList<Pair<Product,Integer>>();
    
        Cart(){};
        Cart(double totalprice){this.totalprice=totalprice;}
    
        public void setTotalprice(double totalprice) {
            this.totalprice = totalprice;
        }
        public double getTotalprice() {
            return totalprice;
        }
    
        public ArrayList<Pair<Product,Integer>> getProducts() {
            return products;
        }
    
        public void addproduct(Pair<Product,Integer> productobj){
            Pair<Product,Integer> pair1 = returnProductsPair(productobj.getKey());
            if (pair1 == null){
                products.add(productobj);
                calculate_price();
            } else{
                products.remove(pair1);
                Pair<Product,Integer> pair2 = new Pair<Product,Integer>(pair1.getKey(),productobj.getValue() + pair1.getValue());
                products.add(pair2);
                calculate_price();
            }
        }

        public void decreaseProductAmountByOne(String name) throws ItemNotFoundException{
            if (products.isEmpty()){
                throw new ItemNotFoundException("Cart is Empty");
            }
            for(Pair<Product,Integer> p:products)
            {
                if(p.getKey().getName().equals(name)) {
                    if (p.getValue() > 1){
                        Pair<Product,Integer> newPair = new Pair<>(p.getKey(),p.getValue() - 1);
                        p.getKey().setNumberOfSameKind(p.getKey().getnumberOfSameKind() + 1);
                        products.remove(p);
                        products.add(newPair);
                        totalprice -= p.getKey().getPrice();
                        return;
                    }
                    else{
                        p.getKey().setNumberOfSameKind(p.getKey().getnumberOfSameKind() + 1);
                        products.remove(p);
                        totalprice -= p.getKey().getPrice();
                        return;
                    }
                }
            }
            throw new ItemNotFoundException("Product is not in cart");
        }

        public void removeProduct(String name) throws ItemNotFoundException{
            if (products.isEmpty()){
                throw new ItemNotFoundException("Cart is Empty");
            }
            for(Pair<Product,Integer> p:products)
            {
                if(p.getKey().getName().equals(name)) {
                    p.getKey().setNumberOfSameKind(p.getKey().getnumberOfSameKind() + p.getValue());
                    products.remove(p);
                    totalprice -= p.getKey().getPrice() * p.getValue();
                    return;
                }
            }
            throw new ItemNotFoundException("Product is not in cart");
        }

        public void calculate_price()
        {
            double total=0;
            for(Pair<Product,Integer> p:products)
            {
                total+=p.getKey().getPrice() * p.getValue();
            }
            totalprice = total;

        }
        public void showCart(){
            if(products.isEmpty()){System.out.println("it is empty.");}
    
            for(Pair<Product,Integer> p:products)
            {
                System.out.println("Name: "+p.getKey().getName() + " Price:" +p.getKey().getPrice() + " Number: " + p.getValue());
            }
            System.out.println("Total price: " + totalprice);
        }
        public void clearCart()
        {
            products.clear();
            setTotalprice(0);
    
        }
        
      
          // @Override
          // public String toString() {
          //     return "Pair{" + "proudct=" + proudct + ", quantity=" + quantity + "}";
          // }
      
    
        @Override
        public String toString() {
    
            return "total price "+ totalprice;
        }

        public boolean isEmpty(){
            return products.isEmpty();
        }
        //Added for validations
        public Pair<Product,Integer> returnProductsPair(Product product1){
            for (Pair<Product,Integer> p: products){
                if (p.getKey().equals(product1)){
                    return p;
                }
            }
            return null;
        }
      }
    

