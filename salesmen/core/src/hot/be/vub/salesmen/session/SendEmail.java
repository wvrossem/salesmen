package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface SendEmail
{
    // seam-gen method
    public void sendEmail();

    // add additional interface methods here
    public void send(String emailxhtml);
}
