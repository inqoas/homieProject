package tw.idv.tibame.tha102.web.service.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.idv.tibame.tha102.web.service.dao.ServiceDao;
import tw.idv.tibame.tha102.web.service.dao.Imp.ServiceDaoImpl;
import tw.idv.tibame.tha102.web.service.vo.Service;

@WebServlet(urlPatterns = "/service/Select_ImgController")
public class Select_ImgController extends HttpServlet{
	
	private ServiceDao servicedaoimpl;
	
	@Override
	public void init() throws ServletException {
		servicedaoimpl = new ServiceDaoImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("image/jpg");
		
		ServletOutputStream servletOutputStream =resp.getOutputStream();
		
		Service service = servicedaoimpl.select_ImgbyId(Integer.parseInt(req.getParameter("service_id")));
		
		InputStream img =new ByteArrayInputStream(service.getService_picture());
		
		BufferedInputStream in = new BufferedInputStream(img);
		
		int num = img.available();
		
		int i;
		
		byte[] bytes = new byte[4 * 1024];	
		
		while( ( i = in.read(bytes)) !=  -1 ) {
			
			servletOutputStream.write(bytes,0,i);
		}
		in.close();
		img.close();
		servletOutputStream.close();
		
	}
}
