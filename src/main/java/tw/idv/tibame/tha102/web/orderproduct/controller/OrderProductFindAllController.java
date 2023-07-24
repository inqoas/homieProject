package tw.idv.tibame.tha102.web.orderproduct.controller;

import static tw.idv.tibame.tha102.core.util.CommonUtil.writePojo2Json;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.core.util.HibernateUtil;
import tw.idv.tibame.tha102.web.orderproduct.service.OrderProductService;
import tw.idv.tibame.tha102.web.orderproduct.service.OrderProductServiceImpl;

@WebServlet("/orderproduct/findall")
public class OrderProductFindAllController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    private OrderProductService service; 

    @Override
    public void init() {
        service = new OrderProductServiceImpl(HibernateUtil.getSessionFactory());
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		writePojo2Json(response, service.findAll());
	}
	
}
