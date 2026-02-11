package com.wipro.book.service;


import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {
	public String addBook(BookBean bookBean)
	{
		//String authorName = bookBean.getAuthor().getAuthorName();
		if(bookBean == null || bookBean.getBookName().isEmpty() || bookBean.getIsbn().isEmpty() || bookBean.getCost()==0 || bookBean.getAuthor().getAuthorName().isEmpty() 
			|| (bookBean.getBookType()!='G'&& bookBean.getBookType()!='T'))

		{
			return "INVALID";
		}
		int result=new BookDAO().createBook(bookBean);
		if(result==1)
		{
			return "SUCCESS";
		}
		return "FAILURE";
	}
	public BookBean viewBook(String isbn)
	{
		BookDAO res=new BookDAO();
		BookBean valid=res.fetchBook(isbn);
		return valid;
	}
}