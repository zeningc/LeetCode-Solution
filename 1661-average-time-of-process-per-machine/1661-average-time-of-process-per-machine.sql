# Write your MySQL query statement below
with start_time as (
    select *
    from Activity
    where activity_type='start'
),
end_time as (
    select *
    from Activity
    where activity_type='end'
)
select start_time.machine_id, round(avg(end_time.timestamp - start_time.timestamp), 3) processing_time
from start_time join end_time on start_time.process_id = end_time.process_id and start_time.machine_id = end_time.machine_id
group by start_time.machine_id