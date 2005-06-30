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