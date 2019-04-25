from cbiit/centos7_base
MAINTAINER Natalia Asafieva

# update packages and install packages for application #
RUN yum -y update && yum -y install cronie git java-11-openjdk-devel sudo util-linux wget which epel-release \ 
    && yum clean all

# set env variables
ENV PATH=/opt/apache-maven/bin:$PATH \
    JAVA_HOME=/usr/lib/jvm/java-11-openjdk

# change to /tmp directory for code downloads #
WORKDIR /tmp
RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.11/bin/apache-tomcat-9.0.11.tar.gz \
    && tar xvfz apache-tomcat-9.0.11.tar.gz \
    && mv apache-tomcat-9.0.11 /usr/local/apache-tomcat
RUN wget https://archive.apache.org/dist/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz \
    && tar xvfz apache-maven-3.5.4-bin.tar.gz \
    && mv apache-maven-3.5.4 /opt \
    && ln -s /opt/apache-maven-3.5.4 /opt/apache-maven
RUN wget http://archive.apache.org/dist/ant/binaries/apache-ant-1.9.9-bin.tar.gz \
    && tar xvfz apache-ant-1.9.9-bin.tar.gz \
    && mv apache-ant-1.9.9 /opt
    
ENV PATH=/opt/apache-ant-1.9.9/bin:/opt/apache-maven/bin:$PATH
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk

RUN chmod 755 /usr/local/apache-tomcat/bin/*.sh

# copy entrypoint to /usr/bin and chown to tomcata #
COPY run.sh /usr/bin/run.sh

#add group and user tomcats
RUN sudo groupadd -r tomcata && sudo useradd -r -g tomcata -d /opt/tomcata -s /sbin/nologin tomcata

# make directory for pulling code from github and change owner to tomcata #
RUN mkdir /usr/src/evssearch && chown -RH tomcata: /usr/src/evssearch
RUN chown tomcata /usr/bin/run.sh && chmod 700 /usr/bin/run.sh

# change user to tomcata #
USER tomcata

# change to source code workdir #
WORKDIR /usr/src/evssearch
RUN git clone -b evsbrowser-4.0.2-06-java11-20190425 https://github.com/CBIIT/cadsr-admin-tool/
WORKDIR /usr/src/evssearch/cadsr-admin-tool/evsbrowser

EXPOSE 8080
ENTRYPOINT /usr/bin/run.sh
