## Microservice:
1. Monolith: 
   - Single code base to achieve multiple functionalities.
   - Problem is;
     - Huge and complex code base
     - Difficult to maintain
     - Mostly can do vertical scaling (resource like memory, cpu increase) only
     - Single point of failure
     - Small change need full application redeployment
     - Slow development
     - Interdependent team
     - Technology adoption is difficult as entire thing need to change
2. Microservices:
    - Each have their own data, model and hence are federated.
    - Each feature can be a single small service by its own
    - Inter-service communication is through HTTP or messaging.
    - Inter-service communication is stateless.
    - Each service can be deployed independently
    - Each service can have separate tech stack
    - Features:
      - Small and focused
      - Loosely coupled
      - Language neutral
      - bounded context - no need that other services know how one service is built. It's own business
    - Advantages:
      - Independent development
      - Independent deployment
      - Fault isolation
      - Mixed tech stack
      - Granular scaling (vertical + horizontal)
3. Principles behind microservices:
    - Independent and autonomous
    - Scalability
    - Continuous deployment
    - Continuous monitoring
    - Decentralization
    - Availability
    - Fault isolation
    - Auto-provisioning
    - Real-time load balancing
    - `Resilient` - when a failure happens, there should not be data loss and should be able to handle failures. 
      - `Hystrix` is one way.
      - How we accommodate failures in system without impacting the operations.
    - `Fault tolerance` - if a failure happens, how quickly it is fixed. 
      - Eg: Car with a spare tire can immediately get fixed.
4. 12 Factor App:
    - One of the main principle behind micro services
    - Put up by Heroku
    - Those includes;
      * One `code base`
      * Isolated `dependencies`
      * Store `config` in environment
      * Separate build(CI) and release(CD) stages
      * One or more stateless processes together form the app
      * Export service via port binding
      * Concurrency (parallelize/ scale out process horizontally)
      * Disposability: Fast start and shutdown
      * Dev/ Prod parity: Almost same dev, stage , prod env
      * Logs are treated as streams
      * Admin processes also is a one-off process
5. Design Patterns for microservices:
   1. `Aggregator`: One service will contact all others to collect data and return it to client
    ```
    client <----->Aggregator Service----->Service A
                                    ----->Service B
    ```
   2. `API Gateway`: One entry point and distributes the traffic among services
   ```
   client<------->API Gateway -------Load balancer---------->Service A
                                                    |
                                                    ------->Service B
   Here, advantage is, if API gateway can understand multiple protocols, irrespective of what protocol the services use, we can serve any client with any protocol.
   
   ```
   3. `Chain of Responsibility`: Services interconnected and interact sequentially. Failure of any one end up in failing all.
   ```
   Service A <-------->Service B<-------->Service C
   ```
   4. `Asynchronous Messaging`: All services communicates async via queues etc.
   ```
   Service A <----q---->Service B<----q---->Service C
   |---------------q------------------------|
   ```
   5. `Event Sourcing`: Whenever a data change happens, event triggers to notify other services
   
   6. `Command Query Responsibility Segregator`(CQRS): Mostly when we have database as a service.
   ```
   One set of services take care of writes. Others take care of read to and from database.
   ```
   7. `Branch`: Mix of chain and aggregator
   8. `Circuit break`: When a service fails, trips the circuit breaker to isolate the service and fallback to seme alternative and timely poll to check if failed system is back.
   ```
   client ----clircuit breaker -------load balancer ------service ecosystem
   ```
   
   