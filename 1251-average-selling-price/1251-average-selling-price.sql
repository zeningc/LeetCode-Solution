# Write your MySQL query statement below
with cte as (
    select p.product_id,
        u.units cnt,
        p.price * u.units total
    from UnitsSold u join Prices p on u.product_id=p.product_id
        and u.purchase_date between p.start_date and p.end_date
)
select product_id, round(sum(total) / sum(cnt), 2) average_price
from cte
group by product_id
