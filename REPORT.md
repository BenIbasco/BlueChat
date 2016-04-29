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
The following files are where we added our changes:
 - BluetoothChatService.java (New reconnect function, global variables for reconnect function, and adjustments in the start function)
 - BluetoothChatFragment.java (Adjustments in the handler object to accommodate acknowledgements, message saving, and a new message type)
 - DeviceListActivity.java (Adjusted doDiscovery function to accommodate bluetooth scanning for Android 6.0)
 - BluetoothArrayAdapter.java (completely new)
 - BluetoothChatMessage.java (completely new)

**Program Flow**
 - Actions on startup:
    - On application startup, MainActivity.java is called and it instantiates a BluetoothChatFragment.java object. It also provides options to do bluetooth scanning which instantiates an object of DeviceListActivity.
    - If discovery is initiated, it creates a bluetooth adapter to do scanning and allows connection initiation.
    - The BluetoothChatFragment class instantiates a BluetoothArrayAdapter which holds BluetoothChatMessage objects to store messages. It also instantiates a BluetoothChatService object which allows connections.
 - Connection Management:
    - The BluetoothChatService class' default state is listening for incoming connections and returns to this state if connection attempts fail.
    - Once a connection is successful, a thread runs for data transmission.
    - If a connection disconnects unintentionally, then a reconnection will be initiated after 1 second.
 - Message Transmission:
    - Sending messages are triggered by the send button inside the BluetoothChatFragment class and call the subsequent service function to do the transmission.
    - When the BluetoothChatService class is in the thread for data transmission, it is constantly listening for incoming data from the bluetooth adapter.
    - When sending or receiving, the BluetoothChatService always calls the functions of the handler from the BluetoothChatFragment to be able to represent on the UI what message was transmitted or received.
<img src="diagrams/uml1.png" alt="UML Diagram Part 1"/>
<img src="diagrams/uml2.png" alt="UML Diagram Part 2"/>