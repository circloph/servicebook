version="1.0.0"

podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'openjdk:17-alpine', command: 'sleep', args: '99d'),
    containerTemplate(name: 'node', image: 'node:16', command: 'sleep', args: '99d'),
    containerTemplate(name: 'kubectl', image: 'roffe/kubectl', ttyEnabled: true, command: 'cat'),
    containerTemplate(name: 'buildkit', image: 'moby/buildkit:master', ttyEnabled: true, privileged: true),
  ],
  volumes: [
       emptyDirVolume(mountPath: '/result', memory: true),
       hostPathVolume(hostPath: '/tmp/.gradle' , mountPath: '/root/.gradle'),
       hostPathVolume(hostPath: '/.kube/config' , mountPath: '/root/.kube/config'),
  ]
  ) {

    node(POD_LABEL) {
        stage('Prepare') {
            checkout scm
        }
        stage('Build java') {
            container('maven') {
                sh """
                  ./gradlew build --info --build-cache
                  ls -lah /result
                  cp build/libs/servicebook-0.0.1-SNAPSHOT.jar /result/knowledgetest.jar
                """
                milestone(1)
            }
        }
        stage('Build Docker Image') {
            container('buildkit') {
                sh """ 
                  pwd
                  ls -lah
                  cp /result/knowledgetest.jar knowledgetest.jar
                  find knowledgetest.jar
                  buildctl build --help
                  buildctl build --frontend dockerfile.v0 --local context=. --local dockerfile=. --output type=image,registry.insecure=true,name=circloph/myrepository:latest,push=true
                """
                milestone(2)
            }
        }
    }
}
