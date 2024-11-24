# SimpleTypingTest

## About
This is a basic typing test tool designed to be *lightweight*, fast, ***minimal***, and configurable.
It is intended for users who prefer a minimal and offline solution to tracking their typing skill.

Compared to a fully fledged typing tests which would have features you may not need, 
this application focuses on getting the job done by tracking a select few variables well.

## (Intended) features
- Tracks various statistics
- Save statistics of your tests
- Visualize progress with a progress graph
- Configure your tests to your preference. (Duration and Word Count)
- Easily visualize your mistakes with a "Diff" view to compare the errors at the character level
- Delete statistic

## What
Essentially the program generates a random string of words outputs it to stdout or a user interface.
It then reads input from the user (stdin/ui).
After this, **variables** related to speed and accuracy are calculated and stored in a **Statistic** object
This object is stored in a collection of **Statistics**
The user can take multiple tests
The statistics can be viewed later by the user to get an overview of their progress.

## Why
I chose to create this mainly since I think this idea has the potential to involve a range of different ideas from Software Development, Computer Science and Mathematics, leading me to learn a variety of skills.
Although this seems laughable due to the simple nature of the project I've described above, I have intentionally limited the scope of the project due to the course's requirements (with respect to the project) and for the sake of time.
However, I do plan to expand on the project after the completion of the course.

I also have an interest in open-source, bloat-free, simple, but powerful terminal-based applications. 
It is also the case that I recently have started training my typing speed.


## User Stories
### Phase 1
- As a user, I want to different variables to be recorded on each test I do. They should include words per minute, accuracy, the number of words I was able to type before I ran out of time, and the letter that I made the most mistakes on.
- As a user, I want to be able to store the variables of each test I do.
- As a user, I want to be able to view all the variables of a statistic, **both**, right after the test i.e. the variables of this test **and** later for some test I select.
- As a user, I want to be able to view a list of all the 'Statistic's of my past tests.
- As a user, I want to be able to see a progress graph of the words per minute over the past tests.
- As a user, When inspecting any given statistic I want to be able to visualize the mistakes using the colors red and green to highlight incorrect and correct letters 
### Phase 2 
- As a user, I want to have the option to save all of my statistics to a file.
- As a user, I want to have the option to load my past statistics from a file.




## Instruction for End User
- Press "Start" to start a test.
- You must choose how many words you want to type in your test
- You must also choose the length of the test
- Press ok, Wait for 5 seconds and the test will start
- Words will be displayed and you must copy them down as fast as possible before the test ends (it will last the duration you chose) and you are sent back to the main menu
- Then, the Statistics of your test will be updated and you will be able to view them on the right side of the screen (Add X to Y) (User Interaction 1)
---
- You may click on the statistic to view more information (Another User Interaction)
- When in the main menu, you can choose to view a graph of your statistics (Visual Element)
- You can also save the statistics to a file for later with the "Save" button 
- You can load them back using the "Load" button
- "Quit" exits the application




## Phase 4: Task 2
```
Sat Nov 23 17:07:09 PST 2024
>> Statistics have been set

Sat Nov 23 17:07:11 PST 2024
>> Showing Description for button number: 2

Sat Nov 23 17:07:12 PST 2024
>> Showing Description for button number: 3

Sat Nov 23 17:07:21 PST 2024
>> Showing Description for button number: 5

Sat Nov 23 17:07:30 PST 2024
>> Hiding Description for button number: 3

Sat Nov 23 17:07:31 PST 2024
>> Hiding Description for button number: 2

Sat Nov 23 17:07:32 PST 2024
>> Hiding Description for button number: 5

Sat Nov 23 17:07:49 PST 2024
>> A statistic was added

Sat Nov 23 17:07:58 PST 2024
>> Showing Description for button number: 6

Sat Nov 23 17:08:00 PST 2024
>> Hiding Description for button number: 6
```
