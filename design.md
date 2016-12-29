The sequence diagram below shows how a request from a user is parsed into an InboundRequest object containing a InboundPayload object of the right type. Which is then passed onto the framework user's code.

![Alt text](http://g.gravizo.com/g?
@startuml;
actor User;
participant "WeChat Service" as WX;
participant "Jersey Service" as Jersey;
participant "WeChatInboundRequestEntityProvider" as IEP;
participant "WeChatInboundRequestW3CParser" as MPW3C;
participant "DefaultInboundPayloadParserRegistry" as PPR;
participant "InboundPayloadParser" as IPP;
participant "WeChatEntryPoint" as Entry;
participant "User Code" as UC;
User -> WX: Send Message;
WX -> Jersey: Send Request;
Jersey -> IEP: readFrom;
IEP --> MPW3C: parse;
MPW3C --> PPR: lookup message_type;
activate PPR;
PPR --> MPW3C: return payload parser;
deactivate PPR;
MPW3C --> IPP: parse;
activate IPP;
IPP --> MPW3C: return inboundPayload object;
deactivate IPP;
MPW3C --> IEP: return InboundRequest object;
deactivate MPW3C;
IEP --> Jersey: return InboundRequest object;
deactivate IEP;
Jersey --> Entry: onMessage passing InboundRequest;
activate Entry;
Entry --> UC: handler.apply;
activate UC;
@enduml
)


This is the default flow which in currently illustrated in the jwechat-exmaple-service project.

There are multiple points where this implementation can be reconfigured through injection of alternative classes. The core project has a small Guice module and a Guice main helper class which constructs this arrangement, but it is trivial to create a different module and use it to inject your own implementation of any step(s)

The following classes are configured with the Guice module.

# WeChatInboundRequestEntityProvider
Must be obtained from GuiceMain and manually registered with Jersey (dropwizard)

# WeChatInboundRequestW3CParser
A basic XML parser implementation. This imlpementation can be swapped for a different framework if needed. 

# DefaultInboundPayloadParserRegistry
Basically a lookup of known payload parsers that implements InboundPayloadParserRegistry this is the most likely thing you might want to swap. If you want to implement support for a new type of Wechat message. But a better approach is to contribute your code to the project. 

# InboundPayloadParser
Interface for all payload parsers.

# WeChatEntryPoint
This must be obtained from GuiceMain and manually registered with Jersey.


