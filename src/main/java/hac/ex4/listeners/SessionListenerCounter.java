package hac.ex4.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A @WebListener class for session counting.
 */
@WebListener
public class SessionListenerCounter implements HttpSessionListener {
    private final AtomicInteger activeSessions;

    /**
     * Constructor.
     */
    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }

    /**
     * Gets all the active sessions
     * @return active sessions
     */
    public int getTotalActiveSession() {
        return activeSessions.get();
    }

    /**
     * Increase the session number and shows how many sessions created,
     * The HttpSession tells that new session has been created.
     * @param event - The HttpSessionEvent that tell that a new session has been created.
     */
    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("SessionListenerCounter +++ Total active session are " + activeSessions.get());
    }

    /**
     * Decrease the session number and shows how many sessions created,
     * The HttpSession tells that new session has been destroyed.
     * @param event The HttpSessionEvent which announces that a new session has been destroyed.
     */
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("SessionListenerCounter --- Total active session are " + activeSessions.get());
    }
}
