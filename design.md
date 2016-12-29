
![Alt text](http://g.gravizo.com/g?
@startuml;
actor User;
participant "WeChat Service" as WX;
participant "Jersey Service" as Jersey;
participant "WeChatInboundRequestEntityProvider" as IEP;
participant "WeChatInboundRequestW3CParser" as MPW3C
participant "DefaultInboundPayloadParserRegistry" as PPR
participant "InboundPayloadParser" as IPP
participant "WeChatEntryPoint" as Entry;
participant "User Code" as UC;
participant "Last Class" as C;
User -> WX: Trigger/Send Message;
activate WX;
WX -> Jersey: Send Request;
activate Jersey;
Jersey -> IEP: readFrom();
activate IEP;
IEP --> MPW3C: parse();
activate MPW3C;
MPW3C --> PPR: lookup(message_type);
deactivate PPR;
MPW3C --> IPP: parse( message payload ) 
deactivate IPP;
deactivate MPW3C;
Jersey --> Entry: onMessage( );
activate Entry;
Entry --> UC: handler.apply(..)
@enduml
)
