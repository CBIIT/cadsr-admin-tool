/*L
 * Copyright Oracle inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-admin-tool/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.service;
import gov.nih.nci.ncicb.cadsr.dao.ContextDAO;
import java.util.List;

public interface ContextService  {
  public void setContextDAO(ContextDAO contextDAO);
  
  public List getAllContexts();
}