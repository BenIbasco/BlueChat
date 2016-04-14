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
 - Downloaded Android Studio to create "Hello World" project.
 - Familairzed ourselves with the IDE.
 - Reviewed code we chose to modify, began to map out the path the code takes while running.
 - Added an ACK feature to the code to help confirm
 
# Progress (Week 04/05/16 - 04/11/16)
 - Ben impoved ACK feature to change sender's chat color to green upon ACK.
 - Assigned work to Eduardo and Mike to prevent chat clear upon disconecction, and handler for constant bluetooth disconecction/reconecction, respectively.
 - Implemented initial reconnect feature (BlueChat/app/src/main/java/com/example/ben/bluechat/)[bluetoothchatfragent.java, bluetoothchatmessage.java, bluetootharrayadapter]
 - Fixed fatal error causing crash upon reconnection (Same files as above worked on)
 - Messages handle on click events (of message) to display message information e.g. timestamp, ACK, destination.

# Progress (Week 04/12/16 - 04/18/16)
 - Work on chat history.
 - Store file on phone of past chat messages.
 
# Schedule/Goals
 - Reliability feature to resend messages upon timeout or failure.
 - Prevent bluechat from clearing chat history after device disconnect.
 - Enable dynamic disconnect/reconnect feature.
 - Expand BluetoothChatService in UML Diagram
 
# Completed Goals
 - ACK feature to notify user of successful message transmission.
 - ACK feature with timestamp.

# Environment
 - Android Studio
