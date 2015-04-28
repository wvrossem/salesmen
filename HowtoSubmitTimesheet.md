# Introduction #

This page will shows developers how they can submit their timesheets _every_ **Monday before 12:00pm** CEST.

## The Big Picture ##

You should commit your timesheet in the form of a simple XML file to the _right_ place in the repository and that's it.

# The Simple XML Format #

## Complete Example ##

This example captures pretty much everything TimeTrack can do.

```
<?xml version='1.0'?>
<timesheet xmlns="http://purl.org/net/timetrack">
  <item duration="PT1H45M">
    <desc type="xhtml">
      <div xmlns="http://www.w3.org/1999/xhtml">
        <ul>
          <li>Item 1.</li>
          <li>Item 2.</li>
        </ul>
      </div>
    </desc>
    <refs>
      <ref>http://example.org/ref-1</ref>
      <ref>http://example.org/ref-2</ref>
    </refs>
  </item>
</timesheet>
```

## One-line Descriptions ##

In case your description is just one simple line, you can forget about XHTML.

```
<item duration="PT20M">
  <desc>One-liner description.</desc>
  <refs>
    <ref>http://example.org/ref-1</ref>
  </refs>
</item>
```

## Optional References ##

The `<refs>` tag is optional but once you use it, it must contain at least one `<ref>` element. In case an item doesn't have any references, then just omit them.

```
<item duration="PT10H">
  <desc>Coded and drank Java.</desc>
</item>
```

## The Duration Attribute ##

The value of `@duration` should comply with the [xsd:duration](http://www.w3.org/TR/2001/REC-xmlschema-2-20010502/#duration) datatype.

### Examples ###

```
P10Y = 10 years
P2Y3M4DT5M = 2 years, 3 months, 4 days, and 5 minutes.
PT2H20M = 2 hours, and 20 minutes.
```

Please note that `P` stands for _period_ and `T` indicates the start of the _time_; therefore, durations for timesheets usually start with `PT`.

# XML Grammar #

The schema for TimeTrack is originally [written](http://salesmen.googlecode.com/svn/trunk/utils/timetrack/schema/rng/timetrack.rnc) in [RELAX NG](http://www.relaxng.org/spec-20011203.html) [Compact Syntax](http://www.relaxng.org/compact-20021121.html). [XML Schemas](http://www.w3.org/TR/2001/REC-xmlschema-2-20010502/) have also been [generated](http://salesmen.googlecode.com/svn/trunk/utils/timetrack/schema/xsd/) using [Trang](http://code.google.com/p/jing-trang/)

# Authoring Tools #

## nXML-mode ##

Emacs users can benefit from a rich set of features provided by the [nXML-mode](http://www.thaiopensource.com/nxml-mode/) including:

  * Instant XML validation against the RELAX NG schema.
  * A powerful element suggestion mechanism.

Please refer to HowtoSetupEmacs in order to install and use nXML-mode.