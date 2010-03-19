package be.vub.salesmen.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.*;
import java.io.Serializable;

@Name("SendEmail")
@Scope(ScopeType.CONVERSATION)
public class SendEmailBean implements SendEmail, Serializable
{
	@Logger
	private Log log;

	@In
	private FacesMessages facesMessages;
	
	@In
	private Renderer renderer;
		
	public void send()
	{
		try
		{
			renderer.render("/confirmEmail.xhtml");
			facesMessages.add("Email sent successfully");
		} catch (Exception e)
		{
			log.error("Error sending mail", e);
			facesMessages.add("Email sending failed: " + e.getMessage());
		}
	}
}