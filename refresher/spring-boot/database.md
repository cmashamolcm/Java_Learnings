### Database
1. ORM: Object Relational Model
    - This is a concept to map tables to entities
    - Hibernate, Ibatis are some implementations of ORM concept.
    - JPA helps to define the APIs that hibernate or other ORM tools are aligned on.
    - JDBC helps to connect to Databases from Java
    - Hibernate gives us a wrapper on top of this all complex connection, session creations
2. JPA Annotations
   - @Entity: Marks a class as can be used by ORM
   - @Table: To specify table name if different from class name
   - @Column: o specify the column name
   - @Id: primary key. Mandatory for @Entity
   - @Embeddable: To create a class which is not entity but helps to segregate a set of columns and when we add this to as 
      attribute to ny other @Entity, columns equivalent to the attributes of @Embeddable are created in database
   - @Transient: To ignore an attribute from becoming a column.
3. Relationships:
   - One to one:
     * @OneToOne
     * Every Student has a Laptop
   ```
   Laptop
      |-lid
      |-lname
   
   Student
      |- sid
      |- sname
      |
      | @OneToOne
      |- Laptop lap;
   
   STUDENT table:
   sid | sname| lid---->foreign key from LAPTOP table.
   
   ```
   - One to Many:
     * @OneToMany
     * One side has list of others
     * Each Student has many laptops
   ```
   Laptop
      |-lid
      |-lname
      |
      |@ManyToOne
      |-student
   
   Student
      |- sid
      |- sname
      |
      | @OneToMany
      |- List<Laptop> laps;
   
   STUDENT table:
   sid | sname
   
   LAPTOP table:
   lid | lname
   
   STUDENT_LAPTOP:
   sid | lid
   
   ```
    * But this creates an extra table. What if we can add sid to the laptop table itself.
    * @OneToMany(mappedBy="student") where student is attribute name from Laptop which is @ManyToOne.
    * **mappedBy ensures that the object which inside which we gave @MappedBy will not create extra mapping table. Instead, tells that the other table will take care of extra column/ table creation.**
  ```
   Laptop
      |-lid
      |-lname
      |
      |@ManyToOne
      |-student
   
   Student
      |- sid
      |- sname
      |
      | @OneToMany(mappedBy="student")
      |- List<Laptop> laps;
   
   STUDENT table:
   sid | sname
   
   LAPTOP table:
   lid | lname | sid
 
 no 3rd table
   
   ``` 
 - Many to Many:
   - @ManyToMany
   - Both the side has multiple entries of other side.
   - Student has n laptops and one laptop has n students as owners.
 ```
   Laptop
      |-lid
      |-lname
      |
      |@ManyToMany
      |-List<Student> students
   
   Student
      |- sid
      |- sname
      |
      | @ManyToMany
      |- List<Laptop> laps;
   
   STUDENT table:
   sid | sname
   
   LAPTOP table:
   lid | lname
   
   STUDENT_LAPTOP mapping table:
   sid | lid
   
   LAPTOP_STUDENT mapping table:
   lid | sid
   
   In effect both are the same. Better to restrict in one side.
   
   ``` 
   * Add @ManyToMany(mappedBy="student") in Student class will make sure that there will not be mapping table created from Student side. But will be taken care of in Laptop side.
