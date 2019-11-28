package ru.itvitality.sbrf.cu.library;

import org.eclipse.jetty.server.ResourceService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.itvitality.sbrf.cu.library.servlet.IndexServlet;

public class StarterWeb {
    private final static int PORT = 8080;

    public static void main( String[] args ) throws Exception {
        new StarterWeb().start();
    }

    private void start() throws Exception {
        Server server = createServer( PORT );
        server.start();
        server.join();
    }

    private Server createServer( int port ) {
        ServletContextHandler context = new ServletContextHandler( ServletContextHandler.SESSIONS );
        context.addServlet( new ServletHolder( new IndexServlet() ), "/" );

        Server server = new Server( port );
        server.setHandler( new HandlerList( context ) );
        return server;
    }
}
