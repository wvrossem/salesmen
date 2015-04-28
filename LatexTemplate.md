# Introduction #

## Overview ##

This wiki page is dedicated to explain and document the LaTeX template softproj.cls. It will contain sections on how to declare it and how to use it. The different options for the .opt file are also explained.

There are also sections for Troubleshooting.

It will also contain a section where you can request future changes/additions.

## About the class ##
This documentclass is created by Patrick Provinciael.

It is created with portability to other projects in mind. For this reason a .opt file can be included to declare several project related details.


# Details #

## Declaring the class ##
You declare the documentclass as follows.

```
\documentclass[optfilename, twosides, otheroptions]{softproj}
```

## Using the Class ##
Within the document environment you must declare a new softproj environment.
Example:
```
\begin{document}
\begin{softproj}
Your text here
\end{softproj}
\end{document}
```

## .opt file ##
When declaring the documentclass, you must add the name of a .opt file which resides in the same folder as the .tex file.

The .opt file currently has following settings:
  * Project Controls
  * _\setprojectname{name}_ : Sets the name of the project.
  * _\setdocumentname{name}_ : Sets the name of the document.
  * _\setmember{memberlist}_ : Sets the list of projectmembers. (Currently has no visual effect)
  * _\setlogoname{name}_ : Sets the name of the logo. This should be a .jpg/.png/.gif file, but you must omit the extension. This is an optional command.
  * Title Controls
  * _\setauthorname{name}_ : Sets the name of the author on the title page.
  * _\setabstract{Short explanation of the document}_ : This will set the abstract of the document, which will be included on the title page.
  * Revision History Controls
  * _\setrevisioncurr{date: explanation}_ : Sets the most recent revision with Date and explanation. Watch out for using \today. Compiling on a new day results in loss of the old date.
  * _\setrevisionhist{revision history using a version history closure}_ : This will create a new page containing the full version history.

# Troubleshooting #

## Additional Packages ##
There are currently no additional packages included. You must still declare the use of additional packages in your tex file.

## Titlepage? ##
You must not make a titlepage within this file. This will be created depending on your .opt file settings.

# Future Revisions #
  * Additional possibilities, depending on the documentname. (Patrick Provinciael)
  * Declaration of several often used packages. (Patrick Provinciael)
  * Making setrevisioncurr obsolete by filtering this out of the Revision History. (Patrick Provinciael)
  * Addition of an easy and consistent referencing system. (Patrick Provinciael)

# Polls #
## Chapters: Yes or No ##
This poll is to decide on chapters. If the answer is yes, the main sections will be created using _\chapter_. If, however, no is the answer, the main sections will be created using section. Current version uses chapters. The decision of the Project Manager will be decisive on a tie. Poll ends monday 23/11.

**Yes:** Nick Wouter

**No:**

**Neutral:**