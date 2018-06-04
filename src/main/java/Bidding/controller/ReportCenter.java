package Bidding.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bidding.User;
import Bidding.dao.BookDao;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class ReportCenter
 */
@WebServlet("/getreports")
public class ReportCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookdao = new BookDao();
	private UserDao userdao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportCenter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher ;
		request.setAttribute("mssg", "You are now redirected to the Report center.");
		dispatcher = request.getRequestDispatcher("reports.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String bookid = request.getParameter("bookid");
		
		List<List<Object>> report = bookdao.getReport(type, bookid);
		request.setAttribute("result", report);
		
		RequestDispatcher dispatcher ;
		if(report != null) {
			request.setAttribute("mssg", "You are now redirected to the Report center.");
			dispatcher = request.getRequestDispatcher("reports.jsp");
		} else {
			request.setAttribute("mssg", "Unable to fetch data!");
			dispatcher = request.getRequestDispatcher("reports.jsp");
		}
		dispatcher.forward(request, response);
	}

}
