package Bidding.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bidding.User;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userdao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String name = request.getParameter("uname");
		String password = request.getParameter("psw");
		String ssn = request.getParameter("ssn");
		String dob = request.getParameter("dob");
		String role = request.getParameter("role");
		String address = request.getParameter("address");
		
		User user = new User();
		user.setUserid(userid);
		user.setName(name);
		user.setPassword(password);
		user.setSsn(ssn);
		user.setRole(role);
		user.setDob(Date.valueOf(dob));
		user.setAcc_bal(0);
		user.setAddress(address);
		user.setRating(0);
		
		UserDao dao = new UserDao();
		int res = dao.regUser(user);
		
		RequestDispatcher dispatcher;
		if(res > 0) {
			Login.populateData(user, request);
			request.setAttribute("user", user);
			request.setAttribute("mssg", "Welcome to UB Bidding!");
			System.out.println("Redirecting to home page");
			dispatcher = request.getRequestDispatcher("home.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}

}
