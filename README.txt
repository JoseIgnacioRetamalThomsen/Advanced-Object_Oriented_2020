An Asynchronous Language Detection System

Jose I. Retamal 
G00351330@gmit.ie

Requeris Tomcat for deploy, place ngrams.war into the webapps directory of Tomcat and the app will be accecible 
through local host. 

Features

* Use a builder pattern to build the database. Because the database is composed of two 
main parts (the map and the distance calculator), and the database is mutable so we can 
easily build different databases. 

* Database builder manager as a singleton, we need only one builder, so it makes sense to
 make it a singleton.  Also, remove the need to create the object,  methods return the 
unique instance so we can call them in a chain.

* We use a factory pattern to create KmerGenerator, because we need to create a different 
generator depending on the type of kmer, having the factory facilitate the creation of them 
by just indicating the type of KmerGenerator needed. We also implement the factory as a 
singleton for easy access.

* We use a work manager object to deal with all the jobs, in and out queue.  It is implemented
 as a singleton with all methods statics, so no need to get an instance of the object. It also 
runs in an independent thread.

* We compress kmers in a long using bits since each character is represented with 16 bits, four 
characters can be fit in a long. When the first long is created using the first four characters, 
 the next one can be created by removing the first character at the start and adding the new at
 the back (move 16 bits for remove the first and append the next using | operator). This makes 
the creation of the representation very fast (just 2 operations for each new kmer). Similarly, 
it can be done for three or fewer characters kmers. 
In short, we remove the last character and add the new. I read that this is a technic  Google 
uses but can't remember where I read it.

* Generate the languages map using a two steps task; several threads perform each task. In the 
first task they are mapped, then in the second, the kmers are sorted ranked, and the most relevant 
are selected. Junit test is implemented for the most relevant components, and test suits are run 
to ensure the application works well. The test suits where run using eclipse.

* Use of iterator pattern for iterate through the string generating the kmers.
 