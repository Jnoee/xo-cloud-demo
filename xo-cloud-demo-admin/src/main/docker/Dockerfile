FROM java:8

ENV WORK_PATH /xo-cloud-demo-admin
ENV APP_NAME @project.build.finalName@.@project.packaging@
ENV APP_VERSION @project.version@

EXPOSE 8010

COPY $APP_NAME $WORK_PATH/

WORKDIR $WORK_PATH

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-server"]
CMD ["-jar", "@project.build.finalName@.@project.packaging@"]

HEALTHCHECK CMD curl -fs localhost:8010/actuator/health || exit 1