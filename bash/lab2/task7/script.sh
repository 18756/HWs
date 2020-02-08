#! /bin/bash
ANS=$(man bash | grep -o -E "[[:alpha:]-]{4,}" | sort | uniq -c | sort -n -r | head -3)
echo "$ANS" | grep -o -E "[[:alpha:]-]+"