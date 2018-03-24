/*Program to implement Trait to facilitate Basic CRUD operations on a class.*/

trait ICompanyserviceprovider{
  def addEmployee(employeeObj : Employee)
  def editEmployee (empID : Int)
  def deleteEmployee (empID : Int)
  def displayEmployee (empID : Int)
  def checkEmployee (empID : Int) : Boolean
}


class Employee {
  var empID : Int = 0
  var employeeName : String = ""
  var employeeSalary : Double = 0.0
  
  def this(empID:Int,employeeName:String,employeeSalary:Double){
    this()
    this.empID=empID
    this.employeeName=employeeName
    this.employeeSalary=employeeSalary
  }
}


class HTCglobalservices extends ICompanyserviceprovider{
  var companyID : Int = 0
  var address : String = ""
  var EmployeeMap : Map[Int,Employee] = Map()
  
  
  //Method to add Employee details
   def addEmployee(employeeOBJ : Employee){
    EmployeeMap += (employeeOBJ.empID ->
                    employeeOBJ)
       println("Added successfully")
       
      if(EmployeeMap.isEmpty){
        println("Not added")
      }
  }
  
  
  //Method to check if employee is present using given ID
  def checkEmployee(EmpId : Int) : Boolean ={
     if(EmployeeMap.contains(EmpId)){
       return true
     }
     else
       return false
  }
  
  
  //Display the employee details
  def displayEmployee(EmpId : Int){
    var empObject = new Employee()
     if(checkEmployee(EmpId)){
      println("Employee Name : " +EmployeeMap(EmpId).employeeName)
      println("Employee Salary : " +EmployeeMap(EmpId).employeeSalary)
    }
     else
       println("Invalid ID")
  }
  
  //Edit employee details
   def editEmployee(EmpId : Int){
    if(checkEmployee(EmpId)){
    println("If you want to edit name enter N |  If you want to edit salary enter S | Enter A to edit both")
    var choice : String = Console.readLine()
    
    //Edit Name
    if(choice.equals("N")){
      println("Enter the Employee name to update")
      var updateName : String = Console.readLine()
      EmployeeMap(EmpId).employeeName=updateName
      println("After updating name")
      displayEmployee(EmpId)
    }
    
    //Edit Salary
     if(choice.equals("S")){
      println("Enter the employee salary to update")
      var updateSalary : Double = Console.readDouble()
      EmployeeMap(EmpId).employeeSalary=updateSalary
       println("After updating Salary")
      displayEmployee(EmpId)
    }
    
    //Edit both salary and name
     if(choice.equals("A")){
      println("Enter the employee name to update")
      var updateName : String = Console.readLine()
      println("Enter the employee Salary to update")
      var updateSalary : Double = Console.readDouble()
      EmployeeMap(EmpId).employeeName=updateName
      EmployeeMap(EmpId).employeeSalary=updateSalary
      println("After updating employee name and salary")
      displayEmployee(EmpId)
    }
  }
    else
      println("Enter valid ID")
  }
   
   
   def deleteEmployee(EmpId : Int){
     if(checkEmployee(EmpId)){
       
       EmployeeMap -= EmployeeMap(EmpId).empID
       
       println("Check if that ID is present of not after deleting...")
       displayEmployee(EmpId)
     }
      else
       println("Invalid ID")
   }
}


object HTCglobalservices {
  def main(args: Array[String]){
    
    
    
    var HTCglobalServicesObject = new HTCglobalservices()
    var employeeObject = new Employee ()
    
    
    
  
 // adding employee details to Employee object
  var employee1 = new Employee (1,"Atchaya",25000)
  var employee2 = new Employee (2,"Srinath",25000)
  var employee3 = new Employee (3,"Vignesh",25000)
  var employee4 = new Employee (4,"Venkatesh",25000)
  var employee5 = new Employee (5,"Eshika",25000)
  var employee6 = new Employee (6,"Meena",25000)
    
  
  //calling the method inside HTCGlobalServices to add employees
  HTCglobalServicesObject.addEmployee(employee1)
  HTCglobalServicesObject.addEmployee(employee2)
  HTCglobalServicesObject.addEmployee(employee3)
  HTCglobalServicesObject.addEmployee(employee4)
  HTCglobalServicesObject.addEmployee(employee5) 
  HTCglobalServicesObject.addEmployee(employee6)
   
  //Get the employee ID from user to display his details
  println("Enter the employee ID to display his details")
  var id : Int = Console.readInt()
  
  //Display the employee details by invoking the method
  HTCglobalServicesObject.displayEmployee(id)
  
  //Get the employee ID from user to edit record
  println("Enter the employee ID to edit")
  var EditEmpid : Int = Console.readInt()
  
  //Method call
  HTCglobalServicesObject.editEmployee(EditEmpid)
  
  
  //Method call to delete the employee
    println("Enter the employee ID to Delete")
    var EmpId : Int = Console.readInt()
    HTCglobalServicesObject.deleteEmployee(EmpId)
  }
}