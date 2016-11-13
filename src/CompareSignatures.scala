object CompareSignatures {

 //  estimates similarity of two integer vectors – minhash signatures – as a fraction of components, in which they agree.
	def compare_sig(sig1: Array[Double] , sig2: Array[Double], minHashLength:Int ): Double = {
			var agreements = 0 
					for (i<- 0 to minHashLength-1  ) {
						if (sig1(i) == sig2(i))
							agreements +=1  
					}
			return  agreements * 1.0/minHashLength
	}


	def main(args: Array[String]): Unit = {
			var s:Array[Double] =Array(1,3,3,7)
			var t:Array[Double] = Array(77,20,3,-5)
			println(compare_sig(s,t , s.size));
	}

}