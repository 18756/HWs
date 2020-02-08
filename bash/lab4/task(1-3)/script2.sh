#! /bin/bash
echo "bash script1.sh" | at now + 1 minute 2> /dev/null
(tail -n 0 -f /home/sasha/report) |
while true; do
    read LINE;
    echo $LINE
done