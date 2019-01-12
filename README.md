# Home IoT CCTV

> **Required equipment:**
> - Raspberry Pi 3 B+ with Raspbian OS + motion for Camera module installed
> - Camera module
> - SG90 servo motor 2ea, holder for camera and motor
> - Android device

The required equipment in this projects are Raspberry Pi 3 Model B+, Camera module, 2 SG90 servo motors, holder for motor and camera module, Android device to monitor and control CCTV.  

If you didn't install Raspbian OS and motion software on your Raspberry Pi, you need to check [this](https://github.com/jwluv/HomeIoT_CCTV/blob/master/RaspberryPi_setup_Camera_install.pdf) first. 


![이미지](https://github.com/jwluv/HomeIoT_CCTV/blob/master/HomeIoT_CCTV.jpg)
**<그림> Home IoT architecture for CCTV**

The source code consists of two parts, the server(Raspberry Pi) and the client(Android device). <br>
In the server side, there are Node.js, html and python code. In the client side, there is an android project. <br>
The python code directly controls the position of sg90 servo motor.

To download the newest version of Node.js(Node.js is a JavaScript runtime built on Chrome's V8 JavaScript engine.),
> curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -

To install Node.js,
> sudo apt-get install -y nodejs

To check the version of Node.js,
> node -v

After placing the source codes such as cctv_control.js, public/cctv_control.html and camera_move.py in the same directory on Raspberry Pi,

To run Node.js on Raspberry Pi, you have to use npm, Node.js package manager. After that run Node.js file, cctv_control.js.
(npm is a package manager for the JavaScript programming language. It is the default package manager for the JavaScript runtime environment Node.js.)

> npm init <br>
> npm install socket.io --save <br>
> node cctv_control.js



----------------------------------------------------------------------------------------------------------
[![Video Label](http://img.youtube.com/vi/M9G-p8C65eI/0.jpg)](https://www.youtube.com/watch?v=M9G-p8C65eI)
