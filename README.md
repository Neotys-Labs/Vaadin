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

* Vaadin 7.+ or 8.+.
* Vaadin UI objects as Button or TextField must have ids (the setId method must be used on theses objects).

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
1. During the Post-recording wizard, search for **Dynamic Parameters**.
    <p align="center"><img src="/screenshots/dynamic-parameters.png" alt="Dynamic parameters" /></p>
1. Apply **Vaadin framework parameters**, uncheck Silverlight parameter.
    <p align="center"><img src="/screenshots/selection-parameters.png" alt="Selection parameters" /></p>
1. Vaadin request in NeoLoad User Path:
    * All Vaadin **HTTP requests** or **WebSocket requests** are displayed with icon <img src="/screenshots/request.png" alt="request" />.
    * **WebSocket channels** are displayed with icon <img src="/screenshots/channel.png" alt="channel" />.

## Design in NeoLoad

Every action done on the UI Web application will refer to a Vaadin RPC id. Those ids must be correlated to ensure the stability of the User Profile.
To manage these ids, you need to create NeoLoad variables with NeoLoad [Variable Extractors](https://www.neotys.com/documents/doc/neoload/latest/en/html/#962.htm).

In order to ease correlation, the Vaadin Data Format Extension build a map associating UI ids to RPC ids and add this map to the response.

### Example

An example of a correlation of the login field on a login page.
In the Vaadin Data Format Extension response, there is a section **mapping**:
```
 <mapping>
    <entry>
      <String>login-login-button</String>
      <String>11</String>
    </entry>
    <entry>
      <String>login-reset-password</String>
      <String>10</String>
    </entry>
    <entry>
      <String>login-view-container</String>
      <String>4</String>
    </entry>
    <entry>
      <String>progress-layout-id</String>
      <String>12</String>
    </entry>
    <entry>
      <String>login-password</String>
      <String>9</String>
    </entry>
    <entry>
      <String>login-view</String>
      <String>5</String>
    </entry>
    <entry>
      <String>login-username</String>
      <String>8</String>
    </entry>
    <entry>
      <String>login-logo</String>
      <String>7</String>
    </entry>
  </mapping>
  ```
  We can see that the ui field with the id **login-username** is associated to the RPC id **8**.
  
  Let's see how to extract this id with a NeoLoad extractor:
  
  1. Create a variable extracting the RPC id of your object.
    <p align="center"><img src="/screenshots/extractor.png" alt="Variable Extractor" /></p>
  1. Then **Move as framework parameter** the created Variable Extractor.
    <p align="center"><img src="/screenshots/move-as-framework.png" alt="Move as framework" /></p>
  1. Add it to the **Vaadin** framework.
    <p align="center"><img src="/screenshots/framework-vaadin.png" alt="Vaadin Framework" /></p>
  1. Define **Injection settings**.
    <p align="center"><img src="/screenshots/inject.png" alt="Injection settings" /></p>
  1. Search for **Dynamic Parameters**.
    <p align="center"><img src="/screenshots/search.png" alt="Search" /></p>
  1. Select the parameter and click on Finish.
    <p align="center"><img src="/screenshots/select-dynamic.png" alt="Select" /></p>

Now the variable is injected the request using the rpc id and the variable extractor will be automatically created and injected in next recording of the application.
```
<com.neotys.codec.vaadin.VaadinWsRequest>
  <size>Size will be automatically computed by Neoload before sending the request.</size>
  <content>
    <map>
      <entry>
        <String>csrfToken</String>
        <String>${crstoken}</String>
      </entry>
      <entry>
        <String>clientId</String>
        <int>1</int>
      </entry>
      <entry>
        <String>rpc</String>
        <org.json.JSONArray>
          <myArrayList>
            <org.json.JSONArray>
              <myArrayList>
                <String>${login-username}</String>
                <String>com.vaadin.shared.ui.textfield.AbstractTextFieldServerRpc</String>
                <String>setText</String>
                <org.json.JSONArray>
                  <myArrayList>
                    <String></String>
                    <int>0</int>
                  </myArrayList>
                </org.json.JSONArray>
              </myArrayList>
            </org.json.JSONArray>
          </myArrayList>
        </org.json.JSONArray>
      </entry>
      <entry>
        <String>syncId</String>
        <int>${SYNCID}</int>
      </entry>
    </map>
  </content>
</com.neotys.codec.vaadin.VaadinWsRequest>
```
