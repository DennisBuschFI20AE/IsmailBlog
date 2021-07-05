package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.db.Db;
import com.model.BlogEntry;
import com.valid.Valid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/reg")
public class Register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private final Db db = new Db();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username 			= request.getParameter("username");
		String pass1    			= request.getParameter("password1");
		String pass2   	 			= request.getParameter("password2");
		out 						= response.getWriter();
		
		if(pass1.equals(pass2)) 
		{
			if( Valid.isUserValid(username)) 
			{
				if(Valid.isPasswordValid(pass2)) 
				{
					db.addUser(username, pass2);
					response.sendRedirect("http://localhost:8080/Blog/login");
				}
				else 
				{
					System.out.println("Password muss mind. 8 Stellen ein Gross und ein Kleinbuchstaben und ein Sonderzeichen enthalten!");
				}
			}
			else 
			{
				System.out.println("Username muss mindestens 2 Buchstaben enthalten und höchstens 255");
			}
		}
		else 
		{
			System.out.println("Die passworter müssen gleich sein");
		}
		
	}

}
