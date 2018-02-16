import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "CrudServlet" , urlPatterns = {"/crud"})
public class CrudServlet extends javax.servlet.http.HttpServlet {

//    private static final Logger logger = Logger.getLogger(CrudServlet.class);

    @EJB
    private WriteBean writeBean;
//    @EJB
//    private SearchBean searchBean;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        if (request.getParameter("action").equals("create"))
            writeBean.doStuff();
//        if (request.getParameter("action").equals("search"))
//            searchBean.searchFor(request.getParameter("expr"));
    }
}
