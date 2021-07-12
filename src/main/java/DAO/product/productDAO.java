package DAO.product;

import DAO.SQLConnection;
import Model.Product;
import Model.ShowCategoryName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productDAO implements IProductDAO {
    public static final String FIND_ALL = "select * from product";
    public static final String FIND_PRODUCT_BY_ID = "select*from product where productId =?";
    public static final String CREATE_NEW_PRODUCT = "insert into product(productName,productPrice,productQuantity,productColor,productDesciption,CategoryId) values(?,?,?,?,?,?)";
    public static final String DELETE_PRODUCT_BY_ID = "delete from product where productId = ?";
    public static final String FIND_BY_NAME = "select * from product where productName like ?";
    public static final String UPDATE_BY_ID = "call update_product(?,?,?,?,?,?,? )";
    public static final String FIND_CATEGORY_ID_BY_PRODUCT_ID = "select categoryId from product where productId = ?";
    public static final String FIND_CATEGORY_Name_BY_CATEGORY_ID = "select category from category where categoryId = ?";
    public static final String FIND_ALL_CATEGORY_NAME = "select * from showList";


    @Override
    public List<Product> findAll() {
        List<Product> Products = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                int productPrice = rs.getInt(3);
                int productQuantity = rs.getInt(4);
                String productColor = rs.getString(5);
                String productDescription = rs.getString(6);
                int categoryId = rs.getInt(7);
                Product product = new Product(productId, productName, productPrice, productQuantity, productColor, productDescription, categoryId);
                Products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Products;
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("productName");
                int productPrice = rs.getInt("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productColor = rs.getString("productColor");
                String productDescription = rs.getString("productDesciption");
                int categoryId = rs.getInt("CategoryId");
                product.setProductId(id);
                product.setProductName(productName);
                product.setProductPrice(productPrice);
                product.setProductQuantity(productQuantity);
                product.setProductColor(productColor);
                product.setProductDescription(productDescription);
                product.setCategoryId(categoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean create(Product product) {
        Connection connection = SQLConnection.getConnection();
        int rowInserted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_PRODUCT);
//            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getProductQuantity());
            preparedStatement.setString(4, product.getProductColor());
            preparedStatement.setString(5, product.getProductDescription());
            preparedStatement.setInt(6, product.getCategoryId());
            rowInserted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted != 0;
    }

    @Override
    public boolean update(int id, Product product) {
        Connection connection = SQLConnection.getConnection();
        int rowUpdate = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setInt(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getProductQuantity());
            preparedStatement.setString(5, product.getProductColor());
            preparedStatement.setString(6, product.getProductDescription());
            preparedStatement.setInt(7, product.getCategoryId());
            rowUpdate = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate != 0;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = SQLConnection.getConnection();
        int rowDelete = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDelete != 0;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> productList = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                int productPrice = rs.getInt(3);
                int productQuantity = rs.getInt(4);
                String productColor = rs.getString(5);
                String productDescription = rs.getString(6);
                int categoryId = rs.getInt(7);
                Product product = new Product(productId, productName, productPrice, productQuantity, productColor, productDescription, categoryId);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public int findCategoryIdByProductId(int productId) {
        int categoryId = -1;


        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CATEGORY_ID_BY_PRODUCT_ID);
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                categoryId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

    @Override
    public String findCategoryName(int productId) {
        int categoryId = findCategoryIdByProductId(productId);
        String categoryName = "";
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CATEGORY_Name_BY_CATEGORY_ID);
            preparedStatement.setInt(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                categoryName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryName;
    }

    public List<ShowCategoryName> FindAllCategory() {
        List<ShowCategoryName> showCategoryNames = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CATEGORY_NAME);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                int productPrice = rs.getInt(3);
                int productQuantity = rs.getInt(4);
                String productColor = rs.getString(5);
                String categoryName = rs.getString(6);

                showCategoryNames.add(new ShowCategoryName(productId, productName, productPrice, productQuantity, productColor, categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showCategoryNames;

    }
    public List<ShowCategoryName> findName(String name) {
        List<ShowCategoryName> productList = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select*from showlist where productName like ?");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                int productPrice = rs.getInt(3);
                int productQuantity = rs.getInt(4);
                String productColor = rs.getString(5);
               String categoryName = rs.getString(6);
             ShowCategoryName showCategoryName = new ShowCategoryName(productId,productName,productPrice,productQuantity,productColor,categoryName);
                productList.add(showCategoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
