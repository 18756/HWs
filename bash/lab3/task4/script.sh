#! /bin/bash
awk '{match(FILENAME, /[0-9]+/, res); print res[0] " " $2-$3}' /proc/*/statm | sort -k2 -n | sed 's/ /:/'
