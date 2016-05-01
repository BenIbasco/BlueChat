# Project Goal
Our group wanted to build a communication service that could be available in the event of no internet or mobile network connection. Thus, the primary goal of this project was to create a Android chat application over a bluetooth ad-hoc network. In order to achieve this, we needed to fulfill secondary goals of implementing core one-to-one chat features such as message acknowledgement, message saving, and reconnection.

# Project Design
**Approach**

The approach to this project consisted of a few elements that were discussed as a team.  First, we had to identify what networking problem we wanted our project to solve and what development tools or languages did we want to learn more about. We wanted to be able to provide a chat service without needing wifi or a data plan. As a result, we decided on a bluetooth reliant messaging service written in java for the the android platform.  We discussed how we were going to create the project by either creating the GUI and bluetooth sockets, or improving a previously existing open source project.  We decided to use google’s bluetooth chat service, https://github.com/googlesamples/android-BluetoothChat, as our framework in order to accelerate the process of learning the Android platform abstraction for the UI and Bluetooth API.  With this open source project we identified what features were missing, what bugs needed to be fixed, and how we could implement an ad-hoc network.

Once we decided on the bluetooth chat service, we studied what the source code had already accomplished.  We found that the Google sample we chose only provided basic functions and UI, but had a lot of limitations such as, random crashes involving bluetooth scanning, phone screen rotation, and no chat history feature. The issues we decided to focus on are reconnection upon disconnection, message acknowledgement  and retransmission, and chat history.  Dividing these tasks amongst our group involved quite a bit of collaboration between the three of us.

**Collaboration**

For collaboration, we all decided to take ownership of at least one major feature each, and then members were free to work on minor features and bug fixes that we discovered while working on the project. Some of the major features we were working on had an effect on each other, so we made sure to properly design how we were going to implement the major features and then let the entire group aware of this design. An example of this is the implementation of message acknowledgement and retransmission where Ibasco decided to create a new message class, which would affect how messages are stored. Ibasco let Pozo know the member variables of each chat message object and wrote supporting methods for him to support message saving.

Because the design process was broken up into pieces/classes that had little to do with each other, testing was made simpler. When testing, each member of the team tested his or her changes independently before pushing the changes. However, collisions could not be avoided completely. Some changes made by a team member introduced new problems that needed to be fixed  on one’s own code.

Furthermore, breaking up each individual’s responsibility required that tasks assigned to teammates did not impede other members progress. As a result, each team member focused on a specific class that had minimum overlap with another teammates responsibility, but it was difficult to keep collisions to zero. In the testing phase, some collisions appeared.

**Testing**

Ibasco’s Testing

As mentioned above, Ibasco’s changes were concerned with message acknowledgement and retransmission. Since, Ibasco cannot control when a message will not reach the other device, he decided to use the older version of BlueChat on one phone and the newer version with message acknowledgement and retransmission on the other phone. As a result, when the devices connected with each other, the older version would not send an ACK back, so the message would not turn green and the retransmission button would appear on the newer version of the application. Furthermore, Ibasco could format the reply in the older version of the app to send an ACK for a specific message.

Pozo’s Testing

Pozo’s testing involved disconnecting and reconnecting to the same device to test if previous messages saved in the database would appear again. However, before Pozo reached this phase of the testing he encountered several problems instantiating variables of SQLhelper and finding the location of the problem. Pozo got around this problem by using the log to print out strings to find where the program was stopping. Furthermore, Pozo found out that he had problems creating multiple database and not clearing them when the application terminated.

Chuluundorj’s Testing

Chuluundorj’s testing involved creating artificial disconnection to test the reconnection feature. Chuluundorj achieved this goal by turning of the Bluetooth on one of the devices and letting the other device initiate the reconnection. Before the reconnection attempt, the bluetooth needed to be turned on again. As a result, Chuluundorj set the timeout of the reconnection to 10 seconds to make room for the relaunch of the application. He successfully tested the reconnection feature and set the timeout to 1 second before pushing it. Furthermore, Chuluundorj set the reconnection feature to only on try.

**Conclusion**

In conclusion, although some improvements could have been made, the breaking up of responsibilities was done correctly. Furthermore, we did not do enough research before choosing our topic, thus the difficulties in creating a bluetooth based ad-hoc network were unseen. Bluetooth is not designed for ad-hoc network, unless it is in master slave mode, because Bluetooth does not support multiple connections. Furthermore, disconnecting and reconnecting to another device requires at least 3 seconds; considering how many nodes the message needs to traverse before reaching its final destination, it is unrealistic.

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
