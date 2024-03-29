package be.vub.salesmen.session;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.*;
import be.vub.salesmen.entity.User;

@Name("sendEmail")
@Scope(ScopeType.CONVERSATION)
public class SendEmail implements Serializable
{
	private static final long serialVersionUID = -8132989898020136666L;

	@Logger
	private Log log;

	//@in annotations
	@In
	private FacesMessages facesMessages;
	@In
	private Renderer renderer;
	@In(create=true)
	private User user;

	public void sendConfirmEmail()
	{
		try
		{
			renderer.render("/confirmAccountEmail.xhtml");
			facesMessages.add("Email sent successfully");
		} catch (Exception e)
		{
			log.error("Error sending mail", e);
			facesMessages.add("Email sending failed: " + e.getMessage());
		}
	}
	
	public void sendPasswordEmail() {
		try
		{
			renderer.render("/passwordEmail.xhtml");
			facesMessages.add("Email sent successfully");
		} catch (Exception e)
		{
			log.error("Error sending mail", e);
			facesMessages.add("Email sending failed: " + e.getMessage());
		}
	}
	 
	public void sendStandardEmail()
	{
		try
		{
			renderer.render("/standardEmail.xhtml");
			facesMessages.add("Email sent successfully");
		} catch (Exception e)
		{
			log.error("Error sending mail", e);
			facesMessages.add("Email sending failed: " + e.getMessage());
		}
	}
}