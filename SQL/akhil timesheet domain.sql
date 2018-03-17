                                                           --TIMESHEET DOMAIN--

--1)Which EMPLOYEE has not yet been allocated to any PROJECT?

 SELECT emp_id,emp_name
 FROM EMPLOYEE 
 WHERE emp_id NOT IN(SELECT emp_id
	             FROM ALLOCATION)

--2)Which role played by the employee 'E03' frequently?

SELECT role_id
FROM ALLOCATION
WHERE emp_id='E03'
GROUP BY emp_id,role_id
HAVING COUNT(role_id)>=ALL(SELECT COUNT(role_id)
			   FROM ALLOCATION
		           WHERE emp_id='E03'
			   GROUP BY emp_id,role_id)

--3)How many employees were there in costliest Project?

SELECT COUNT(DISTINCT emp_id),project_id
FROM ALLOCATION 
GROUP BY project_id
HAVING project_id=(SELECT project_id
	          FROM ALLOCATION
	          GROUP BY project_id,emp_id
	          HAVING SUM(amount_per_day*(to_date-from_date))>=ALL(SELECT SUM(amount_per_day*(to_date-from_date))
								  FROM ALLOCATION
								  GROUP BY project_id,emp_id)
	         )

      
--4)Prepare a report in this format
--Emp Name Role Name Number of Projects

SELECT employee.emp_name,role.role_title, COUNT(allocation.project_id) as NUMBER_OF_PROJECTS
FROM EMPLOYEE employee INNER JOIN ALLOCATION allocation
ON employee.emp_id=allocation.emp_id
INNER JOIN ROLE role
ON allocation.role_id=role.role_id
GROUP BY employee.emp_id,role.role_title

--5)Prepare a report in this format
--Mgr_id Number of Employees

SELECT mgr_id, COUNT(emp_id)
FROM EMPLOYEE
WHERE MGR_ID IS not null
GROUP BY mgr_id


--6)Prepare a report in this format
--Emp id Role id Project id Total Amount collected
CREATE VIEW view
AS
SELECT emp_id, role_id, project_id, SUM(amount_per_day*(to_date-from_date)) AS Total_Amonut_Collected
FROM ALLOCATION
GROUP BY emp_id, role_id, project_id

SELECT*
FROM view

--7)7. Prepare a report in this format?
--Emp id Mgr Id Comments
--123 432 Has Manager
--456 No Manger
--If Manager_id is NULL for an employee then the comment must be "No Manager"

SELECT emp_id, mgr_id,'has manager' AS comments FROM employee
where mgr_id IS NOT NULL
UNION
SELECT emp_id, mgr_id, 'No manager' AS comments FROM employee
where mgr_id IS  NULL

--8)Which employees earns more than his/her manager?

SELECT employee2.emp_id
FROM EMPLOYEE employee1 JOIN EMPLOYEE employee2
ON employee1.emp_id=employee2.mgr_id
WHERE employee2.salary>employee1.salary

--9)Which manager joined after his/her sub ordinates?

SELECT employee1.emp_id
FROM EMPLOYEE employee1 JOIN EMPLOYEE employee2
ON employee2.emp_id=employee1.mgr_id
WHERE employee1.hire_date<employee2.hire_date

--12)Display the departments that does not have employees(ALL POSSIBILITIES)?

--ONE
SELECT dept_id,dept_name
FROM DEPARTMENT 
WHERE dept_id NOT IN(SELECT dept_id
                     FROM EMPLOYEE)
--TWO   
                  
SELECT department.dept_id,department.dept_name
FROM EMPLOYEE employee RIGHT OUTER JOIN DEPARTMENT department 
ON employee.dept_id=department.dept_id
WHERE employee.dept_id is  NULL

--THREE
SELECT dept_id,dept_name
FROM DEPARTMENT department 
WHERE NOT EXISTS(SELECT dept_id
                 FROM EMPLOYEE employee
                 WHERE employee.dept_id=department.dept_id)
--four
SELECT dept_id
FROM DEPARTMENT
EXCEPT (SELECT dept_id
        FROM EMPLOYEE)
--13)Find the department which has employees(ALL POSSIBILITIES)?
--ONE
SELECT dept_id,dept_name
FROM DEPARTMENT 
WHERE dept_id IN(SELECT dept_id
                 FROM EMPLOYEE)
--TWO   
                  
SELECT DISTINCT department.dept_id,department.dept_name
FROM EMPLOYEE employee RIGHT OUTER JOIN DEPARTMENT department 
ON employee.dept_id=department.dept_id
WHERE employee.dept_id is NOT NULL

--THREE
SELECT dept_id,dept_name
FROM DEPARTMENT department 
WHERE EXISTS(SELECT dept_id
              FROM EMPLOYEE employee
              WHERE employee.dept_id=department.dept_id)
              
--14)What is the total amount spent for unassigned employees?

