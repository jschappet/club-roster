package com.schappet.c4.clubRoster.web;



import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.schappet.c4.clubRoster.dao.ClubRosterDaoService;
import edu.uiowa.icts.spring.Security;

/**
 * Generated by Protogen
 * @see <a href="https://github.com/ui-icts/protogen">https://github.com/ui-icts/protogen</a>
 * @since 04/21/2016 20:21:12 CDT
 */
public abstract class AbstractClubRosterResource {

	@Autowired
	protected ClubRosterDaoService clubRosterDaoService;

	@ModelAttribute( value = "username" )
	public String getUsername() {
		if ( Security.isAuthenticated() ) {
			return SecurityContextHolder.getContext().getAuthentication().getName();
		}
		return null;
	}

}