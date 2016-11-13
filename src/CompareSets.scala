import scala.io.Source


object CompareSets {
 
 // estimates the Jaccard similarity of two sets of integers – two sets of hashed shingles.
  def compute_sim(set1: Set[Int], set2: Set[Int]): Double = {
    val intersection = set1.intersect(set2).size
    val union = set2.union(set2).size
    return (intersection * 1.0) / union
  }
  
  
  	def main(args: Array[String]): Unit = {	
		
	  var s:Set[Int] = Set(1,3,5,7)
	  var t:Set[Int]= Set(77,20,3,-5)
	  println(compute_sim(s,t));
	  
	  
  	}
  
  
}



