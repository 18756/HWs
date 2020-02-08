#!/bin/bash
echo "$$" > pid

TIME=0

term() {
	echo "Stop working, time of working is $TIME seconds"
	exit
}
trap 'term' SIGTERM

while true; do
	let TIME=$TIME+1
	sleep 1
done