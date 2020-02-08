#!/bin/bash
echo "$$" > pid
RES=1
MODE="+"

usr1() {
	MODE="+"
}

usr2() {
	MODE="*"
}

sigterm() {
	echo "end of the programm"
	exit
}

trap 'usr1' USR1
trap 'usr2' USR2
trap 'sigterm' SIGTERM

while true; do
	if [[ "$MODE" = "+" ]]; then
		let RES=$RES+2
	else
		let RES=$RES*2
	fi
	echo "$RES"
	sleep 1
done