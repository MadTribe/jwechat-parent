# Description

This project intends to provide a flexible and simple Java API for interaction with Wechat Services

The project goals are that it should be:

Modular - A user should not need to depend on features that they don't use.
Widely compatible, although there will be opinionated decisions about some frameworks, by default it will use Guice for dependency injection and an event bus.
Fail fast design.

A major intention is to keep the API up to date with the latest Wechat features.


## Kicking the tires.
Since the project is still in its early days there are a few more manual steps needed than is desirable. Will fix this as we go.

Developing in Wechat requires a server that the Wechat service can connect to. For kicking the tires, this is a bit inconvenient.

You can easily set up a Sandbox account on Wechat [here](http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login).

TODO - Notes on configuring Wechat Sandbox

Docker is a convenient way to get things installed and some basic scripts are available to do this.
Localtunnel is a useful tool for exposing your local machine to wechat.

### You will need these common tools:
Java8
Maven
NodeJS
NPM
LocalTunnel (Node JS Module)
DockerMachine





