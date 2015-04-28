# 2009 #

## Week 43 ##

### Timesheet Integration ###

#### Problem ####

Publishing timesheets has been a challenge so far, in that it's tedious to do all the work by hand. Therefore, we decided to automate this process by introducing a simple XML vocabulary, which developers can use to submit their timesheets. The idea is to directly use the semantic data provided by developers to generate everything else that's necessary to publish high quality timesheets.

#### Solution ####

The following actions should be taken in order to automate the publication of timesheets.
  1. Defining a simple XML grammar using RELAX NG.
  1. Writing an XSLT stylesheet to generate:
    * HTML version of timesheets.
    * Plain text version of timesheets.
    * Metadata for publishing timesheets in various formats.

#### Risks ####

  * Is it a good idea to let developers submit their timesheets in XML?
  * Will this process not be error-prone?
  * How simple can an XML grammar be?
  * How long will it take to write a RELAX NG schema?
  * Do we need to impose rule-based constraints to the XML grammar?
  * Can SilkPage be easily extended to provide support for the new XML format?
  * Will the stylesheets be ready on time?
  * URFM is an RDF/XML vocabulary that describes downloadable resources. How easy is it going to be to generate a graph structure from a tree structure using XSLT?