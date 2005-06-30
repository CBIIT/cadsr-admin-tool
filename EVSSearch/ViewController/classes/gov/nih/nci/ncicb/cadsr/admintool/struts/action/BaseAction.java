package gov.nih.nci.ncicb.cadsr.admintool.struts.action;


import gov.nih.nci.ncicb.cadsr.service.ConceptService;
import gov.nih.nci.ncicb.cadsr.service.ContextService;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;


import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public abstract class BaseAction extends Action {
  private ConceptService conceptService;
  private ContextService contextService;

  public ActionForward execute(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws Exception {
    String user = null;
    Cookie[] cookieArray = request.getCookies();
    if (cookieArray != null) {
      for (int i = 0; i < cookieArray.length; i++) {
        Cookie c = cookieArray[i];
        if (c.getName().equals("ADMIN_TOOL_USER")) {
          user = c.getValue();
          System.out.println("Reading username from cookie :" + user);
          System.out.println("Domain: " + c.getDomain());
          System.out.println("Path: " + c.getPath());
        }
      }
    }
    if (user == null) {
      return mapping.findForward("login");
    }

    return executeAction(mapping, form, request, response);
  }

  public abstract ActionForward executeAction(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws Exception;

 

  
}
