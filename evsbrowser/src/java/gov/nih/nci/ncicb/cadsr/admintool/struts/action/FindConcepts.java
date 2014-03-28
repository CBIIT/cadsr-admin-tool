package gov.nih.nci.ncicb.cadsr.admintool.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;

import gov.nih.nci.ncicb.cadsr.admintool.struts.action.EVSSearch;

import org.apache.struts2.util.ServletContextAware;
import org.apache.struts2.ServletActionContext;
import javax.servlet.ServletContext;

public class FindConcepts extends ActionSupport implements ServletContextAware {

	protected ServletContext servletContext;

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	public String execute() throws IOException, ServletException {
		HttpServletRequest request = ServletActionContext.getRequest();
		List concept = new ArrayList();
		String term = request.getParameter("term");
		String searchIn = request.getParameter("searchIn");
		int mLimit = Integer.parseInt(request.getParameter("mLimit"));

		try {

			String cacoreServiceURL = servletContext
					.getInitParameter("cacore-service-url");

			EVSSearch.CACORE_SERVICE_URL = cacoreServiceURL;

			concept = EVSSearch.searchEVS(term, "NCI_Thesaurus", "Include",
					searchIn, "term", "All Sources", mLimit);

			request.setAttribute("concept", concept);
			request.setAttribute("term", term);

			return "success";
		}

		catch (Exception ex) {
			System.out.println("Error");
			ex.printStackTrace();
			return "failure";
		}

	}

}