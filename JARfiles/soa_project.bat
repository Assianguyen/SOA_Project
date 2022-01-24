ECHO OFF
ECHO Starting microservices...
start "BlindMS" java -jar BlindMS.jar
ECHO BlindMS started
start "DoorMS" java -jar DoorMS.jar
ECHO DoorMS started
start "GazMS" java -jar GazMS.jar
ECHO GazMS started
start "LightMS" java -jar LightMS.jar
ECHO LightMS started
start "LuminosityMS" java -jar LuminosityMS.jar
ECHO LuminosityMS started
start "NoiseMS" java -jar NoiseMS.jar
ECHO NoiseMS started
start "PresenceMS" java -jar PresenceMS.jar
ECHO PresenceMS started
start "StationMS" java -jar StationMS.jar
ECHO StationMS started
start "TemperatureMS" java -jar TemperatureMS.jar
ECHO TemperatureMS started
start "ToFMS" java -jar ToFMS.jar
ECHO ToFMS started
start "WindowMS" java -jar WindowMS.jar
ECHO WindowMS started
start "GlobalMS" java -jar GlobalMS.jar > logGlobal.txt
ECHO GlobalMS started
start "node-red" node-red
start http://localhost:1880/#flow/af0ec05852629a65
start http://localhost:1880/ui