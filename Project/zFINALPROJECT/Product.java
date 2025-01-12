public class Product implements available,Comparable<Product>{
    private String name;
    private double price;
    private int numberOfSameKind; /*Remember To Decrease When customer adds to cart
                                    and maybe increase when Customer removes from cart*/
    private String categoryName;

    Product()throws ItemNotFoundException{

        this("Unknown Product",0,0,"Unknown Category");
    }

    Product(String name,double price,int numberOfSameKind,String categoryName)throws ItemNotFoundException{
        Category catCheck=Database.getCategory(categoryName);
        if(catCheck==null){

            throw new ItemNotFoundException("Category not found");
        }
        else{
            this.name=name;
            this.price=price;
            this.numberOfSameKind=numberOfSameKind;
            this.categoryName=categoryName;
            addToCategory(catCheck);
        }
    }

    // getters and setters for name and number of categories
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
    public int getnumberOfSameKind() {
        return this.numberOfSameKind;
    }
    public String getCategory() {
        return this.categoryName;
    }


    public void setName(String name) {

        this.name = name;
    }
    public void setPrice(float price) /*Remember to Validate in main*/ {
            this.price = price;
    }
    public void setNumberOfSameKind(int numberOfSameKind) {
        this.numberOfSameKind = numberOfSameKind;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    //add to category
    public void addToCategory(Category cat){
        //Category cat=Database.getCategory(categoryName);
        cat.addProductInThisCategory(this);

    }


    //In stock check
    @Override
    public boolean availableForUse() {
        return this.numberOfSameKind>0;
    }
    //toString
    @Override
    public String toString(){
        return "Name: "+getName()+"\n Category: "+getCategory()+"\n Price: "+getPrice()+"\n Number Of Products available: "+   getnumberOfSameKind();
    }

    //comparable

    public int compareTo(Product o) {
        return Double.compare(getPrice(), o.getPrice());
    }

    public boolean equals(Product product1){
        return name.equals(product1.getName());
    }
}