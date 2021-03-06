physim
======

A physics simulation language, designed to allow concise descriptions of physics problems and simulations written in Scala. Input will be a simple Scala program using the created library. Output will be formatted text files and generated graphics written in HTML 5 and Javascript. The simplest version could be useful for educational purposes, but it is intended to be written with enough flexibility to be expanded to more complex simulations/applications.


###Naming Conventions
Scala files are named lower case \_ seperated,
classes and objects are camelCase.

e.g. basic\_test.scala is the src file for BasicTest.class (or $.class)

###The Model
The model assumed in all these related classes
is that there are 3 orthoganal base vectors,
labeled x, y, z in the class files.

z is assumed to be pointed up/ the height in the 
library functions and class extentions 
e.g. Gravity(...) applies a force as -Z

x,y are assumed to define a plane normal to the Z direction

###Units
Units are scaled through the convert functions 
to work with milliseconds. That means distance is
stored as millimeters, and mass is stored as grams.
This means that all compound units match SI values,
for instance grams * mm / ms^2 is identicle to 
Newtons

###Directory Structure
Most directories are managed by the package structure of 
Scala to maintain simplicity, the folowing two contain
source files and are visable in the repository

* src/ *contains all src files*

* tests/ *contains test files for development*


