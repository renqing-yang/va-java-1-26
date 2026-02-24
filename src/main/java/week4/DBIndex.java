package week4;
/**
 * --Query performance / DB performance
 * full table scan vs index scan
 *
 * student
 * stu_id, name,   row_id(hidden col), tx_id(hidden col), roll_pointer(hidden col)
 *  1   , Tom
 *
 *
 *
 * B+ tree
 *
 *     []
 *     / \
 *   []    []
 *   rowid1  rowid2
 *
 * ---------------------------
 * non-clustered index
 *     []
 *     / \
 *   []    []
 *   rowid1  rowid2
 *     |
 *     |_______________>  table
 *                         rowid1  1, Tom
 *                         rowid2  2, Alex
 *
 * clustered index
 *
 *     []
 *     / \
 *   []    []
 * rowid1  rowid2
 *  id
 *  name
 *  ..
 *
 * ----------------------------
 * B+
 * insert [1, 2, 3]
 * root [1, 2, 3]
 *
 * insert [4]
 * root [1, 2, 3, 4]
 *
 *     root[3]
 *     /   \
 * [1, 2]  [3, 4]
 *
 * insert [5, 6]
 *    root[3]
 *     /   \
 * [1, 2]  [3, 4, 5, 6]
 *
 *  root [3, 5]
 *     /   \       \
 * [1, 2]  [3, 4]  [5, 6]
 *
 * insert [7, 8]
 *  root  [3, 5,  7]
 *     /   \       \       \
 * [1, 2]  [3, 4]  [5, 6]   [7, 8]
 *
 *
 * insert [9, 10]
 *  root     [3, 5,  7, 9]
 *     /   \       \       \       \
 * [1, 2]  [3, 4]  [5, 6]   [7, 8]  [9, 10]
 *
 *  root           [7]
 *             /           \
 *           [3, 5]          [7, 9]
 *     /   \       \           /      \
 * [1, 2]<->[3, 4]<->[5, 6] <->[7, 8]<->[9, 10]
 *
 *
 * ------------------------------------
 * bitmap
 * int -> 32 bit -> 000000000..010
 *
 * rowid , id, state       rowid,  NJ, NY
 * xxxxx,  1 , NJ                  1,  0
 * yyyyy,  2 , NY                  0,  1
 * zzzzz,  3 , NJ                  1,  0
 *
 * NJ : 101
 * NY : 010
 *
 *
 *
 * select
 * from
 * where state == 'NJ' or state == 'NY' (101 || 010)
 *  ---------------------------------
 full table scan
 index scan
 1. index unique scan
 2. index range scan
 3. index full scan
 4. index fast full scan

 Join
 1. nested loop join :  use_nl
 for(int i = 0; i < m; i++) {
 for(int j = 0; j < n; j++) {
 if(table1[i] == table2[j]) {
 result.add(table1[i] + table2[j]);
 }
 }
 }
 2. hash join:  use_hash
 [][][][][][][][][]
 3. merge join: use_merge
 two array -> merge them together
 a. sort them
 b. two pointer to merge



 hint /*+ */
//
// *  root           [7]
//         *             /           \
//         *           [3, 5]          [7, 9]
//         *     /   \       \           /      \
//         * [1, 2]<->[3, 4]<->[5, 6] <->[7, 8]<->[9, 10]
//
//
//        select /*+ index(e) */ *
//        from hr.employees e
//
//
//        select /*+ full(e) */  first_name
//        from hr.employees e
//
//        select employee_id
//        from hr.employees e
//        where employee_id = 101 or employee_id = 102
//
//
//        select employee_id
//        from hr.employees e
//        where employee_id >= 101 and employee_id <= 102
//
//
//
//        select e.*
//        from hr.employees e join hr.departments d on e.department_id = d.department_id
//
//        select e.*
//        from hr.employees e
//        where e.department_id is not null
//
//
//        select /*+ full(d) index(e) leading(e) use_merge(e d) */ e.*, d.department_name
//        from hr.employees e join hr.departments d on e.department_id = d.department_id
//
//        */

/**
 * How to optimize DB performance
 * 1. execution plan
 *      is using index or not
 * 2. correct index? b+ tree <-> bitmap
 * 3. remove index? more write queries > read
 * 4. hint + rewrite query ?
 *      full table scan
 *      index scan
 *      diff join / hash join, nested loop join, merge
 *      leading
 *      parallel
 * 5. horizontal scaling: add more reader / follower node
 * 6. vertical scaling: add more cpu / memory / disk..
 * 7. cache in DB
 *      result cache
 *      material view
 * 8. global cache layer
 *      redis : data structure , single thread
 *      memcache : multithread, string
 * 9. deploy DB in diff region
 * 10. CDN cache (Cloudfront in AWS)
 *
 *      user -> fetch video -> edge location ->  udemy / youtube
 *
 *      user -> udemy
 *          <- video index file
 *                  video_chunk1, cdn address
 *                  video_chunk2, cdn address
 *          <- caption index file
 *                  cn, cdn address
 *                  en, cdn address
 *                  ..
 *
 *
 *
 *   Terraform
 *
 *   start from next monday:  10:30 ~ 12:30 edt every day
 *
 *   afternoon section: 2pm edt -> 4pm edt
 *
 */