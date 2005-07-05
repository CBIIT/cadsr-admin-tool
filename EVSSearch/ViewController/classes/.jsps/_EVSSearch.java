  /*@lineinfo:filename=/EVSSearch.jsp*/
  /*@lineinfo:generated-code*/

import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import gov.nih.nci.ncicb.cadsr.admintool.struts.action.EVSBean;
import gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts;
import java.util.List;
import java.util.ArrayList;


public class _EVSSearch extends oracle.jsp.runtime.HttpJsp {

  public final String _globalsClassName = null;

  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    if (pageContext.getAttribute(OracleJspRuntime.JSP_REQUEST_REDIRECTED, PageContext.REQUEST_SCOPE) != null) {
      pageContext.setAttribute(OracleJspRuntime.JSP_PAGE_DONTNOTIFY, "true", PageContext.PAGE_SCOPE);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
      return;
}
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _EVSSearch page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {
      // global beans
      // end global beans


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      out.write(__oracle_jsp_text[3]);
      out.write(__oracle_jsp_text[4]);
      out.write(__oracle_jsp_text[5]);
      out.write(__oracle_jsp_text[6]);
      out.write(__oracle_jsp_text[7]);
      /*@lineinfo:translated-code*//*@lineinfo:9^1*/      gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts conceptList;
      if ((conceptList = (gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts) pageContext.getAttribute( "conceptList", PageContext.PAGE_SCOPE)) == null) {
        conceptList = (gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts) new gov.nih.nci.ncicb.cadsr.admintool.struts.action.FindConcepts();
        pageContext.setAttribute( "conceptList", conceptList, PageContext.PAGE_SCOPE);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[8]);
      /*@lineinfo:translated-code*//*@lineinfo:10^1*/      OracleJspRuntime.__jspSetAllParams(request, (Object)conceptList);
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[9]);
      /*@lineinfo:user-code*//*@lineinfo:13^1*/        List c =  new ArrayList() ; 
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[10]);
      /*@lineinfo:user-code*//*@lineinfo:14^1*/       c = conceptList.concept;
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[11]);
      /*@lineinfo:translated-code*//*@lineinfo:46^10*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_1=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_1.setParent(null);
        __jsp_taghandler_1.setKey("EVSSearch.Term");
        __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_1.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[12]);
      /*@lineinfo:translated-code*//*@lineinfo:51^9*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_2=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_2.setParent(null);
        __jsp_taghandler_2.setKey("EVSSearch.SearchIn");
        __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_2.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[13]);
      /*@lineinfo:translated-code*//*@lineinfo:61^9*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_3=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_3.setParent(null);
        __jsp_taghandler_3.setKey("EVSSearch.Dictionary");
        __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_3.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[14]);
      /*@lineinfo:translated-code*//*@lineinfo:74^9*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_4=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_4.setParent(null);
        __jsp_taghandler_4.setKey("EVSSearch.Retired");
        __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_4.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[15]);
      /*@lineinfo:translated-code*//*@lineinfo:76^11*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_5=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_5.setParent(null);
        __jsp_taghandler_5.setKey("EVSSearch.Yes");
        __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_5.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[16]);
      /*@lineinfo:translated-code*//*@lineinfo:77^7*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_6=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_6.setParent(null);
        __jsp_taghandler_6.setKey("EVSSearch.No");
        __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_6.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[17]);
      /*@lineinfo:user-code*//*@lineinfo:82^4*/       int csize = c.size();
             int i = 0 ;
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[18]);
      /*@lineinfo:user-code*//*@lineinfo:84^7*/       while (i < csize){     
              EVSBean evsConcept = (EVSBean) c.get(i);
              String jslink = "javascript:passback('"+  evsConcept.getCode()
                                                     +"','"+evsConcept.getName()
                                                     +"','"+evsConcept.getDefinition()
                                                     +"','"+evsConcept.getSource()
                                                     +"','"+evsConcept.getDictionary()
                                                     +"','"+evsConcept.getType()
                                                     +"')";
                                                     
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[19]);
      /*@lineinfo:translated-code*//*@lineinfo:97^7*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_7=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_7.setParent(null);
        __jsp_taghandler_7.setKey("EVSSearch.Term");
        __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_7.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[20]);
      /*@lineinfo:translated-code*//*@lineinfo:100^7*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_8=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_8.setParent(null);
        __jsp_taghandler_8.setKey("EVSSearch.Code");
        __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_8.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[21]);
      /*@lineinfo:translated-code*//*@lineinfo:103^7*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_9=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_9.setParent(null);
        __jsp_taghandler_9.setKey("EVSSearch.Definition");
        __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_9.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[22]);
      /*@lineinfo:translated-code*//*@lineinfo:106^7*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_10=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_10.setParent(null);
        __jsp_taghandler_10.setKey("EVSSearch.DefinitionSource");
        __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_10.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[23]);
      /*@lineinfo:translated-code*//*@lineinfo:109^7*/      {
        org.apache.struts.taglib.bean.MessageTag __jsp_taghandler_11=(org.apache.struts.taglib.bean.MessageTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.struts.taglib.bean.MessageTag.class,"org.apache.struts.taglib.bean.MessageTag key");
        __jsp_taghandler_11.setParent(null);
        __jsp_taghandler_11.setKey("EVSSearch.Dictionary");
        __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
        if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
        {
          do {
          } while (__jsp_taghandler_11.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
        }
        if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11);
      }
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[24]);
      /*@lineinfo:user-code*//*@lineinfo:114^15*/      out.print(jslink);
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[25]);
      /*@lineinfo:user-code*//*@lineinfo:114^29*/      out.print(evsConcept.getName());
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[26]);
      /*@lineinfo:user-code*//*@lineinfo:117^7*/      out.print( evsConcept.getCode());
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[27]);
      /*@lineinfo:user-code*//*@lineinfo:120^7*/      out.print( evsConcept.getDefinition());
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[28]);
      /*@lineinfo:user-code*//*@lineinfo:123^7*/      out.print( evsConcept.getSource());
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[29]);
      /*@lineinfo:user-code*//*@lineinfo:126^7*/      out.print( evsConcept.getDictionary());
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[30]);
      /*@lineinfo:user-code*//*@lineinfo:129^5*/       i++;}
      /*@lineinfo:generated-code*/
      out.write(__oracle_jsp_text[31]);


    }
    catch( Throwable e) {
      try {
        if (out != null) out.clear();
      }
      catch( Exception clearException) {
      }
      pageContext.handlePageException( e);
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext,true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[32][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\n".toCharArray();
    __oracle_jsp_text[3] = 
    "\n".toCharArray();
    __oracle_jsp_text[4] = 
    "\n".toCharArray();
    __oracle_jsp_text[5] = 
    "\n".toCharArray();
    __oracle_jsp_text[6] = 
    "\n".toCharArray();
    __oracle_jsp_text[7] = 
    "\n".toCharArray();
    __oracle_jsp_text[8] = 
    "\n".toCharArray();
    __oracle_jsp_text[9] = 
    "\n\n\n".toCharArray();
    __oracle_jsp_text[10] = 
    "\n".toCharArray();
    __oracle_jsp_text[11] = 
    "\n\n<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">\n    <title>EVS List of Values</title>\n    <link href=\"css/insert.css\" rel=\"stylesheet\" media=\"screen\"/>\n    <SCRIPT LANGUAGE=\"JavaScript\">\n<!--\nfunction passback(P_CODE,P_NAME,P_DEFINITION,P_DICTIONARY,P_DEFINITION_SOURCE,P_EVS_NAME) {\n   opener.document.forms[0].P_PREFERRED_NAME.value = P_CODE;\n    opener.document.forms[0].P_LONG_NAME.value = P_NAME;\n   opener.document.forms[0].P_PREFERRED_DEFINITION.value = P_DEFINITION;\n   opener.document.forms[0].P_ORIGIN.value = P_DICTIONARY;\n   opener.document.forms[0].P_DEFINITION_SOURCE.value = P_SOURCE;\n   opener.document.forms[0].P_EVS_TYPE.value = P_EVS_TYPE;\n   opener.document.forms[0].P_PREFERRED_NAME.focus();\n   close();\n}\n\nfunction closeOnClick() {\n    close();\n}\n</SCRIPT>\n     </head>\n  <body>\n  <a href=\"http://cbioqatest501.nci.nih.gov:8080/cacore30/server/HTTPServer\">EVS</a>\n    <form action = \"findAction.do\">\n    \n    <table>\n      <tr>      \n       <td>\n         ".toCharArray();
    __oracle_jsp_text[12] = 
    "\n       </td>\n        <td><input type=\"text\" name=\"term\"/></td></tr>\n        <tr>\n      <td>\n        ".toCharArray();
    __oracle_jsp_text[13] = 
    "\n      </td>\n        <td> <SELECT size=\"1\" name=\"searchIn\">\n      <OPTION selected value=\"Synonym\">Synonym</OPTION>\n      <OPTION value=\"Concept Code\">Concept Code/CUI</OPTION> \n      <OPTION value=\"Code\">Code (Metathesaurus)</OPTION> \n   </SELECT>\n</td></tr>\n      <tr>\n      <td>\n        ".toCharArray();
    __oracle_jsp_text[14] = 
    "\n      </td>\n      <td> <select name=\"dictionary\" size=\"1\" >\n                <option value=\"Thesaurus/Metathesaurus\" selected>Thesaurus/Metathesaurus</option>\n                <option value=\"GO\" >GO</option>\n                <option value=\"VA_NDFRT\" >VA_NDFRT</option>\n                <option value=\"LOINC\" >LOINC</option>\n                <option value=\"MGED_Ontology\" >MGED_Ontology</option>\n                <option value=\"MedDRA\" >MedDRA</option>\n\n        </select></td></tr>\n      <tr>\n      <td>\n        ".toCharArray();
    __oracle_jsp_text[15] = 
    "\n      </td>\n      <td>".toCharArray();
    __oracle_jsp_text[16] = 
    "<input type=\"radio\" name=\"retired\" value=\"Include\" checked/>\n      ".toCharArray();
    __oracle_jsp_text[17] = 
    "<input type=\"radio\" value=\"Don't Include\" name=\"retired\"/></td></tr>\n      </table>\n      <input type=\"submit\" value=\"Find\"/>\n      <input type=\"reset\" value=\"Reset\"/>\n    </form>\n   ".toCharArray();
    __oracle_jsp_text[18] = 
    "\n      ".toCharArray();
    __oracle_jsp_text[19] = 
    "\n    <table  cellspacing=\"2\" cellpadding=\"3\" border=\"1\" width=\"100%\">\n<tr>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[20] = 
    "\n    </td>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[21] = 
    "\n    </td>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[22] = 
    "\n    </td>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[23] = 
    "\n    </td>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[24] = 
    "\n    </td>\n   \n    <tr>  \n    <td>\n     <a href=\"".toCharArray();
    __oracle_jsp_text[25] = 
    "\" >".toCharArray();
    __oracle_jsp_text[26] = 
    "</a>\n    </td>\n      <td>    \n      ".toCharArray();
    __oracle_jsp_text[27] = 
    "\n    </td>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[28] = 
    "\n    </td>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[29] = 
    "\n    </td>\n    <td>\n      ".toCharArray();
    __oracle_jsp_text[30] = 
    "\n    </td>\n    </tr>\n    ".toCharArray();
    __oracle_jsp_text[31] = 
    "\n</table>\n\n  </body>\n</html>\n".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
