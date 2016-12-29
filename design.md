
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
@enduml
)
