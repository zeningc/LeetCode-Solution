with cte as (
        select order_id,
        sum(quantity) / count(1) avg_qty,
        max(quantity) max_qty
        from OrdersDetails
        group by order_id
)
select distinct order_id
from cte
where max_qty > (select max(avg_qty) from cte)