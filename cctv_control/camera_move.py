#https://circuitdigest.com/microcontroller-projects/raspberry-pi-servo-motor-control

import RPi.GPIO as GPIO
import time
import sys
import getopt

def main(argv):
    x_axis = y_axis = 0

    try:
        opts, args = getopt.getopt(argv, "hx:y:", ["x_axis=", "y_axis="])
    except getopt.GetoptError:
        print 'camera_move -x <x_axis> -y <y_axis>'
        sys.exit(1)

    for opt, arg in opts:
        if opt == '-h':
            print 'camera_move -x <x_axis> -y <y_axis>'
            sys.exit(1)
        elif opt in ("-x", "--x_axis"):
            x_axis = float(arg)
        elif opt in ("-y", "--y_axis"):
            y_axis = float(arg)

    #if len(argv) < 2:
    #    print "Usage: python camera_move.py -x x_axis -y y_axis"
    #    sys.exit(1)
    
    for arg in argv:
        print "argument: ", arg

    print "x_axis: ", x_axis
    print "y_axis: ", y_axis
    

    pin_x = 18 # pin num 18(x-axis)
    pin_y = 4   # 4(y-axis)

    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(pin_x, GPIO.OUT)
    GPIO.setup(pin_y, GPIO.OUT)
    p_x = GPIO.PWM(pin_x, 50)
    p_y = GPIO.PWM(pin_y, 50)

    MIN_VALUE = 2.5
    MAX_VALUE = 12.5
    CENTER_VALUE = 7.5

    try:
        if x_axis != 0:
            print "Move camera x: ", x_axis
            p_x.start(x_axis)
            time.sleep(0.5)
            p_x.ChangeDutyCycle(x_axis) 
        elif y_axis != 0:
            print "Move camera y: ", y_axis
            p_y.start(y_axis)
            time.sleep(0.5)
            p_y.ChangeDutyCycle(y_axis)
        else:
            p_x.start(CENTER_VALUE)
            time.sleep(0.5)
            p_x.ChangeDutyCycle(x_axis)
            time.sleep(0.5)
            p_y.start(CENTER_VALUE)
            time.sleep(0.5)
            p_y.ChangeDutyCycle(y_axis)   
        
    except KeyboardInterrupt:
        p_x.stop()
        p_y.stop()
    finally:
        GPIO.cleanup()

if __name__ == "__main__":
        main(sys.argv[1:])





    


 




