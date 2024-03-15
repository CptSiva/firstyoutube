package com.uniquedeveloper.registration;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
   {
	   PrintWriter pw=res.getWriter();
	   
		res.setContentType("text/html");
		
		String uname=req.getParameter("name");
	   		String uemail=req.getParameter("email");
	   	 String upass=req.getParameter("pass");
		String umobile=req.getParameter("contact");
		
		
		try
		{
			Class.forName("com.oracle.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","siva");
			
			  PreparedStatement ps=con.prepareStatement("insert into user_table values(?,?,?,?)");
			  ps.setString(1, uname); 
			  ps.setString(2, upass); 
			  ps.setString(3, uemail);
			  ps.setString(4, umobile);
			  int k=ps.executeUpdate(); 
			  RequestDispatcher rd=req.getRequestDispatcher("registration.jsp");
			  if(k>0) 
			  {
			  req.setAttribute("status","success");
			   } 
			  else 
			  {
				  
				  req.setAttribute("status","failed"); 
			  }
			  rd.forward(req, res);
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
