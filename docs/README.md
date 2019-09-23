# User Guide - Grumpy Cat Task Manager

## Features 
- [`todo`](#-todo-) - Add a new Todo to the list.
- [`deadline`](#-deadline-) - Add a new Deadline to the task list.
- [`event`](#-event-) - Add a new Event to the task list.
- [`list`](#-list-) - List out all current tasks.
- [`done`](#-done-) - Mark a task as done.
- [`delete`](#-delete-) - Delete an existing task.
- [`clear`](#-clear-) - Clear the task list of all tasks.
- [`sort`](#-sort-) - Sort tasks by the category provided.
- [`find`](#-find-) - Find a task using a keyword.
- [`joke`](#-joke-) - Ask grumpy cat to tell you a joke.
- [`bye`](#-bye-) - Exit the program.

## Usage

The Grumpy Cat knows how to add 3 kinds of tasks:

Type | Parameters | Description
-----| -----------| -----------
`todo` | description | Adds a `todo` task with a `description`
`deadline` | description \[dd-mm-yyyy] [HHmm] | Adds a  `deadline` task with a `description`, `date` and `time`
`event` | description \[dd-mm-yyyy] [HHmm]-[HHmm] | Adds a  `deadline` task with a `description`, `startDate` ,`startTime` and `endTime`

### `todo` 
- Add a new Todo to the list.

Example of usage: 

`todo feed grumpy cat`

Expected outcome:  
```
I've added this task: 
[T][✘] feed grumpy cat
Now you have 1 task in this list.
```

### `deadline` 
- Add a new Deadline to the task list.

Example of usage: 

`deadline pat grumpy cat /12/12/2019 1234`

Expected outcome:  
```
I've added this task: 
[D][✘] pat grumpy cat (by: 12:34 2019-12-12)
Now you have 1 task in this list.
```

### `event` 
- Add a new Event to the task list.

Example of usage: 

`event pat grumpy cat /12/12/2019 1234-2345`

Expected outcome:  
```
I've added this task: 
[E][✘] walk grumpy cat (at: 12:34-23:45 2019-12-12)
Now you have 1 task in this list.
```

### `list` 
- List out all current tasks.

Example of usage:

`list`

Expected outcome:  
```
1. [T][✘] feed grumpy cat
2. [D][✘] pat grumpy cat (by: 12:34 2019-12-12)
3. [E][✘] walk grumpy cat (at: 12:34-23:45 2019-12-12)
```

### `done` 
- Mark a task as done.

Example of usage:

`done 2`

Expected outcome:
```
Finally doing something useful I see:
[D][✓] pat grumpy cat (by: 12:34 2019-12-12)

```

### `delete` 
- Delete an existing task.

Example of usage:  

`delete 1` 
 
Expected outcome:
```
I've removed this task, now kindly remove yourself from my sight:
[T][✘] feed grumpy cat
You have 2 tasks in this list.
```

### `clear` 
- Clear the task list of all tasks.

Example of usage:  

`clear`

Expected outcome:
```
Yours tasks have been cleared.
```

### `sort` 
- Sort tasks by the category provided.

Example of usage:  

`sort startDate`

Expected outcome:
```
Sort it yourseld... Ok fine here it is:
1. [D][✘] feed me (by: 12:34 2000-01-01)
2. [D][✘] feed me later (by: 12:34 2019-12-12)
```

### `find` 
- Find a task using a keyword.

Example of usage:  

`find later`

Expected outcome:
``` 
Here are the matching tasks in your list:
Now where's my fish.
1. [D][✘] feed me later (by: 12:34 2019-12-12)
```

### `joke` 
- Ask grumpy cat to tell you a joke.

Example of usage:  

`tell me a joke`

Expected outcome:  

`you`

### `bye` 
- Exit the program.

Example of usage:  

`bye`

Expected outcome:  

*Grumpy Cat task manager closes*


