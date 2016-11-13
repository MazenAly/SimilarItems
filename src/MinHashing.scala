object MinHashing {

	def getMinHash(shingles: Seq[Int] , minHashLength: Int) : Array[Double]= {

		var minHash = Array.fill[Double](minHashLength)(Double.PositiveInfinity);	
		for((elem,i) <- shingles.zipWithIndex) {

		  if (elem == 1){
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