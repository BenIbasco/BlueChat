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
 - Ben fixed fatal error causing crash upon reconnection.
 - Ben implemented messages to handle on click events to display message information e.g. timestamp, ACK, destination.

# Progress (Week 04/12/16 - 04/18/16)
 - Ben implemented message retransmission feature.
 - Chulundorj refined reconnection feature.
 - Ben and Chulundorj begin work on ad-hoc network.
 
# Schedule/Goals
 - Reliability feature to resend messages upon timeout or failure.
 - Prevent bluechat from clearing chat history after device disconnect.
 - Enable dynamic disconnect/reconnect feature.
 - Expand BluetoothChatService in UML Diagram
 
# Completed Goals
 - Message transmission.
 - ACK feature to notify user of successful message transmission.
 - Message timestamps.
 - Message retransmission.
 - Reconnection in the event of unintentional disconnected.

# Environment
 - Android Studio