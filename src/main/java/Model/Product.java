package Model;

public class Product {
    private int productId;
    private String productName;
    private int productPrice;
    private int productQuantity;
    private String productColor;
    private String productDescription;
    private int categoryId;

    public Product() {
    }

    public Product(String productName, int productPrice, int productQuantity, String productColor, String productDescription, int categoryId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productColor = productColor;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
    }

    public Product(int productId, String productName, int productPrice, int productQuantity, String productColor, String productDescription, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productColor = productColor;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productColor='" + productColor + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
