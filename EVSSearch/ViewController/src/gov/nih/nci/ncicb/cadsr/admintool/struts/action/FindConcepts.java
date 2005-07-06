package gov.nih.nci.ncicb.cadsr.admintool.struts.action;

import gov.nih.nci.evs.domain.DescLogicConcept;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.evs.query.EVSQueryImpl;
import gov.nih.nci.ncicb.cadsr.admintool.struts.action.EVSBean;
import gov.nih.nci.ncicb.cadsr.admintool.struts.action.EVSSearch;
import gov.nih.nci.system.applicationservice.ApplicationService;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FindConcepts extends Action {
  public static List concept = new ArrayList();

  public ActionForward perform(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException, ServletException {
    DynaActionForm dynaForm = (DynaActionForm) form;

    String term = request.getParameter("term");
    String searchIn = request.getParameter("searchIn");
    String retired = request.getParameter("retired");
    String dictionary = request.getParameter("dictionary");
    System.out.println(term);
    try {
      String cacoreServiceURL = 
        this.getServlet().getInitParameter("cacore-service-url");
      EVSSearch.CACORE_SERVICE_URL = cacoreServiceURL;
      concept =
        EVSSearch.searchEVS(
          term, dictionary, retired, searchIn, "term", "meta", 1000);

      return mapping.findForward("success");
    }

    catch (Exception ex) {
      System.out.println("Error");
      ex.printStackTrace();

      return mapping.findForward("failure");
    }
  }
}
