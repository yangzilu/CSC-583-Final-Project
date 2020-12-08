CSC-583-Final-Project
------------

Author: Yangzi Lu [yangzilu@email.arizona.edu](mailto:yangzilu@email.arizona.edu)  
Date: December 9, 2020

## Notes

* Run with Maven(mvn compile exec:java)
* [Lucene Index](https://arizona.box.com/s/5vg4d01pyek7u9191vwdya870zfe3r1z)
* The Lucene index need to be placed in resources directory and is for the default configuration which is lemmatizing and stemming.

## Include files
* QueryEngine.java - This is the main class for this project. 

* Lemma.java - This is only used for lemmatize the wiki files. It lemmatizes the wiki files and writes in to wiki_lemma directory. It is not the part of QueryEngine.java and also contains a main method.
