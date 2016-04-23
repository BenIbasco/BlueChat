# BlueChat
Android application that allows chat over a peer-to-peer Bluetooth network

# Contributers
  - Ben Ibasco
  - Eduardo Pozo
  - Chulundorj Zorigtbaatar

# Files Changed
  - BluetoothArrayAdapter (Completely new)
  - BluetoothChatMessage (Completely new)
  - BluetoothChatFragment (Most changes are in Handler class)
  - BluetoothChatService
  
# Progress (Week 03/29/16 - 04/04/16)
 - We each created our own "Hello World" projects to familiarize ourselves with Android Studio and Android development.
 - Familiarized ourselves with the code of open source Google sample, and strategized how we will modify it to implement our feature goals.
 - Chulundorj and Ben implemented message transmission.
 - Ben added a rudimentary ACK feature.
 
# Progress (Week 04/05/16 - 04/11/16)
 - Ben refined ACK feature by creating a new class for messages and a corresponding custom array adapter for the new message class.
 - Assigned saving chat history feature to Eduardo.
 - Assigned disconnected connection recovery to Chulundorj.
 - Chulundorj implemented rudimentary reconnection feature.
 - Ben fixed fatal error in color resource retrieval for messages.
 - Ben implemented messages to handle on click events to display message information e.g. timestamp, ACK, destination.

# Progress (Week 04/12/16 - 04/18/16)
 - Ben implemented message retransmission feature.
 - Chulundorj refined reconnection feature.
 - Chulundorj and Ben begin work on ad-hoc network.
    - Chulundorj is working on a custom tree data structure to store and represent the device network.
    - Ben is working on acquiring the network information through device scanning.

# Progress (Week 04/19/16 - 04/25/16)
 - Chulundorj completed preliminary work on the custom tree data structure and now needs the network data to test it.
 - Ben fixed the bluetooth scanning bug for Android 6.0.
 - Ben fixed the message acknowledgement bug for identical messages.
 
# Schedule/Goals
 - Prevent bluechat from clearing chat history after device disconnect.
 - Expand BluetoothChatService in UML Diagram
 
# Completed Goals
 - Message transmission.
 - ACK feature to notify user of successful message transmission.
 - Message timestamps.
 - Message retransmission.
 - Reconnection in the event of unintentional disconnected.

# Environment
 - Android Studio
