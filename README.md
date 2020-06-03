# Record Parsing
There are two systems inside this project one being a command line parser and the other is a Spring microservice.

# Command Line Parser
The Commmand Line Parser's entry point is located in the com.example.parse package and to run this simply run the main method and pass a list of paths via the program arguments. There are test files located in the resource folder under sample files to save some time, else create you own and referece the asbolute path. When the parser is ran it will scan each file collecting the valid line and sorting them accordingly

# Web API
The Web Api version of the parser it located in the com.example.api package and to run this also run the main method in ApiAplication.
