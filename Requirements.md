# Requirements #

## Guests/Visitors ##
Guests are visitors of the site which don't have an account yet
  * Guests can
    * Browse through items
    * Request an account
    * Log in

## Account creation ##
This information needs to be supplied when creating an account
  * First Name
  * Last Name
  * Address (Bart: optional?)
  * Phone number (Bart: optional?)
  * Date of birth (minimum 18 years old)
  * E-mail address (+ confirmation)
  * Username
  * Password (+ confirmation)
  * Secret question + answer (Bart: possible (end-user) security risk, I wouldn't use this for password recovery, instead, let the site generate a new PW and send it to the user's email)
After the account is created, it needs to be confirmed through e-mail (Bart: after changes to email, a new confirmation is required)

## Users/Members ##
Users are members of the site, i.e. they have an acount
  * Users have:
    * Public
      * Username
      * Location
      * Objects they sell
    * Private
      * Name
      * Address
      * E-mail address
      * Phone number
  * A user can
    * Browse through actions
    * Place an auction
    * Bid on auctions
    * Search a user
    * Contact other users
    * Follow a user (rss feed of the auctions this user places) (Bart: very nice to have?)
    * Log out
    * (Bart) Choose a language of their liking (default language: English)
    * (Bart) Account (virtual money)
  * User home has
    * Followed auctions
    * Bought auctions
    * Auctions for sale
    * Auctions currently bid on
    * Favorite sellers
      * Add seller
      * Delete seller
      * Contact seller
    * Messages (from other users or the system)
      * View message
      * Delete message
      * Reply to message
    * Account information (view or change)

## Auctions ##
  * Auctions have
    * (Bart) a name/title
    * (Bart) transport-options (national/international delivery, pickup,..)
    * Minimum price
    * Duration
    * Auctionnumber
    * Information about the object being sold
    * Auction type
    * Category (Bart: at least 1, possibly more than 1, categories are created by site owner)
    * Tags (Bart: tags are user defined)
    * (Bart) The auction-organiser has to pay a fee for the auction (fixed price/percentage ?)

  * Auction types
    * English auction
    * Silent auction
    * Auction with fixed price (with best offer?)
  * A user can
    * Bid on the object
    * Buy the auction (if it has a fixed price)
    * Send a question to the seller about the object (Bart: private/public?)
    * (Bart) Users can bid on auctions BUT (when using virtual money) can NEVER surpass their accountlimit (<0EUR)

## GENERAL ##
  * (Bart) Search-engine (basic (must have) vs extended (want/nice to have))
  * (Bart) Web 2.0 (nice to have) (eg facebook: no need to refresh page contents, updates done by DOM manipulation, AJAX get/post, ..)

## Security ##
  * Users need an acount to sell objects
  * Users need an acount to bid on objects
  * Users need to be verified to be able to place auctions (Bart: how?)
  * Users need to log in before they can place auctions
  * Users need to log in before they can bid on objects
  * Users with multiple accounts are linked to one "super account" (Bart: ?)
  * (Bart: we need to specify a certain maximum amount of dangerous/bad/'dangerless' amount of 'bugs' per 'something')
  * (Bart) communication of sensitive data (eg password) should be encrypted (eg, using https)