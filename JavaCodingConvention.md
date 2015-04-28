# Introduction #

These are the coding conventions for java programming in the Salesmen Project. Please follow these conventions. When you do not follow these conventions, add the reason why in comments with the code.

## Usage ##
First and Second denote the first and second word in a multiword variable.
All other words (or letters) must be used as shown in that particular convention.


# Details #

## Naming Conventions ##

### Variables/Parameters ###
  * variable name: firstSecond
  * Variable getter: getFirstSecond()
  * Variable setter: setFirstSecond()
  * Boolean getter: isFirstSecond()
  * loop counters: i, j, k, l, counterFirstSecond. No other letters/words allowed.

Refrain from using abbreviations. Loop counters are the exception on this rule. Although even here it is preferred to use a name that states what you are iterating/counting.

Refrain from using nameless variables, like "temp". Try to use something along the lines of tempVariableName.

### Classes ###
  * Classname: FirstSecond
  * Constructors: same name as classname
  * Destructors: finalize()

### Functions, methods, procedures ###
  * name: firstSecond()

### Constants ###
  * name: FIRST\_SECOND

## Usage Conventions ##

### Indentation ###
Every level of indentation must be expressed by one single tab. Every closure starts a deeper indentation.

Exceptions can be made when multiple parameters are set. In that case, an indentation should be added until the new line starts at the same place as the first assignment. In cases of an assignment using "=", one should indent the new line in such a way the "=" signs are aligned.

### Closures ###
  * Opening "{" always at the start of a new line on the old indentation.
  * Closing "}" always at the start of a new line on the old indentation.
  * Code in between must be on a new (deeper) indentation.
Example:
```
firstSecond(params)
{
   Some Code
}
```


## Files ##
Same name as the class it implements.

## Documentation and comments ##
### Comments ###
Add comments to clarify when needed. Also use comments to make distinctions between sections within a file.

## Coding Convention Websites ##
Please feel free to glance over the following Java coding conventions. With exceptions explained here, we will use the SUN coding convention.

  * [A collection of links to Java Coding Standards](http://www.ontko.com/java/java_coding_standards.html)
  * [Java Coding Conventions by Stephan Janssen](http://www.bejug.org/confluenceBeJUG/display/JJGuidelines/Home)