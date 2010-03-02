package be.vub.salesmen.session;

/* Because we want to display a user-friendly name in the web form, and store a short and consistent name in the database,
the enum has a "label" or "name" field and a getter method. */

public enum Country {

  /* ISO-3166 country codes */
   BE("Belgium"),
   NL("Netherlands"),
   FR("France");

   private String longName;
  
   private Country(String longName) {
       this.longName = longName;
   }

  /* Used to display the full name of a country. */
   public String getLongName() {
       return longName;
   }

   @Override
   public String toString () {
       return getLongName();
   }
  
}
