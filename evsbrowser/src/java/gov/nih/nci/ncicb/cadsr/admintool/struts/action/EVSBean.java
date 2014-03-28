package gov.nih.nci.ncicb.cadsr.admintool.struts.action;

public class EVSBean 
{
  String code;
  String definition;
  String type;
  String name;
  String source;
  String dictionary;

  public EVSBean()
  { 
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getDefinition()
  {
    return definition;
  }

  public void setDefinition(String definition)
  {
    this.definition = definition;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
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

  public String getDictionary()
  {
    return dictionary;
  }

  public void setDictionary(String dictionary)
  {
    this.dictionary = dictionary;
  }
}