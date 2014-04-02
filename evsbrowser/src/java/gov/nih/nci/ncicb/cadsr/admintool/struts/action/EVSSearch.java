package gov.nih.nci.ncicb.cadsr.admintool.struts.action;


import gov.nih.nci.system.client.ApplicationServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.LexGrid.LexBIG.DataModel.Collections.ConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet.ActiveOption;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet.PropertyType;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.LexBIG.caCore.interfaces.LexEVSApplicationService;
import org.LexGrid.concepts.Definition;


public class EVSSearch {

	public static String CACORE_SERVICE_URL;

	public static List searchEVS(String term, String dtsVocab, String retired,
			String sSearchInEVS, String sUISearchType,
			String sMetaSource,
			int sMetaLimit) throws Exception {

		String algorithm;
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
		String sDefDefault = "No value exists.";
		String sDef="";
		String sDefSrc="";
		//List vCon = null;
		List vMetaDefs = new ArrayList();
		//List vMetaDefs = null;

		if (dtsVocab.equals("Thesaurus/Metathesaurus") ||
				dtsVocab.equals("") || dtsVocab.equals("NCI Thesaurus") ||
				dtsVocab.equals("NCI_Thesaurus")) {
			dtsVocab = "NCI Thesaurus";
			altNameType = "NCI_CONCEPT_CODE";
		}
		// for search Meta by (LOINC) code
		else if (dtsVocab.equals("Metathesaurus") ||
				sSearchInEVS.equals("Code")) {
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
		else
			altNameType = "";


		LexEVSApplicationService evsService =
			(LexEVSApplicationService)ApplicationServiceProvider.getApplicationService("EvsServiceInfo" );

		/*  if (!sSearchInEVS.equals("Concept Code") || term.equals("")) {*/
		Pattern pattern = Pattern.compile("^C\\d{2,8}");
	    Matcher matcher = pattern.matcher(term);

	    algorithm = getAlgorithm(term);
	    term = cleanTerm(term);
		int totalReturnCount = 0;

		try {
			if (retired.equals("Include")) // do this if all concepts, including retired, should be included
				isRetired = new Boolean(false);
			else {

				try {

					ResolvedConceptReferenceList rcrl = searchPrefTerm(evsService, dtsVocab, term, sMetaLimit, algorithm);
					if (rcrl != null && rcrl.getResolvedConceptReferenceCount() >0)
						isRetired = (Boolean) (!rcrl.getResolvedConceptReference(0).getEntity().isIsActive());

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}


			if (isRetired.equals(bFalse)) {
				ResolvedConceptReferenceList concepts = null;
				//CCode = term;
				int codesSize = 0;
				try {
					if (sSearchInEVS.equals("Concept Code") || matcher.matches())
						concepts = searchConceptCode(evsService, dtsVocab, term, sMetaLimit);
					else
						concepts = searchPrefTerm(evsService, dtsVocab, term, sMetaLimit, algorithm);

					codesSize = concepts.getResolvedConceptReferenceCount();

				} catch (Exception ex) {
					System.out.println("Error do_EVSSearch get concept: " +
							ex.toString());
				}

				if (concepts != null && codesSize > 0) {

					codeFoundInThesaurus = new Boolean(true);

					prefName = concepts.getResolvedConceptReference(0).getEntityDescription().getContent();

					concepts = new ResolvedConceptReferenceList();
					try {
						concepts = searchPrefTerm(evsService, dtsVocab, prefName, sMetaLimit, algorithm);
						codesSize = concepts.getResolvedConceptReferenceCount();
					} catch (Exception ex) {
						//ex.printStackTrace();
						System.out.println("Error do_EVSSearch DescLogic: " +
								ex.toString());
					}
					if (concepts != null) {
						ResolvedConceptReference rcr = new ResolvedConceptReference();
						for (int i = 0; i < codesSize; i++) {
							rcr = concepts.getResolvedConceptReference(i);
							prefName = rcr.getEntityDescription().getContent();
							CCode = rcr.getConceptCode();

							Definition[] defs = rcr.getEntity().getDefinition();
							if(defs == null)
								defs = new Definition[0];
							sDef = sDefDefault; // "No Value Exists.";
							sDefSrc = "";

							for(int k=0;k< defs.length;k++){

								Definition def = defs[k];

								if (def != null) {
									if(def.getIsPreferred() != null){
										sDef = def.getValue().getContent();
										if (def.getSourceCount() > 0)
											sDefSrc = def.getSource(0).getContent(); //get def source
									}

								}
							}

							EVSBean conBean = new EVSBean();
							conBean.setCode(CCode);
							conBean.setDefinition(sDef);
							conBean.setDictionary(dtsVocab);
							conBean.setName(prefName);
							conBean.setSource(sDefSrc);
							conBean.setType(altNameType);
							vCon.add(conBean);
							totalReturnCount++;

							if(totalReturnCount >= sMetaLimit)
								return vCon;

						}
					}
				}
			}

		} catch (Exception ex) {
			System.out.println("Error do_EVSSearch Concept code: " + ex.toString());
		}


		//Search Meta
		int length = 0;
		length = term.length();
		ResolvedConceptReferenceList concepts = null;
		if (sSearchInEVS.equals("Concept Code"))
			concepts = searchConceptCode(evsService, "NCI Metathesaurus", term, sMetaLimit);
		else if (!sSearchInEVS.equals("Concept Code"))
			concepts = searchPrefTerm(evsService, "NCI Metathesaurus", term, sMetaLimit, algorithm);

		if (concepts != null) {
				ResolvedConceptReference rcr = new ResolvedConceptReference();
				for (int i = 0; i < concepts.getResolvedConceptReferenceCount(); i++) {
					if(totalReturnCount >= sMetaLimit)
						break;
					rcr = concepts.getResolvedConceptReference(i);
					prefName = rcr.getEntityDescription().getContent();
					CCode = rcr.getConceptCode();

					Definition[] defs = rcr.getEntity().getDefinition();
					if(defs == null)
						defs = new Definition[0];
					sDef = sDefDefault; // "No Value Exists.";
					sDefSrc = "";

					for(int k=0;k< defs.length;k++){

						Definition def = defs[k];

						if (def != null) {
								sDef = def.getValue().getContent();
								if (def.getSourceCount() > 0)
									sDefSrc = def.getSource(0).getContent(); //get def source
						}

						EVSBean conBean = new EVSBean();
						conBean.setCode(CCode);
						conBean.setDefinition(sDef);
						conBean.setDictionary("NCI Metathesaurus");
						conBean.setName(prefName);
						conBean.setSource(sDefSrc);
						conBean.setType("UMLS_CUI");
						vCon.add(conBean);
						totalReturnCount++;
						if(totalReturnCount >= sMetaLimit)
							break;
					}
				}
			}

		evsService = null;
		System.out.println(vCon.size());
		return vCon;
	}

	private static ResolvedConceptReferenceList searchConceptCode(LexEVSApplicationService evsService, String dtsVocab, String CCode, int sMetaLimit) {

		ResolvedConceptReferenceList concepts = new ResolvedConceptReferenceList();

		int codesSize = 0;
		try {
			CodedNodeSet metaNodes = evsService.getNodeSet(dtsVocab, null, null);

			ConceptReferenceList crefs = ConvenienceMethods.
			createConceptReferenceList(new String[]{CCode}, dtsVocab);
			metaNodes = metaNodes.restrictToCodes(crefs);

			metaNodes.restrictToStatus(ActiveOption.ACTIVE_ONLY, null);

			concepts = metaNodes.resolveToList(
					null, //Sorts used to sort results (null means sort by match score)
					null, //PropertyNames to resolve (null resolves all)
					null,  //PropertyTypess to resolve (null resolves all)
					sMetaLimit	  //cap the number of results returned (-1 resolves all)
			);

			codesSize = concepts.getResolvedConceptReferenceCount();

		} catch (Exception ex) {
			System.out.println("Error do_EVSSearch get concept: " +
					ex.toString());
		}
		return concepts;

	}

	private static ResolvedConceptReferenceList searchPrefTerm(LexEVSApplicationService evsService, String dtsVocab, String prefName, int sMetaLimit, String algorithm) {


		ResolvedConceptReferenceList concepts = new ResolvedConceptReferenceList();
		int codesSize = 0;
		try {
			CodedNodeSet metaNodes = evsService.getNodeSet(dtsVocab, null, null);

			metaNodes = metaNodes.restrictToMatchingDesignations(prefName, //the text to match
					CodedNodeSet.SearchDesignationOption.ALL,  //whether to search all designation, only Preferred or only Non-Preferred
					algorithm, //the match algorithm to use
					null); //the language to match (null matches all)

			metaNodes = metaNodes.restrictToStatus(ActiveOption.ACTIVE_ONLY, null);

			concepts = metaNodes.resolveToList(
					null, //Sorts used to sort results (null means sort by match score)
					null, //PropertyNames to resolve (null resolves all)
					new CodedNodeSet.PropertyType[] {PropertyType.DEFINITION, PropertyType.PRESENTATION},  //PropertyTypess to resolve (null resolves all)
					sMetaLimit	  //cap the number of results returned (-1 resolves all)
			);
			codesSize = concepts.getResolvedConceptReferenceCount();
		} catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("Error do_EVSSearch DescLogic: " +
					ex.toString());
		}

		return concepts;
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
				if (iStartDefSource > 1 && iEndDefSource > 1)
					source = termStr.substring(iStartDefSource, iEndDefSource);


			}
		} catch (Exception ee) {
			System.err.println("problem in Thesaurus syn EVSSearch-getSource: " +
					ee);
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

				if (iStartDef > 1 && iEndDef > 1)
					definition = termStr.substring(iStartDef, iEndDef);
			}
		} catch (Exception ee) {
			System.err.println("problem in Thesaurus syn EVSSearch-getDefinition: " +
					ee);
			return definition;
		}
		return definition;
	}

	public static String getEVSCode(String prefName, String dtsVocab) {
		LexEVSApplicationService evsService = null;

		try {
			evsService =
				(LexEVSApplicationService)ApplicationServiceProvider.getApplicationService("EvsServiceInfo");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (dtsVocab == null)
			dtsVocab = "";
		String CCode = "";
		if (dtsVocab.equals("Thesaurus/Metathesaurus") ||
				dtsVocab.equals("") || dtsVocab.equals("NCI Thesaurus") ||
				dtsVocab.equals("NCI_Thesaurus"))
			dtsVocab = "NCI_Thesaurus";
		ResolvedConceptReferenceList codes2 = null;
		int codesSize = 0;

		try {
			CodedNodeSet metaNodes = evsService.getNodeSet("NCI MetaThesaurus", null, null);

			metaNodes = metaNodes.restrictToMatchingDesignations(prefName, //the text to match
					CodedNodeSet.SearchDesignationOption.PREFERRED_ONLY,  //whether to search all designation, only Preferred or only Non-Preferred
					"exactMatch", //the match algorithm to use
					null); //the language to match (null matches all)

			metaNodes = metaNodes.restrictToStatus(ActiveOption.ACTIVE_ONLY, null);

			codes2 = metaNodes.resolveToList(
					null, //Sorts used to sort results (null means sort by match score)
					null, //PropertyNames to resolve (null resolves all)
					new CodedNodeSet.PropertyType[] {PropertyType.DEFINITION, PropertyType.PRESENTATION},  //PropertyTypess to resolve (null resolves all)  //PropertyTypess to resolve (null resolves all)
					10	  //cap the number of results returned (-1 resolves all)
			);
			codesSize = codes2.getResolvedConceptReferenceCount();

		} catch (Exception ex) {
			System.err.println("Error do_getEVSCode:resolveToList: " + ex.toString());
			ex.printStackTrace();
		}

		if (codes2 != null) {
			ResolvedConceptReference conceptReference = new ResolvedConceptReference();
			//logger.debug("Got "+codesSize+" results for the do_getEVSCode search using prefName and exactMatch");
			for (int i = 0; i < codesSize; i++) {
				conceptReference = (ResolvedConceptReference) codes2.getResolvedConceptReference(i);
				CCode = (String) conceptReference.getConceptCode();

			}
		}

		evsService = null;
		return CCode;
	}


	public static void main(String[] args) {

		try {

			EVSSearch evsSearch = new EVSSearch();
			List<EVSBean> concept =
				(List<EVSBean>)evsSearch.searchEVS("gene*" +
						"", "NCI_Thesaurus",
						"Include", "" +
						"Synonym",
						"term", "All Sources", 100);
			for (EVSBean b: concept) {
				System.out.println("-----");

				System.out.println(b.getName());
				System.out.println(b.getDefinition());
				System.out.println(b.getType());
				System.out.println(b.getCode());
				System.out.println(b.getSource());
				System.out.println(b.getDictionary());
				System.out.println("-----");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

private static String getAlgorithm(String termStr) {

		String algorithm = "exactMatch";

		boolean starts = false;
		boolean ends = false;
		boolean contains = false;
		boolean multiple = false;
		boolean embed = false;

		termStr = termStr.trim();
		ends = termStr.startsWith("*"); // Term ends with rest of the string
		starts = termStr.endsWith("*"); // Term starts with rest of the string

		contains = termStr.substring(1,termStr.length()-1).indexOf(" *") >= 0 ||
					termStr.substring(1,termStr.length()-1).indexOf("* ") >= 0;
		if (!contains)
			embed = termStr.substring(1,termStr.length()-1).indexOf("*") >= 0;

		multiple = termStr.indexOf(' ') > 0;


		if (starts)
			algorithm = "startsWith";
		if (contains || ends)
			algorithm = "contains";
		if (multiple && starts && ends)
			algorithm = "nonLeadingWildcardLiteralSubString";
		if (multiple && starts && ends && contains)
			algorithm = "contains";

		return algorithm;
	}

	private static String cleanTerm(String termStr) {
		termStr = termStr.trim();
		termStr = termStr.replace("*","");
		return termStr;
	}

}
