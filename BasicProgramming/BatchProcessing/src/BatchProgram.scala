import java.io.FileWriter
import java.io.FileNotFoundException

class Product {
  var pcode: Int = 0;
  var pname: String = ""
  var price: Int = 0
  var qty: Int = 0
}

/* Summary
 * Order Management System for a retail store
 * This object is used to handle the transaction of goods 
 * Product Name and quantity are taken as input and according to 
 * the entered details the product will be processed 
 */
object BatchProgram {

  def main(args: Array[String]) {

    try 
    {
      // Initializing the Shipping file and Bending file
      val shipping_File: FileWriter = new java.io.FileWriter("./src/Ship_File.csv", true)
      val pending_File: FileWriter = new java.io.FileWriter("./src/Pend_File.csv", true)
      // Initializing the Product List file
      val bufferedSource = io.Source.fromFile("./src/Shop_File.csv")
      val requirement= io.Source.fromFile("./src/Transaction_File.csv")
      var length = 0;
      var Prod = new Product();

      // Initializing the object to write the data into the file
      
      val ship = new java.io.PrintWriter(shipping_File)
      val pending = new java.io.PrintWriter(pending_File)

      // Creating the temporary array to store the product list
      var arr: Array[Product] = new Array[Product](5)

      // Reading the shop file line by line and storing it in array using 
      // temporary product object
      println("Product_code, Produt_Name, Product_Price, Quantity_on_hand")
      for (line <- bufferedSource.getLines.drop(1)) {
        length += 1;
        val cols = line.split(",").map(_.trim)
        var product = new Product;
        product.pcode = cols(0).toInt;
        product.pname = cols(1).toString()
        product.price = cols(2).toInt
        product.qty = cols(3).toInt
        arr.update(length, product)

        // Print Product details
        println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
      }
      bufferedSource.close()

     
      
      for (line <- requirement.getLines().drop(1)) 
      {
         // Variable to check the product availability
         var checkQty = 0
         // Variable to check the product
         var checkProduct = 0
         val cols = line.split(",").map(_.trim)
         var transID = cols(0).toInt;
         var pCode = cols(1).toInt;
         var quantity = cols(2).toInt;
         for (args1 <- 1 to arr.length - 1) 
         {
           if (arr(args1).pcode.equals(pCode)) 
           {
             // set the checkProduct to q if the product is available
             checkProduct = 1
             if ((arr(args1).qty) == 0) {
               checkQty = 2;
             }
             else if ((arr(args1).qty) >= quantity) {
               // Storing particular product details in the product object
               Prod.pcode = arr(args1).pcode;
               Prod.pname = arr(args1).pname
               Prod.price = arr(args1).price
               Prod.qty = arr(args1).qty;
               arr(args1).qty -= quantity;
                        
               // Write the shipping details(Transaction Code, Bill amount) in ship file
               ship.append(String.format("%n"));
               ship.append(transID.toString() + ",")
               ship.append((Prod.price * quantity).toString())
                   
               val shop = new java.io.PrintWriter(new java.io.FileOutputStream("./src/Shop_File.csv", false));
               shop.write("Product_code, Produt_Name, Product_Price, Quantity_on_hand")
               for (arg <- 1 to (arr.length - 1)) {
                 var product = new Product;
                 product = arr(arg)
                 shop.write(String.format("%n"));
                 shop.write(product.pcode + ",");
                 shop.write(product.pname + ",");
                 shop.write(product.price + ",");
                 shop.write((product.qty).toString());
               }
               shop.flush()
               Console.println("You successfully Ordered the Product");
               Console.println("Order Details");
               Console.println("---------------")
               Console.println("Transaction_ID : " + transID);
               Console.println("Bill Amount : " + Prod.price * quantity);
             }
             // set variable to 1 if the entered quantity is greater than
             // quantity on hand
             else if ((arr(args1).qty) < quantity) {
             checkQty = 1
           }             
         }
       }
       if (checkQty == 1) {
         pending.append(String.format("%n"));
         pending.append(transID.toString() + ",")
         pending.append("NS")
         Console.println("\nTransaction_ID : "+transID)
         Console.println("Sorry!....Out of stock");
       }
       else if (checkQty == 2) {
         pending.append(String.format("%n"));
         pending.append(transID.toString() + ",")
         pending.append("NA")
         Console.println("\nTransaction_ID : "+transID)
         Console.println("Currently The product is not available in our store")
       }
       if (checkProduct == 0) {
         Console.println("\nTransaction_ID : "+transID)
         Console.println("Ooops....The entered product is not available in our store")
       }
      }
      pending.close()
      ship.close()
    } 
    catch {
      case fnf: FileNotFoundException => println("File is not available in the mentioned directory" + fnf);
      case npe: NullPointerException => println("Sorry...Unable to get the data" + npe);
      case e: Exception => println("Oops.. Exception Occured" + e.printStackTrace())
    }
  }
}