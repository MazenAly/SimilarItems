import scala.io.Source
import scala.collection.mutable.ListBuffer



object Shingling {
	def main(args: Array[String]): Unit = {
	  
	 val minHashLength = 100 ;

	  var s = Set(1,3,5,7)
	  var t = Set(77,20,3,-5)
			var files = new java.io.File("/home/mazen/workspace/Lab1/src/data").listFiles.filter(_.getName.endsWith(".data"));
			val shingleLength = 10;
			
			
			var shingle_lists = new ListBuffer[Set[Int]]()
			var list_sim = new ListBuffer[Double]()

			for ((file , i )<- files.zipWithIndex){
			  println(i + " " +  file );
				var file_contents= Source.fromFile(file, "ISO-8859-1");
				var shingles = file_contents.toList.sliding(shingleLength)
						.map(_.mkString)
						.map(shingle => shingle.toLowerCase)
						.map(shingle => shingle.hashCode())
						.toSet;
				shingle_lists += shingles
				//println(shingles);
				println(shingles.size);
				println("=======================")
			} 
			
			  var all_shingles = shingle_lists.flatten.toSet.toSeq.sorted;
		    // println(all_shingles.size); 
			  var all_shingle_bin_lists = new ListBuffer[Seq[Int]]()
			  var all_signatures_list = new ListBuffer[Array[Double]]()

		     for( i <- 0 to files.size - 1 ){
		      all_shingle_bin_lists  += all_shingles.map( x => if (shingle_lists(i).contains(x))  1 else  0 )		                
          all_signatures_list +=  MinHashing.getMinHash(all_shingle_bin_lists(i) , minHashLength ) 
		     }
				  
		   for( i <- 0 to files.size - 2 ){
		       for( j <- i + 1 to files.size-1){
		         println( i + " " + j);
			       val sim =  CompareSets.compute_sim(shingle_lists(i) , shingle_lists(j)) ;
		         list_sim += sim
		        println(sim)
		        var sig_sim =  CompareSignatures.compare_sig(all_signatures_list(i) , all_signatures_list(j), minHashLength)
		         println(sig_sim)
		         	println("=======================")
		       }
      }
			//list_sim.sorted.foreach(println);	
				 
	}
}