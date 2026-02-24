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
 * select sum(salary)
 * from hr.employees
 *
 *
 * select department_id, sum(salary), count(*)
 * from hr.employees
 * where salary < 5000
 * group by department_id
 * having sum(salary) > 100000
 *
 * select *
 * from hr.employees
 *
 * -----------------
 * --cross join
 * select *
 * from A, B
 *
 *
 * A
 * id
 * 1
 * 2
 *
 * B
 * id
 * 5
 * 6
 * 7
 * a_id, b_id
 * 1, 5
 * 1, 6
 * 1, 7
 * 2, 5
 * 2, 6
 * 2, 7
 *
 * select dept.department_name, emp.department_id as e_id, dept.department_id as d_id
 * from hr.departments dept join hr.employees emp on emp.department_id = dept.department_id
 *
 *
 * select dept.department_name, emp.department_id as e_id, dept.department_id as d_id
 * from hr.departments dept, hr.employees emp
 * where emp.department_id = dept.department_id
 *
 *
 * select dept.department_name, emp.first_name
 * from hr.departments dept full join hr.employees emp on emp.department_id = dept.department_id
 * where dept.department_name is null or first_name is null
 *
 *
 * --write a query to count employee number in each department, return department id, department name and count
 *
 * SELECT
 *     d.department_id,
 *     d.department_name,
 *     COUNT(e.employee_id) AS employee_count
 * FROM hr.departments d
 * LEFT JOIN hr.employees e
 *     ON d.department_id = e.department_id
 * GROUP BY
 *     d.department_id,
 *     d.department_name
 * ORDER BY
 *     d.department_id;
 *
 *
 *
 * select d.department_id, d.department_name, count(e.employee_id) as employee_count
 * from hr.departments d join hr.employees e on d.department_id = e.department_id
 * group by d.department_id, d.department_name;
 *
 * --list ranked salary in each department, return department id, and salary  + rank
 * select department_id, salary, dense_rank() over (partition by department_id order by salary desc) as rank
 * from hr.employees
 *
 * --get 3rd highest salary in each department, department id, department name and salary. sort result by salary in descending order
 *
 * -- WITH result AS (
 * --     select
 * --         DEPARTMENT_ID,
 * --         SALARY,
 * --         DENSE_RANK() over (partition by DEPARTMENT_ID order by salary DESC) AS RANK
 * --     from hr.EMPLOYEES
 * -- )
 * select
 *     res.DEPARTMENT_ID,
 *     dept.DEPARTMENT_NAME,
 *     res.SALARY
 * from (select
 *         DEPARTMENT_ID,
 *         SALARY,
 *         DENSE_RANK() over (partition by DEPARTMENT_ID order by salary DESC) AS RANK
 *     from hr.EMPLOYEES) res join hr.DEPARTMENTS dept on res.DEPARTMENT_ID = dept.DEPARTMENT_ID
 * where res.rank = 3
 * order by res.SALARY desc
 *
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