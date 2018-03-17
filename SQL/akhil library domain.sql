
--LIBRARY DOMAIN--

--1)Title wise how may copies of books are available in the ‘rdbms’ category?

SELECT*
FROM BOOKS

SELECT title, copy_no
FROM BOOKS
WHERE book_category='RDBMS'

--2)Which publisher(s) books are the maximum available in the library?

SELECT COUNT(BOOK_ID),PUBLISHER_ID
FROM BOOKS
GROUP BY PUBLISHER_ID
HAVING COUNT(BOOK_ID)>=ALL(SELECT COUNT(BOOK_ID)
			   FROM BOOKS
                           GROUP BY PUBLISHER_ID)

 --3)3. Prepare a list of books that are due as on date, in delay descending sequence as shown in the following format:
--Title member name due-date delay

SELECT*
FROM BOOK_TRANSACTION


SELECT books.Title,members.member_name,book_transaction.due_date,(book_transaction.return_date-book_transaction.due_date) AS delay
FROM BOOK_TRANSACTION book_transaction INNER JOIN MEMBERS members
ON book_transaction.member_id=members.member_id
INNER JOIN BOOKS books 
ON books.book_id=book_transaction.book_id
GROUP BY books.Title,members.member_name,book_transaction.due_date,(book_transaction.return_date-book_transaction.due_date)
ORDER BY DELAY DESC

--4). Which books were not lent during the last quarter of the year(oct-dec2003)?

SELECT*
FROM BOOKS

SELECT books.book_id,books.title
FROM BOOKS books INNER JOIN BOOK_TRANSACTION book_transaction 
ON books.book_id=book_transaction.book_id
WHERE book_transaction.issue_date NOT BETWEEN '2010-09-01' AND '2010-12-31'


--5)Which author’s book is costliest?

SELECT authors.author_id,authors.author_name
FROM BOOKS books INNER JOIN AUTHORS authors
ON books.author_id=authors.author_id
GROUP BY authors.author_id, authors.author_name
HAVING MAX(books.price)>=(SELECT MAX(PRICE)
                          FROM BOOKS)

--6)Which is being the favorite book (frequently accessed) of the frequent accesser of the library?

SELECT*
FROM BOOK_TRANSACTION

SELECT*
FROM BOOKS

SELECT book_id
FROM BOOK_TRANSACTION
GROUP BY BOOK_ID,member_id
HAVING COUNT(book_id)>=ALL(

                SELECT COUNT(book_id)
                FROM BOOK_TRANSACTION
                GROUP BY BOOK_ID,member_id
                HAVING member_id=(SELECT member_id
                                  from  book_transaction
                                  GROUP BY member_id
                                   having COUNT(member_id)>=ALL(SELECT COUNT(member_id)
                                                                FROM  book_transaction
                                                                GROUP BY member_id)

                                     )

                        )  


--7)Who is the frequent accesser in each department?

SELECT*
FROM BOOK_TRANSACTION

SELECT*
FROM MEMBERS

SELECT members1.member_id,members1.member_dept
FROM MEMBERS members1 INNER JOIN BOOK_TRANSACTION book_transaction1
ON members1.member_id=book_transaction1.member_id
GROUP BY members1.member_id
HAVING members1.member_id=(SELECT members2.member_id
                         FROM MEMBERS members2 INNER JOIN BOOK_TRANSACTION book_transaction2
                         ON members2.member_id=book_transaction2.member_id
                         WHERE members2.member_dept=members1.member_dept
                         GROUP BY members2.member_id
                         HAVING COUNT(members2.member_id)>=ALL(SELECT COUNT(members2.member_id)
                                                               FROM MEMBERS members2 INNER JOIN BOOK_TRANSACTION book_transaction2
                                                               ON members2.member_id=book_transaction2.member_id
                                                               WHERE members2.member_dept=members1.member_dept
                                                               GROUP BY members2.member_id)
                          )
                          

--8)List the book detail taken by each member.


SELECT members.member_id,members.member_name,books.title,books.book_id,books.book_category,books.edition,books.ye_of_pub,books.price
FROM MEMBERS members INNER JOIN BOOK_TRANSACTION book_transaction
ON members.member_id=book_transaction.member_id
INNER JOIN  BOOKS books
ON books.book_id=book_transaction.book_id

--9)Which are the categories of books that are least utilized?

SELECT*
FROM BOOK_TRANSACTION
SELECT*
FROM BOOKS

SELECT books.book_category
FROM BOOKS books INNER JOIN BOOK_TRANSACTION book_transaction 
ON books.book_id=book_transaction.book_id
GROUP BY books.book_category
HAVING COUNT(books.book_id)<=ALL(SELECT COUNT(books.book_id)
			         FROM BOOKS books INNER JOIN BOOK_TRANSACTION book_transaction 
				 ON books.book_id=book_transaction.book_id
				 GROUP BY books.book_category)

