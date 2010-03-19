package be.vub.salesmen.session;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;

/* To make the list of a given enum's values available to the view,
just add one new annotated method to this class. */

@Name("factories")
public class Factories
{
	
	// Factory for the list of countries a User can live in.
	@Factory("countries")
	public Country[] getCountries(){
		return Country.values();
	}
}