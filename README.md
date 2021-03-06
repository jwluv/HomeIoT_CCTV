# Home IoT CCTV

<img src="https://github.com/jwluv/HomeIoT_CCTV/blob/master/raspberry_camera.jpg"  width="500" height="375"> 

> **Required equipment:**
> - Raspberry Pi 3 B+ with Raspbian OS + motion for Camera module
> - Camera module
> - 2 SG90 servo motor, holder for camera and SG90
> - Android device

The required equipment in this projects are Raspberry Pi 3 Model B+, Camera module, 2 SG90 servo motors, holder for motor and camera module, Android device to monitor and control CCTV.  

If you didn't install Raspbian OS and motion software on your Raspberry Pi, you need to check [this](https://github.com/jwluv/HomeIoT_CCTV/blob/master/RaspberryPi_setup_Camera_install.pdf) first. 


![이미지](https://github.com/jwluv/HomeIoT_CCTV/blob/master/HomeIoT_CCTV.jpg)
**<Fig.> Home IoT architecture for CCTV**

The source codes consist of two parts for the server(Raspberry Pi) and the client(Android device). <br>
In the server side, there are Node.js, html and python code. In the client side, there is an android project code. <br>
Node.js communicates with the client(Android app.) through html using socket connection and also can control the GPIO of Raspberry Pi.
In this project, the python code directly controls the angle of 2 sg90 servo motors whenever the client requests it.

To download the newest version of Node.js (Node.js is a JavaScript runtime built on Chrome's V8 JavaScript engine.),
> curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -

To install Node.js,
> sudo apt-get install -y nodejs

To check the version of Node.js, installed,
> node -v

Place the source codes such as cctv_control.js, public/cctv_control.html and camera_move.py in the same directory on Raspberry Pi.

To run Node.js on Raspberry Pi, you have to use npm, Node.js package manager which is installed with Node.js. After that, run Node.js file, cctv_control.js.
(npm is a package manager for the JavaScript programming language. It is the default package manager for the JavaScript runtime environment Node.js.)

> npm init <br>
> npm install socket.io --save <br>
> node cctv_control.js



----------------------------------------------------------------------------------------------------------
Watch the video to check how this project works. 

[![Video Label](http://img.youtube.com/vi/M9G-p8C65eI/0.jpg)](https://www.youtube.com/watch?v=M9G-p8C65eI)
