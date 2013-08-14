/*L
 * Copyright Oracle inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-admin-tool/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.admintool.struts.action;

import gov.nih.nci.common.util.*;
import gov.nih.nci.evs.domain.*;
import gov.nih.nci.evs.query.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.system.applicationservice.ApplicationService;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import java.io.IOException;
import java.io.Serializable;

import java.util.*;

import javax.servlet.*;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;


public class EVSSearch {
  public static String CACORE_SERVICE_URL;
  public static List searchEVS(
    String term,
    String dtsVocab,
    String retired,
    String sSearchInEVS,
    String sUISearchType,
    String sMetaSource,
    int sMetaLimit) throws Exception {
    String altNameType;
    String CCode;
    String prefName = "";
    Boolean isRetired = new Boolean(false);
    Boolean bTrue = new Boolean(true);
    Boolean bFalse = new Boolean(false);
    boolean isMetaCodeSearch = false;
    Boolean codeFoundInThesaurus = new Boolean(false);
    String source = "";
    String definition = "";
    List vCon = new ArrayList();
    List vMetaDefs = new ArrayList();

    if (
      dtsVocab.equals("Thesaurus/Metathesaurus") || dtsVocab.equals("") ||
          dtsVocab.equals("NCI Thesaurus") || dtsVocab.equals("NCI_Thesaurus")) {
      dtsVocab = "NCI_Thesaurus";
      altNameType = "NCI_CONCEPT_CODE";
    }

    // for search Meta by (LOINC) code
    else if (dtsVocab.equals("Metathesaurus") || sSearchInEVS.equals("Code")) {
      dtsVocab = "NCI_Thesaurus";
      altNameType = "NCI_CONCEPT_CODE";
      isMetaCodeSearch = true;
      sUISearchType = "term";
    }

    //for Meta searches only (no Thes search), like in getSuperConcepts Meta
    else if (dtsVocab.equals("NCI Metathesaurus")) {
      altNameType = "UMLS_CUI";
      isMetaCodeSearch = false;
      sUISearchType = "term";
    }
    else if (dtsVocab.equals("VA NDFRT") || dtsVocab.equals("VA_NDFRT")) {
      dtsVocab = "VA_NDFRT";
      altNameType = "VA_NDF_CODE";
    }
    else if (
      dtsVocab.equals("UWD VISUAL ANATOMIST") ||
          dtsVocab.equals("UWD_VISUAL_ANATOMIST")) {
      dtsVocab = "UWD_Visual_Anatomist";
      altNameType = "UWD_VA_CODE";
    }
    else if (dtsVocab.equals("MGED") || dtsVocab.equals("MGED_Ontology")) {
      dtsVocab = "MGED_Ontology";
      altNameType = "NCI_MO_CODE";
    }
    else if (dtsVocab.equals("GO")) {
      altNameType = "GO_CODE";
    }
    else if (dtsVocab.equals("LOINC")) {
      altNameType = "LOINC_CODE";
      isMetaCodeSearch = true;
    }
    else if (dtsVocab.equals("MedDRA")) {
      altNameType = "MEDDRA_CODE";
    }
    else if (dtsVocab.equals("HL7_V3")) {
      altNameType = "HL7_V3_CODE";
    }
    else
    {
      altNameType = "";
    }

    ApplicationService evsService = 
      ApplicationService.getRemoteInstance(CACORE_SERVICE_URL);
        
        
        //"http://cabio.nci.nih.gov/cacore30/server/HTTPServer");

    //"http://cbioqatest501.nci.nih.gov:8080/cacore30/server/HTTPServer");
    // ApplicationService evsService = ApplicationService.getRemoteInstance( servletName.evsConnect);
    if (evsService == null) {
      System.out.println("service not found");
    }
    System.out.println("Correct Code2");

    if (
      sSearchInEVS.equals("Concept Code") && !term.equals("") &&
          (isMetaCodeSearch == false) && !dtsVocab.equals("NCI Metathesaurus")) {
      try {
        if (retired.equals("Include")) { // do this if all concepts, including retired, should be included
          isRetired = new Boolean(false);
        }
        else {
          EVSQuery query5 = new EVSQueryImpl();
          query5.isRetired(dtsVocab, term);
          List bool = null;
          try {
            bool = evsService.evsSearch(query5);
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
          isRetired = (Boolean) bool.get(0);
        }

        if (isRetired.equals(bFalse)) {
          EVSQuery query = new EVSQueryImpl();
          List concepts = null;
          query.getDescLogicConceptNameByCode(dtsVocab, term);
          CCode = term;
          try {
            concepts = evsService.evsSearch(query);
          }
          catch (Exception ex) {
            System.out.println("Error do_EVSSearch1: " + ex.toString());
          }
          if ((concepts != null) && (concepts.size() > 0)) {
            codeFoundInThesaurus = new Boolean(true);
            prefName = (String) concepts.get(0);
            query.searchDescLogicConcepts(dtsVocab, prefName, 1000);
            concepts = null;
            try {
              concepts = evsService.evsSearch(query);
            }
            catch (Exception ex) {
              System.out.println("Error do_EVSSearch: " + ex.toString());
            }
            if (concepts != null) {
              DescLogicConcept aDescLogicConcept = new DescLogicConcept();
              for (int i = 0; i < concepts.size(); i++) {
                aDescLogicConcept = (DescLogicConcept) concepts.get(i);
                prefName = (String) aDescLogicConcept.getName();

                if (
                  dtsVocab.substring(0, 2).equalsIgnoreCase("GO") ||
                      dtsVocab.substring(0, 3).equalsIgnoreCase("NCI") ||
                      dtsVocab.substring(0, 3).equalsIgnoreCase("MGE") ||
                      dtsVocab.substring(0, 3).equalsIgnoreCase("Med")) // both have Preferred_Name property
                 {
                  EVSQuery query4 = new EVSQueryImpl();
                  query4.getPropertyValues(
                    dtsVocab, prefName, "Preferred_Name");
                  List concepts4 = null;
                  try {
                    concepts4 = evsService.evsSearch(query4);
                  }
                  catch (Exception ex) {
                    System.out.println("Error do_EVSSearch: " + ex.toString());
                  }
                  if ((concepts4 != null) && (concepts4.size() > 0)) {
                    String results = "";
                    for (int m = 0; m < 1; m++) {
                      prefName = concepts4.get(0).toString();
                    }
                  }
                }
                EVSQuery query3 = new EVSQueryImpl();
                query3.getPropertyValues(dtsVocab, prefName, "DEFINITION");
                List concepts3 = null;
                try {
                  concepts3 = evsService.evsSearch(query3);
                }
                catch (Exception ex) {
                  System.out.println("Error do_EVSSearch: " + ex.toString());
                }
                if ((concepts3 != null) && (concepts3.size() > 0)) {
                  String results = "";
                  for (int m = 0; m < concepts3.size(); m++) {
                    results = concepts3.get(m).toString();
                    if (dtsVocab.equals("NCI_Thesaurus")) {
                      definition = getDefinition(results);
                      source = getSource(results);
                    }
                    else if (
                      dtsVocab.equals("GO") ||
                          dtsVocab.equals("MGED_Ontology")) {
                      definition = results;
                    }
                    EVSBean conBean =
                      new EVSBean(
                        CCode, prefName, definition, source, altNameType,
                        dtsVocab);

                    vCon.add(conBean);
                  }
                }
                else // no definitions
                 {
                  EVSBean conBean =
                    new EVSBean(
                      CCode, prefName, "No value exists.", "", altNameType,
                      dtsVocab);
                  vCon.add(conBean); //add bean to vector
                }
              }
            }
          }
        }
      }
      catch (Exception ex) {
        System.out.println("Error do_EVSSearch: " + ex.toString());
      }
    }
    else if (
      !term.equals("") && (isMetaCodeSearch == false) &&
          !dtsVocab.equals("NCI Metathesaurus")) // Synonym search
     {
      System.out.println("Search in synonym");
      try {
        if (retired.equals("Include")) { // do this if all concepts, including retired, should be included
          isRetired = new Boolean(false);
        }
        else {
          EVSQuery query5 = new EVSQueryImpl();
          query5.isRetired(dtsVocab, term);
          List bool = null;
          try {
            bool = evsService.evsSearch(query5);
            if (bool != null) {
              System.out.println("bool returned");
            }
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
          isRetired = (Boolean) bool.get(0);
        }

        if (isRetired.equals(bFalse)) {
          EVSQuery query = new EVSQueryImpl();
          query.getConceptWithPropertyMatching(
            dtsVocab, sSearchInEVS, term, 10000);
          List concepts = null;

          try {
            if (query != null) {
              concepts = evsService.evsSearch(query);
            }
            if (concepts == null) {
              System.out.println("No concepts Returned");
            }
            else
            {
              System.out.println("Concepts returned");
            }
          }
          catch (Exception ex) {
            System.out.println("Error do_EVSSearch: " + ex.toString());
          }

          System.out.println(concepts.size());
          if (concepts != null) {
            List bool2 = null;
            EVSQuery query5 = new EVSQueryImpl();
            for (int i = 0; i < concepts.size(); i++) {
              prefName = concepts.get(i).toString();
              CCode = getEVSCode(prefName, dtsVocab);
              query5.isRetired(dtsVocab, CCode);
              try {
                bool2 = evsService.evsSearch(query5);
                if (bool2 == null) {
                  System.out.println("No bool2 Returned");
                }
                else
                {
                  System.out.println("Bool2 returned");
                }
              }
              catch (Exception ex) {
                System.out.println("Error do_EVSSearch: " + ex.toString());
              }
              isRetired = (Boolean) bool2.get(0);
              if (retired.equals("Include")) { // do this if all concepts, including retired, should be included
                isRetired = new Boolean(false);
              }
              if (isRetired.equals(bFalse)) {
                if (
                  dtsVocab.substring(0, 2).equalsIgnoreCase("GO") ||
                      dtsVocab.substring(0, 3).equalsIgnoreCase("NCI") ||
                      dtsVocab.substring(0, 3).equalsIgnoreCase("MGE") ||
                      dtsVocab.substring(0, 3).equalsIgnoreCase("Med")) // both have Preferred_Name property
                 {
                  EVSQuery query4 = new EVSQueryImpl();
                  query4.getPropertyValues(
                    dtsVocab, prefName, "Preferred_Name");
                  List concepts4 = null;
                  try {
                    concepts4 = evsService.evsSearch(query4);
                    if (concepts == null) {
                      System.out.println("No concepts4 Returned");
                    }
                    else
                    {
                      System.out.println("Concepts4 returned");
                    }
                  }
                  catch (Exception ex) {
                    System.out.println("Error do_EVSSearch: " + ex.toString());
                  }
                  if ((concepts4 != null) && (concepts4.size() > 0)) {
                    String results = "";
                    for (int m = 0; m < 1; m++) {
                      prefName = concepts4.get(0).toString();
                    }
                  }
                }

                EVSQuery query3 = new EVSQueryImpl();
                query3.getPropertyValues(dtsVocab, prefName, "DEFINITION");
                List concepts3 = null;
                try {
                  concepts3 = evsService.evsSearch(query3);
                }
                catch (Exception ex) {
                  System.out.println("Error do_EVSSearch: " + ex.toString());
                }

                if ((concepts3 != null) && (concepts3.size() > 0)) {
                  String results = "";
                  for (int m = 0; m < concepts3.size(); m++) {
                    results = concepts3.get(m).toString();
                    if (dtsVocab.equals("NCI_Thesaurus")) {
                      definition = getDefinition(results);
                      source = getSource(results);
                    }
                    else if (
                      dtsVocab.equals("GO") ||
                          dtsVocab.equals("MGED_Ontology")) {
                      definition = results;
                    }

                    System.out.println(definition + prefName + "definition");
                    EVSBean conBean =
                      new EVSBean(
                        CCode, prefName, definition, source, altNameType,
                        dtsVocab);
                    vCon.add(conBean); //add OC bean to vector
                  }
                }
                else // no definitions
                 {
                  EVSBean conBean =
                    new EVSBean(
                      CCode, prefName, "No value exists.", "", altNameType,
                      dtsVocab);
                  vCon.add(conBean); //add bean to vector
                }
                System.out.println("new Code");
              }
            }
          }
          else
          {
            System.out.println("do_EVSSearch term search concepts is null.");
          }
        }
      }
      catch (Exception ee) {
        System.err.println(
          "problem in Thesaurus syn EVSSearch-do_EVSSearch: " + ee);
      }
    }
    System.out.println(codeFoundInThesaurus + dtsVocab + term + sUISearchType);
    if (
      (dtsVocab.equals("NCI_Thesaurus") //Search Meta
           ||dtsVocab.equals("NCI Metathesaurus")) &&
          sUISearchType.equals("term") && !term.equals("") &&
          codeFoundInThesaurus.equals(bFalse)) {
      int length = 0;
      length = term.length();
      EVSQuery query = new EVSQueryImpl();
      List concepts = null;
      if (isMetaCodeSearch == true) {
        query.searchByLoincId(term, sMetaSource);
      }
      else if (sSearchInEVS.equals("Concept Code")) {
        query.searchMetaThesaurus(
          term, sMetaLimit, sMetaSource, true, false, false);
      }
      else if (!sSearchInEVS.equals("Concept Code")) {
        query.searchMetaThesaurus(
          term, sMetaLimit, sMetaSource, false, false, false);
      }
      try {
        concepts = evsService.evsSearch(query);
      }
      catch (Exception ex) {
        System.out.println("Error do_EVSSearch Meta: " + ex.toString());
      }

      if (concepts != null) {
        MetaThesaurusConcept aMetaThesaurusConcept = new MetaThesaurusConcept();
        for (int i = 0; i < concepts.size(); i++) {
          // Do this so only one result is returned on Meta code search (API is dupicating a result)
          if ((isMetaCodeSearch == true) && (i > 0)) {
            break;
          }
          aMetaThesaurusConcept = (MetaThesaurusConcept) concepts.get(i);
          prefName = (String) aMetaThesaurusConcept.getName();
          CCode = (String) aMetaThesaurusConcept.getCui();

          vMetaDefs = aMetaThesaurusConcept.getDefinitionCollection();
          Definition mDefinition = null;
          if ((vMetaDefs != null) && (vMetaDefs.size() > 0)) {
            for (int j = 0; j < vMetaDefs.size(); j++) {
              mDefinition = (Definition) vMetaDefs.get(j);
              definition = mDefinition.getDefinition();
              source = mDefinition.getSource().getAbbreviation();
              EVSBean conBean =
                new EVSBean(
                  CCode, prefName, definition, source, altNameType, dtsVocab);

              vCon.add(conBean); //add OC bean to vector
            }
          }
          else // no definitions
           {
            EVSBean conBean =
              new EVSBean(
                CCode, prefName, "No value exists.", "", altNameType, dtsVocab);
            vCon.add(conBean);
          }
        }
      }
    }
    evsService = null;
    System.out.println(vCon.size());

    return vCon;
  }

  public static String getSource(String termStr) {
    String source = "";

    try {
      int length = 0; //<def-source>,  <def-definition>
      length = termStr.length();
      System.out.println(termStr);
      int iStartDefSource = 0;
      int iEndDefSource = 0;

      if (length > 0) {
        iStartDefSource = termStr.lastIndexOf("<def-source>");
        iStartDefSource = iStartDefSource + ("<def-source>").length();
        iEndDefSource = termStr.indexOf("</def-source>");
        if ((iStartDefSource > 1) && (iEndDefSource > 1)) {
          source = termStr.substring(iStartDefSource, iEndDefSource);
        }
      }
    }
    catch (Exception ee) {
      System.err.println("problem in Thesaurus syn EVSSearch-getSource: " + ee);

      //logger.fatal("ERROR - EVSSearch-getSource for Thesaurus : " + ee.toString());
      return source;
    }

    return source;
  }

  public static String getDefinition(String termStr) {
    String definition = "";
    try {
      int length = 0; //<def-source>,  <def-definition>
      length = termStr.length();
      int iStartDef = 0;
      int iEndDef = 0;

      if (length > 0) {
        iStartDef = termStr.lastIndexOf("<def-definition>");
        iStartDef = iStartDef + ("<def-definition>").length();
        iEndDef = termStr.indexOf("</def-definition>");

        if ((iStartDef > 1) && (iEndDef > 1)) {
          definition = termStr.substring(iStartDef, iEndDef);
        }
      }
    }
    catch (Exception ee) {
      System.err.println(
        "problem in Thesaurus syn EVSSearch-getDefinition: " + ee);

      //logger.fatal("ERROR - EVSSearch-getDefinition for Thesaurus : " + ee.toString());
      return definition;
    }

    return definition;
  }

  public static String getEVSCode(
    String prefName,
    String dtsVocab) {
    ApplicationService evsService =
      ApplicationService.getRemoteInstance(CACORE_SERVICE_URL);

    //"http://cbioqatest501.nci.nih.gov:8080/cacore30/server/HTTPServer");
    if (dtsVocab == null) {
      dtsVocab = "";
    }
    String CCode = "";
    if (
      dtsVocab.equals("Thesaurus/Metathesaurus") || dtsVocab.equals("") ||
          dtsVocab.equals("NCI Thesaurus") || dtsVocab.equals("NCI_Thesaurus")) {
      dtsVocab = "NCI_Thesaurus";
    }
    else if (dtsVocab.equals("VA NDFRT") || dtsVocab.equals("VA_NDFRT")) {
      prefName = filterName(prefName, "display");
      dtsVocab = "VA_NDFRT";
    }
    else if (
      dtsVocab.equals("UWD VISUAL ANATOMIST") ||
          dtsVocab.equals("UWD_VISUAL_ANATOMIST")) {
      dtsVocab = "UWD_Visual_Anatomist";
    }
    else if (dtsVocab.equals("MGED") || dtsVocab.equals("MGED_Ontology")) {
      dtsVocab = "MGED_Ontology";
    }

    else if (dtsVocab.equals("LOINC")) {
      prefName = filterName(prefName, "display");
    }
    else if (dtsVocab.equals("MedDRA")) {
      prefName = filterName(prefName, "display");
    }

    if (!dtsVocab.equals("NCI Metathesaurus")) {
      EVSQuery codequery = new EVSQueryImpl();
      codequery.getConceptCodeByName(dtsVocab, prefName);
      List codes = null;
      try {
        codes = evsService.evsSearch(codequery);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
      if (codes.size() > 0) {
        CCode = (String) codes.get(0);
      }

      // hardcode to fix a bug in api
      if (
        dtsVocab.equals("GO") &&
            (prefName.equals(
              "double-strand break repair via homologous recombination ") ||
            prefName.equals(
              "double-strand break repair via homologous recombination"))) {
        CCode = "GO:0000724";
      }
    }
    else if (dtsVocab.equals("NCI Metathesaurus")) {
      EVSQuery codequery2 = new EVSQueryImpl();
      List codes2 = null;
      codequery2.searchMetaThesaurus(prefName, 100, "", false, false, false);
      try {
        codes2 = evsService.evsSearch(codequery2);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
      if (codes2 != null) {
        MetaThesaurusConcept aMetaThesaurusConcept = new MetaThesaurusConcept();
        for (int i = 0; i < codes2.size(); i++) {
          aMetaThesaurusConcept = (MetaThesaurusConcept) codes2.get(i);
          CCode = (String) aMetaThesaurusConcept.getCui();
        }
      }
    }
    evsService = null;

    return CCode;
  }

  private static String filterName(
    String nodeName,
    String type) {
    if (type.equals("display")) {
      nodeName = nodeName.replaceAll("_", " ");
    }
    else if (type.equals("js")) {
      nodeName = nodeName.replaceAll(" ", "_");
    }

    return nodeName;
  }

  /*  public static void main(String[] args) {
     System.out.println("here");
         get_vocab_name();
            }
  
     public static void get_vocab_name()
     {
       List vocab = new ArrayList();
  
       ApplicationService evsService =
         ApplicationService.getRemoteInstance("http://cbioqatest501.nci.nih.gov:8080/cacore30/server/HTTPServer");
       EVSQuery query1 = new EVSQueryImpl();
       query1.getVocabularyNames();
       try{
       vocab = evsService.evsSearch(query1);
       System.out.println(vocab.size());
       for (int i=0;i==vocab.size()-1;i++)
        System.out.println(vocab.get(i).toString());
     }
     catch(Exception ee){}
     }*/
}
