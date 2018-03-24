import jdk.nashorn.internal.ir.ContinueNode


object IpAddress {
  def main(args: Array[String]){
    if(validateIP("172.16.123.2"))
    {
      println("Well! Your IP Address is in Format..")
    }
    else
      println(" IP Address is not in Format.. Check")
    
  }
  def validateIP(ipAddress:String):Boolean={
    var ipCount:Int=0
   var count:Int=0
   var len:Int=ipAddress.length()
   var ipArr=new Array[Char](len)
   ipArr=ipAddress.toCharArray()
   if((ipArr.length >= 7) && (ipArr.length <= 15))
    {
     if(ipArr(0)!='.'&&ipArr(len-1)!='.'){
       for(i<-0 to len-1){
         if(ipArr(i)=='.'){
           count=count+1
         }
       }
       if(count==3){
         var dot=ipArr.indexOf('.')
         if(ipArr(dot)!=ipArr(dot+1)){
           for(i<-0 to len-1){
             if((ipArr(i)>='0' && ipArr(i)<='9')||ipArr(i)=='.'){
               ipCount=ipCount+1
             }
           }
           if(ipCount==len){
             return true
           }
           else{
             return false
           }
         }
         else{
           return false
         }
       }
       else{
         return false
       }
     }
     else{
       return false
     }
    }
   
   else{
     return false
   }
    
  }
  /*def validateIPAddress(ipAddress: String):Boolean={
   
    var count :Int =0
    var len = ipAddress.length()
    var ipAddressArr=new Array[Char](len)
    ipAddressArr = ipAddress.toCharArray()
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    if((ipAddressArr.length >= 7) && (ipAddressArr.length <= 15))
    {
    
      
     if(ipAddressArr(0) !='.' && ipAddressArr.length-1 !='.')
      {
     
        for(i <- 0 to (ipAddressArr.length-1))
         {
           
            if(ipAddressArr.contains("."))
            {
              count =count+1
             
            }
         }
            
            if(count == 3)
            {
             
              if(ipAddressArr(i)=='.' && ipAddressArr(i+1)!='.')
              {
               
              }
             
            }
         
      } 
    }
   
  }*/
}