# Sean User Guide

---

Sean chatbot is a text-based Java Command Line Interface (CLI) application with CRUD features for task tracking. It supports 
adding todo, deadline, and event tasks, along with the option to delete these tasks, mark these tasks as complete or incomplete
as well as searching for tasks with a specific keyword. 

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details

## Launching Sean

---

1. Download the latest `.jar` file [here](https://github.com/tongkiankiat/ip/releases/tag/A-Release).

2. Copy the file to the folder you want to use as the _home folder_ for your Sean chatbot.

3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ip.jar` command to run<br>the chatbot.

4. You should see the following text on screen:
```angular2html
Hello! I'm Sean
What can i do for you?
```

5. Type the command in the command line and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
return the list of commands available.

6. Refer to the [Commands](Commands) below for details of each command

---

## Commands

---
### Viewing help: `help`
Shows the list of commands available.

Format: `help`

Sample output:
```angular2html
List of commands:
1. bye
    - Exits the program.
2. list
    - Lists all tasks.
3. unmark <task index>
    - Mark the task as undone.
4. mark <task index>
    - Mark the task as done.
5. delete <task index>
    - Delete the task.
6. todo <task>
    - Add a todo task.
7. deadline <task> /by <by>
    - Add a deadline task with a due date.
8. event <task> /from <from> /to <to>
    - Add an event task with a start and end date.
9. find <keyword>
    - Finds all tasks containing the keyword.
```

### List all tasks: `list`
Shows the list of tasks currently in the task list.

Format: `list`

Sample output:
```angular2html
Here are the tasks in your list:
1. [T][ ] sleep
2. [D][ ] wake up (by: 12pm)
3. [E][ ] study (from: 2pm to: 9pm)
```

### Add a todo task: `todo`
Adds a todo task to the task list.

Format: `todo <task>`

Examples:
* todo sleep
* todo eat chicken rice

Sample output:
```angular2html
Got it. I've added this task.
  [T][ ] sleep
Now you have 3 tasks in the list.
```

### Add a deadline task: `deadline`
Adds a deadline task to the task list.

Format: `deadline <task> /by <deadline>`
* `<deadline>` need not be in date/time format

Examples:
* deadline sleep /by 12am
* deadline eat dinner /by 12pm

Sample output:
```angular2html
Got it. I've added this task.
  [D][ ] sleep (by: 12pm)
Now you have 3 tasks in the list.
```

### Add an event task: `event`
Adds an event task to the task list.

Format: `event <task> /from <start> /to <end>`
* `<start>` and `<end>` need not be in date/time format

Examples:
* event party /from 7pm /to 12am
* event sleep /from 12am /to 7am

Sample output:
```angular2html
Got it. I've added this task.
  [E][ ] party (from: 7pm to: 12am)
Now you have 5 tasks in the list.
```

### Mark a task as done: `mark`
Mark a task in the task list as done. 

Format: `mark <task index>`
* `<task index>` **must be a positive integer**

Example:
* mark 1

Sample output:
```angular2html
Nice! I've marked this task as done!
  [D][X] wake up (by: 12pm)
```

### Unmark a task as done: `unmark`
Unmark a task in the task list as not done.

Format: `unmark <task index>`
* `<task index>` **must be a positive integer**

Example:
* unmark 1

Sample output:
```angular2html
OK, I've unmarked this task as not done.
  [E][ ] party (from: 7pm to: 12am)
```

### Finding a task: `find`
Searches for tasks containing the specified keyword.

Format: `find <keyword>`
* This command will also return the task if the keyword is in `/by`, `/from`, or `/to`

Example:
* find sleep
* find 12pm

Sample output:
```angular2html
Here are the matching tasks in your list:
1. [D][X] wake up (by: 12pm)
2. [D][ ] sleep (by: 12pm)
3. [D][ ] eat dinner (by: 12pm)
```

### Exiting the program: `bye`
Exits the program

Format: `bye`

Sample output:
```angular2html
Bye. Hope to see you again soon!
```