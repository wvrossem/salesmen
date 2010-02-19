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
public class SendConfirmEmailBean implements SendConfirmEmail
{
    @Logger private Log log;

    @In StatusMessages statusMessages;

    public void sendConfirmEmail()
    {
        // implement your business logic here
        log.info("SendConfirmEmail.sendConfirmEmail() action called");
        statusMessages.add("sendConfirmEmail");
    }
    
    @In(create=true)
    private Renderer renderer;
       
    public void send() {
       try {
           renderer.render("/sendConfirmEmail.xhtml");
           statusMessages.add("Email sent successfully");
       } catch (Exception e) {
           statusMessages.add("Email sending failed: " + e.getMessage());
       }
    }
    // add additional action methods

}
