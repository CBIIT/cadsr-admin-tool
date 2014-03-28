<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="gov.nih.nci.ncicb.cadsr.admintool.struts.action.EVSBean" %>
<%@ page import="gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts" %>
<%@ page import="gov.nih.nci.ncicb.cadsr.admintool.struts.action.EVSSearch" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<% List c = new ArrayList(); %>
<% if (request.getAttribute("concept") != null)
  c = (List) request.getAttribute("concept");%>
  


<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>EVS List of Values</title>
    <link href="css/insert.css" rel="stylesheet" media="screen"/>
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
    <SCRIPT LANGUAGE="JavaScript">
<!--
function passback(P_CODE,P_NAME,P_DEFINITION,P_DEFINITION_SOURCE,P_DICTIONARY,P_EVS_NAME) {
   opener.document.forms[0].P_PREFERRED_NAME[0].value = P_CODE;
   opener.document.forms[0].P_LONG_NAME[0].value = P_NAME;
   opener.document.forms[0].P_PREFERRED_DEFINITION[0].value = P_DEFINITION;
   opener.document.forms[0].P_DEFINITION_SOURCE[0].value = P_DEFINITION_SOURCE;
   opener.document.forms[0].P_ORIGIN[0].value = P_DICTIONARY;
   opener.document.forms[0].P_EVS_TYPE[0].value = P_EVS_NAME;
   opener.document.forms[0].P_PREFERRED_NAME[0].focus();
   close();
}

function closeOnClick() {
    close();
}
</SCRIPT>
     </head>
  <body>

   <s:form action="findAction" theme="simple">
    <table>
      <tr>      
       <td>
         Term
       </td>
        <td>
        <s:textfield name="term"/>
        </td></tr>
        <tr>
      <td>
        Search In
      </td>
        <td> <s:select size="1" name="searchIn" list="#{'Synonym':'Synonym', 'Concept Code/CUI':'Concept Code/CUI'}" value="Synonym"/>
      </td></tr>
   
        
        <tr>
          <td>
            Set Meta Returns Limit:
          </td>
          <td>
           <s:select size="1" name="mLimit" list="#{'100':'100', '250':'250', '500':'500', '750':'750'}" value="100"/>
          </td>
        </tr>
     </table>
     <s:submit value="Find" name="submit"/>
     <s:reset value="Reset" name="reset"/>
    </s:form>
    <% int csize = c.size();
       %>
       <% if (csize != 0) {%> 
          <%=csize%> Rows Returned
          <%}%>
          <br>
    <table  cellspacing="2" cellpadding="3" border="1" width="100%">
<tr>
    <th>
     Term
    </th>
    <th>
      Identifier
    </th>
    <th>
      Definition
    </th>
    <th>
      DefinitionSource
    </th>
    <th>
      Dictionary
    </th></tr>
  
      <% int i = 0 ;
           while (i < csize){     
        EVSBean evsConcept = (EVSBean) c.get(i);
        String jslink = "javascript:passback('"+  evsConcept.getCode()
                                               +"','"+evsConcept.getName()
                                               +"','"+evsConcept.getDefinition()
                                               +"','"+evsConcept.getSource()
                                               +"','"+evsConcept.getDictionary()
                                               +"','"+evsConcept.getType()
                                               +"')";
                                               %>
    <tr>  
    <td>
     <a href="<%=jslink%>" ><%=evsConcept.getName()%></a>
    </td>
      <td>    
      <%= evsConcept.getCode()%>
    </td>
    <td>
      <%= evsConcept.getDefinition()%>
    </td>
    <td>
      <%= evsConcept.getSource()%>
    </td>
    <td>
      <%= evsConcept.getDictionary()%>
    </td>
    </tr>
    <% i++;}%>
</table>

  </body>
</html>