###Maven:
1. Definition
   - Build tool from apache
2. `Why` we need it? `to easily manage dependencies`
   - We don't want to download dependencies manually and add to project.
   - It is easy to upgrade the versions
   - Can easily get transitive dependencies when we add one library.(eg: spring-boot if we add, spring also comes.)
   - Maven downloads depdencies for the first time from maven central and then next time onwards, from .m2 folder acting as local repo for us.
   - Others: gradle, ant etc.
3. Project metatdata:
   - project name
   - group id - com.myproject - each world between . will become folders in .m2 on `install` phase.
   - artifact id - this comes as jar file first part.
   - version - second part. Semantic version `major.minor.patch`
   - package type: jar, war etc how we are going to build our project
   - settings.xml have basic configs for maven
4. `dependencies`:
   ```
   <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
   ...add n more
   </dependencies>
   ```
   Whatever we give here, the transitive dependencies of them as well get downloaded by maven.
5. Maven Life Cycle: (just do mvn and it lists all as part of error)
    `Life cycle consists of phases`
   - Clean Life Cycle: (clean target and remove olds build leftovers)
     - pre-clean
     - `clean` - 1
     - post-clean
   - Default Life Cycle (project deployement)
     - validate - checks all necessary info to build project is available or not;
     - initialize
     - generate-sources
     - process-sources
     - generate-resources
     - process-resources
     - `compile` - 1 - compile source code (with javac in Java projects)
     - process-classes
     - generate-test-sources
     - process-test-sources
     - generate-test-resources
     - process-test-resources
     - test-compile (compiles test source code)
     - process-test-classes
     - `test` (run unit tests)
     - prepare-package
     - `package` - 2 (convert compiled code to distributable format. eg: jar, war)
     - pre-integration-test
     - integration-test (process and deploy package to conduct integration testing)
     - post-integration-test
     - `verify` - 3 (verify the integration test results)
     - `install` - 4 (install package to local repo)
     - `deploy` -5 (copy package to remote repo)
   - Site LifeCycle (for sire documentation)
     - pre-site
     - site - 1
     - post-site
     - site-deploy
   `Numbers indicates important life cycle phases in each specific life cycle.`
   **When we execute a phase, all previous phases in the order of life cycle will get executed.**
   - Goals vs Phases:
   - Entire life cycle is consisting of multiple `phases`
   - Each phase has multiple `goals`
   - compiler:compile : indicates that maven compiler plugin has a goal named compiler which is linked to compile phase
   - surefire:test : indicates that surefire maven plugin goal is run at phase 'test'
   - `mvn help:describe -Dcmd=PHASENAME` to find goals attached to each phase.
   - `mvn help:describe -Dcmd=compile`
   - We can add goals with the help of `<plugins`
   - Eg: 
   - Here, failsafe plugin has 2 goals one attached to integration-test. Other one to verify
   - Use `mvn <pligin>:help` to find the goals in it.
   ```
    <build>
    <plugins>
        <plugin>
            <artifactId>maven-failsafe-plugin</artifactId>
            <version>${maven.failsafe.version}</version>
            <executions>
                <execution>
                    <goals>
                        <goal>integration-test</goal>
                        <goal>verify</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
    </build>

   ```
6. dependency vs dependency-management tags:
    ```
    Everything with dependency tag will be ALWAYS included as dependency for the module.
    When we have something in dependency management, child poms may or may not add the same under
    depdencies and can inherit the same version. Or can use its own version.
    Only if child explicitly adds it, then only it is packed for child.
    To chanage version of such depdencies, we just need to change in parent pom and children will get it unless it overridden that.
    dependency-management helps for flexible inheritance of dependencies.
   
    Eg: extensitvely used by springboot. Whatever version is there for springboot parent, same comes for all springboot-starters as well.
    ```
7. *Scope*: 6 default scopes
    - `<scope>compile</scope>`: Default scope. Mandatory for the project to get compiled. Will be bundled with package.
     ```
    <dependency>
      <groupId>commons-lang</groupId>
      <artifactId>commons-lang</artifactId>
      <version>2.6</version>
    </dependency>
     ```
    - `<scope>provided</scope>`: No need to bundle it as it will be available in runtime environment provided by JDK or container.
      ```
        <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>javax.servlet-api</artifactId>
           <version>4.0.1</version>
           <scope>provided</scope>
       </dependency>
      ```
    -  `<scope>provided</scope>`: Not required at compile time. but need for runtime and test.
    ```
        <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>8.0.28</version>
           <scope>runtime</scope>
       </dependency>
      ```
    - `<scope>test</scope>`:Helps to make maven aware that this library is used only in test and no need to pack it inside jar.
   ```
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
   ```
    -  `<scope>import</scope>`: Only for dependency type `pom`.
   ***This indicates that replace this dependency with all dependencies mentioned in `<dependencyManagement>` section of pom.xml of custom-project***
    - Kind of inheritance
    - Used when parent poms and multiple sub-modules are there.
    - Advantage is; change in parent and all children gets the changes. 
   ```
    <dependency>
     <groupId>com.myproject</groupId>
     <artifactId>custom-project</artifactId>
     <version>1.3.2</version>
     <type>pom</type> -- type pom
     <scope>import</scope>
    </dependency>

   ```
    - https://www.baeldung.com/maven-dependency-scopes
    -  `<scope>system</scope>`: Deprecated. To directly point to jar on machine
8. JaCoCo: Java Code Coverage vs Surefire:
    - JaCoCo uses Java agent to find out code coverage for unit tests and integration tests as 2 goals.
    - `Surefire` goal bound to test to make unit tests `run`. Surefire fails on single unit test failure.
    - `Failsafe` goal bound to run integration test. It will not fail if integration test fails due to some other systems.
    - If we use surefire instead of failsafe in integration test phase, it terminates process on failure of other systems too.
    - Surefire an dFailsafe also generates test reports.
    - But JaCoCo is more feature rich and when surefire runs the tests, JaCoCo Java agents collects coverage information from JVM 
    - and stores it as `jacoco.exec` and `jacoco-it.exec` in `target` folder. Then  we can use `report` or `report-integration` goals of JaCoCo to
    - generate xml, html, csv reports as we need.
    - `Surefire and Failsafe runs the test and generates basic reports. JaCoCo collects test report from JVM and presents in feature rich.`
    - https://ccbill.com/blog/code-coverage-with-surefire-and-jacoco