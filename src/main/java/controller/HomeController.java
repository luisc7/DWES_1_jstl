package controller;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.daos.EventoListImpl;
import modelo.daos.IntEventoDao;

/**
 * Servlet implementation class HomeController
 * @author Luis Cifuentes
 * @since 22 Octubre 2021
 */
@WebServlet("/inicio")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * <pre>Genera un objeto "IntEventoDao" que se pasa como el atributo
		 * de sesión "interEventos". Uno de los atributos de este objeto es 
		 * la lista de Eventos que hay en memoria.</pre>
		 */
		HttpSession sesion = request.getSession();
		IntEventoDao ievent = null;
		
		// try/catch que pedía Eclipse tras incorporar SimpleDateFormat
		try {
			ievent = new EventoListImpl();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sesion.setAttribute("interEventos", ievent);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
