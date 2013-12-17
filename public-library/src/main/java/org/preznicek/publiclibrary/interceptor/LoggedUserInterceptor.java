package org.preznicek.publiclibrary.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.utils.Constants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Namapovano v <code>app-servlet.xml</code> na <code>/private/**</code>.
 */
public class LoggedUserInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Zjisti, jestli je v <code>session</code> ulozeny objekt prihlaseneho uzivatele. Pokud ano, 
	 * dovoli dale pokracovat. Pokud ne, provede se presmerovani na uvodni stranku aplikace.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("LoggedUserInterceptor");
		
		LoggedUser loggedUser = (LoggedUser) request.getSession().getAttribute(Constants.SessionKey.LOGGED_USER);
		
		if (loggedUser == null) {
			System.out.println("LoggedUserInterceptor redirect home");
			response.sendRedirect(request.getContextPath() + "/home");
			return false;
		}
		
		return true;
	}
}
