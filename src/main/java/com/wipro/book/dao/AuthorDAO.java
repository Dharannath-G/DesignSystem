package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DButil;

public class AuthorDAO {
	Connection con=DButil.getDBConnection();
	public AuthorBean getAuthor(int authorcode)
	{
		String query="select * from AUTHOR_TBL where Author_Code=?";
		try
		{
			AuthorBean author=new AuthorBean();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, authorcode);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				author.setAuthorCode(rs.getInt(1));
				author.setAuthorName(rs.getString(2));
				author.setContactNo(rs.getLong(3));
				return author;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public AuthorBean getAuthor(String authorName)
	{
		String query="select * from AUTHOR_TBL where Author_name=?";
		try
		{
			AuthorBean author=new AuthorBean();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, authorName);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				author.setAuthorCode(rs.getInt(1));
				author.setAuthorName(rs.getString(2));
				author.setContactNo(rs.getLong(3));
				return author;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}