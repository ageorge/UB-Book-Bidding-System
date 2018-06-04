package Bidding.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bidding.dao.DatabaseConnection;

/**
 * Servlet implementation class AllQuery
 */
@WebServlet("/allQuery")
public class AllQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllQuery() {
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
		String query = request.getParameter("query");
		DatabaseConnection databaseConnection = new DatabaseConnection();
		databaseConnection.ConnectToDB();
		
		List<List<Object>> res = databaseConnection.processQuery(query);
		request.setAttribute("result", res);
		RequestDispatcher dispatcher = request.getRequestDispatcher("query.jsp");
		dispatcher.forward(request, response);
	}

}
