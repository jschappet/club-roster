package com.schappet.c4.clubRoster.controller;




import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schappet.c4.clubRoster.domain.ClubMember;

import edu.uiowa.icts.datatable.DataTableColumn;
import edu.uiowa.icts.datatable.DataTableRequest;
import edu.uiowa.icts.datatable.DataTableSearch;

/**
 * Generated by Protogen
 * @see <a href="https://github.com/ui-icts/protogen">https://github.com/ui-icts/protogen</a>
 * @since Thu Apr 21 20:21:12 CDT 2016
 */
public class ClubMemberControllerMvcTest extends AbstractControllerMVCTests {
	
    private ClubMember firstClubMember;
    
    @Before
    public void before() {
              // add 20 records to test database
        for(int x=1; x<21; x++){
        	ClubMember clubMember = new ClubMember();
        	clubRosterDaoService.getClubMemberService().save(clubMember);
	        if (x == 1){
	        	// use this ID for update, show, and delete assertions
	        	firstClubMember = clubMember;
	        }
        }   
        this.clubRosterDaoService.getClubMemberService().getSession().flush();
        this.clubRosterDaoService.getClubMemberService().getSession().clear();
          }

    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void addShouldDisplayNewClubMemberForm() throws Exception {
       mockMvc.perform(get("/clubmember/add"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("clubMember")) 
       .andExpect(view().name("/clubroster/clubmember/edit"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void listShouldSimplyLoadPage() throws Exception {
       mockMvc.perform(get("/clubmember/list"))
       .andExpect(status().isOk())
       .andExpect(view().name("/clubroster/clubmember/list"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void indexShouldDisplayListPage() throws Exception {
       mockMvc.perform(get("/clubmember/"))
       .andExpect(status().isOk())
       .andExpect(view().name("/clubroster/clubmember/list"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void listAltShouldLoadListOfClubMembers() throws Exception {
       mockMvc.perform(get("/clubmember/list_alt"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("clubMemberList")) 
       .andExpect(view().name("/clubroster/clubmember/list_alt"));
    }
    
    
          	  
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void saveNewShouldPersistAndRedirectToListView() throws Exception {
       long count = clubRosterDaoService.getClubMemberService().count();
       
       mockMvc.perform(post("/clubmember/save").with(csrf())).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/clubmember/list"));   
       
       assertEquals("count should increase by 1", count +1 , clubRosterDaoService.getClubMemberService().count());
	}
     
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void saveExistingShouldPersistAndRedirectToListView() throws Exception {
       long count = clubRosterDaoService.getClubMemberService().count();
         
       mockMvc.perform(post("/clubmember/save").param("memberId", firstClubMember.getMemberId().toString()).with(csrf())).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/clubmember/list"));   
         
       assertEquals("count NOT should increase", count , clubRosterDaoService.getClubMemberService().count());
  	}      
  
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void editShouldLoadObjectAndDisplayForm() throws Exception {
    	mockMvc.perform(get("/clubmember/edit").param("memberId", firstClubMember.getMemberId().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("clubMember")) 
         .andExpect(view().name("/clubroster/clubmember/edit"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void showShouldLoadAndDisplayObject() throws Exception {
    	mockMvc.perform(get("/clubmember/show").param("memberId", firstClubMember.getMemberId().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("clubMember")) 
         .andExpect(view().name("/clubroster/clubmember/show"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void deleteGetShouldLoadAndDisplayYesNoButtons() throws Exception {
    	mockMvc.perform(get("/clubmember/delete").param("memberId", firstClubMember.getMemberId().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("clubMember")) 
         .andExpect(view().name("/clubroster/clubmember/delete"));
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void deletePostSubmitYesShouldDeleteAndRedirectToListView() throws Exception {
        long count = clubRosterDaoService.getClubMemberService().count();

       mockMvc.perform(post("/clubmember/delete").param("memberId", firstClubMember.getMemberId().toString())
       .param("submit", "Yes").with(csrf())).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/clubmember/list"));  
       
       assertEquals("count should decrease by 1", count - 1 , clubRosterDaoService.getClubMemberService().count());
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void deletePostSubmitNoShouldNotDeleteAndRedirectToListView() throws Exception {
        long count = clubRosterDaoService.getClubMemberService().count();

       mockMvc.perform(post("/clubmember/delete").param("memberId", firstClubMember.getMemberId().toString())
       .param("submit", "No").with(csrf())).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/clubmember/list"));  
       
       assertEquals("count should NOT decrease by 1", count , clubRosterDaoService.getClubMemberService().count());
    }
      
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void defaultDatatableShouldReturnJSONDataWith10Rows() throws Exception {
    	DataTableRequest dtr = getDataTableRequest( Arrays.asList("urls","emailAddress","firstName","isGuest","lastName","memberCode","memberGears","memberId","memberSince","password","username" ));
    	ObjectMapper mapper = new ObjectMapper();
    	
    	mockMvc.perform(post("/clubmember/datatable").content(mapper.writeValueAsString(dtr))
			.param("display", "list")
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
			.with(csrf()))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    	.andExpect(jsonPath("$.recordsTotal", is((int) clubRosterDaoService.getClubMemberService().count())))
    	.andExpect(jsonPath("$.recordsFiltered", is((int) clubRosterDaoService.getClubMemberService().count())))
    	.andExpect(jsonPath("$.draw", is("1")))
    	// max # of returned data rows should be 10 per "length" value
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
    	.andExpect(jsonPath("$.data[0][0]", containsString("show?")))
		.andExpect(jsonPath("$.data[0][0]", containsString("edit?")))
		.andExpect(jsonPath("$.data[0][0]", containsString("delete?")))
        ;
    }
    	  
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void defaultDatatableShouldReturnJSONDataWith10RowsAndDisplayAltnerateGlyphiconURLsAsEmptyByDefault() throws Exception {
    	DataTableRequest dtr = getDataTableRequest( Arrays.asList("urls","emailAddress","firstName","isGuest","lastName","memberCode","memberGears","memberId","memberSince","password","username" ));
    	ObjectMapper mapper = new ObjectMapper();
    	
    	mockMvc.perform(post("/clubmember/datatable").content(mapper.writeValueAsString(dtr))
			.param("display", "alternateURLs")
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
			.with(csrf()))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    	.andExpect(jsonPath("$.recordsTotal", is((int) clubRosterDaoService.getClubMemberService().count())))
    	.andExpect(jsonPath("$.recordsFiltered", is((int) clubRosterDaoService.getClubMemberService().count())))
    	.andExpect(jsonPath("$.draw", is("1")))
    	// max # of returned data rows should be 10 per "length" value
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
    	.andExpect(jsonPath("$.data[0][0]", is("")))
        ;
    }
    
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void defaultDatatableShouldReturnErrorTextForBogusColumnName() throws Exception {
    	DataTableRequest dtr = new DataTableRequest();
    	dtr.setStart(1);
    	dtr.setDraw("1");
    	dtr.setLength(10);
    	dtr.setIndividualSearch(true);
    	
    	List<DataTableColumn> columns = dtr.getColumns();
    	DataTableColumn column = new DataTableColumn("0", "asdfasdf", null, true, true, true);
    	DataTableSearch columnSearch = new DataTableSearch("", false);
    	column.setSearch(columnSearch);
    	columns.add(column);
    	dtr.setColumns(columns);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	mockMvc.perform(post("/clubmember/datatable").content(mapper.writeValueAsString(dtr))
			.param("display", "list")
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
			.with(csrf()))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is((int) clubRosterDaoService.getClubMemberService().count())))
    	.andExpect(jsonPath("$.recordsFiltered", is((int) clubRosterDaoService.getClubMemberService().count())))
    	.andExpect(jsonPath("$.draw", is("1")))
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
    	.andExpect(jsonPath("$.data[0].0", is("[error: column asdfasdf not supported]")))
    	;
    }    
    	  
    @Test
    @WithMockUser(username="user", roles={"ADMIN"})
    public void defaultDatatableShouldReturnExceptionBecauseCantSearchColumnThatDoesntExist() throws Exception {			
		DataTableRequest dtr = new DataTableRequest();
    	dtr.setStart(1);
    	dtr.setDraw("1");
    	dtr.setLength(10);
    	dtr.setIndividualSearch(true);
    	
    	List<DataTableColumn> columns = dtr.getColumns();
    	DataTableColumn column = new DataTableColumn("0", "asdfasdf", null, true, true, true);
    	DataTableSearch columnSearch = new DataTableSearch("epic fail", false);
    	column.setSearch(columnSearch);
    	columns.add(column);
    	dtr.setColumns(columns);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	mockMvc.perform(post("/clubmember/datatable").content(mapper.writeValueAsString(dtr))
			.param("display", "list")
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
			.with(csrf()))	
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    	.andExpect(jsonPath("$.recordsTotal", is(0)))
    	.andExpect(jsonPath("$.recordsFiltered", is(0)))
    	.andExpect(jsonPath("$.draw", is("1")))
    	.andExpect(jsonPath("$.data", hasSize(is(0))))
    	.andExpect(jsonPath("$.error", IsNull.notNullValue()))
    	;
    }      
          
    
}