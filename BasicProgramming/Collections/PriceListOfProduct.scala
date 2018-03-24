

object PriceListOfProduct {
 def main(args: Array[String]) {
    var productList = Map("225" -> "500.75",
                        "226" -> "600.80",
                        "227" -> "715.00",
                        "228" -> "860.20",
                        "229" -> "698.35",
                        "230" -> "744.20",
                        "231" -> "125.50",
                        "232" -> "263.40",
                        "233" -> "640.00",
                        "234" -> "400.00"
                        )
                        
      println("Please enter the product ID between 225-234 to find the product price")
      var productID : String = Console.readLine()
      
      if(productList.contains(productID)){
        println("The price is :" +productList(productID))
      } else
        println("Entered Product ID is Wrong! Please enter the product ID between 225-234")
 }
}