package gov.nih.nci.ncicb.cadsr.admintool.struts.action;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

public abstract class EVSBeanBean implements EntityBean 
{
  private EntityContext context;

  public Long ejbCreate()
  {
    return null;
  }

  public void ejbPostCreate()
  {
  }

  public void ejbActivate()
  {
  }

  public void ejbLoad()
  {
  }

  public void ejbPassivate()
  {
  }

  public void ejbRemove()
  {
  }

  public void ejbStore()
  {
  }

  public void setEntityContext(EntityContext ctx)
  {
    this.context = ctx;
  }

  public void unsetEntityContext()
  {
    this.context = null;
  }

  public abstract String getPreferredName();

  public abstract void setPreferredName(String preferredName);

  public abstract String getDefinition();

  public abstract void setDefinition(String definition);

  public abstract String getSource();

  public abstract void setSource(String source);

  public abstract String getName();

  public abstract void setName(String name);

  public abstract String getNameType();

  public abstract void setNameType(String nameType);

  public abstract String getVocabulary();

  public abstract void setVocabulary(String vocabulary);
}