package gov.nih.nci.ncicb.cadsr.service.impl;

import gov.nih.nci.ncicb.cadsr.dao.ConceptDAO;
import gov.nih.nci.ncicb.cadsr.domain.Concept;
import gov.nih.nci.ncicb.cadsr.service.ConceptService;

import java.util.List;


public class ConceptServiceImpl implements ConceptService {
  private ConceptDAO conceptDAO;

  public ConceptServiceImpl() {
  }

  public void setConceptDAO(ConceptDAO conceptDAO) {
  }

  public List findConcept(Concept c) throws Exception {
    return conceptDAO.find(c);
  }
}
