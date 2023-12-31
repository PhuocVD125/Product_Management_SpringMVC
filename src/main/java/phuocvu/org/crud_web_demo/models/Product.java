package phuocvu.org.crud_web_demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "ProductID")
    private String productID;
    private String categoryID;
    @NotNull
    @NotBlank(message = "Product's name can't not be null")
    @Size(min = 3, max = 300)
    @Column(name = "productName")
    private String productName;
    @Column(name = "price")
    @Min(0)
    @NotNull
    private int price;

    @NotNull
    @NotBlank(message = "Product's description can't not be null")
    @Size(min = 3)
    @Column(name = "description")
    private String description;

    public Product(String productID, String categoryID, String productName, int price, String description) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }
    public Product() {}

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }
    public String getFormattedPrice() {
        return String.format("$ %.2f", (price * 100.00) / 100.00);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
