##Finding Similar Items: Textually Similar Documents
In this homework, we implement the stages of finding textually similar documents based on Jaccard similarity using the shingling, minhashing, and locality-sensitive hashing (LSH) techniques. The implementation is done using Scala.
To test and evaluate our implementation, we write a program that uses our implementation to find similar documents in a corpus of 7 documents (and edited ones) of the dataset “Opinosis Opinion ⁄ Review Data Set“
https://archive.ics.uci.edu/ml/datasets/Opinosis+Opinion+%26frasl%3B+Review


The Scala project consists of 5 classes:
##ShinglingMain
This is main class to run the whole program and it’s where we set the program parameters. 
It also includes the the part of reading the documents and generating the shingles.


##CompareSets
It estimates the Jaccard similarity of two sets of hashed shingles.


##MinHashing
It generate the signature of a given document shingles using Minhasing technique


##CompareSignatures
It estimates similarity of two integer vectors (minhash signatures) as a fraction of components in which they agree.


##LSH ( the bonus part)
It uses a collection of minhash signatures and a similarity threshold t in the form of b (number of bands) and r (number of rows in a band) to find all candidate pairs of signatures that agree on at least fraction t of their components.


##Instructions of how to build and to run:
The project is pure scala project (not Spark) and can be imported in eclipse as Scala project.
Before running the project, change the below variables in the main function of  ShinglingMain class.
val path = folder_path  //the path of the documents folder
val file_ext = ".data"  // documents extension
val minHashLength = 100 ;  // number of hash functions for minhashing
val shingleLength = 10;    // number of chars in a shingle
val b = 20   // number of bands
val r = 5    // number of rows in a band
val s = 0.8 // Jaccard Similarity Threshold


After running the project, the documents indexes with Jaccard similarity more than the threshold will be printed along with their minhash signature similarity. 
In addition, all the candidate pairs will be printed using LSH
