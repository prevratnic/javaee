package sevrice;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 19.11.12
 * Time: 22:59
 */
public class ServletContextService implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("RMI_SERVICE", new RMIService());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        return;
    }
}
