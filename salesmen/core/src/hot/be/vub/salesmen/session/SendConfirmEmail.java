package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface SendConfirmEmail
{
    // seam-gen method
    public void sendConfirmEmail();

    // add additional interface methods here
    public void send();
}
