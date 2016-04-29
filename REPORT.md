# Project Goal
Our group wanted to build a communication service that could be available in the event of no internet or mobile network connection. Thus, the primary goal of this project was to create a Android chat application over a bluetooth ad-hoc network. In order to achieve this, we needed to fulfill secondary goals of implementing core one-to-one chat features such as message acknowledgement, message saving, and reconnection.

# Project Design

**Connection Behavior**
 - The application's default state is listening mode where it waits for incoming connections.
 - To make connections, the application must initiate bluetooth discovery to find out what are the available devices to connect to. Then the user selects a device in order to initiate a connection.
 - The bluetooth adapter of the receiving device will then accept the connection and so the two devices will be paired and allow data transmission through input and output streams from the bluetooth adapters.
 - In the event of an unintentional disconnection, the application is designed to initiate a reconnection after 1 second. Even though it's possible for both devices to initiate a reconnection, the first reconnection attempt will always be accepted and any connection attempts that occur after are ignored.
<img src="diagrams/Connections.png" alt="Connection Behavior"/>

**Message Transmission Behavior**
 - Messages are sent as arrays of bytes and trigger acknowledgements from the other device.
 - If the application receives an acknowledgement for a message, it searches for its message array for that message and set its acknowledgement. Setting the acknowledgement allows the UI adapter to turn the message green.
 - In the event of a message being lost, the UI adapter will reveal a button for retransmission.
<img src="diagrams/msgAck.png" alt="Message Transmission Behavior"/>

# File Structure

<img src="diagrams/uml1.png" alt="UML Diagram Part 1"/>
<img src="diagrams/uml2.png" alt="UML Diagram Part 2"/>