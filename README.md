<p align="center"><img src="/screenshots/vaadin.png" width="40%" alt="Dynatrace Logo" /></p>

# Vaadin Load Testing with NeoLoad

## Overview

This Data Format Extension allows you to load test [Vaadin](https://vaadin.com/) with [NeoLoad](https://www.neotys.com/neoload/overview).

| Property | Value |
| -----| -------------- |
| Maturity | Experimental |
| Author   | Neotys Partner Team |
| License  | [BSD Simplified](https://www.neotys.com/documents/legal/bsd-neotys.txt) |
| NeoLoad  | 5.1+ (Enterprise or Professional Edition w/ Integration & Advanced Usage required)|
| Bundled in NeoLoad | No
| Download Binaries | See the [latest release](https://github.com/Neotys-Labs/Vaadin/releases/latest)

## Prerequisites

Vaadin UI objects as Button or TextField must have ids (the setId method must be used on theses objects).

## Installation

1. Download the [latest release](https://github.com/Neotys-Labs/Vaadin/releases/latest).
1. Read the NeoLoad documentation to see [How to install a Data Format Extension](https://www.neotys.com/documents/doc/neoload/latest/en/html/#6232.htm).
1. Import the framework
    * Go to NeoLoad preferences (Edit/Preferences)
    * On the left panel select the Frameworks section.
    * Click the Import button to add the [NeoLoad Vaadin Framework](files/Vaadin.xml.zip).
    
<p align="center"><img src="/screenshots/framework.png" alt="Frameworks" /></p>

## Record in NeoLoad

Once installed, how to record Vaadin based applications:

1. Read the NeoLoad documentation to see [How to start a record](https://www.neotys.com/documents/doc/neoload/latest/en/html/#752.htm)
1. During the post-recording wizard, search for dynamic parameters
    <p align="center"><img src="/screenshots/dynamic-parameters.png" alt="Dynamic parameters" /></p>
1. Apply Vaadin framework parameters.
    <p align="center"><img src="/screenshots/selection-parameters.png" alt="Selection parameters" /></p>
1. Vaadin request in NeoLoad User Path:
    * All Vaadin HTTP requests or websocket requests are displayed with icon <img src="/screenshots/request.png" alt="request" />.
    * Websocket channels are displayed with icon <img src="/screenshots/channel.png" alt="channel" />.

## Design in NeoLoad

Every action done on the UI Web application will refer to a Vaadin RPC id. Those ids must be correlated to ensure the stability of the User Profile.
To manage these ids, you need to create NeoLoad variables with NeoLoad [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm).

In order to ease correlation, the Vaadin Data Format Extension build a map associating UI ids to RPC ids and add it to the response.

### Example
