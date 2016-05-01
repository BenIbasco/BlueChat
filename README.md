# BlueChat
Android application that allows chat over a peer-to-peer Bluetooth network

# Contributers
  - Ben Ibasco
  - Eduardo Pozo
  - Chuluundorj Zorigtbaatar

# Files Changed
  - BluetoothArrayAdapter (Completely new)
  - BluetoothChatMessage (Completely new)
  - BluetoothChatFragment (Most changes are in Handler class)
  - BluetoothChatService
  - AndroidManifest.xml
  - MacAddressTree
  
# Progress (Week 03/29/16 - 04/04/16)
 - We each created our own "Hello World" projects to familiarize ourselves with Android Studio and Android development.
 - Familiarized ourselves with the code of open source Google sample, and strategized how we will modify it to implement our feature goals.
 - Chuluundorj and Ben implemented message transmission.
 - Ben added a rudimentary ACK feature.
 
# Progress (Week 04/05/16 - 04/11/16)
 - Ben refined ACK feature by creating a new class for messages and a corresponding custom array adapter for the new message class.
 - Assigned saving chat history feature to Eduardo.
 - Assigned disconnected connection recovery to Chuluundorj.
 - Chuluundorj implemented rudimentary reconnection feature.
 - Ben fixed fatal error in color resource retrieval for messages.
 - Ben implemented messages to handle on click events to display message information e.g. timestamp, ACK, destination.

# Progress (Week 04/12/16 - 04/18/16)
 - Ben implemented message retransmission feature.
 - Chuluundorj refined reconnection feature.
 - Chuluundorj and Ben begin work on ad-hoc network.
    - Chuluundorj is working on a custom tree data structure to store and represent the device network.
    - Ben is working on acquiring the network information through device scanning.

# Progress (Week 04/19/16 - 04/25/16)
 - Chuluundorj completed preliminary work on the custom tree data structure and now needs the network data to test it.
 - Chuluundorj fixed screen orientation bug
 - Ben fixed the bluetooth scanning bug for Android 6.0.
 - Ben fixed the message acknowledgement bug for identical messages.
 
 # Progress (Week 04/26/16 - 04/28/16)
 - Eduardo completed rudimentary chat history saving feature.
 - Ben and Chuluundorj complete presentation diagrams.

# Completed Goals
 - Message transmission.
 - ACK feature to notify user of successful message transmission.
 - Message timestamps.
 - Message retransmission.
 - Reconnection in the event of unintentional disconnected.
 - Message history saving
 - Reliable Bluetooth scanning on Android 6.0

# Demonstrations
All video files are inside the BlueChat/videodemo/ directory

ReconnectGalaxy.mp4 and ReconnectMoto.mp4 showcase the reconnection feature.

baseProject.mp4 and noAcknowledgement.mp4 showcase the original project and what happens during the event that our application does not receive an message acknowledgement.

messageTransmission.mp4 showcases the normal behavior of our application during message transmission.

messageHistory.mp4 showcases the rudimentary message history saving feature.

# Environment
 - Android Studio
