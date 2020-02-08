#! /bin/bash
bash ../task5/script.sh | sed "s/=/= /g" | awk '
{
	if (NR <= 1 || ppid != $5) {
		if (ppid != $5) {
			print "Average_Sleeping_Children_of_ParentID=" ppid " is " sum/amount
		}
		amount=0
		sum=0
		ppid=$5
	}
	amount++
	sum+=$8
	print $0
}
END {
	print "Average_Sleeping_Children_of_ParentID=" ppid " is " sum/amount
}' | sed "s/= /=/g"