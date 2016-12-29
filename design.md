
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
