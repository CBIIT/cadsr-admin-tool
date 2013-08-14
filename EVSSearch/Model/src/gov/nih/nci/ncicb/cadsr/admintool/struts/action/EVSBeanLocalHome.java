/*L
 * Copyright Oracle inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-admin-tool/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.admintool.struts.action;
import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.util.Collection;

public interface EVSBeanLocalHome extends EJBLocalHome 
{
  EVSBeanLocal create() throws CreateException;

  EVSBeanLocal findByPrimaryKey(Long primaryKey) throws FinderException;

  Collection findAll() throws FinderException;
}