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
@RestController( value = "com_schappet_c4_clubRoster_resource_clubmember_resource" )
@RequestMapping( "/rest/clubmember" )
public class ClubMemberResource extends AbstractClubRosterApiResource {

    private static final Log log = LogFactory.getLog( ClubMemberResource.class );
    
    @RequestMapping( value = { "{clubMemberId}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ClubMember get( @PathVariable( "clubMemberId" ) Integer clubMemberId ) {
    	 ClubMember clubMember = clubRosterDaoService.getClubMemberService().findById( clubMemberId );
		 if (clubMember == null){
			 throw new EntityNotFoundException();
		 } 
	     return clubMember;
    }
    
    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE  )
    public ClubMember create( @RequestBody @Valid ClubMember clubMember ) {
		 clubRosterDaoService.getClubMemberService().save( clubMember );
		 return clubMember;
    }
    
    @RequestMapping( value = { "{clubMemberId}" }, method = { RequestMethod.POST, RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE  )
    public ClubMember update( @PathVariable( "clubMemberId" ) Integer clubMemberId, @RequestBody @Valid ClubMember clubMember ) {
    	ClubMember clubMemberRecord = clubRosterDaoService.getClubMemberService().findById( clubMemberId );
    	if ( clubMemberRecord == null || !clubMemberRecord.getMemberId().equals(clubMember.getMemberId())){
			 throw new EntityNotFoundException(); 
		 } 
    	 clubRosterDaoService.getClubMemberService().getSession().flush();
         clubRosterDaoService.getClubMemberService().getSession().clear();
		 clubRosterDaoService.getClubMemberService().update( clubMember );
		 return clubMember;
    }
    
    @RequestMapping( value = { "{clubMemberId}" }, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE )
    public String delete( @PathVariable( "clubMemberId" ) Integer clubMemberId ) {
    	ClubMember clubMember = clubRosterDaoService.getClubMemberService().findById( clubMemberId );
		 if ( clubMember == null ){
			 throw new EntityNotFoundException();
		 } 
		 clubRosterDaoService.getClubMemberService().delete(clubMember);
	     return "";
    }
    
    @RequestMapping( value = {  "", "/"  }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public List<ClubMember> list() {
    	 return clubRosterDaoService.getClubMemberService().list();
    }

}