import scala.io.Source
import scala.collection.mutable.ListBuffer



object ShinglingMain {
	
  
  def main(args: Array[String]): Unit = {

	  // change the below parameters
   val path = "/home/mazen/workspace/SimilarItems/src/data" //the path of the documents folder
   val file_ext = ".data"  // documents extension
	 val minHashLength = 100 ;  // number of hash functions for minhashing
   val shingleLength = 10;    // number of chars in a shingle
   val b = 20   // number of bands
	 val r = 5    // number of rows in a band
	 val s = 0.8 // Jaccard Similarity Threshold

  // put  and filter them by extention type e.g .txt
	var files = new java.io.File(path).listFiles.filter(_.getName.endsWith(file_ext));
			
			
			var shingle_lists = new ListBuffer[Set[Int]]()
			var list_sim = new ListBuffer[Double]()

			for ((file , i )<- files.zipWithIndex){
			  println("File index: " + i + " " + "File name: " +  file.getName);
				var file_contents= Source.fromFile(file, "ISO-8859-1"); // open document 
				//get the list of shingles of a given document and hash the shingles to a signed integer
				var shingles = file_contents.toList.sliding(shingleLength)  
						.map(_.mkString)
						.map(shingle => shingle.toLowerCase)
						.map(shingle => shingle.hashCode())
						.toSet;
				shingle_lists += shingles
				//println(shingles);
				println("File shingles size: " +shingles.size);
				println("=======================")
			} 
			
			  var all_shingles = shingle_lists.flatten.toSet.toSeq.sorted; //get a sort a list of all shingles

			 
			  var all_shingle_bin_lists = new ListBuffer[Seq[Int]]() //list of binary representation of all documents i.e if a shingle exists or not in the document
			  var all_signatures_list = new ListBuffer[Array[Int]]() //list of all signatures of all documents

			  // for all documents, generate the binary representation and then create its signature
		     for( i <- 0 to files.size - 1 ){
		      all_shingle_bin_lists  += all_shingles.map( x => if (shingle_lists(i).contains(x))  1 else  0 )		                
          all_signatures_list +=  MinHashing.getMinHash(all_shingle_bin_lists(i) , minHashLength ) 
		     }
			
			  
			  // for all combinations of documents, print the Jaccard similarity of the documents and their signature similarity 
		   for( i <- 0 to files.size - 2 ){
		       for( j <- i + 1 to files.size-1){
			       val sim =  CompareSets.compute_sim(shingle_lists(i) , shingle_lists(j)) ;
			       var sig_sim =  CompareSignatures.compare_sig(all_signatures_list(i) , all_signatures_list(j), minHashLength)

			     if (sim>= s){
			      println("File index: " + i + "--- " + "File index: " + j);
		        println("Jaccard similarity: " + sim)
		        println("signatures similarity: "+sig_sim)
		        println("=======================")
			      }
		       }
      }
		   
		   // Generate the candidate pairs using LSH
		   LSH.generateCandidatePairs(all_signatures_list , minHashLength, files.size , b ,r )
				 
	}
}