```
   Laptop
      |-lid
      |-lname
      |
      |@ManyToMany
      |-List<Student> students
   
   Student
      |- sid
      |- sname
      |
      | @ManyToMany(mappedBy="students")
      |- List<Laptop> laps;
   
   STUDENT table:
   sid | sname
   
   LAPTOP table:
   lid | lname
   
   LAPTOP_STUDENT mapping table:
   lid | sid
   
   Only one extra table and created by LAPTOP side.
   
   ``` 
   - Relations ending in `Many` has mappedBy such as OneToMany, ManyToMany etc. But ManyToOne does not have.
   - But @OneToOne has mappedBy to decide on which side extra column need to be added.
   - If we are not giving mappedBy, in oneToMany, extra table comes. To avoid that, add manyToOne in other side and specify mappedBy in oneToMany side.
   - `mappedBy: Don't bother about creating mapper in your end. OTher one is taking care of it.`
   - Eager vs Lazy:
     * Eager means, fetches full data and initialize object on fetching from database.
     * Lazy means, fetch only direct columns, values of mappings ending in One. such as @ManyToOne, @OneToOne etc. It will not load data for @ManyToMany, @OneToMany etc to save memory.
     * This is done by `proxy` structural design patten approach. Only when we explicitly uses it, another query is fired to fetch not loaded data.
     * Mark as fetch type EAGER to load it together on initialization on fetch from database.
     * In JPA, @OneToMany, @ManyToMany are lazy. (ending with too many)
     * In JPA, @OneToOne, @ManyToOne are EAGER as it has less data.
     * @Column does not have fetch type attribute
   - Hibernate Caching:
     * To avoid loading of data everytime from database, there are 2 levels of caching in hibernate.
     * 1st level caching is per session. Enabled by default in hibernate.
     * 2nd level caching is across multiple sessions and in hibernate persistence context.
     * Need to explicitly enable it.
     * Internally, when we query something with session.get(), it checks 1st level cache, second level cache if enabled and finally to database.
     * When a first time fetch happens, hibernate puts an entry to 1st level cache if result is not NULL.
     * https://javatute.com/hibernate/hibernate-first-level-cache-example-using-spring-boot/?utm_content=cmp-true
     * Then no need of further direct hit to database and can use it from 1st level cache.
   - `@Transactional`
     * When we use  to mark a method, whatever comes within that method will be under same hibernate session and hence has same first level cache.
     * @Transactional defines the transaction boundary.
     * This can be applied on class level or method level.
     * If any error happens within that boundary, entire thing rolls back.
     * But if we are not adding this annotation and multiple tables are there in use, chance of partial commits are there.
     * So, safer is to use @Transactional over method if we have multiple tables in action.
     * JPARepository methods are by default @Transactional and hence each of them has individual sessions.
     * So, we use two JPARepo methods in same service method without @Transactional, 2 commits to EntityManager can be seen in log.
     * But if we add @Transactional, only one final commit we will be able to see.
     * So, it combines them come under single session.
     * Transaction are run on persistence context which usually is an entity manager that deals with complexities of Config, session, transaction etc..(`@PersistenceContext Entitymanager em;`)
     * All are not directly on Entity Manager but instead on proxy for em first and then after all transactions under @Transactional finishes, actual commit happens to real database.
     * `Make sure to use Set instead of list if possible for OneToMany etc. Else, when we add new one to that list, full delete and re-insert occurs. If it is set, only one insert.`
     * https://thorben-janssen.com/5-common-hibernate-mistakes-that-cause-dozens-of-unexpected-queries/
     * `Rollback` happens in @Transactional for `only Runtime Exceptions` and not compile time ones.
     * We can set it by @Transactional(rollBack)
     * `@Transactional(readOnly=true)` makes sure that the transactions happening within the boundary are only read. 
     * And hence underlying ORM provider can optimize the operation and prevent accidental data change etc. 
   - 2nd Level Caching:
     - Not enabled by default and if we manually enabled for our app, it will be shared across multiple sessions.
     - So, in multiple sessions, we can avoid direct database hit for same object.
     - EHcache is one such tool for 2nd level caching.
     - Make entities as `@Cacheable`
   - How can we optimize database operations?
     - Use `Set` for ToMany instead of List.
     - @Transactional to rollback safe
     - set readOnly to true if only read heavy to inform hibernate to optimize it.
     - JpaRepository is @Transactional(readOnly=true) in class level. but can override it in individual methods or subclasses.
4. JpaRepository vs CRUDRepository:
   - CRUDRepo is basically gives APIs for all CRUD operations
   - PagingAndSoringRepo gives CRUD + sort and paging
   - JpaRepository inherits from PagingAndSoringRepo and som extra to handle store specific extensions such as batch processing, flush etc.
   - It can support method queries as well.
   - @Repository is not mandatory. But if we add, sql exceptions will be converted to runtime exceptions to handle by spring advices.
   - Make sure to have getters and setters for entity class for proper jackson json parsing.
5. ACID: Principle behind RDBMS
    - Atomicity - a transaction succeeds or fails. Not partially done.
            Send money - 1.check sender balance, reduce amount from account 1, transfer amount to account 2.
            If any of the 3 fails, everything is reverted. If all 3 succeeds, full success.
            Not partially.
    - Consistency - Data correctness. If updated, on query, new value should come.
                  Also, with the help of constraints. (not null)
    - Isolation - Independent. One transaction is not depending on other. If dependent transaction is there, do it only after 1st one is committed.
                    When concurrent transaction comes also, it should be treated like sequential to avoid errors.
                    When 2 updates, 100 rs insert and 50 reduction from account happens, if initial balance is 10,
                    10+100 and 10-50 can happen and go wrong. Actually, do 10+100 and then to the result of it, subtract 50.
                    
    - Durability - Data is stored safe to avoid loss in non-volatile storage.
6. CAP: For distributed systems
    - Consistency - Data is up to date.
    - Availability - Even if wrong, data is available
    - Partition tolerance - Keep duplicates for safe data
    - AP -Cassandra
    - CP -MongoDB
    - Mostly 2 of the 3 only will be available.
7. HQL:
    - Hibernate Query Language
    - table name = class name
    - column = class attribute
    - `from Student where id = 10`
    - It returns list
    - These are `native queries`.
    - `from Student where id = ?1` prepare statement and 1st entry in method parameter will be assigned here.
8. Persistence Lifecycle in Hibernate:
    ```
   new ------> - Transient - created but not saved yet
                                    ---------------(session.save())------->persistent - saved to hibernate or entity manager/ persistence context
                                                                                    | commit() saved to database
                                                                                    |
                                                                                    -----------session.detach(obj)------>to make object detached from persistence state so that chnages are not updated in actual database
                                                                                    |---removed
   
   Detach helps to protect from update by mistake.
   @Transactional(readOnly=true) also helps here to avoid unexpected writes.
   ---------------------------------------------------GCed------------------------------------------------------------------------------------------
   

9. find vs get:
    - find not throws exception on not found. Returns optional.
    - get throws exception.
    - session.get() vs session.load(): load gives proxy object where get() gives real object from database.
                                                                                                        
       
    ```