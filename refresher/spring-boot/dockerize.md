### Docker and Kubernetes:
1. It works on my system so as in other systems as well.
2. Image: 
   - App and set/ bundle of environment we need to run our application. 
   - Image helps to create containers which are actual runtime.
   - We define images with `Dockerfile`. This is simple `text file`.
3. Docker desktop is miniature version of docker environment and runtime.
4. We define and build images. Then `push` our images into `container registry`.
5. `Docker hub` is a docker container registry repository
5. To deploy it, `pull` images from `container registry`
6. docker-compose:
    - Only for single service, Dockerfile is enough.
    - But when we have more than one docker images and need the containers interrelated, docker-compose helps.
7. K8s and Docker swam are for orchestration.
8. Hypervisor:
    - VMWare
    - Can install another OS
    - We can pack image of VMWare and others can create instances from it.
    - But problem is, we need to install OS inside hypervisors also which are licenced and hence costly.
9. That is how docker came. We don't need OS installed inside a docker container.
10. Deployment with Docker:
    ```
    container 1        container 2       .... container n-1      container n
    
    -------------------------------------------------------------------------
                              CONTAINER ENGINE
    -------------------------------------------------------------------------
                                   HOST OS
    -------------------------------------------------------------------------
                            Clound Infrastructure
    -------------------------------------------------------------------------
    
    ```
11. Commands:
    - `docker images` - listing images
    - `docker image remove <image id>` - works only if all containers created from that image are stopped and removed with docker container stop, docker rm
    - 
    - `docker ps` - listing containers or `docker container ls`
    - `docker ps -a` - will list all containers including stopped ones
    - `docker run <image name>` - to start container from image
    - `docker run -d <image name>` - run in background or detached mode
    - `docker run -p 8080:8080 <image name>` to expose port 8080 of container and link to localhost 8080
    - `docker start <container id>`,  `docker start <container name>`
    - `docker stop <container id>`,  `docker stop <container name>`
    - `docker logs <image id>` to get logs for specific container
    - `docker rm <image id>`
    - `docker container prune` will remove all stopped containers
    - `docker push <image name>:<tag-name>` - make sure to create a docker repo in docker hub and ensure taht image name has docker repository name prefixed to push safely.
12. How is docker working internally? (Architecture)
    ```
        Docker client (we use cli to trigger commands such as docker pull, docker run etc. Cleint to help users)
                |
                |
        Docker Daemon
                  |
                  |manages everything related to
        |---------|---------------------------|
        |         |                           |
    conatiners    Local images              Image Registry
    
    ```
    Internally,
    ```
         Docker client
            |
            Interacts with REST API to 
            |
        Docker Daemon(real server managing the containers) with the help of
            |
            | 
        Conatinerd (process managing container lifecycles)
                |
                | each container is managed by many
                |
            runc      runc....
            |          |
        container 1   container 2..
    ```
13. Dockerfile:
    - This is the file that helps us to construct docker images from other images
    - This is basic text file named `Dockerfile`
    - When we do `docker build .`, it searched in current directory for the Dockerfile and executes and create image accordingly.
    - Commands in Dockerfile:
      - FROM  - base image to start build process. This will be the first line
      - RUN - takes command and arguments to execute from image. Eg: `RUN eacho 'hai'`
      - CMD - Similar to run. But it executes only after container is instantiated. And we may not see it in log of `build` command.
            - This happens on container start only. Not at image build
      - ENTRYPOINT - similar to CMD. But we cannot override it while `docker run`
      
            - cmd can be overridden so that at runtime
            - cmd internally uses shell/ binary.
            - ENTRYPOINT helps us to create executable docker images and instruction given inside it `must` be executed on container initialization.
            - Both can be used in same Dockerfile also. ENTRYPOINT definitely executes. CMD can be if we didn't override it in docker run command.
            - https://www.bmc.com/blogs/docker-cmd-vs-entrypoint/ 
      - ADD - copy files from source to inside the container
      - ENV - to define environment variables
      - EXPOSE - the default port to help other containers find and interact with this containers. Not exposing to outside. 
        - vs `-p` or `--publish` in docker run:
          - -p is more powerful than EXPOSE. Expose just ensure that a port is open on this container inside docker to interact with other containers.
          - But when we add -p, it exposes port to outside of docker.
          - This -p port will be accessible to internal other containers also automatically.
          - `-p maps a host port to conatiner port.`
          - `-p host-port:container-port`
                
      ```
      # comment at begin of file
      FROM openjdk:17
      ENV port=8080
      RUN echo 'This is docker image preparation begin'
      EXPOSE ${port}
      ADD target/*.jar doctor.jar
      CMD ["java", "-jar", "doctor.jar"]
      CMD ["echo", "hai", "with cmd"]
      #ENTRYPOINT ["java", "-jar", "doctor.jar"]
      RUN echo 'Docker image build success! '
      # END of file
      ```
      - 