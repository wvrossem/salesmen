package be.vub.salesmen.session;

import javax.ejb.Local;

@Local
public interface Authenticator {

    boolean authenticate();

}
