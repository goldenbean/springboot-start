FROM my-openjdk-8

WORKDIR /work

ADD entrypoint.sh /work
ADD server/target/springboot-start-server-0.0.1-SNAPSHOT.jar /work

RUN chmod +x /work/entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/work/entrypoint.sh"]