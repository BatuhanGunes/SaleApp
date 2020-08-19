**Language :** English / [Turkish](https://github.com/BatuhanGunes/SaleApp/blob/master/README(Turkish).md)

# SaleApp
Android SaleClient and desktop SaleServer application 

### Overview

This project is designed for the sale of various products to the user from an android client application with the information obtained from a server application created with java. In other words, this project consists of the communication of two different applications, server and client, that are in communication with each other and the processes that occur as a result of this communication.

The server application is designed to create, update and report products to be used in the client application. The server application keeps these products in its memory. It communicates mutually with the client and ensures that the products in its memory are delivered to the client. It decides which data will be transmitted by messages from the client. It also takes on the task of saving the new data generated by the customer. Again, in this process, it determines how and which data will be saved by messages from the client. It saves the data that comes with the message to the database.

The client application gets the required connection values for the server application from the user. Communication is attempted with the information received. If the process goes smoothly, the user moves to a new page. On the new page, the user can request the data on the server to be transmitted to the client. After this request, it sends a message to the server and waits for the response of this message. The client listens to the responses sent by the server and stores the previously recorded products in its memory. By presenting these products to the user, it allows the user to shop through the client application. The user can choose any amount of the available products. The total price of the products to be purchased is determined according to the user's choices. The user can make the payment by cash or credit card. There is no real equivalent in payment transactions, it is assumed to be paid by the user. After the user pays, a voucher is created with the purchased products. If the user wishes, they can create a new receipt on the existing receipt to continue shopping. When the customer ends the shopping process, data regarding the shopping made by the user is collected. These collected data are sent to the server in an order. These data coming to the server are separated and saved in the database.

### Database Tables

<img align="center" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/tableProduct.png"> <img align="center" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/tableSale.png"> <img align="center" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/tableSaleDetails.png">

## Screenshots
### Client App

<img align="center" width="180" height="320" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Client1.jpeg">  <img align="center" width="180" height="320" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Client5.jpeg"> <img align="center" width="180" height="320" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Client2.jpeg">  <img align="center" width="180" height="320" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Client3.jpeg"> <img align="center" width="180" height="320" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Client4.jpeg">

### Server App

<img align="center" width="440" height="350" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Server1.png"> <img align="center" width="440" height="350" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Server2.png"> <img align="center" width="440" height="350" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Server3.png"> <img align="center" width="440" height="350" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/Server4.png"> 

## Getting Started

Download a copy of the project files to your local machine to run the project. Once you have the required environments, you can open the project and run it in this environment. You can run the program on an android device.

## Requirements

*   Android Studio (installed on a Linux, Mac or Windows machine)
*   Android device in [`developer mode`](https://developer.android.com/studio/debug/dev-options) with USB debugging enabled
*   USB cable (to connect Android device to your computer)
*   Java IDE (installed on a Linux, Mac or Windows machine)
*   Java Development Kit (JDK)

## Build and run

### Step 1. Clone the SaleApp source code

Clone the Sign Language Converter GitHub repository to your computer to get the application.

```
git clone https://github.com/BatuhanGunes/SaleApp
```

Open the Client source code in the Java IDE. To do this, open the Java IDE and follow the path `SaleApp/ProductProgramming/`.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build8.png" />

Open the Client source code in Android Studio. To do this, open Android Studio and follow the path `SaleApp/SaleClient/`.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build1.png" />

### Step 2. Build the Java Application project
After saving the project file in your workspace, right click on the project and follow the path `Run As -> Maven Build`. Enter the `clean verify` label in the `Goal` field in the window that opens. Then run it with the `Run` option. After making the necessary configurations, the `pom.xml` file will automatically download the missing libraries.

### Step 3. Install and run the Java app

It will be sufficient to follow the path `Run -> Run`.

### Step 4. Build the Android Studio project

Select `Build -> Make Project` and check that the project builds successfully. You will need Android SDK configured in the settings. The `build.gradle` file will prompt you to download any missing libraries.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build2.png" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build3.png" />

### Step 5. Install and run the Android app

Connect the Android device to the computer and be sure to approve any ADB permission prompts that appear on your phone. Select `Run -> Run app.` On connected devices, select the delivery destination to the device where the application will be installed. This will install the application on the device. In order for the application to work correctly, both applications must be open at the same time.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build4.png" style="width: 60%" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build5.png" style="width: 70%" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build6.png" style="width: 40%" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build7.png" style="width: 80%" />

To test the app, open the app called `SaleClient` on your device. Re-installing the app may require you to uninstall the previous installations.

## Authors

* **Batuhan Güneş**  - [BatuhanGunes](https://github.com/BatuhanGunes)

See also the list of [contributors](https://github.com/BatuhanGunes/SaleApp/graphs/contributors) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE.md](https://github.com/BatuhanGunes/master/master/master/LICENSE) file for details.


