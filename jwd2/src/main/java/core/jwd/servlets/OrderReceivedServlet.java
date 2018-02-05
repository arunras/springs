package core.jwd.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.jwd.data.MenuDao;
import core.jwd.data.MenuDaoFactory;
import core.jwd.domain.Order;
import core.jwd.websockets.KitchenDisplaySessionHandler;
import core.jwd.websockets.KitchenDisplaySessionHandlerFactory;

@WebServlet("/orderReceived.html")
public class OrderReceivedServlet extends HttpServlet {
	
	MenuDao menuDao = MenuDaoFactory.getMenuDao();
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int maxId = menuDao.getFullMenu().size();
		Order order = menuDao.newOrder(request.getUserPrincipal().getName());
		for (int i = 0; i <maxId+1; i++) {
			String quantity = request.getParameter("item_" + i);
			 try  
			  {  
			    int q = Integer.parseInt(quantity);
			    if (q > 0) {
			    	menuDao.addToOrder(order.getId(), menuDao.getItem(i), q);
			    	order.addToOrder(menuDao.getItem(i), q);
			    }
			  }  
			  catch(NumberFormatException nfe)  
			  {  
			    //that's fine it just means there wasn't an order for this item 
			  }  
			  
		}
		
	  KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandlerFactory.getHandler();	
    handler.newOrder(order);



		HttpSession session = request.getSession();
		session.setAttribute("orderId", order.getId());
		
		String redirectUrl = "/thankYou.html";
		redirectUrl = response.encodeURL(redirectUrl);
		response.sendRedirect(redirectUrl);

	}
}
