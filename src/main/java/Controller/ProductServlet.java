package Controller;

import DAO.Category.CategoryDAO;
import DAO.product.IProductDAO;
import DAO.product.productDAO;
import Model.Category;
import Model.Product;
import Model.ShowCategoryName;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/Product")
public class ProductServlet extends HttpServlet {

    private CategoryDAO categoryDAO = new CategoryDAO();
    productDAO productDAO = new productDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            action="";

        }
        switch (action){
            case "create":
                showCreateForm(request,response);

                break;
            case "update":
                showUpdateForm(request,response);

                break;
            case "delete":
                delete(request,response);
                break;
            default:

                finhAll(request,response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String action = request.getParameter("action");
if(action==null){
    action="";
}
switch (action){
    case "create":
        createNewProduct(request,response);
        break;
    case "update":
        update(request,response);
        break;
    default:
        finhAll(request,response);

}
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryDAO.findCategoryList();
        request.setAttribute("categorylist", categoryList);
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Product/Update.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryDAO.findCategoryList();
        request.setAttribute("categorylist", categoryList);
        List<String> categoryNameList = categoryDAO.FindCategoryNameList();
        request.setAttribute("categoryNamelist", categoryNameList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Product/create.jsp");
        dispatcher.forward(request, response);
    }



    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher;
        productDAO.delete(id);
        response.sendRedirect("/Product");
    }


    private void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryDAO.findCategoryList();
        request.setAttribute("categorylist", categoryList);
        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("productName");
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        String color = request.getParameter("productColor");
        String productDescription = request.getParameter("productDescription");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Product product = new Product(id,productName,productPrice,productQuantity,color,productDescription,categoryId);

        productDAO.update(id,product);
       finhAll(request,response);

    }
    private void createNewProduct(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        String productColor =request.getParameter("productColor");
        String productDescription = request.getParameter("productDescription");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Product product = new Product(productName,productPrice,productQuantity,productColor,productDescription,categoryId);

        productDAO.create(product);
     finhAll(request,response);


    }
    private void finhAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<ShowCategoryName> productList = null;
        String search = request.getParameter("search");
        if(search==null|| search.equals("")){
            productList = productDAO.FindAllCategory();
        }else {
            productList= productDAO.findName(search);
        }
        request.setAttribute("showAll",productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Product/test.jsp");
        dispatcher.forward(request,response);
    }
}
