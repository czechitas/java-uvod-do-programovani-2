package cz.czechitas.milenakm;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.context.embedded.*;
import org.springframework.boot.web.support.*;
import org.springframework.context.event.*;

@SpringBootApplication
public class SpousteciTrida extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(SpousteciTrida.class);


    /**
     * Spousteci metoda v aplikaci, pokud je aplikace spoustena jako .jar
     */
    public static void main(String[] args) {
        SpringApplication.run(SpousteciTrida.class);
    }


    /**
     * Spousteci metoda v aplikaci, pokud je aplikace spoustena jako .war
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpousteciTrida.class);
    }


    /**
     * Posluchac udalosti nasazeni webove aplikace na Tomcat, ktery zaloguje adresu,
     * na ktere je aplikace nasazena
     * @param evt
     */
    @EventListener
    public void onAppEvent(EmbeddedServletContainerInitializedEvent evt) {
        int port = evt.getEmbeddedServletContainer().getPort();
        logger.info("Your web app address: http://localhost:" + port +
                evt.getApplicationContext().getServletContext().getContextPath());
    }

}
