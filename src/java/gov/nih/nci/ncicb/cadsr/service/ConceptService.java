package gov.nih.nci.ncicb.cadsr.service;

import gov.nih.nci.ncicb.cadsr.dao.ConceptDAO;
import gov.nih.nci.ncicb.cadsr.domain.Concept;
import java.util.List;

public interface ConceptService  {
  public void setConceptDAO (ConceptDAO conceptDAO);
  
  public List findConcept(Concept c) throws Exception;
}