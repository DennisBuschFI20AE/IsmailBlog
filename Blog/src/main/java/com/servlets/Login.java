package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;

import com.db.Db;
import com.model.BlogEntry;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Db db;
	private PrintWriter out;
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("drin in login");
		response.setContentType("text/html");
		//db = new Db();
		//TODO
		//In Datenbank
		//Verlinken auf eigene Blogseite blogEntrys.html
		LinkedList<BlogEntry> blogEintraege = new LinkedList<>();
		blogEintraege.add(new BlogEntry("Dennis", LocalDate.now(), "ueberschrift", "Hier kommt der text!"));
		out = response.getWriter();
		out.append("<html><head></head><body>");
		out.append("<h1>Blog Eintraege</h1>" 
					+ "<br>"	);
		
		for(BlogEntry be : blogEintraege) {
			out.append("<h5>" + be.getUsername() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"  )
			.append("<br><h2>" + be.getTitle() + "</h2>")
			.append("<br><br>")
			.append(be.getText())
			.append("<br>")
			.append("<hr><br>");
		}
		
		out.append("</body></html>");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
//		db = new Db();
//		db.addUser("hallo","Dennis");
	}

}
