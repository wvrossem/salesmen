# Introduction #

An issue tracking system can tremendously help software projects. In this document, we present the best practices when it comes to issue management for Salesmen developers.

# Quick Tips #
  * Comments are not for discussions. Only comment to provide readers with concise status report about the issue.
  * Give readers --ourselves-- context by referring to discussions on the [dev-list](MailingList.md) or SVN revisions.
  * Issue notifications are posted the dev-list. Use them for detailed discussions.
  * Don't forget to refer to SVN revisions by typing rX where X would be the revision number. A link to the relevant issue is automatically generated.

# F²AQ #

**Note:** The _2_ superscript on _F_ implies that this section represents a **_Fake_ FAQ**. That is, no-one has really asked these questions yet but a _dialogue_ is being used to convey the message.

  * Should requirements be encoded as issues?
    * Yes. Requirements are a distinct class of issues. They're blocked by concrete units of work.
    * Example:
      * [Issue 5](https://code.google.com/p/salesmen/issues/detail?id=5) represents Req. 1.
      * [Issue 7](https://code.google.com/p/salesmen/issues/detail?id=7) represents a task that is assigned to a developer.
      * **[Issue 5](https://code.google.com/p/salesmen/issues/detail?id=5)** or **Req. 1** is **blocked** by **[Issue 7](https://code.google.com/p/salesmen/issues/detail?id=7)**
    * This method of _establishing dependencies_ between issues and _mapping requirements onto issues_ enables us to divide large tasks into smaller units of work. Needless to say that [Issue 7](https://code.google.com/p/salesmen/issues/detail?id=7) can itself be divided into sub-tasks.

  * When should we comment on issues?
    * Make sure it all makes sense. People will stumble upon issues out of the context. Give them a context by putting references to persistent Web resources such as: mailing-list discussions, other issues, SVN revisions, etc.
    * At the same time, you're not discouraged to share things that don't necessarily make sense; read on.

  * What if I want to say something regarding an issue but it will not only help resolve the issue but rather, forms confusions?
    * You're absolutely encouraged to **share any concern** or detail so there's a solution to this very issue.
    * An email is sent to the mailing-list upon every change recorded by the issue tracking system. Reply to those emails in order to **open a discussion** or brainstorming regarding an issue **on the mailing-list**.
    * If the discussion turns out to be useful, we can always **refer to it in relevant issues**.

  * Can I use Dutch to comment or describe issues?
    * You can even write in Latin if you want to but no language other than English is recommended.