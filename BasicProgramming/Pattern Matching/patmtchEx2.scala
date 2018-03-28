package htc_seed4_Scala

object patmtchEx2 {
    def main(args: Array[String]){
    val one = new example(1)
    val two = new example(2)
    val three = new example(3)
    val four = new example(4)
    val five = new example(5)
  
  
 for (matcmp <- List(one, two, three, four, five)) {
    matcmp match {
      case example(1)=>println(matcmp+" : This is first set")
      case example(2)=>println(matcmp+" : This is second set")
      case example(3)=>println(matcmp+" : This is third set")
      case example(y)=>println(matcmp+" : "+y+" belongs to default set")  
  }
 }
 }
  case class example(y:Int)
}
