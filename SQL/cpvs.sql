/* CREATE VIEW autores_californianos
AS SELECT au_fname from authors 
WHERE state = 'CA'
 */

/* CREATE VIEW tit_pop
AS SELECT tittle FROM Title WHERE type='popular_comp' */


/* CREATE VIEW autor_info
AS (
    SELECT a.au_fname, tl.title, tl.ytd_sales, (tl.ytd_sales*tl.price) AS ganancia
    FROM Authors a
    JOIN TitleAuthor t 
    ON a.au_id = t.au_id 
    JOIN Titles tl
    ON t.title_id = tl.title_id
)
 */
/* 
CREATE VIEW cant_lib_au
AS (
    SELECT a.au_fname, COUNT(ta.title_id) AS cantidad_libros
    FROM Authors a
    JOIN TitleAuthor ta
    ON ta.au_id = a.au_id
    WHERE cantidad_libros > 2 --- el otro inciso
    GROUP BY a.au_fname
) */

GRANT INSERT, DELETE ON authors
TO Juan

GRANT ALL PRIVILEGIE ON Employee
TO Juan

GRANT INSERT ON Title
TO Jose
WITH GRANT OPTION

REVOKE DELETE ON Authors
TO Jose

REVOKE INSERT ON Titles
TO Jose
WITH GRANT OPTION