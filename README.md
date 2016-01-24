# CodeArchive
## Overview
This repository contains my solutions to interesting and sometimes unique problems using Java and other languages. These problems include classic Computer Science algorithms and exercises I've used to expand my knowledge of specific languages. They also include programs and code snippets I've developed in my career that solve common issues across a variety of applications. All of these range in difficulty from trivial to complex.

My purpose in developing this repository is to:
- Retain and reuse solutions I've developed to common problems.
- Establish a basis for developing similar language-specific archives for new languages I learn.
- Blatantly demonstrate my coding style to peers and prospective employers rather than just describing my capabilities in my resume.

## Code

All programs included in this repository have been developed using the Eclipse IDE. These programs are:

1. **_ClassComposition_** -
This program compares and contrasts the HAS-A (interfaces) and IS-A (inheritance) types of class composition to provide examples of how two different capabilities of the Java language, namely interfaces and inheritance, can be used to accomplish the same thing. Class design and composition is a key topic in Java since it directly and significantly impacts both the performance and maintainability of the application. The [Liskov Substitution Principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle) is an important concept which states that "if S is a subtype of T, then objects of type T may be replaced with objects of type S". When a class X is derived from a class Y and class X is used out of context in place of class Y this can result in erroneous behavior. For example, if a Square is defined as a subclass of a Rectangle and then used in place of a Rectangle errors will result when computing the dimensions of the object since a Square will impose the restriction that width and height must be the same.

2. **_EightQueens_** - 
This class finds the number ways eight queens can be placed on a chessboard so that no two queens can attack each other. This is a classic problem in both Mathematics and Computer Science that was first posited in the mid-19th century. From a Computer Science standpoint it is interesting since it requires considerable finesse to arrive at an efficient solution and since it demonstrates the power of a recursive solution.

3. **_FactoryPatternDemo_** -
This is an example of how to implement the Factory design pattern using Java. Of particular interest in this example is the fact that much of the functionality is implemented in the FactoryType ENUM. This class also contains a commented-out solution that used reflection to create the Vehicle objects rather than a more explict solution. While this was frugal in terms of the number of lines of code required, it detracted from the ability to easily read and understand how the class operates. As a result this was replaced with a more explicit solution.

4. **_rptstrip_** -
This program reads a text file containing a report strips off page titles, footers, and all column headers in the report body except those on the first page of the report. At this point in time rptstrip.js is merely a conceptual model for a grander vision of a program that will allow the user to build a template file from a report and then apply that template to any report file containing the same report to create a CSV file from it. The motivation for this is that during my career I've encountered many cases where information could easily be generated in report format, but the same was not true for files. This is often true of legacy applications. Being able to easily transform reports into CSV files makes it relatively easy to import information into other applications and data analysis tools.

5. **_SingletonPatternDemo_** -
This is an example of how to implement the Singleton design pattern in a thread safe manner.

6. **_Sounder_** - 
This program demonstrates how to control devices by controlling the pitch and volume of a sound on the local computer using the mouse. Pressing and releasing the right mouse button starts and stops the sound, moving the mouse up and down adjusts the volume, and moving it left and right adjusts the pitch. Since two discrete "things" must interact, namely mouse and sound, this is an interesting example of how to use design patterns to both simplify the code and make it easily maintainable and extensible.

7. **_WeatherStats_** - 
WeatherStats demonstrates how to retrieve and parse data from a website using standard Java libraries without resorting to maintaining that data on the local server. In this case weather data from a monitoring station at Lake Pend Oreille in Northeastern Washington is retrieved from a [website](http://lpo.dt.navy.mil) maintained by the U.S. Navy and used to generate basic statistics. 

## Development Environment
My development environment consists of the following components:

| Component           | Development (IDE) | Runtime   |
|---------------------|-------------------|-----------|
| Eclipse             |   Eclipse Mars    |   N/a     |
| Languages           |   Java SE8        | Java SE8  |
|                     |   Nodeclipse      | Node.js   |
| Deployment          |   Maven           | Maven     |
| Database            |      N/a          | MySQL     |
| Frameworks          |      N/a          | AngularJS |
|                     |      N/a          | Bootstrap |
|                     |   STS             | Spring    |
| Source Code Control |   EGit            | Git       |
| Task Management     |   Mylyn           | Mylyn     |
