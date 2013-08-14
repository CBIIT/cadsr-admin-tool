/*L
 * Copyright Oracle inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-admin-tool/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.service.impl;
import gov.nih.nci.ncicb.cadsr.dao.ContextDAO;
import java.util.List;
import gov.nih.nci.ncicb.cadsr.service.ContextService;

public class ContextServiceImpl implements ContextService  {
  private ContextDAO contextDAO;
  public ContextServiceImpl() {
  }

  public void setContextDAO(ContextDAO contextDAO) {
    this.contextDAO = contextDAO;
  }

  public List getAllContexts() {
    return contextDAO.findAll();
  }
}