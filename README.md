# CSCI345-Assignment2-Deadwood

## Clone

Clone repo with `git clone https://github.com/pjschinske/CSCI345-Assignment2-Deadwood/`.

## Compile and run

Compile with `javac -d . src/deadwood/*.java src/deadwood/board/*.java src/deadwood/xml/*.java`.

Run with `java deadwood.Deadwood`.

## Possible commands:
- whoami: lists info about player
- where: tells you where all players are
- move [location]: moves to the specified location if able
- scene: lists info about the scene card at the current tile, if the current tile has one.
- work [role]: Player states their intention to work a role
- act: Player acts the role
- rehearse: Player rehearses the role
- upgrade: if at casting office, allows the user to upgrade to a higher level
- end: end your turn

### Location Names:
 - Trailers
 - Main Street
 - Saloon
 - Casting Office
 - Ranch
 - Secret Hideout
 - Bank
 - Church
 - Hotel
 - Train Station
 - Jail
 - General Store
