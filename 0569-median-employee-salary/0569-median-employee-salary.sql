WITH cte AS (
    SELECT *,
        COUNT(1) OVER (PARTITION BY company) cnt,
        ROW_NUMBER() OVER (PARTITION BY company ORDER BY salary) row_num
    FROM employee
)
SELECT id, company, salary
FROM cte
where (cnt % 2 = 0 and (row_num = cnt / 2 OR row_num = cnt / 2 + 1))
OR (cnt % 2 = 1 and row_num = ceil(cnt / 2))
