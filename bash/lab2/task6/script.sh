#! /bin/bash
TOTAL=$(find /var/log/ -readable -name "*.log" 2>/dev/null | xargs wc -l | tail -1)
echo "$TOTAL" | grep -o -E "[0-9]+"