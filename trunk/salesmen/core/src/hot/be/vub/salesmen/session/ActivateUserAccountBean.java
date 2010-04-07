package be.vub.salesmen.session;

import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.faces.FacesMessages;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import be.vub.salesmen.entity.UserAccount;

@Scope(ScopeType.SESSION)
@Name("activateUserAccount")
public class ActivateUserAccountBean implements ActivateUserAccount, Serializable
{

  @RequestParameter
  private String username;

  @RequestParameter
  private Long key;

  private boolean activated = false;

  @In EntityManager entityManager;
  @In EmailService emailService;
  @In BasicSearch basicSearch;

  public void verifyActivationKey(){
    if(username==null || key==null) return;
    UserAccount result  = basicSearch.findUserAccount(username);
		if (result != null && result.getActivationKey() == key)
    {
      result.setEnabled(true);
      emailService.sendConfirmAccountEmail(result);
      activated = true;
    }
    else
    {
      activated = false;
    }
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getKey() {
    return key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public boolean isActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }



}
