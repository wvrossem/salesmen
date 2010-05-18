package be.vub.salesmen.session;

import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.annotations.*;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static org.jboss.seam.ScopeType.SESSION;

@Name("userIdentity")
@Scope(SESSION)
public class UserIdentityBean implements UserIdentity, Serializable {

  private static final long serialVersionUID = -5946804403467846878L;
  private static final int numberOfAttemptsAllowed = 3;

  @In EntityManager entityManager;
  @In Identity identity;
  @In Search search;
  @In Credentials credentials;

  private loginState loginResult;

  public void login()
  {
    UserAccount result  = search.findUserAccount(credentials.getUsername());
    identity.login();
    if(identity.isLoggedIn())
    {
      // Login OK
      loginResult = loginState.ok;
      result.setLoginAttempts(0);
    }
    else
    {
		  if (result != null)
      {
        // Account exists
        int attempts = result.getLoginAttempts();
        // Account already banned
        if(attempts == numberOfAttemptsAllowed)
        {
          loginResult = loginState.banned;
          return;
        }
        attempts = attempts + 1;
        result.setLoginAttempts(attempts);
        if(attempts == numberOfAttemptsAllowed){
          loginResult = loginState.banned;
          result.setEnabled(false);
        }
      }
      else
        loginResult = null;
    }
  }

  public boolean isLoggedIn()
  {
    if(loginResult == loginState.ok)
    {
      return true;
    }
    return false;
  }

  public boolean isBanned()
  {
    if(loginResult == loginState.banned)
    {
      return true;
    }
    return false;
  }



}
