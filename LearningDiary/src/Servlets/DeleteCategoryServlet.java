package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.websocket.SendResult;

import Managers.CategoryManager;


@WebServlet({ "/DeleteCategoryServlet", "/deleteCategory" })
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/MyDB")
	DataSource ds;
    
    public DeleteCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getContextPath() + "/category";
		boolean updateSucceeded = false;
		List<Domain.Category> theCategory  = null;
    	CategoryManager cm  = new CategoryManager(ds);
    	int id = new Integer(request.getParameter("id"));
    	
    	try {
    			updateSucceeded = new CategoryManager(ds).deleteCategoryWithID(id);
    			theCategory = cm.getCategoryTotals(); 
        		
    		} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	if(updateSucceeded != true) {
    		request.setAttribute("error_delete", "OOOPS You have books in your Category.. !!");
    		request.setAttribute("categoryList", theCategory);
    		url = "/WEB-INF/viewcategory.jsp";
    		getServletContext().getRequestDispatcher(url).forward(request, response);
    		return;
    		
    	} else {
    	
    		response.sendRedirect(url);
    	}
	}

}