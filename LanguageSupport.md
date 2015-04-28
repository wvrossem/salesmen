# INSTRUCTIONS #

To allow a string to be displayed in multiple languages, follow the next instructions:

  * Open messages\_XX.properties in the resource folder. XX stands for a specific language and can be either EN or FR.
  * Add a new message. For example:
```
    salesmen.Auction.Title = TitleText
```
> > with _TitleText_ the text to display as the title for an auction.
  * In the view-file, display the title with the expression:
```
    <..... value="#{messages['salesmen.Auction.Title']} .../>
```