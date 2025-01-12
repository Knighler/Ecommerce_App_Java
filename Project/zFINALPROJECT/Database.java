import java.util.ArrayList;

public abstract class Database implements CRUD , Searchable{
    private static final ArrayList<Category> categories= new ArrayList<>();
    private static final ArrayList<Users> users = new ArrayList<>();
    private static final ArrayList<Order> orders = new ArrayList<>();

    //Create operations
    public static void addCategory(Category cat) throws ItemFoundException /*Exception*/{
        if (searchCategoriesByName(cat.getCatName())){
            throw new ItemFoundException("Category Already Exists");
        }
        else {
            categories.add(cat);
        }
    }

    public static void addUser(Users user) throws ItemFoundException /*Exception*/{
        if(searchUsersByName(user.getUsername())){
            throw new ItemFoundException("User Already Exists");
        }
        else{
            users.add(user);
        }
    }

    public static void addOrder(Order order){
        orders.add(order);
    }

    //Might Be useless
    public static void addProduct(String name,double price,int numberOfSameKind,String categoryName) throws ItemNotFoundException, ItemFoundException /*Exception*/{
// add validations for making product and what not
        if (searchProducts(name)) {
            throw new ItemFoundException("Product Already Exists");
        }
        else {
            new Product(name, price, numberOfSameKind, categoryName);
        }
    }

    //Search methods
    public static boolean searchUsers(Users user){
        for (Users user1: users){
            if (user1.equals(user))
                return true;
        }
        return false;
    }

