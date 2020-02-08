#! /bin/bash
grep -o -I -s -h -R -E "ACPI.*" /var/log > errors.log
grep -o -E "[^[:space:]]+\.[^[:space:]]+" errors.log