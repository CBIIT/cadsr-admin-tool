/*L
 * Copyright Oracle inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-admin-tool/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.admintool.struts.action;

public class EVSBean 
{
  String preferredName;
  String definition;
  String altNameType;
  String name;
  String source;

  public EVSBean(String sPreferredName,String sDefinition, String sAltNameType, String sName, String sSource)
  { this.setPreferredName(sPreferredName);
    this.setDefinition(sDefinition);
    this.setAltNameType(sAltNameType);
    this.setName(sName);
    this.setSource(sSource);
  }

  public String getPreferredName()
  {
    return preferredName;
  }

  public void setPreferredName(String preferredName)
  {
    this.preferredName = preferredName;
  }

  public String getDefinition()
  {
    return definition;
  }

  public void setDefinition(String definition)
  {
    this.definition = definition;
  }

  public String getAltNameType()
  {
    return altNameType;
  }

  public void setAltNameType(String altNameType)
  {
    this.altNameType = altNameType;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getSource()
  {
    return source;
  }

  public void setSource(String source)
  {
    this.source = source;
  }
}