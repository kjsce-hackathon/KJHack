import RPi.GPIO as GPIO
import time
import os
#import subprocess
#from subprocess import call
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(3, GPIO.IN)
#GPIO.setup(3, GPIO.OUT)
while True:
	i=GPIO.input(3)
	if i==0:
		print "No intruders",i


	elif i==1:
		print "Intruder detected",i
		os.system('fswebcam -r 1280x720 webcam0.jpg')
	time.sleep(5)
