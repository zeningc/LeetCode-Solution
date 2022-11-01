# Write your MySQL query statement below
with all_month_avg as (
    select DATE_FORMAT(s.pay_date, '%Y-%m') pay_month, sum(s.amount) / count(s.amount) avg_salary 
    from Employee e
    join Salary s on e.employee_id = s.employee_id
    group by month(s.pay_date)
),
dept_month_avg as (
    select DATE_FORMAT(s.pay_date, '%Y-%m') pay_month, e.department_id, sum(s.amount) / count(s.amount) avg_salary 
    from Employee e
    join Salary s on e.employee_id = s.employee_id
    group by month(s.pay_date), e.department_id
)
select d.pay_month, d.department_id, 
    case when d.avg_salary = a.avg_salary
        then 'same'
    when d.avg_salary < a.avg_salary
        then 'lower'
    else 'higher'
    end comparison
from dept_month_avg d
join all_month_avg a on d.pay_month = a.pay_month