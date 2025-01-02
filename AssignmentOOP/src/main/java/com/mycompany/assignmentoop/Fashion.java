
package com.mycompany.assignmentoop;


public class Fashion extends Product {

    String categoryName = "Fashion";

    public Fashion(String productID, String productName, int quantity, double price, double weight, double width, double height, double length) {
        super(productID, productName, quantity, price, weight, width, height, length);
    }

    public void getProductDetail() {
        System.out.println(
                "Product Name : " + getProductName()
                + "Product Quantity :" + getQuantity()
                + "Product Price" + getPrice()
        );
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Fashion{" + "categoryName=" + categoryName + '}';
    }

    
    
    @Override
    public Fashion clone() {
        return new Fashion(
                this.getProductID(),
                this.getProductName(),
                this.getQuantity(),
                this.getPrice(),
                this.getLength(),
                this.getWidth(),
                this.getHeight(),
                this.getWeight()
        );
    }
}
