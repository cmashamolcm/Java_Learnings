### Kubernetes
1. k8s is open source container orchestration tool from Google
2. It helps to manage containers
3. It is orchestration. So, master and workers will be there. If it was choreography, every node sync with each other without dedicated master.
4. What problem it solves?
    - Manages multiple containers
    - Deployment
    - Monitor health of containers etc.
5. Features:
    - high availability
    - Scalability
    - Disaster recovery
6. Architecture:
    - Workers and master nodes
    - Each node has node agent called `kubelet`
    - Kubelet manages each node
   ```
        cluster:
   
            - control plane (master) - crucial and hence need to have multiple copies of master
                    |
                    |----|
                         |---API Server (RESTful entry point to cluster)
                         |---Controller Manager (keep track of what is happening in cluster)
                         |---Scheduler (pod palcements)   
                         |---etcd - store the cluster state information. Key value pair.
                         |---cloud controller manager (to deal with other cloud services like storage, etc.)
       --------------------virtual network-------------connecting all nodes---------------------         
            - worker node 1
               (has kubelet) |
                             |---- many containers
   
            - worker node 2
            -  etc.  
   
   Kubelet helps to create container using docker daemon etc.  
   
   https://www.youtube.com/watch?v=lbCn0EGZ9Io&list=PLBRBRV08tHh21k417YR04lZQdlTiqiaOJ
   ```
7. K8s Components:
   - worker node
     - virtual machine
   - pod:
     - smallest deployable unit in k8s
     - It is abstraction over container.
     - A pod can hold more than one container. But not recommended.
     - `1app per pod is best`
     - But we can provide side-car services also if unavoidable
     - Each pod has `own IP address`. When we create a new pod, a new IP comes.
     - Since pods are ephemeral(shot living), there is high chance that the IP changes frequently.
     - `So, how to make sure that we connect to app even if IP of pod changes?`
     - Services helps here.
   - Service:
     - This component helps to interact with pods even if their IP changes.
     - Each pod attaches to a service
     - Life cycle of pod and service are not related.
     - Service has constant IP.
     - So, we can contact service and they can identify the nodes and return what we need.
       - Internal service - helps to interact within k8s
       - External service - helps outside world to connect to k8s
       - Ingress 
         - Service IPs are something like node IP+port etc which is difficult to remember.
         - Another way is ingress
         - Ingress helps to configure some easy to remember url for app and it can resolve and find backend service and thereby the pod and container to interact with.
       ```
       |-----------------------------INGRESS - helps to act as DNS to find service based on simple URLs.
       |   ------------------        |
       |   |my app container|  |service | services can do external interactions too. But URLs are compilcated.
       |   ------------------        ^
       |                             |
       |                             |services interacts with each other for internal communication
       |   -------------             v
       |   |DB container|     |service  |
       |   --------------            |
       |node                         |
       ------------------------------kube-proxy---handles networking
       ```
       - ConfigMap:
         - This holds the configuration details such as service name etc.
         - We can define many things in image files(yaml files etc). But need a rebuild and redeploy to make it applied.
         - But if we use config map, we can change it easily and pods gets adjusted accordingly without redeploy
       - Secrets:
         - Stores config data encrypted with base64
       - Volumes:
         - Helps to attach physical storage to our cluster (hard disk or cloud storage etc)
         - It helps to restore data even if pod restarts
   - Deployment:
     - Definition of pods
     - This is blueprint of how pods look like
     - Also sets how many replicas are there for a pod
     - When we have multiple copies of pod, still service will be shared among the replica of pods.
     - So that we can balance the load.
     - This is blue print for `stateless APPs`
   - StatefulSet:
     - We cannot create replicas of pods for databases as state is important for it.
     - Similar is the case for any stateful app.
     - To manage such app pods, StatefulSet is used.
     - Most of the time, we keep database outside cluster
8. K8s Configuration:
    - yaml config
    - delarative
    - Have 3 parts
      - metadata
      - spec
      - Status - auto-added part having details like replica count etc. This is coming frm `etcd`
      - Additionally, kind - what we want to create (service, deployment)
9. Minikube and kubectl
    - Open source tool to create a one node cluster in local
    - In prod, we can have a cluster with many control planes and worker nodes
    - But, minikube gives us a miniature of it with `single node having master process, worker process`
    - Kubectl:
      - command line tool to interact with cluster.
      - This interacts with API server in master node
    - minikube need Docker as driver. Minikube itself will be docker container.
    - Inside minikube container, there will be docker to have containers inside the minikube
    - `minikube start`// takes docker as driver by default
    - `kubectl get node` // kubectl will get installed with minikube in local. This get node will show single node with master and worker processes.
    - In minikube, with localhost, we may not be able to access our service.
    - `minikube service <service name> --url ` to get url and access our service
    - Refer doctor-service and associated yaml files to find sample k8s configs.
    - label vs matchLabel vs selector:
    - Labels are basically for grouping of objects.
     ```
        label: name of a component. Can be service, pod or deployment etc.
        matchLabel: this usually comes with Deployment type to ensure that this deployment is applicable to pods with this matching label.
        selector: this usually comes as part of Service config. Indicates the name of deployment/ pod
        https://medium.com/@zwhitchcox/matchlabels-labels-and-selectors-explained-in-detail-for-beginners-d421bdd05362
     ```
    - If we have a pod with label `it-testing`, from the master branch, we can try to trigger deployment.yaml setting selector as `it-testing`. 
    - Then, when code merge happens to main, only pods with label of `it-testing` will be recreated.
9. GKE: Google Kubernetes Engine
    - Install gcloud in local. https://cloud.google.com/sdk/docs/install-sdk
    - Try to run docker image first.
      - login into cloud shell
      - `docker run manikuttyselffav/test-tag`
      - Enable Artifactory Registry APIs and create a repo to store docker images
      - `docker tag manikuttyselffav/test-tag us-central1-docker.pkg.dev/kubernetes-app-391320/k8s-repo/test-tag`
      - Push this tag to gcp repo.
      - 
    - Enable Kubernetes engine API in GCP
    - Create project
    - Create cluster
    - Then `kubectl create deployment name --image=<docker image>` or `kubectl apply -f app.yaml`
    - Mostly in GKE, we will have docker images available from artifact registry of GCP and just get image and create deployment.
    - But for more configurations, deploy based on yaml file.