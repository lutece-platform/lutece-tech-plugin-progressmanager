<jsp:useBean id="progressmanagerBean" scope="session" class="fr.paris.lutece.plugins.progressmanager.web.ProgressManagerJspBean" />
<% String strContent = progressmanagerBean.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
