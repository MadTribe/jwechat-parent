
![Alt text](http://g.gravizo.com/g?
@startuml;
actor User;
participant "WeChat Service" as WX;
participant "First Class" as A;
participant "Second Class" as B;
participant "Last Class" as C;
User -> WX: Trigger/Send Message;
activate WX;
WX -> B: Create Request;
activate B;
B -> C: DoWork;
activate C;
C --> B: WorkDone;
destroy C;
B --> A: Request Created;
deactivate B;
A --> User: Done;
deactivate A;
@enduml
)
