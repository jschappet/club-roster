package com.schappet.c4.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uiowa.icts.exception.MappingNotFoundException;

@Controller
@RequestMapping( "/*" )
public class DefaultController {

	private static final Log log = LogFactory.getLog( DefaultController.class );

	@RequestMapping( "/**" )
	public String mappingNotFound( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws MappingNotFoundException {
		// throw new MappingNotFoundException( request.getRequestURL().toString() );
		response.setStatus( 404 );
		model.addAttribute( "error", true );
		model.addAttribute( "headerText", "404" );
		model.addAttribute( "message", "Page not found: " + request.getRequestURL().toString() );
		return "message";
	}

	@ExceptionHandler
	public ModelAndView error( HttpServletRequest request, Exception exception ) {
		log.error( "Error URI: " + request.getRequestURI() );
		log.error( "Error Message: " + exception.getMessage(), exception );
		ModelMap model = new ModelMap();
		model.addAttribute( "exception", exception );
		return new ModelAndView( "error", model );
	}
	
	@RequestMapping( value = "{page}" , method = RequestMethod.GET )
	public String displayDefault( @PathVariable String page, ModelMap model ) {
		return page;
	}

	@RequestMapping( value = { "/", "index" } , method = RequestMethod.GET )
	public String indexPage( ModelMap model ) {
		return "index";
	}

	@RequestMapping( value = "switch_user" , method = RequestMethod.GET )
	public String switch_user( ModelMap model, @RequestParam( value = "error" , required = false ) Boolean error ) {
		model.addAttribute( "error", error );
		return "switch_user";
	}
	
	@RequestMapping( value = "message" , method = RequestMethod.GET )
	public String message( ModelMap model, 
		@RequestParam( value = "error" , required = false ) Boolean error,
		@RequestParam( value = "headerText" , required = false ) String headerText, 
		@RequestParam( value = "message" , required = false ) String message ) {
		
		model.addAttribute( "error", error == null ? false : error );
		model.addAttribute( "headerText", headerText );
		model.addAttribute( "message", message );
		
		return "message";
	}

	@RequestMapping( value = "login" , method = RequestMethod.GET )
	public String login( ModelMap model, @RequestParam( value = "error" , required = false ) String error ) {
		model.addAttribute( "error", error == null ? false : error );
		return "login";
	}

}