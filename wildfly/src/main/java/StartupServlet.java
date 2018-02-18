import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//@WebServlet(name = "CrudServlet" , urlPatterns = {"/crud"})
@WebServlet
public class StartupServlet extends javax.servlet.http.HttpServlet {

    private static final Logger logger = Logger.getLogger(StartupServlet.class);

    @EJB
    private StartupBean startupBean;

    @Override
    public void init() {
        logger.info("starting up ........................................");
        startupBean.doStuff();
    }

//    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
//
//    }
}
