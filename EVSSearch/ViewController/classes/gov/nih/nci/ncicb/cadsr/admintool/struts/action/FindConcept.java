/*L
 * Copyright Oracle inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-admin-tool/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.admintool.struts.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
public class returnSuccess 
{
  public returnSuccess()
  {
  }
  
  protected ActionForward dispatchMethod(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response,
    String name) throws Exception {
  
      return mapping.findForward("success");
    }
}