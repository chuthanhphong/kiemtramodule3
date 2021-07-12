package DAO.product;

import DAO.IGeneralDAO;
import Model.Product;

public interface IProductDAO extends IGeneralDAO<Product> {
    int findCategoryIdByProductId(int productId);
    String findCategoryName(int productId);
}
