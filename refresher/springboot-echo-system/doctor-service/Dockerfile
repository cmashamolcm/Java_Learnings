# comment at begin of file
FROM openjdk:17
ENV port=8080
RUN echo 'This is docker image preparation begin'
EXPOSE ${port}
ADD target/*.jar doctor.jar
#CMD ["java", "-jar", "doctor.jar"]
#CMD ["echo", "hai with cmd"]
ENTRYPOINT ["java", "-jar", "doctor.jar"]
RUN echo 'Docker image build success! '
# END of file