--10)Find the books that is lent after fewer days of its acquisition?

--11)List the authors belonging to each publication

SELECT*
FROM AUTHORS
SELECT*
FROM BOOKS
SELECT*
FROM publishers


SELECT publishers.publisher_id,authors.author_id,authors.author_name
FROM BOOKS books INNER JOIN PUBLISHERS publishers
ON books.publisher_id=publishers.publisher_id
INNER JOIN AUTHORS authors
on books.author_id=authors.author_id
GROUP BY publishers.publisher_id,publishers.publisher_name,authors.author_id,authors.author_name

--12)List the bookname, author_name, publisher_name for the books available in rack no. 5
SELECT*
FROM BOOKS

select books.title, authors.author_name, publishers.publisher_name
FROM BOOKS books INNER JOIN PUBLISHERS publishers
ON books.publisher_id=publishers.publisher_id
INNER JOIN AUTHORS authors
on books.author_id=authors.author_id
where books.rack_number=5

--13)Who is the most delaying member in the library?
SELECT*
FROM BOOKS
SELECT*
FROM BOOK_TRANSACTION

SELECT booktransaction.member_id
FROM BOOKS books INNER JOIN BOOK_TRANSACTION booktransaction 
ON books.book_id=booktransaction.book_id
GROUP BY booktransaction.member_id, (booktransaction.return_date-booktransaction.due_date)
HAVING (booktransaction.return_date-booktransaction.due_date)>=ALL(SELECT booktransaction.return_date-booktransaction.due_date
								   FROM BOOKS books INNER JOIN BOOK_TRANSACTION booktransaction 
								   ON books.book_id=booktransaction.book_id
								   GROUP BY booktransaction.member_id, (booktransaction.return_date-booktransaction.due_date)
								  )


--14)Count the inactive members in each department. (All Possibilities)
--not answer to this question

SELECT  COUNT(members.member_id),members.member_dept
FROM MEMBERS members LEFT OUTER JOIN  BOOK_TRANSACTION book_transaction
ON members.member_id=book_transaction.member_id
GROUP BY members.member_id, members.member_dept
HAVING members.member_id NOT IN (SELECT  members.member_id
                                     FROM MEMBERS members INNER JOIN  BOOK_TRANSACTION book_transaction
                                     ON members.member_id=book_transaction.member_id
                                     GROUP BY  members.member_id,members.member_dept)


--1st

  SELECT COUNT(member_id), member_dept
  FROM MEMBERS
  WHERE member_id NOT IN (SELECT member_id
                          FROM BOOK_TRANSACTION)
  GROUP BY MEMBER_DEPT  


--2nd

  SELECT COUNT(member_id), member_dept
  FROM MEMBERS
  WHERE member_id IN (SELECT member_id
                      FROM MEMBERS
                      EXCEPT(SELECT member_id
                      FROM BOOK_TRANSACTION)) 
  GROUP BY MEMBER_DEPT                    
--3rd

  SELECT COUNT(member_id), member_dept
  FROM MEMBERS members
  WHERE NOT EXISTS (SELECT ''
                    FROM BOOK_TRANSACTION booktransaction
                    WHERE members.member_id=booktransaction.member_id)                        
  GROUP BY MEMBER_DEPT 
--4th

  SELECT COUNT(members.member_id), member_dept
  FROM MEMBERS members
  LEFT OUTER JOIN BOOK_TRANSACTION booktransaction
  ON members.member_id=booktransaction.member_id
  WHERE booktransaction.member_id is NULL
  GROUP BY member_dept



--15)15.Write a PL/PGSQL function
--(a) Should insert the new Book to the Book table and return “successfully inserted” message.
--(b) If the book already exists, it should update the row with the new fields given and should return “successfully updated” message.


SELECT*
FROM BOOKS

CREATE OR REPLACE FUNCTION insertorupdatebook(bookid integer, booktitle varchar(30), bookcategory varchar(10))
RETURNS character
AS
$$
	DECLARE flag int;
BEGIN

            flag=0;
            SELECT bookid into flag
            FROM BOOKS
            WHERE book_id=bookid;

	    IF(flag=bookid) THEN
			UPDATE BOOKS
			SET title=booktitle,
			book_category=bookcategory
			WHERE book_id=bookid;
			RETURN 'successfully_updated' ;
            ELSE
			INSERT INTO BOOKS(book_id,title,book_category)
			VALUES(bookid,booktitle,bookcategory);
			RETURN 'successfully_inserted';

            END IF;
         
END
$$ 
LANGUAGE PLPGSQL

SELECT insertorupdatebook(111, 'JAVA', 'OOPS');


SELECT insertorupdatebook(38, 'C#', 'OOPS');


























