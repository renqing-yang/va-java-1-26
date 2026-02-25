package week4;

/**
 * Transaction
 *      Atomicity
 *      Consistency
 *      Isolation Level
 *          Oracle
 *          Read Committed
 *          Serializable
 *          Read Only
 *
 *          MySQL
 *          Read Uncommitted
 *          Read Committed
 *              Tx1         select: 10 rows                  select: 15 rows
 *                              |                               |
 *              ---------------------------------------------------------------------->
 *                                      |               |
 *                                insert 5 rows       commit
 *              Tx2
 *          Repeatable Read
 *
 *              Tx1         select: 10 rows      ==       select: 10 rows
 *                              |                               |
 *              ---------------------------------------------------------------------->
 *                                      |               |
 *                                update 5 rows       commit
 *              Tx2
 *          Serializable (read + write lock)
 *
 *      Durability
 * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * MVCC
 *
 *  id name , row_id, tx_id, rollback_pointer
 *  1, Alex  ,   xxxx,   3  ,    |
 *                              |
 *                             1, Tom  ,   xxxx,   1  ,    null
 *
 *  in transaction:
 *      select : get read view list = [committed tx id list]
 *
 *  read committed
 *      select : generate new read view each time
 *
 *  repeatable read
 *      first select : generate read view
 *      following select: re-use previous read view
 * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Read Lock + Write Lock
 *
 * Read lock -> block write lock
 *              allow read lock
 *
 * Write lock -> block read + write lock
 * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * insert, update , delete : use write lock
 * select .... for update  : use write lock
 *      id
 *      1
 *      2
 *      5
 *      select ..from..where id >= 1
 *      read committed : record lock
 *          1 lock
 *          2 lock
 *          5 lock
 *      repeatable read: next - key lock (record lock + gap lock)
 *          1 lock
 *          (1, 2) gap lock
 *          2 lock
 *          (2, 5) gap lock
 *          5 lock
 *          (> 5)  gap lock
 *
 * select .... for share  : use read lock
 * select ....            : no lock select by default
 *
 * commit / rollback tx : release the lock
 * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Optimistic Lock
 *
 *
 *  salary,  id
 *   100 ,   1
 *   200 ,   2
 *
 *
 *   user1                                  user2
 *   select salary where id = 1             select salary where id = 1
 *   salary = 100                           salary = 100
 *   salary += 200                          salary += 200
 *   salary = 300                           salary = 300
 *   write to db                            write to db
 *   acquire lock                           wait for lock
 *   update db salary = 300
 *   commit tx
 *   release lock
 *                                          acquire lock
 *                                          update db salary = 300
 *                                          commit tx
 *                                          release lock
 *
 *
 *  salary,  id ,  version
 *   100 ,   1  ,    1
 *   200 ,   2  ,    1
 *
 *
 *   user1                                  user2
 *   select v, salary where id = 1          select v, salary where id = 1
 *   salary = 100                           salary = 100
 *   version = 1                            version = 1
 *   salary += 200                          salary += 200
 *   salary = 300                           salary = 300
 *   write to db                            write to db
 *   acquire lock                           wait for lock
 *   update
 *      db salary = 300
 *      version = 2
 *      where version == 1
 *   commit tx + release lock
 *                                          acquire lock
 *                                          update db salary = 300, version = 3 where version == 1
 *                                          fail
 *
 *                                          re-select v, salary where id = 1
 *                                          salary = 300
 *                                          version = 2
 *                                          salary += 200
 *                                          write to db
 *                                          acquire lock
 *                                          update db salary = 500 , version = 3 where version == 2
 *                                          commit tx + release lock
 *
 */

