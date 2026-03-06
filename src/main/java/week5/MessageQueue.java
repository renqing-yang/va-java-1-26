package week5;

/**
 *  Producer(server) -> Message Queue(Cluster)  -> Consumer(server)
 *
 *  Queue
 *        Producer(server) -> Message Queue(Cluster)  -> Consumer(server)
 *
 *  PubSub
 *        Producer(server) -> Message Queue(Cluster) -> Subscribers
 *
 *  Message Retention
 *        Kafka : 7 days(default?)
 *        SQS : few days(?)
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  SQS , SNS
 *
 *                         -> SQS -> send text
 *  user -> message -> SNS -> SQS -> send email
 *                         -> SQS -> save to db
 *
 *
 *  user -> message -> SQS -> consumers
 *
 *
 *  SQS: Visibility Timeout(few mins)
 *      1. consumer pull message
 *      2. other consumers cannot see this message during visibility timeout
 *      3. consumer sometimes need to extend visibility timeout
 *         if don't extend it -> other consumers can pull this message from mq
 *
 *  SQS: FIFO / Standard
 *      1. standard queue (no order)
 *      2. FIFO (insertion order)
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Kafka
 *  1. Topic, Partition,
 *          Topic1                      Consumer Group1
 *          Partition1[m1][m2]          Consumer1: pulls t1p1
 *          Partition2                  Consumer2: pulls t1p2
 *          Partition3[m3]              Consumer4: pulls t1p3
 *                                      Consumer5: idle
 *
 *                                      Consumer Group2
 *                                      Consumer3
 *
 *     1 partition can be pulled by only 1 consumer in same consumer group
 *     partition m - 1 consumer 1 - 1 consumer group
 *
 *  2. Broker + Fault tolerance
 *       Broker1(server1)
 *          T1P1(Leader)
 *          T1P2(Follower)
 *          T1P3(Follower)
 *
 *       Broker2(server2)
 *          T1P1(Follower)
 *          T1P2(Leader)
 *          T1P3(Leader)
 *
 *  3. PubSub model vs Queue model
 *      Queue model : 1 consumer group
 *      PubSub : multiple consumer groups
 *  4. Schema Registry
 *      {
 *          "key1" : type1,
 *          "key2" : type2
 *      }
 *              schema registry(avro library)
 *            /      |      \
 *      producer -  kafka - consumer
 *  5. Re-Balance
 *      1. when first register consumer + consumer group to broker
 *         or
 *         one of the consumer not working
 *      2. broker select one of the consumer in this consumer group as leader
 *      3. broker will send all partitions info + consumers info to consumer leader
 *      4. consumer leader will balance partition (assign jobs)
 *              who is pulling data from this partition
 *              who is pulling data from another partition
 *         send this mapping (consumer - partition mapping) to broker
 *      5. broker start sending info to all consumers
 *      6. consumers start pulling data from partitions
 *
 *  6. Producer ACK
 *      producer -> send message to broker(topic)
 *      0: producer send message(doesn't care the transaction in kafka)
 *      1: message must be committed in leader
 *      all: message must be committed in All Followers + Leader
 *  7. Consumer ACK
 *      at most 1 = commit "offset" before processing message
 *      at least 1 = commit "offset" after processing message
 *      exact 1 = need kafka downstream / need other infrastructure to track message status
 *  8. offset
 *      index in partition
 *  9. how to let kafka process your message in order / tx in order
 *      in SQS : FIFO
 *      in Kafka : set key to message, messages with same key are pushed to same partition
 *      no order guaranteed cross diff partitions
 * 10. max number of consumers in each consumer group
 *      partition number + 1
 * 11. how does kafka handle fault tolerance
 *      enough message retention days
 *      producer ack : 1 or all
 *      consumer ack : at least one processing + commit "offset"
 *      start extra consumer as backup in consumer group
 *      define more brokers : leader + follower partitions
 *      re-balance
 * 12. what do we do if we meet a failed message
 *      retry : design retry logic in kafka (customized) , then push to dead letter queue
 *              sqs can setup retry with dead letter queue
 *      push to dead letter queue if hit max retry times
 *
 *      retry -> dead letter queue -> trigger alert -> send to slack / email
 *      someone from support team or someone from technical team need to check the message in DLQ  (user interfaces / frontend page / aws console to see the message)
 *      a. retry it again/reprocess it again
 *      b. generate ticket -> send it to developers ..
 *      ...
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Homework 5 (AI)
 *      1. create frontend page (react/angular) to upload file
 *      2. call backend api -> api gateway -> backend spring boot generate s3 pre-signed url
 *          deploy spring boot in docker container in ECS
 *          ECS:
 *              task definition
 *              use fargate or EC2
 *          ECR:
 *              save docker image
 *      3. frontend upload file through the url
 *      4. once s3 get new file -> trigger event bridge -> push file metadata to sqs -> lambda consumer -> save file metadata in dynamodb
 *      5. push message to DLQ after 3 retries
 * Deadline Mar 16th
 */