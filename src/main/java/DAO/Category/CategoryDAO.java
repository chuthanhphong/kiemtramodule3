package DAO.Category;

import DAO.SQLConnection;
import DAO.product.IProductDAO;
import DAO.product.productDAO;
import Model.Category;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    IProductDAO productDAO = new productDAO();
    public static String FIND_ALL_CATEGORY = "select*from category";
    public static String  FIND_CATEGORY_NAME_BY_ID ="select category from category where id =?";
    public static String FIND_CATEGORY_NAME_LIST = "select category from category";

    public List<Category> findCategoryList(){
        List<Category> categoryList = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CATEGORY);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int categoryId= rs.getInt(1);
                String categoryName =rs.getString(2);
                categoryList.add(new Category(categoryId,categoryName));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categoryList;
    }
    public List<String> FindCategoryNameList(){
        List<String> categoryNameList = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(FIND_CATEGORY_NAME_LIST);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                categoryNameList.add(rs.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        return categoryNameList;
    }
    public String getFindCategoryNameById(int id) {
        String categoryName ="";
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CATEGORY_NAME_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                categoryName= rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categoryName;

    }



}
