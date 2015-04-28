# Introduction #

This is a short guide for Salesmen developers to setup the Emacs editor on their machines running Windows.

# Download & Install #

## Windows ##

  1. [Download](http://ftp.gnu.org/gnu/emacs/windows/) a pre-compiled distribution of Emacs for Windows. E.g. `emacs-23.1-bin-i386.zip`.
  1. Extract the archive in a suitable place e.g. Emacs home: `C:\apps\emacs`.
  1. There are two options for running Emacs:
    * Add the `bin` directory of the Emacs distribution to your %PATH% environment variable.`
    * Create a shortcut of `bin/emacs.exe` and use that on your Desktop or elsewhere to run Emacs.

# Setup nXML-mode #

  1. [Download](http://www.thaiopensource.com/download/) the latest distribution of nXML-mode. E.g. `nxml-mode-20041004.tar.gz`.
  1. Extract it in a suitable directory e.g. `C:\apps\nxml-mode` or `/opt/nxml-mode`.
  1. Create a file called `schemas.xml` with the following XML content. Please note that, this step will help you author and validate your TimeTrack timesheets.
```
<?xml version="1.0"?>
<locatingRules xmlns="http://thaiopensource.com/ns/locating-rules/1.0">
  <namespace
      ns="http://purl.org/net/timetrack"
      uri="/path/to/salesmen/trunk/utils/timetrack/schema/rng/timetrack.rnc"/>
</locatingRules>
```
  1. Open your `.emacs` file.
    * In GNU/Linux: edit `~/.emacs`
    * In order to find out where Emacs reads your `.emacs` in Windows, change one of the options in Emacs menu, then go to: `Options > Save Options`. Check immediately the  `*Messages*` buffer --the footbar-- to see where it saves your `.emacs`.
  1. Add the following Lisp commands to your `.emacs`
```
(load "/path/to/your/extracted/nxml-mode/dist/rng-auto.el")
(setq auto-mode-alist
      (cons '("\\.\\(xml\\|xsl\\|rdf\\|rng\\|xhtml\\)\\'" . nxml-mode)
            auto-mode-alist))
(push "/path/to/your/schemas.xml" rng-schema-locating-files-default)
```

# Further Reading #
  * [Emacs Windows FAQ](http://www.gnu.org/software/emacs/windows/ntemacs.html)
  * [James Clark's nXML-mode](http://www.thaiopensource.com/nxml-mode/)
  * [nXML-mode commands by Dave Pawson](http://www.dpawson.co.uk/relaxng/nxml/info.html)
  * [Useful nXML-mode HOWTOs on EmacsWiki](http://www.emacswiki.org/emacs/NxmlMode)