# fluentd/Dockerfile

FROM fluent/fluentd:v1.16.2-1.1
USER root
RUN ["gem", "install", "fluent-plugin-elasticsearch", "--no-document"]
#, "--version", "5.2.1"]
USER fluent