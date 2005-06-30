package gov.nih.nci.ncicb.cadsr.admintool.struts.action;
import javax.ejb.EJBLocalObject;

public interface EVSBeanLocal extends EJBLocalObject 
{
  String getPreferredName();

  void setPreferredName(String preferredName);

  String getDefinition();

  void setDefinition(String definition);

  String getSource();

  void setSource(String source);

  String getName();

  void setName(String name);

  String getNameType();

  void setNameType(String nameType);

  String getVocabulary();

  void setVocabulary(String vocabulary);
}