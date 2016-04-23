package com.schappet.c4.clubRoster.resource;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

import com.schappet.c4.clubRoster.domain.*;
import edu.uiowa.icts.exception.EntityNotFoundException;
import edu.uiowa.icts.spring.GenericDaoListOptions;

/**
 * Generated by Protogen 
 * @see <a href="https://github.com/ui-icts/protogen">https://github.com/ui-icts/protogen</a>
 * @since 04/23/2016 10:55:33 CDT
 */
@RestController( value = "com_schappet_c4_clubRoster_resource_gear_resource" )
@RequestMapping( "/rest/gear" )
public class GearResource extends AbstractClubRosterApiResource {

    private static final Log log = LogFactory.getLog( GearResource.class );
    
    @RequestMapping( value = { "{gearId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public Gear get( @PathVariable( "gearId" ) Integer gearId ) {
    	 Gear gear = clubRosterDaoService.getGearService().findById( gearId );
		 if (gear == null){
			 throw new EntityNotFoundException();
		 } 
	     return gear;
    }
    
    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE  )
    public Gear create( @RequestBody @Valid Gear gear ) {
		 clubRosterDaoService.getGearService().save( gear );
		 return gear;
    }
    
    @RequestMapping( value = { "{gearId}" }, method = { RequestMethod.POST, RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE  )
    public Gear update( @PathVariable( "gearId" ) Integer gearId, @RequestBody @Valid Gear gear ) {
    	Gear gearRecord = clubRosterDaoService.getGearService().findById( gearId );
    	if ( gearRecord == null || !gearRecord.getGearId().equals(gear.getGearId())){
			 throw new EntityNotFoundException(); 
		 } 
    	 clubRosterDaoService.getGearService().getSession().flush();
         clubRosterDaoService.getGearService().getSession().clear();
		 clubRosterDaoService.getGearService().update( gear );
		 return gear;
    }
    
    @RequestMapping( value = { "{gearId}" }, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE )
    public String delete( @PathVariable( "gearId" ) Integer gearId ) {
    	Gear gear = clubRosterDaoService.getGearService().findById( gearId );
		 if ( gear == null ){
			 throw new EntityNotFoundException();
		 } 
		 clubRosterDaoService.getGearService().delete(gear);
	     return "";
    }
    
    @RequestMapping( value = {  "", "/"  }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Gear> list() {
    	 return clubRosterDaoService.getGearService().list();
    }

}