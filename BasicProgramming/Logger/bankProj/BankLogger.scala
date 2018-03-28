package bankProj

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

 object BankLogger {

	/*creating Objects*/
	val logger:Logger=Logger.getLogger("bankProj")/*Setting the path of class*/

	  val consoleHandler : ConsoleHandler=new ConsoleHandler();
		try
		{
	      val fileHandler : FileHandler = new FileHandler("LoggerFile.log")

	
		    fileHandler.setFormatter(new SimpleFormatter());/*setting the formatter*/
		    fileHandler.setLevel(Level.SEVERE);				/*Setting the level*/
		     logger.addHandler(fileHandler);				/*Adding to file*/
		     consoleHandler.setLevel(Level.SEVERE);
		 	logger.addHandler(consoleHandler);
			}
			catch
			{
			  case e : Exception => println(e.printStackTrace())
				
			}
	
	/*Method for success Logger*/
	def StatusLogger(operationType : String,operationStatus : Boolean) : String =
	{
	  var result : String = "Failed"
	  if(operationStatus == true)
	  {
	    logger.log(Level.SEVERE,operationType +" : Success");
		  result = "Success"
	  }
		return result
	}
	/*Method for Failure Logger*/
	def StatusLogger(operationType : String,e : Exception)  =
	{
		logger.log(Level.SEVERE,operationType + " : "+e.toString());
		
	}
}
