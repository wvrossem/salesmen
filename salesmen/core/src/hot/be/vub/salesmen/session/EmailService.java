package be.vub.salesmen.session;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;

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
	private String email;
	private String username;
	private String password;

	@Logger
	private Log log;

	//@in annotations
	@In(create=true)
	private Renderer renderer;

	@Asynchronous
	public void sendConfirmation(String email, String username)
	{
		setEmail(email);
		setUsername(username);
		try
		{
			renderer.render("/confirmEmail.xhtml");
		}
		catch (Exception e)
		{
			log.error("Error sending mail", e);
		}
	}
		public void sendPassword(String email, String username, String password)
	{
		setEmail(email);
		setUsername(username);
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

	//Public Attribute getters/setters
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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