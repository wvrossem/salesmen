package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface SendEmail
{
    public void send();
}
