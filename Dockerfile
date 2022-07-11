FROM openjdk:17-alpine
COPY knowledgetest.jar /home/knowledgetest.jar
CMD ["java","-jar","/home/knowledgetest.jar"]
