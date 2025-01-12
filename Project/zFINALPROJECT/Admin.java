import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Users {
    private String role;
    private double workingHours;
/*


Remember To put CRUD function callers in admin



*/
    public Admin() throws ItemFoundException{

    }

    public Admin(String username, String password, String dateOfBirth,Gender sGender, String role, double workingHours){
        super(username,password,dateOfBirth,sGender);
        this.role=role;
        this.workingHours=workingHours;
    }
//    public void showAll(){
//
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public void addAdmin() throws ItemFoundException {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        String dateOfBirth = "";
        Gender genderEnum;
        String role;
        double workingHours;

        // Username validation
        do {
            System.out.print("Enter Username: ");
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

        // Date of Birth validation
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

        do {
            System.out.print("Enter Gender (Male/Female): ");
            String genderInput = scanner.nextLine().trim();
            if (!genderInput.equalsIgnoreCase("Male") && !genderInput.equalsIgnoreCase("Female")) {
                System.out.println("Invalid gender. Please enter Male or Female.");
            } else {
                genderEnum = Gender.valueOf(genderInput.substring(0, 1).toUpperCase() + genderInput.substring(1).toLowerCase());
                break;
            }
        } while (true);

        // Role validation
        do {
            System.out.print("Enter Role (must not be empty): ");
            role = scanner.nextLine().trim();
            if (role.isEmpty()) {
                System.out.println("Role cannot be empty. Please try again.");
            }
        } while (role.isEmpty());

        // Working Hours validation
        do {
            System.out.print("Enter Working Hours (must be a positive number): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
            workingHours = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (workingHours <= 0) {
                System.out.println("Working hours must be a positive number. Please try again.");
            }
        } while (workingHours <= 0);

        // Create Admin object
        Admin ad = new Admin(username, password, dateOfBirth, genderEnum, role, workingHours);

        // Check if Admin already exists in the database
        if (Database.searchUsers(ad)) {
            System.out.println("Admin already exists!");
        } else {
            Database.addUser(ad);
            System.out.println("Admin added successfully!");
        }
    }

    @Override
    public String toString() {
        return "username "+ getUsername() +"Role "+getRole() +"WorkingHours "+getWorkingHours() +"\n";
    }

    // Add Methods
    public void addCategory(Category category) throws ItemFoundException {
        Database.addCategory(category);
    }

    public void addUser(Users user) throws ItemFoundException {
        Database.addUser(user);
    }

    public void addProduct(String name, float price, int numberOfSameKind, String categoryName)
            throws ItemNotFoundException, ItemFoundException {
        Database.addProduct(name, price, numberOfSameKind, categoryName);
    }

    // Update Methods
    public void updateUsername(Users user, String newUsername) {
        Database.updateUsername(user, newUsername);
    }

    public void updatePassword(Users user, String newPassword) {
        Database.updatePassword(user, newPassword);
    }

    public void updateDateOfBirth(Users mainUser,String date){
        Database.updateDateOfBirth(mainUser,date);
    }

    public void updateAdminRole(Users mainUser,String role){
        Database.updateAdminRole(mainUser,role);
    }

    public void updateAdminWorkingHours(Users mainUser,double workingHours){
        Database.updateAdminWorkingHours(mainUser,workingHours);
    }

    public void updateCategoryName(Category category, String newName) {
        Database.updateCategoryName(category, newName);
    }

    public  void updateProductName(Product mainProduct,String name){
        Database.updateProductName(mainProduct,name);
    }

    public void updateProductPrice(Product product, float newPrice) {
        Database.updateProductPrice(product, newPrice);
    }

    public void updateProductCategoryName(Product mainProduct,String name){
        Database.updateProductCategoryName(mainProduct,name);
    }

    public void updateProductNumber(Product mainProduct,int number){
        Database.updateProductNumber(mainProduct,number);
    }

    // Remove Methods
    public void removeCategory(String categoryName) throws ItemNotFoundException {
        Database.removeCategory(categoryName);
    }

    public void removeUser(String username) throws ItemNotFoundException {
        Database.removeUser(username);
    }

    public void removeProduct(String productName) throws ItemNotFoundException {
        Database.removeProduct(productName);
    }

    // Get Methods
    public Users getUser(String username) {
        return Database.getUserByName(username);
    }

    public ArrayList<Users> getAllUsers(){
        return Database.getAllUsers();
    }

    public Category getCategory(String categoryName) {
        return Database.getCategory(categoryName);
    }

    public ArrayList<Category> getAllCategories(){
        return Database.getAllCategories();
    }

    public Product getProduct(String name){
       return Database.getProduct(name);
    }

    public ArrayList<Product> getCategoryProducts(String catName){
        return Database.getCategoryProducts(catName);
    }

    public  ArrayList<Product> getAllProducts(){
        return Database.getAllProducts();
    }

    public ArrayList<Order> getSpecificUserOrders(String username){
        return Database.getSpecificUserOrders(username);
    }

    public ArrayList<Order> getAllOrders(){
        return Database.getAllOrders();
    }
}
