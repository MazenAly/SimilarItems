import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map 


object LSH {

	def generateCandidatePairs(sigs: ListBuffer[Array[Int]], minHashLength:Int, docs_size:Int , b:Int, r:Int) : Unit= {

			var bands_buckets:Array[Map[Int,ListBuffer[Int]]] = new Array[Map[Int,ListBuffer[Int]]](b)
					for( band_index <- 0  to b-1){
						for (doc <- 0 to docs_size -1) {
							var rows_vector = "" // a string to store the band vector of integers for a given document
									// loop on the rows to get its vector
									for( rr <- 0  to r-1){
										val row_index= band_index*r + rr
												rows_vector = rows_vector + sigs(doc)(row_index) +""
									}
							val hash_key = rows_vector.hashCode()  //hash the string to a given bucket
									if (bands_buckets(band_index) == null){
										bands_buckets(band_index)= Map()
									}

							// if the bucket key is new for this band, then add it in the Map along with empty list
							if (!bands_buckets(band_index).contains(hash_key)) 
							{	bands_buckets(band_index)  += hash_key -> new ListBuffer[Int]()	
							}

							// add the document id to the list
							bands_buckets(band_index)(hash_key) += doc 
						}
					}


	// a set of integer sets (pairs)
	var candidate_pairs = scala.collection.mutable.Set[scala.collection.immutable.Set[Int]]()
			for( band_index <- 0  to b-1){
				bands_buckets(band_index).keys.foreach{ i =>  
				if (bands_buckets(band_index)(i).length >1)  // if the size of documents list is < 2.. skip..as there are no pairs 
				{
					// generating combinations then filter the non-pairs and add them to the set of candidate pairs
					bands_buckets(band_index)(i).toSet.subsets.toList.filter(s => s.size ==2).foreach(s => candidate_pairs += s )
				}	   	
				}
			}
	// print all candidate pairs
	candidate_pairs.foreach(println)
	// threshold approximation
	println(math.pow(1.0/b , 1.0/r ))
	}

}