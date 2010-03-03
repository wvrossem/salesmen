package be.vub.salesmen.session;

import javax.ejb.Stateless;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.international.StatusMessages;

@Stateless
@Name("SendConfirmEmail")
public class SendEmailBean implements SendEmail
{
    @Logger private Log log;

    @In StatusMessages statusMessages;

    public void sendEmail()
    {
        log.info("SendEmail.sendEmail() action called");
        statusMessages.add("sendEmail");
    }
    
    @In(create=true)
    private Renderer renderer;
       
    public void send(String emailxhtml) {
       try {
           renderer.render(emailxhtml);
           statusMessages.add("Email sent successfully");
       } catch (Exception e) {
           statusMessages.add("Email sending failed: " + e.getMessage());
       }
    }
}
