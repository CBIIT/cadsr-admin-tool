/*L
 * Copyright Oracle inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-admin-tool/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.admintool.struts.action;

import gov.nih.nci.ncicb.cadsr.domain.Context;
import gov.nih.nci.ncicb.cadsr.service.ConceptService;
import gov.nih.nci.ncicb.cadsr.service.ContextService;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import org.apache.struts.actions.DispatchAction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BaseDispatchAction extends DispatchAction {
  private WebApplicationContext webAppContext;
  private ConceptService conceptService;
  private ContextService contextService;
  
  protected ActionForward dispatchMethod(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response,
    String name) throws Exception {
    
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
    return super.dispatchMethod(mapping, form, request, response, name);
  }

  public void setServlet(ActionServlet servlet) {
    super.setServlet(servlet);
    ServletContext servletContext = servlet.getServletContext();
    WebApplicationContext wac =
      WebApplicationContextUtils.getRequiredWebApplicationContext(
        servletContext);
    conceptService = (ConceptService) wac.getBean("conceptService");
    contextService = (ContextService) wac.getBean("contextService");
    this.printAllContexts();
  }
  
  protected void setWebAppContext(WebApplicationContext webAppContext) {
    this.webAppContext = webAppContext;
  }

  protected WebApplicationContext getWebAppContext() {
    return webAppContext;
  }

  protected ConceptService getConceptService() {
    return conceptService;
  }

  protected ContextService getContextService() {
    return contextService;
  }

  private void printAllContexts() {
    List contexts = this.contextService.getAllContexts();
    for (Iterator it = contexts.iterator(); it.hasNext();) {
      Context c = (Context) it.next();
      System.out.println("Context name is: " + c.getName());
    }
  }
}