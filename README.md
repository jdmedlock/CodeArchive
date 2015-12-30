# CodeArchive
## Overview
This repository contains my solutions to interesting and sometimes unique problems using Java and other languages. These problems include classic Computer Science algorithms and exercises I've used to expand my knowledge of specific languages. They also include programs and code snippets I've developed in my career that solve common issues across a variety of applications. All of these range in difficulty from trivial to complex.

My purpose in developing this repository is to:
- Retain and reuse solutions I've developed to common problems.
- Establish a basis for developing similar language-specific archives for new languages I learn.
- Blatantly demonstrate my coding style to peers and prospective employers rather than just describing my capabilities in my resume.

## Code

All programs included in this repository have been developed using the Eclipse IDE. These programs are:

1. **_Sounder.java_** - 
This program demonstrates how to control devices by controlling the pitch and volume of a sound on the local computer using the mouse. Pressing and releasing the right mouse button starts and stops the sound, moving the mouse up and down adjusts the volume, and moving it left and right adjusts the pitch. Since two discrete "things" must interact, namely mouse and sound, this is an interesting example of how to use design patterns to both simplify the code and make it easily maintainable and extensible.

2. **_EightQueens.java_** - 
This class finds the number ways eight queens can be placed on a chessboard so that no two queens can attack each other. This is a classic problem in both Mathematics and Computer Science that was first posited in the mid-19th century. From a Computer Science standpoint it is interesting since it requires considerable finesse to arrive at an efficient solution and since it demonstrates the power of a recursive solution.

3. **_WeatherStats.java_** - 
WeatherStats demonstrates how to retrieve and parse data from a website using standard Java libraries without resorting to maintaining that data on the local server. In this case weather data from a monitoring station at Lake Pend Oreille in Northeastern Washington is retrieved from a [website](http://lpo.dt.navy.mil) maintained by the U.S. Navy and used to generate basic statistics. 

4. **_rptstrip.js_** -
This program reads a text file containing a report strips off page titles, footers, and all column headers
in the report body except those on the first page of the report. At this point in time rptstrip.js is merely a conceptual model for a grander vision of a program that will allow the user to build a template file from a report and then apply that template to any report file containing the same report to create a CSV file from it.

The motivation for this is that during my career I've encountered many cases where information could easily be generated in report format, but the same was not true for files. This is often true of legacy applications. Being able to easily transform reports into CSV files makes it relatively easy to import information into other applications and data analysis tools.

## Development Environment
My development environment consists of the following components:

| Component           | Development (IDE) | Runtime  |
|---------------------|-------------------|----------|
| Eclipse             |         X         |   N/a    |
| Languages           |      Java SE8     | Java SE8 |
|                     |     Nodeclipse    | Node.js  |
| Database            |        N/a        | MySQL    |
| Source Code Control |       EGit        |   N/a    |
