#!/bin/bash
while true; do
 read LINE
 if [[ "$LINE" = "TERM" ]]; then
 	kill -SIGTERM $(cat pid)
 fi
done