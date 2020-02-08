#! /bin/bash
REG="[[:alnum:]]+@[[:alpha:]]+\.[[:alpha:]]+"
grep -R -I -s -o -h -E $REG /etc > emails.lst