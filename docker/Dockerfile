FROM debian:sid
MAINTAINER Miguel de la Cruz <mgdelacroix@gmail.com>

# BOOTSTRAP
RUN apt-get update && \
    apt-get install -yq locales ca-certificates wget sudo && \
    rm -rf /var/lib/apt/lists/* && \
    localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8

# INSTALL DEPS
RUN apt-get update && \
    apt-get install -yq \
                    vim \
                    tmux \
                    curl \
                    rlwrap \
                    openjdk-8-jdk

# INSTALL LEIN
ENV LEIN_ROOT 1
RUN curl https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein -o /usr/local/bin/lein && chmod +x /usr/local/bin/lein && lein upgrade

# COPY LOCALDIR
COPY . /app

# INSTALL LEIN DEPS
WORKDIR /app
RUN lein deps

# PORTS
EXPOSE 5050

CMD lein run
