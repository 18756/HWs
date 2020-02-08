#! /bin/bash
PIDS=$(grep -h -E "^Pid" /proc/*/status | grep -o -E "[0-9]+")
PPIDS=$(grep -h -E "^PPid" /proc/*/status | grep -o -E "[0-9]+")
AVG_LOADS=$(grep -h -E "\.load_avg" /proc/*/sched | grep -o -E "[0-9]+")
RES=$(paste <(printf %s "$PIDS") <(printf %s "$PPIDS") <(printf %s "$AVG_LOADS") | sort -k2 -n)
echo "$RES" | awk '{print "ProcessID=" $1 " : Parent_ProcessID=" $2 " : Average_Sleeping_Time=" $3}'
