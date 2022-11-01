# Write your MySQL query statement below
with dept_month_avg as (
    select DATE_FORMAT(s.pay_date, '%Y-%m') pay_month, e.department_id, sum(s.amount) / count(s.amount) avg_salary, sum(s.amount) amt, count(s.amount) cnt 
    from Employee e
    join Salary s on e.employee_id = s.employee_id
    group by month(s.pay_date), e.department_id
),
all_month_avg as (
    select pay_month, sum(amt) / sum(cnt) avg_salary
    from dept_month_avg
    group by pay_month
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

# with dept_month_avg as (
#     select DATE_FORMAT(s.pay_date, '%Y-%m') pay_month, e.department_id, sum(s.amount) / count(s.amount) avg_salary, sum(s.amount) amt, count(s.amount) cnt 
#     from Employee e
#     join Salary s on e.employee_id = s.employee_id
#     group by month(s.pay_date), e.department_id
# )
#     select pay_month, sum(amt) / sum(cnt) avg_salary
#     from dept_month_avg
#     group by pay_month, department_id
