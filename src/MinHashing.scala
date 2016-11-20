object MinHashing {

	def getMinHash(shingles: Seq[Int] , minHashLength: Int) : Array[Int]= {

		var minHash = Array.fill[Int](minHashLength)(Int.MaxValue);	// initialize the document signature with the size of hash functions and with value infinity 
		
		// loop on every element and its index
		for((elem,i) <- shingles.zipWithIndex) {
		  // if the element is 1
		  if (elem == 1){ 
		    // hash the index using all the hash functions and update the signature of a given hash if the result is less than the current value
		    for( h <- 1 to minHashLength ) {
		      val new_hash_value = ((99*i +  (h*76) + 3013) % (shingles.size ))		      
		      if (minHash(h-1) > new_hash_value) {
		        minHash(h-1) = new_hash_value
		      } 
		    }
		  }
		}
		return minHash
	
	}

}