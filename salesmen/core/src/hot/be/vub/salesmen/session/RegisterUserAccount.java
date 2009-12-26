package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface RegisterUserAccount
{
    public String begin();
    public String increment();
    public String end();
    public int getValue();
    public void destroy();
  
    // add additional interface methods here

}
