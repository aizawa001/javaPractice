package listener;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class DocoTsubuListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  {
    	String jdbcUrl = null;
		String dbUser = null;
 		String dbPass = null;
		try (Reader fr = new FileReader ("C:\\pleiades\\workspace\\docoTsubu\\WebContent\\WEB-INF\\config\\DB.properties");) {
			Properties p = new Properties();
			p.load(fr);
			jdbcUrl = p.getProperty("JDBC_URL");
			dbUser = p.getProperty("DB_USER");
			dbPass = p.getProperty("DB_PASS");
		}catch (IOException e) {
			e.printStackTrace();
		}

		ServletContext application = sce.getServletContext();
		application.setAttribute("jdbcUrl", jdbcUrl);
		application.setAttribute("dbUser", dbUser);
		application.setAttribute("dbPass", dbPass);
    }

}
