package Bidding.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bidding.Message;
import Bidding.User;
import Bidding.dao.UserDao;

/**
 * Servlet implementation class MessageControl
 */
@WebServlet("/messagewindow")
public class MessageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		request.setAttribute("mssg", "You are now redirected to a different site than UB Bidding System.");
		request.setAttribute("userid", userid);
		
		List<User> users = userDao.getAllUsers();
		List<Message> messages = userDao.getMyMessages(userid);
		
		request.setAttribute("allusers", users);
		request.setAttribute("allmsgs", messages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("userid");
		String receiver = request.getParameter("to");
		String message_body = request.getParameter("messagebody");
		String subject = request.getParameter("subject");
		
		Message message = new Message();
		message.setSender_id(sender);
		message.setReceiver_id(receiver);
		message.setMessage_msg(message_body);
		message.setSubject(subject);
		
		int res = userDao.sendMessage(message);
		List<User> users = userDao.getAllUsers();
		List<Message> messages = userDao.getMyMessages(sender);
		request.setAttribute("mssg", "Your message was sent");
		request.setAttribute("userid", sender);
		
		request.setAttribute("allusers", users);
		request.setAttribute("allmsgs", messages);
		RequestDispatcher dispatcher;
		if(res > 0) {
			dispatcher = request.getRequestDispatcher("message.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("error.jsp");
		}
		dispatcher.forward(request, response);
	}

}