SELECT SUM(salary)
FROM EMPLOYEE
WHERE emp_id IN
		(SELECT emp_id
		FROM EMPLOYEE
		WHERE emp_id NOT IN(SELECT emp_id
				    FROM ALLOCATION)
		)

15)How many projects are handled by senior most employee?

--ROUGH WORK
SELECT *FROM EMPLOYEE
SELECT *FROM EMPLOYEESKILL
SELECT *FROM ALLOCATION
SELECT *FROM ROLE
SELECT *FROM T_PROJECT
SELECT *FROM SKILL
SELECT *FROM DEPARTMENT
SELECT *FROM MOBILERECHARGE
---------------------------------------------------------------------------------------------------------------------------------------
--ASSIGNMENT QUESTIONS
--1)List the members ineach dept who accessed the liberary during june 2003
SELECT*
FROM members

SELECT*
FROM BOOK_TRANSACTION

SELECT members.member_id, members.member_dept
FROM MEMBERS members INNER JOIN BOOK_TRANSACTION booktransaction
ON members.member_id=booktransaction.member_id
WHERE booktransaction.issue_date BETWEEN '2003-06-01' AND '2003-06-30'
--WHERE booktransaction.issue_date BETWEEN '2010-09-01' AND '2010-09-30'
GROUP BY members.member_dept, members.member_id

--2)which publication book is most delayed in returning?
SELECT*
FROM BOOKS

SELECT books.publisher_id
FROM BOOKS books INNER JOIN BOOK_TRANSACTION booktransaction
ON books.book_id=booktransaction.book_id
GROUP BY books.publisher_id,(booktransaction.return_date-booktransaction.due_date)
HAVING (booktransaction.return_date-booktransaction.due_date)>=ALL(SELECT (booktransaction.return_date-booktransaction.due_date)
								    FROM BOOKS books INNER JOIN BOOK_TRANSACTION booktransaction
							            ON books.book_id=booktransaction.book_id
							            GROUP BY (booktransaction.return_date-booktransaction.due_date))



 --3)which book is being returned very late frequently?

SELECT book_id
FROM BOOK_TRANSACTION
WHERE (return_date-due_date)>0
GROUP BY BOOK_ID
HAVING COUNT(book_id)>=ALL(

                SELECT COUNT(book_id)
                FROM BOOK_TRANSACTION
                WHERE (return_date-due_date)>0
                GROUP BY BOOK_ID
                HAVING book_id=(SELECT book_id
                                  from  book_transaction
                                  GROUP BY book_id,(return_date-due_date)
                                  having (return_date-due_date)>=ALL(SELECT (return_date-due_date)
                                                                FROM  book_transaction
                                                                GROUP BY book_id,(return_date-due_date))

                                     )

                        )  
  --4)which books were lent during the first quarter of the year?

  SELECT book_id,DATE_PART('YEAR',issue_date),DATE_PART('MONTH',issue_date)
  FROM BOOK_TRANSACTION
  WHERE DATE_PART('MONTH',issue_date) BETWEEN 10 AND 12
  GROUP BY book_id,DATE_PART('YEAR',issue_date),DATE_PART('MONTH',issue_date)
  ORDER BY DATE_PART('YEAR',issue_date),DATE_PART('MONTH',issue_date) ASC

--5)how many projects are completed till date?

SELECT project_id
FROM ALLOCATION
WHERE CURRENT_DATE>=to_date
--WHERE '2010-12-22'>=to_date

--6)List top 5 salaried employees?

WITH temptable 
AS
(SELECT emp_id,salary, DENSE_RANK() OVER(ORDER BY salary DESC) AS salaryrank 
 FROM EMPLOYEE)
 SELECT EMP_ID, SALARY
 FROM temptable
 LIMIT 5

--7)List top 3 departments with max no. of employees

SELECT dept_id, COUNT(emp_id) AS no_of_employees
FROM EMPLOYEE
GROUP BY dept_id
ORDER BY COUNT(emp_id) DESC
LIMIT 3

---8)FOR EACH DEPT display the 2nd max salary employee?

WITH second_high 
AS
(
SELECT emp_id,dept_id,salary, DENSE_RANK() OVER(PARTITION BY dept_id ORDER BY salary DESC) AS salary_rank 
FROM EMPLOYEE
)
 
 SELECT emp_id, salary,dept_id
 FROM second_high
 WHERE salary_rank=2

--------------------------------------------------------------------------------------------------------------------------------------- 

--ROUGH WORK
SELECT *FROM EMPLOYEE
SELECT *FROM EMPLOYEESKILL
SELECT *FROM ALLOCATION
SELECT *FROM ROLE
SELECT *FROM T_PROJECT
SELECT *FROM SKILL
SELECT *FROM DEPARTMENT
SELECT *FROM MOBILERECHARGE
-----------------------------------------MOVIES DOMAIN-------------------------------------------------------

--1) Which theater has the maximum ticket sales for the film directed by 'YASH CHOPRA', produced by 'KARAN JOHAR' and acted by 'SALMAN KHAN'?	


