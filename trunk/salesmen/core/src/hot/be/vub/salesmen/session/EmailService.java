package be.vub.salesmen.session;

import be.vub.salesmen.entity.Transaction;
import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.log.Log;

import java.io.Serializable;

@Name("emailService")
@AutoCreate
public class EmailService implements Serializable
{
	private static final long serialVersionUID = -8132989898020136666L;

  //Private Attributes
  private UserAccount account;
	private String password;
	private Transaction transaction;

	@Logger
	private Log log;

	//@in annotations
	@In(create=true)
	private Renderer renderer;
	;

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

	@Asynchronous
	public void sendRequestPaymentEmail(Transaction transaction)
	{
		this.transaction = transaction;
		this.account = transaction.getBuyer();
		try
		{
			renderer.render("/requestPaymentEmail.xhtml");
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

	public Transaction getTransaction()
	{
		return transaction;
	}

	public void setTransaction(Transaction transaction)
	{
		this.transaction = transaction;
	}
}