#! /bin/bash
ALL_USERS=$(grep -o -E "^[^:]+:[^:]+:[^:]+" /etc/passwd)
echo "$ALL_USERS" | sed -E 's/:[^:]+:/ /' | sort -k2 -n
