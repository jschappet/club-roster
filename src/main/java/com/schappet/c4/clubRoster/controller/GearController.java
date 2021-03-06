package com.schappet.c4.clubRoster.controller;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.uiowa.icts.datatable.DataTable;
import edu.uiowa.icts.datatable.DataTableColumn;
import edu.uiowa.icts.datatable.DataTableRequest;
import com.schappet.c4.clubRoster.domain.*;
import edu.uiowa.icts.spring.GenericDaoListOptions;

/**
 * Generated by Protogen 
 * @see <a href="https://github.com/ui-icts/protogen">https://github.com/ui-icts/protogen</a>
 * @since 04/21/2016 20:21:12 CDT
 */
@Controller( value = "com_schappet_c4_clubRoster_controller_gear_controller" )
@RequestMapping( "/gear" )
public class GearController extends AbstractClubRosterController {

    private static final Log log = LogFactory.getLog( GearController.class );

    @RequestMapping( value = "list_alt", method = RequestMethod.GET )
    public String listNoScript(Model model) {
        model.addAttribute( "gearList", clubRosterDaoService.getGearService().list() );
        return "/clubroster/gear/list_alt";
    }

    @RequestMapping( value = { "list", "", "/" }, method = RequestMethod.GET )
    public String list(Model model) {
    	// needed for AngularJS grid/CRUD functionality
    	 
        return "/clubroster/gear/list";
    }


	@ResponseBody
	@RequestMapping( value = "datatable" , produces = "application/json" )
	public DataTable datatable( @RequestBody DataTableRequest dataTableRequest, HttpServletRequest request,
		@RequestParam( value = "display" , required = false , defaultValue = "list" ) String display ) {
		
		String contextPath = request.getContextPath();
		GenericDaoListOptions options = dataTableRequest.getGenericDaoListOptions();

		try {

			Integer count = clubRosterDaoService.getGearService().count( options );
            List<Gear> gearList = clubRosterDaoService.getGearService().list( options );
            
			List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();

			for( Gear gear : gearList ){

				LinkedHashMap<String, Object> tableRow = new LinkedHashMap<String, Object>();

				for ( DataTableColumn column : dataTableRequest.getColumns() ) {

					String headerName = column.getName();
					String dataName = column.getData();

					switch ( headerName ) {
						case "gearId" :
							tableRow.put( dataName, gear.getGearId() );
							break;
						case "gearType" :
							tableRow.put( dataName, gear.getGearType() );
							break;
						case "modelName" :
							tableRow.put( dataName, gear.getModelName() );
							break;
						case "makeName" :
							tableRow.put( dataName, gear.getMakeName() );
							break;
						case "description" :
							tableRow.put( dataName, gear.getDescription() );
							break;
						case "memberGears" :
							tableRow.put( dataName, gear.getMemberGears().size() );
							break;
						case "urls" :
							String urls = "";
							if( StringUtils.equals( "list", display ) ){
								urls += "<a href=\"" + contextPath + "/gear/show?"+"gearId="+gear.getGearId()+"\"><span class=\"glyphicon glyphicon-eye-open\"></a>";
								urls += "<a href=\"" + contextPath + "/gear/edit?"+"gearId="+gear.getGearId()+"\"><span class=\"glyphicon glyphicon-pencil\"></a>";
								urls += "<a href=\"" + contextPath + "/gear/delete?"+"gearId="+gear.getGearId()+"\"><span class=\"glyphicon glyphicon-trash\"></a>";
							} else {

							}
							tableRow.put( dataName, urls );
							break;
						default :
							tableRow.put( dataName, "[error: column " + headerName + " not supported]" );
							break;
					}
				}
				data.add( tableRow );
			}

			DataTable dataTable = new DataTable();
			dataTable.setDraw( dataTableRequest.getDraw() );
			dataTable.setRecordsFiltered( count );
			dataTable.setRecordsTotal( count );
			dataTable.setData( data );
			return dataTable;
			
		} catch ( Exception e ) {
			log.error( "error builing datatable json object for Gear", e );
			return datatableError( e, dataTableRequest.getDraw() );
		}
		
	}

    @RequestMapping( value = "add", method = RequestMethod.GET )
    public String add( Model model ) {
        model.addAttribute( "gear", new Gear() );

        return "/clubroster/gear/edit";
    }

    @RequestMapping( value = "edit", method = RequestMethod.GET )
    public String edit( ModelMap model, @RequestParam( value = "gearId" ) Integer gearId ) {


        model.addAttribute( "gear", clubRosterDaoService.getGearService().findById( gearId ) );
        return "/clubroster/gear/edit";
    }

    @RequestMapping( value = "show", method = RequestMethod.GET )
    public String show( ModelMap model, @RequestParam( value = "gearId" ) Integer gearId ) {

        model.addAttribute( "gear", clubRosterDaoService.getGearService().findById( gearId ) );
        return "/clubroster/gear/show";
    }

    @RequestMapping( value = "save", method = RequestMethod.POST )
    public String save(@Valid @ModelAttribute( "gear" ) Gear gear, BindingResult result, Model model ) {


		if (result.hasErrors()) { 
			
			return "/clubroster/gear/edit"; 
		} else {
			try {
				clubRosterDaoService.getGearService().saveOrUpdate( gear );
			} catch (NonUniqueObjectException e) {
				log.debug("Merging Results");
				clubRosterDaoService.getGearService().merge( gear );
			}
		}
		return "redirect:/gear/list";
    }

    @RequestMapping( value = "delete", method = RequestMethod.GET )
    public String confirmDelete( ModelMap model, @RequestParam( value = "gearId" ) Integer gearId ) {

        model.addAttribute( "gear", clubRosterDaoService.getGearService().findById( gearId ) );
        return "/clubroster/gear/delete";
    }

    @RequestMapping( value = "delete", method = RequestMethod.POST )
    public String doDelete( ModelMap model, @RequestParam( value = "submit" ) String submitButtonValue, @RequestParam( value = "gearId" ) Integer gearId ) {

        if ( StringUtils.equalsIgnoreCase( submitButtonValue, "yes" ) ) {
            clubRosterDaoService.getGearService().delete( clubRosterDaoService.getGearService().findById( gearId ) );
        }
        return "redirect:/gear/list";
    }
}