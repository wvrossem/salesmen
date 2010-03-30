package be.vub.salesmen.session;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;

import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.*;
import be.vub.salesmen.entity.User;
import org.jboss.seam.faces.Renderer;

@Name("emailService")
@AutoCreate
public class EmailService implements Serializable
{
	private static final long serialVersionUID = -8132989898020136666L;

  //Private Attributes
  private UserAccount account;
	private String password;

	@Logger
	private Log log;

	//@in annotations
	@In(create=true)
	private Renderer renderer;

	@Asynchronous
	public void sendConfirmAccountEmail(UserAccount account)
	{
		this.account = account;
		try
		{
			renderer.render("/confirmAccountEmail.xhtml");
		}
		catch (Exception e)
		{
			log.error("Error sending mail", e);
		}
	}

  @Asynchronous
  public void sendPassword(UserAccount account, String password)
	{
    this.account = account;
		setPassword(password);
		try
		{
			renderer.render("/passwordEmail.xhtml");
		}
		catch (Exception e)
		{
			log.error("Error sending mail", e);
		}
	}

  @Asynchronous
	public void sendActivateAccountEmail(UserAccount account)
	{
		this.account = account;
		try
		{
			renderer.render("/activateAccountEmail.xhtml");
		}
		catch (Exception e)
		{
			log.error("Error sending mail", e);
		}
	}

	//Public Attribute getters/setters
  public UserAccount getAccount() {
    return account;
  }

  public void setAccount(UserAccount account) {
    this.account = account;
  }

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}