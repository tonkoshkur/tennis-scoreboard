package ua.tonkoshkur.tennis.common.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import ua.tonkoshkur.tennis.common.factory.ComponentFactory;
import ua.tonkoshkur.tennis.match.finished.FinishedMatchesService;
import ua.tonkoshkur.tennis.match.ongoing.OngoingMatchesService;
import ua.tonkoshkur.tennis.player.PlayerService;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initAttributes(sce.getServletContext());
    }

    private void initAttributes(ServletContext context) {
        ComponentFactory factory = new ComponentFactory();
        context.setAttribute(SessionFactory.class.getSimpleName(), factory.getSessionFactory());
        context.setAttribute(PlayerService.class.getSimpleName(), factory.getPlayerService());
        context.setAttribute(OngoingMatchesService.class.getSimpleName(), factory.getOngoingMatchesService());
        context.setAttribute(FinishedMatchesService.class.getSimpleName(), factory.getFinishedMatchesService());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        closeSessionFactory(sce.getServletContext());
    }

    private void closeSessionFactory(ServletContext context) {
        SessionFactory sessionFactory = (SessionFactory) context.getAttribute(SessionFactory.class.getSimpleName());
        sessionFactory.close();
    }
}
