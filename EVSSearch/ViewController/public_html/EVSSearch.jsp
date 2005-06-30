<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="gov.nih.nci.ncicb.cadsr.admintool.struts.action.EVSBean" %>
<%@ page import="gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id="conceptList" class="gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts" scope="page"/>
<jsp:setProperty name="conceptList" property="*"/>

<%  List c =  new ArrayList() ; %>
<% c = conceptList.concept;%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>EVS List of Values</title>
    <link href="css/insert.css" rel="stylesheet" media="screen"/>
    <SCRIPT LANGUAGE="JavaScript">
<!--
function passback(P_CODE,P_NAME,P_DEFINITION,P_DICTIONARY,P_DEFINITION_SOURCE,P_EVS_NAME) {
   opener.document.forms[0].P_PREFERRED_NAME.value = P_CODE;
    opener.document.forms[0].P_LONG_NAME.value = P_NAME;
   opener.document.forms[0].P_PREFERRED_DEFINITION.value = P_DEFINITION;
   opener.document.forms[0].P_ORIGIN.value = P_DICTIONARY;
   opener.document.forms[0].P_DEFINITION_SOURCE.value = P_SOURCE;
   opener.document.forms[0].P_EVS_TYPE.value = P_EVS_TYPE;
   opener.document.forms[0].P_PREFERRED_NAME.focus();
   close();
}

function closeOnClick() {
    close();
}
</SCRIPT>
     </head>
  <body>
    <form action = "findAction.do">
    
    <table>
      <tr>      
       <td>
         <bean:message key="EVSSearch.Term"/>
       </td>
        <td><input type="text" name="term"/></td></tr>
        <tr>
      <td>
        <bean:message key="EVSSearch.SearchIn"/>
      </td>
        <td> <SELECT size="1" name="searchIn">
      <OPTION selected value="Synonym">Synonym</OPTION>
      <OPTION value="Concept Code">Concept Code/CUI</OPTION> 
      <OPTION value="Code">Code (Metathesaurus)</OPTION> 
   </SELECT>
</td></tr>
      <tr>
      <td>
        <bean:message key="EVSSearch.Dictionary"/>
      </td>
      <td> <select name="dictionary" size="1" >
                <option value="Thesaurus/Metathesaurus" selected>Thesaurus/Metathesaurus</option>
                <option value="GO" >GO</option>
                <option value="VA_NDFRT" >VA_NDFRT</option>
                <option value="LOINC" >LOINC</option>
                <option value="MGED_Ontology" >MGED_Ontology</option>
                <option value="MedDRA" >MedDRA</option>

        </select></td></tr>
      <tr>
      <td>
        <bean:message key="EVSSearch.DefinitionSource"/>
      </td>
      <td><input type="text" name="definitionSource"/></td></tr>
      <tr>
      <td>
        <bean:message key="EVSSearch.Retired"/>
      </td>
      <td><bean:message key="EVSSearch.Yes"/><input type="radio" name="retired" value="Include" checked/>
      <bean:message key="EVSSearch.No"/><input type="radio" value="Don't Include" name="retired"/></td></tr>
      </table>
      <input type="submit" value="Find"/>
      <input type="reset" value="Reset"/>
    </form>
  
    <table  cellspacing="2" cellpadding="3" border="1" width="100%">
<tr>
    <td>
      <bean:message key="EVSSearch.Term"/>
    </td>
    <td>
      <bean:message key="EVSSearch.Code"/>
    </td>
    <td>
      <bean:message key="EVSSearch.Definition"/>
    </td>
    <td>
      <bean:message key="EVSSearch.DefinitionSource"/>
    </td>
    <td>
      <bean:message key="EVSSearch.Dictionary"/>
    </td>
    <% int csize = c.size();
       int i = 0 ;%>
      <% while (i < csize){     
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