    public static boolean searchUsersByName(String username){
        for (Users user1: users){
            if (user1.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public static boolean searchCategories(Category cat){
        for (Category cati: categories){
            if (cati.equals(cat))
                return true;
        }
        return false;
    }

    public static boolean searchCategoriesByName(String catName){
        for (Category cati: categories){
            if (cati.getCatName().equals(catName))

                return true;
        }
        return false;
    }

    //I feel like this is unnecessary
//    public static boolean searchOrders(Order order){
//        for (Order order1: orders){
//            if (order1.equals(order)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean searchProducts(String  name){
        for (Category cati: categories){
            if (cati.searchProduct(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean searchProducts(Product product){
        for (Category cati: categories){
            if (product.getCategory().equals(cati.getCatName()) ) {
                if (cati.searchProduct(product)) {
                    return true;
                }
            }
        }
        return false;
    }


    //Read methods
    public static Category getCategory(String catName){
        for (Category category : categories) {
            if (category.getCatName().equals(catName)) {
                return category;
            }
        }
        return null;
    }

    public static Users getUser(String username, String password){
        for (Users user : users) {
            if (password.equals(user.getPassword()) && username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public static Users getUserByName(String username){
        for (Users user : users) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    //Don't think it's needed
    // I really think this should be adjusted
    public static ArrayList<Order> getSpecificUserOrders(String username){
        ArrayList<Order> newOrders = new ArrayList<>();
        for (Order order: orders){
            if (username.equals(order.getUsername())){
                newOrders.add(order);
            }
        }
        return newOrders;
    }

    //Don't think it's needed
    public static Product getProduct(String name){
        for (Category category: categories){
            if (category.searchProduct(name))
                return category.getProduct(name);
        }
        return null;
    }

    //Update methods
    //for admin use only
    /*
    *
    * Might need to add update functions in Customer and Admin
    *
    * */
    public static void updateUsername(Users mainUser,String username){
        mainUser.setUsername(username);
    }

    public static void updatePassword(Users mainUser,String password){
        mainUser.setPassword(password);
    }

    public static void updateDateOfBirth(Users mainUser,String date){
        mainUser.setDateOfBirth(date);
    }

    public static void updateCustomerBalance(Users mainUser,double balance){
        ((Customer)mainUser).setBalance(balance);
    }

    public static void updateCustomerAddress(Users mainUser,String address){
        ((Customer)mainUser).setAddress(address);
    }

    public static void updateCustomerInterests(Users mainUser,String[] interests){
        ((Customer)mainUser).setInterests(interests);
    }

    public static void updateAdminRole(Users mainUser,String role){
        ((Admin)mainUser).setRole(role);
    }

    public static void updateAdminWorkingHours(Users mainUser,double workingHours){
        ((Admin)mainUser).setWorkingHours(workingHours);
    }

    //Not sure if its needed
    public static void updateCategoryName(Category mainCategory,String catName){
        mainCategory.setCatName(catName);
    }

    public static void updateProductName(Product mainProduct,String name){
        mainProduct.setName(name);
    }

    public static void updateProductPrice(Product mainProduct,float price){
        mainProduct.setPrice(price);
    }

    public static void updateProductNumber(Product mainProduct,int number){
        mainProduct.setNumberOfSameKind(number);
    }

    public static void updateProductCategoryName(Product mainProduct,String name){
        mainProduct.setCategoryName(name);
    }

    //Delete methods
    public static void removeCategory(String catName)  throws ItemNotFoundException/*Exception*/{

        for (Category cat: categories) {
            if (catName.equals(cat.getCatName())) {
                categories.remove(cat);
                return;
            }
        }
        throw new ItemNotFoundException("Category Does Not Exist");
    }

    public static void removeCategory(Category cat) throws ItemNotFoundException/*Exception*/{
        if (searchCategories(cat)){
            categories.remove(cat);
        }
        else {
            throw new ItemNotFoundException("Category Does Not Exist");
        }
    }

    public static void removeUser(String username) throws ItemNotFoundException /*Exception*/{
        for (Users user1: users) {
            if (username.equals(user1.getUsername())) {
                users.remove(user1);
                return;
            }
        }
        throw new ItemNotFoundException("User Does Not Exist");
    }

    public static void removeUser(Users user) throws ItemNotFoundException/*Exception*/{
        if (searchUsers(user)){
            users.remove(user);
        }
        else {
            throw new ItemNotFoundException("User Does Not Exist");
        }
    }

    //Don't think its needed
    public static void removeProduct(String prodName) throws ItemNotFoundException/*Exception*/{
        for (Category cat: categories) {
            if (cat.searchProduct(prodName)) {
                cat.removeProduct(prodName);
                return;
            }
        }
        throw new ItemNotFoundException("Product Does Not Exist");
    }

    public static void removeProduct(Product product) throws ItemNotFoundException/*Exception*/{
        for (Category cat: categories) {
            if (product.getCategory().equals(cat.getCatName())) {
                cat.removeProduct(product);
                return;
            }
        }
        throw new ItemNotFoundException("Product Does Not Exist");
    }

    //Order removal might not be needed
//    public static void removeOrder(String  payMethod, String username) throws ItemNotFoundException/*Exception*/{
//        boolean found = false;
//        for (Order order: orders){
//            if (payMethod.equals(order.getPaymentmethod())){
//                orders.remove(order);
//                found = true;
//            }
//        }
//        if (!found){
//            throw new ItemNotFoundException("Order Does Not Exist");
//        }
//    }

//    public static void removeOrder(Order order)/*Exception*/{
//
//        orders.remove(order);
//    }


    //Methods for Admin to see all things in database
    public static void printAll(){
        printAllUsers();
        printAllProducts();
        printAllOrders();
    }

    public static void printAllOrders(){
        if (orders.isEmpty()){
            System.out.println("No Orders");
            return;
        }
        System.out.println("Orders: ");
        int i = 1;
        for (Order order: orders){
            System.out.println("Order No. " + i);
            i++;
            System.out.println(order.toString());
        }
    }

    public static void printAllUsers(){
        if (users.isEmpty()){
            System.out.println("No Users");
            return;
        }
        System.out.println("Admins: ");
        for (Users user : users) {
            if (user instanceof Admin){
                System.out.println(((Admin)user).toString());
            }
        }
        System.out.println("Customers: ");
        for (Users user : users) {
            if (user instanceof Customer){
                System.out.println(((Customer)user).toString());
            }
        }
    }

    public static void printAllProducts(){
        if (categories.isEmpty()){
            System.out.println("No Categories");
            return;
        }
        System.out.println("Categories: ");
        for (Category cat: categories){
            System.out.println(cat.toString());
            cat.display();
        }
    }

    public static ArrayList<Category> getAllCategories(){
        return categories;
    }

    public static ArrayList<Users> getAllUsers(){
        return users;
    }

    public static ArrayList<Order> getAllOrders(){
        return orders;
    }

    public static ArrayList<Product> getCategoryProducts(Category cat){
        return cat.getListOfProducts();
    }

    public static ArrayList<Product> getCategoryProducts(String catName){
        if (searchCategoriesByName(catName)){
            return getCategory(catName).getListOfProducts();
        } else {
            return new ArrayList<Product>();
        }
    }

    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> newArray = new ArrayList<>();
        for (Category cat: categories){
            newArray.addAll(cat.getListOfProducts());
        }
        return newArray;
    }

}
