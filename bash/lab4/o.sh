#!/bin/bash
(tail -n 0 -f data.txt) |
while true; do
 read LINE;
 case $LINE in
 QUIT)
 echo "exit"
killall tail
exit
;;
 *)
 echo $LINE
;;
 esac
done