#! /bin/bash
sudo cpulimit --pid 13756 --limit 15 --background
while true; do
	let A=1+1
done