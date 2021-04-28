# BluetoothConnectApp

This is the code for getting callback of button press in bluetooth headset in Android app. This is written in Java.
For service class, MediaBrowserServiceCompat class is used and for callback MediaSessionCompat.Callback is used.

### Steps:
1. create a service class extending from MediaBrowserServiceCompat
2. override onGetRoot & onLoadChildren
3. Inside onCreate create a MediaSessionCompat and set callback
4. For callback MediaSessionCompat.Callback is used. And all button click events is captured in onMediaButtonEvent
5. In mainActivity create MediaBrowserCompat using service class
6. Finally add service in AndroidManifest file
