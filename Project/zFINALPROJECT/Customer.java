import java.util.Scanner;
import java.lang.*;
import javafx.util.Pair;

public class Customer extends Users{
    private double balance;
    private String address;
    private String[] interests;

    // for cart and order
    private Cart cart=new Cart();

    public Customer() throws ItemFoundException {

    }
    public Customer(String username, String password, String dateOfBirth, double balance, String address, Gender gender, String[] interests) {
        super(username, password, dateOfBirth,gender);
        this.balance = balance;
        this.address = address;
        this.interests = interests;
    }

    public void placeOrder() /*Disable purchase in gui if cart is empty*/  {
        if (cart.isEmpty()){
            System.out.println("Cart is empty");
        }
        else{
            Order order = new Order();
            order.purchase(cart,this.getUsername());
        }
    }
    public void addToCart(Product newProduct, Integer count)/*Remember To handle the Count And Available product numbers in GUI*/
    {
        if (newProduct.getnumberOfSameKind() < count){
            System.out.println("Logical Error: number added to cart is greater than number available");
        }
        else{
        Pair<Product,Integer> productobj = new Pair<Product,Integer>(newProduct, count);
        cart.addproduct(productobj);
        newProduct.setNumberOfSameKind(newProduct.getnumberOfSameKind() - count);
        }
    }

    public void displayCart(){
        cart.showCart();
    }

    public void removeCompletelyFromCart(String productName) throws ItemNotFoundException {
        cart.removeProduct(productName);
    }

    public void removeOneProductFromCart(String productName) throws ItemNotFoundException {
        cart.decreaseProductAmountByOne(productName);
    }

    public static void signup() throws ItemFoundException {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        String dateOfBirth="";
        double balance;
        String genderInput;
        String[] interests;

        // Username validation
        System.out.println("Sign Up: ");
        do {

            System.out.print("Enter Username : ");
            username = scanner.nextLine();
            if (username.isEmpty() || username.length() > 16) {
                System.out.println("not define or too long(shouldn't exceed 16), Please try again.");
            }
        } while (username.isEmpty() || username.length() > 16);

        // Password validation
        do {
            System.out.print("Enter Password (must be at least 8 characters long and contain at least one number): ");
            password = scanner.nextLine();
            if (password.length() < 8 || !password.matches(".*\\d.*")) {
                System.out.println("Password must be at least 8 characters long and contain at least one number. Please try again.");
            }
        } while (password.length() < 8 || !password.matches(".*\\d.*"));

        // Date of birth validation
        boolean key = false;

        while(!key){
            System.out.print("Enter Date of Birth (YYYY MM DD): ");
            String  year = scanner.nextLine();
            String  month = scanner.nextLine();
            String  day = scanner.nextLine();
            int year1 = Integer.parseInt(year);
            int month1 = Integer.parseInt(month);
            int day1 = Integer.parseInt(day);
            dateOfBirth = "Undefined";
            if (year1 >=2025 || month1>12 || month1<1 || day1 >31 || day1 <1 ){
                System.out.println("Enter Again");
                continue;
            }
            if (month1 < 10){
                month = '0'+ month;
            }
            if (day1 < 10){
                day = '0'+ day;
            }

             dateOfBirth = year + '-' + month + '-' + day;
            key = true;
        }

        // Balance validation
        do {
            System.out.print("Enter Balance (must be a positive number): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
            balance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (balance <= 0) {
                System.out.println("Balance must be a positive number. Please try again.");
            }
        } while (balance <= 0);

        // Address input (optional, no validation needed)
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        // Gender validation
        do {
            System.out.print("Enter Gender (Male/Female): ");
            genderInput = scanner.nextLine().trim();
            if (!genderInput.equalsIgnoreCase("Male") && !genderInput.equalsIgnoreCase("Female")) {
                System.out.println("Invalid gender. Please enter Male or Female.");
            }
        } while (!genderInput.equalsIgnoreCase("Male") && !genderInput.equalsIgnoreCase("Female"));
        Gender gender = Gender.valueOf(genderInput.substring(0, 1).toUpperCase() + genderInput.substring(1).toLowerCase());

        // Interests validation
        System.out.print("Enter Interests (comma-separated): ");
        String interestsInput = scanner.nextLine();
        interests = interestsInput.split(",");
        for (int i = 0; i < interests.length; i++) {
            interests[i] = interests[i].trim(); // Trim whitespace
        }

        // Create a new Customer object
        Customer newCustomer = new Customer(username, password, dateOfBirth, balance, address, gender, interests);

        Database.addUser(newCustomer);
        System.out.println("Signup successful! Welcome, " + username);
    }


    //setters and getters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername()+" Address: " + getAddress()+" Balance: " + getBalance();
    }
}
