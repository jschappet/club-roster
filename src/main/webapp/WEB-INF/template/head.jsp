<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/include.jsp"  %>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<meta name="description" content="club-roster" />
<meta name="keywords" content="club-roster" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- disables device zooming --%>
<%-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon" /> --%>

<title>${ pageTitle }<c:if test="${ not empty pageTitle }"> : </c:if>club-roster</title>

<!-- jquery -->
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.min.css" />" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.structure.min.css" />" />
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/jquery/jquery-ui.theme.min.css" />" />
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-ui.min.js"/>"></script>

<!-- bootstrap -->
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>

<!-- data tables -->
<script type="text/javascript" src="<c:url value="/resources/datatable/media/js/jquery.dataTables.js" />"></script>
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/datatable/media/css/jquery.dataTables.min.css" />" />

<!-- responsive data tables -->
<script type="text/javascript" src="<c:url value="/resources/datatable/extensions/Responsive/js/dataTables.responsive.js" />"></script>
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/datatable/extensions/Responsive/css/dataTables.responsive.css" />" />

<%--
<!-- bootstrap data tables -->
<script type="text/javascript" src="<c:url value="/resources/datatable/media/js/dataTables.bootstrap.js" />"></script>
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/datatable/media/css/dataTables.bootstrap.css" />" />

<!-- jquery -->
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/jquery/development-bundle/themes/base/jquery.ui.all.css"/>"/>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-ui-1.9.1.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/development-bundle/external/jquery.cookie.js"/>"></script>

<!-- touch punch - jQuery support for touch screens -->
<script type="text/javascript" src="<c:url value="/resources/touch-punch/jquery.ui.touch-punch.min.js"/>"></script>

<!-- tiny mce -->
<script type="text/javascript" src="<c:url value="/resources/tinymce/tinymce.min.js" />" ></script>
--%>

<!-- bootstrap -->
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" />

<!-- application css and js -->
<link type="text/css" media="screen" rel="stylesheet" href="<c:url value="/resources/css/layout.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/utils.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/messager.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/init.js" />"></script>

<!--[if lte IE 8]>
<script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script>
<![endif]-->

<script type="text/javascript">
	$(function(){
		intializePage(); // see resources/js/init.js 
	});
</script>

<%--
NEED JIRA ISSUE COLLECTOR
<script type="text/javascript" src="https://www.icts.uiowa.edu/jira/s/en_US8f0l6v-1988229788/6099/18/1.4.0-m3/_/download/batch/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector/com.atlassian.jira.collector.plugin.jira-issue-collector-plugin:issuecollector.js?collectorId=bdc2a3bb"></script>
--%>