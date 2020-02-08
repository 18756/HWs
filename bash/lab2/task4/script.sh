#! /bin/bash
ALL_SCRIPTS=$(grep -R -I -s -h -o -E "#! /bin/[[:alnum:]\.]+" /bin)
FREQUENT_SCRIPT=$(echo "$ALL_SCRIPTS" | sort | uniq -c | sort -n -r | head -1)
echo $FREQUENT_SCRIPT | sed -E 's/[0-9 #!]+//'