SELECT MAX(collectionmade),collections.theatorid
FROM MOVIEDETAILS moviedetails 
INNER JOIN ACTORS actors
on moviedetails.actorid=actors.actorid
INNER JOIN DIRECTORS directors
on moviedetails.directorid=directors.directorid
INNER JOIN PRODUCERS producers
on moviedetails.producerid=producers.producerid
INNER JOIN COLLECTIONS collections
on moviedetails.movieid=collections.movieid
WHERE actors.actorname='SALMAN KHAN' AND producers.producername='YASH CHOPRA' AND directors.directorname='YASH CHOPRA'
GROUP BY collections.theatorid
HAVING MAX(collectionmade)>=ALL(SELECT MAX(collectionmade)
				FROM MOVIEDETAILS moviedetails 
				INNER JOIN ACTORS actors
				on moviedetails.actorid=actors.actorid
				INNER JOIN DIRECTORS directors
				on moviedetails.directorid=directors.directorid
				INNER JOIN PRODUCERS producers
				on moviedetails.producerid=producers.producerid
				INNER JOIN COLLECTIONS collections
				on moviedetails.movieid=collections.movieid
				WHERE actors.actorname='SALMAN KHAN' AND producers.producername='YASH CHOPRA' AND directors.directorname='YASH CHOPRA'
				GROUP BY collections.theatorid)




--2)2) Which actress has done maximum films?

SELECT actressid
FROM moviedetails
group by actressid
having COUNT(actressid)>=ALL(SELECT COUNT(actressid)
FROM moviedetails
group by actressid)

--3)3) Which actor has not played in any films yet ?

SELECT ACTORID, ACTORNAME
FROM ACTORS
WHERE ACTORID NOT IN(SELECT ACTORID
                      FROM MOVIEDETAILS)

--4)4) Which producer has not produced any movie in the year 2011 ?  

SELECT PRODUCERID
FROM PRODUCERS
WHERE PRODUCERID NOT IN(SELECT PRODUCERID
                      FROM MOVIEDETAILS
                      WHERE DATE_PART('YEAR',releasedate)='2012')

--5)5) Display the movie details released in the month of JAN 2012 in the following format : 

SELECT MOVIES.MOVIENAME, ACTORS.ACTORNAME, DIRECTORS.DIRECTORNAME, PRODUCERS.PRODUCERNAME
FROM MOVIEDETAILS moviedetails 
INNER JOIN ACTORS actors
on moviedetails.actorid=actors.actorid
INNER JOIN DIRECTORS directors
on moviedetails.directorid=directors.directorid
INNER JOIN PRODUCERS producers
on moviedetails.producerid=producers.producerid
INNER JOIN MOVIES movies
on moviedetails.movieid=movies.movieid
WHERE DATE_PART('YEAR',moviedetails.releasedate)='2012' AND DATE_PART('MONTH',moviedetails.releasedate)='01'

--6) Display the collection details of the theater “ESCAPE” in the year 2012 in following format: 
--Movie Name Collection Amount Category Of Film

select movies.moviename,collectionmade,'SUPER HIT' AS COMMENTS
FROM MOVIEDETAILS moviedetails 
INNER JOIN MOVIES movies
on moviedetails.movieid=movies.movieid
INNER JOIN COLLECTIONS collections
on moviedetails.MOVIEID=collections.movieid
INNER JOIN THEATORS theators
ON collections.theatorid=theators.theatorid
where theators.theatorname='ESCAPE' AND DATE_PART('YEAR',releasedate)='2012' AND collectionmade>2000
UNION
select movies.moviename,collectionmade,'DABBA FILM' AS COMMENTS
FROM MOVIEDETAILS moviedetails INNER JOIN MOVIES movies
on moviedetails.movieid=movies.movieid INNER JOIN COLLECTIONS collections
on moviedetails.MOVIEID=collections.movieid
INNER JOIN THEATORS theators
ON collections.theatorid=theators.theatorid
where theators.theatorname='ESCAPE' AND DATE_PART('YEAR',releasedate)='2012' AND collectionmade<2000

20)--)Display the movie details, which has been directed by senior most director and producer by senior most producer?


SELECT *
FROM MOVIEDETAILS moviedetails 
INNER JOIN DIRECTORS directors
on moviedetails.directorid=directors.directorid
INNER JOIN PRODUCERS producers
on moviedetails.producerid=producers.producerid
INNER JOIN MOVIES movies
on moviedetails.movieid=movies.movieid
WHERE directors.dob = (SELECT MIN(DOB)
		        FROM DIRECTORS) AND producers.dateofbirth = (SELECT MIN(dateofbirth)
                                                                      FROM PRODUCERS)




--ROUGH
SELECT* FROM PRODUCERS
SELECT* FROM MOVIEDETAILS
SELECT* FROM ACTORS
SELECT* FROM ACTRESS
SELECT* FROM DIRECTORS
SELECT* FROM MOVIES
SELECT* FROM COLLECTIONS
SELECT* FROM THEATORS













