package com.wipro.book.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation.equals("AddBook"))
		{
			String result=addBook(request);
			if(result.equals("SUCCESS"))
			{
				response.sendRedirect("Menu.html");
			}
			else if(result.equals("INVALID"))
			{
				response.sendRedirect("Invalid.html");
			}
			else
			{
				response.sendRedirect("Failure.html");
			}
		}
		else if(operation.equals("Search"))
		{
			String isbn=request.getParameter("isbn");
			BookBean bookBean=addBook(isbn);
			if(bookBean == null)
			{
				response.sendRedirect("Invalid.html");
			}
			else
			{
				HttpSession session=request.getSession();
				session.setAttribute("book", bookBean);
				RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
				rd.forward(request, response);
			}
			
		}
	}
	private BookBean addBook(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}
	public String addBook(HttpServletRequest request)
	{
	    String isbn = request.getParameter("isbn");
	    String bookName = request.getParameter("bookName");
	    String bookType = request.getParameter("bookType");
	    int authorCode = Integer.parseInt(request.getParameter("authorCode"));
	    String cost = request.getParameter("cost");

	    BookBean book = new BookBean();
	    book.setIsbn(isbn);
	    book.setBookName(bookName);
	    book.setBookType(bookType.charAt(0));
	    book.setCost(Float.parseFloat(cost));

	    String authorCodeStr = request.getParameter("authorCode");

	    if (authorCodeStr == null || authorCodeStr.trim().isEmpty()) {
	        return "INVALID";
	    }

	    String result = new Administrator().addBook(book);
	    return result;
	}
}