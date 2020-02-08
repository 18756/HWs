#!/bin/bash
RES=1
MODE="+"

(tail -n 0 -f data.txt) |
while true; do
	read LINE;
	if [[ "$LINE" = "+" ]] || [[ "$LINE" = "*" ]]; then
		MODE=$LINE
	elif ( echo "$LINE" | grep -Eq "^[0-9]+$" ); then
		if [[ "$MODE" = "+" ]]; then
			let RES=$RES+$LINE
		else
			let RES=$RES*$LINE
		fi
	elif [[ "$LINE" = "QUIT" ]]; then
		echo "End of the programm"
		exit
	else
		echo "Wrong input data" "$LINE"
		exit 
	fi
	echo "$RES"
done