# SimpleTypingTest

## TODO
- Refactor
- Back to CLI first
- Switch to build tool
- Higher accuracy calculations for Statistic fields
- Better cli graph creation to fit in the screen
- Delete statistic  

## About
This is a basic typing test tool designed to be *lightweight*, fast, ***minimal***, and configurable.
It is intended for users who prefer a minimal and offline solution to tracking their typing skill.

Compared to a fully fledged typing tests which may have features you may not need, 
this application focuses on getting the job done by tracking a select few variables well.


## What
Essentially the program generates a random string of words outputs it to stdout or a user interface.
It then reads input from the user (stdin/ui).
After this, **variables** related to speed and accuracy are calculated and stored in a **Statistic** object
This object is stored in a collection of **Statistics**
The user can take multiple tests
The statistics can be viewed later by the user to get an overview of their progress.

