package gov.nih.nci.ncicb.cadsr.service;
import gov.nih.nci.ncicb.cadsr.dao.ContextDAO;
import java.util.List;

public interface ContextService  {
  public void setContextDAO(ContextDAO contextDAO);
  
  public List getAllContexts();
}