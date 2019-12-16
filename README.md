# OOD_final_project_GradingSystem

Overview (High level overview of the document and what I should expect to get out of reading this documentation)

This document introduces a new grading system that is designed for Boston University professors and graders designed by group 21.  It will explain the scope, functionality, goals, object diagram and object justification of the grading system.


Scope (What is the problem and reason for you making this piece of software anyways)

First of all, as we know currently most of graders and professors are using Excel to grade assignments, labs, and exams. The advantage of Excel is its flexibility since it does not matter what type of assignments, you can grade them with the Excel and get the job done. However, the disadvantages of Excel are many. For example, a grader can only type in the data rather than importing from a file. Also, even though Excel has password protection, it is not secure in terms of having data integrity. Another problem is that a grader cannot add comments for the data, which is a very nice feature to have when collaborating in a team. Last but not least, the data of Excel is not persistent in the sense that when you lost power while you are editing the file, you will lose the file and the data cannot be recovered. Given these disadvantages, our group has designed a new grading system that has both the flexibility and the various input sources, data security and comment functionality.


Functionality (What where you expecting the program to do?)

Our grading system has four main major functionalities. First, it has a Graphical User Interface (GUI). It is an easy-to-use interface and it can do basically everything the system can do. It is very easy to navigate to add a new class, view class information, students, raw grades, calculate final grades and many other functionalities.

Second, it has some sort of flexibility and it can be used by anyone for any class. For example, it can add or remove an assignment such as an exam, a quiz or an assignment. In addition, it can add comments for the data and has the student management.

Third, it can get final grades by one click. The system can automatically calculate all students’ final grades of a class by just one click. It is much easier than what the Excel offers and improves the overall efficiency.

Last but not least, it has some new features which makes grading much easier. Features such as input/update grades from external unsorted files, support different input types such as raw score grade (80) or percentage (80%) or even points off (-80), database as storage for all data to protect the client from data loss.


Goals (What goals did you have, which ones did you meet and didn’t meet)

Our goals are to design a flexible and easy-to-use grading system with a GUI, along with many new features such as comments, import data from files and etc.

Among all these goals, we have implemented the Graphical User Interface, comments on specific comments, client management, and file inputs.


Object Diagram ( Note that a UML diagram that lists every class is meaningless. We are looking for a very simple but informative diagram, something as simple as what class of objects talk to other classes or depends on other classes of objects)

class SystemDatabase
* This class serves as the database class within the application. From this class, it allows to connect/disconnect from the database. And it can read/write to the database as well.
The benefit of this class is that having a single class that deals with the database operations makes things easier for developers to maintain/add/delete features of the application related to the database operations. From this single class, developers can control the data source and it is very easy to modify.
The design decision is based on we have “Clients” acted as the users who use the grading system. So we save the client information as byte stream, which is portable and easy to save/read from the database.



Object Justification (List out objects or class of objects (Do need to list every single object) (Justify each group of objects, i.e. explains what it represents/does and why need it)

class SystemDatabase
* The reason we need to have this class is that it provides a single place for developers to maintain the database operations such as read, write to the database. Every database operation is called from this class and we then transfer them to other objects such as Client. It is important to have a database to use the persistent storage which saves the progress of the grading.

