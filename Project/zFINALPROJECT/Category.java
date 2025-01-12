import java.util.ArrayList;



public class Category implements available,Comparable<Category>{
    private String name;
    private static int number=0;
    private ArrayList<Product> productsInThisCategory= new ArrayList<>();
    private int numberOfproductsIncluded;


    Category() throws ItemFoundException{
        this("Unknown Category");
    }
    Category(String name) throws ItemFoundException
    {
        this.name=name;
        Category.number++;
    }

    // getters and setters for name and number of categories
    public String getCatName(){return this.name;}
    public int getCatNumber(){return Category.number;}
    public int getNumberOfProductsIncluded(){return this.numberOfproductsIncluded;}


    public void setCatName(String name){this.name=name;}

    //add Product to the category
    public void addProductInThisCategory(Product product){
        productsInThisCategory.add(product);
        numberOfproductsIncluded++;
    }

    //display all products in this category
    public void display(){
        for(int i=0;i<productsInThisCategory.size();i++){
            System.out.println(productsInThisCategory.get(i).getName());
        }
        if(productsInThisCategory.isEmpty()){
            System.out.println("There are currently no products in this category");
        }
    }

    public ArrayList<Product> getListOfProducts(){
        return productsInThisCategory;

    }

    //check if there are any categories
    @Override
    public boolean availableForUse() {
        return getCatNumber()>0;
    }

    //to string
    @Override
    public String toString(){
        return "Name: "+ getCatName()+"\nNumber of products: "+getNumberOfProductsIncluded();
    }

    //comparTo

    @Override
    public int compareTo(Category o) {
        return Integer.compare(getNumberOfProductsIncluded(), o.getNumberOfProductsIncluded());
    }

    public boolean equals(Category cat){
        return name.equals(cat.getCatName());
    }

    public boolean searchProduct(Product product){
        for (Product product1: productsInThisCategory){
            if (product1.equals(product))
                return true;
        }
        return false;
    }

    public boolean searchProduct(String name){
        for (Product producti: productsInThisCategory){
            if (name.equals(producti.getName()))
                return true;
        }
        return false;
    }

    public Product getProduct(String name){
        for (Product product: productsInThisCategory){
            if (name.equals(product.getName()))
                return product;
        }
        return null;
    }

    //exception here
    public void removeProduct(String name)throws ItemNotFoundException{
        for (Product product1 : productsInThisCategory){
            if (name.equals(product1.getName())) {
                productsInThisCategory.remove(product1);
                numberOfproductsIncluded--;
                return;
            }
        }
        throw new ItemNotFoundException("Product not found");
    }
    // exception here
    public void removeProduct(Product product)throws ItemNotFoundException{
        for (Product product1 : productsInThisCategory){
            if (product.getName().equals(product1.getName())) {
                productsInThisCategory.remove(product1);
                numberOfproductsIncluded--;
                return;
            }
        }
        throw new ItemNotFoundException("Product not found");

    }



}