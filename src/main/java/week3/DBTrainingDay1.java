package week3;

/**
 * -- select * from hr.employees
 *
 * -- select first_name as fn, (
 * --     case
 * --         when last_name = 'Abel'
 * --         then '1'
 * --         else '0'
 * --     end
 * -- ) as ln
 * -- from hr.employees
 * -- where first_name = 'Ellen'
 *
 * -- count , min, max, sum, avg
 *
 * select count(COMMISSION_PCT)
 * from hr.employees
 *
 * select sum(salary)
 * from hr.employees
 *
 * select min(salary)
 * from hr.employees
 *
 * select count(*)
 * from hr.employees
 * where salary > (select min(salary) from hr.employees)
 *
 * --write a query to get 2nd highest salary from hr.employees
 *
 * select max(salary)
 * from hr.employees
 * where salary < (
 *     select max(salary)
 *     from  hr.employees
 * )
 *
 * select max(salary)
 * from hr.employees
 * where salary < max_salary
 *
 * select *
 * from hr.employees e1
 * where 0 = (select count(distinct(salary)) from hr.employees e2 where e1.salary < e2.salary)
 *
 *
 * id salary
 * 1   100  -> 2 salaries are larger than current row  -> this row is 3rd highest salary
 * 2   101  -> 1 salary is larger than 101 -> this is 2nd highest salary
 * 3   400  -> 0 salaries larger than 400 -> this is highest salary
 *
 *
 * select *
 * from (select salary, dense_rank() over (order by salary desc) as rank
 *     from hr.employees)
 * where rank = 2
 *
 *
 * Home3 (AI)
 * 1. use chat-gpt / ai tool to generate alarm data in csv
 * 2. use isolation forest model to train alarm data -> identify false alarm
 * 3. dump the model into a file to your local
 * 4. create AWS free account
 * 5. create Api Gateway(post, /verify, pass data through request body) -> linked with AWS Lambda
 * 6. use Lambda SAM as local lambda development tool
 * 7. upload model to AWS S3
 * 8. setup libraries in lambda layer
 * 9. lambda load model from S3  -> serve the result through api gateway
 *
 * test api through Postman
 * Deadline is Thursday morning 10am EDT
 */