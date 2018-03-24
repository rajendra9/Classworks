

object MapExample {
  def main(args:Array[String]){
    var stateCapitals1=Map("Tamil Nadu" -> "Chennai","Maharashtra" -> "Mumbai")
    var stateCapitals2:Map[String,String]=Map()
    stateCapitals2+=("Kerela" -> "Trivandrum")
    stateCapitals2+=("Gujarat" -> "Ahmedabad")
    println(stateCapitals1.keys)
    println(stateCapitals2.keys)
    println(stateCapitals1.values)
    println(stateCapitals2.values)
  }
}