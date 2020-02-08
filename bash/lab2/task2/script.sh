#! /bin/bash
WARNIGNS=$(grep -E "(WW)" /var/log/Xorg.0.log)
INFORMATIONS=$(grep -E "(II)" /var/log/Xorg.0.log)
echo "${WARNIGNS//(WW)/Warning: }" > full.log
echo "${INFORMATIONS//(II)/Information: }" >> full.log
cat full.log