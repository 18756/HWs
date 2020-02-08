#! /bin/bash

error2() 
{
	:
}

secondPart() 
{
	trap 'error2' ERR
	ping http://www.net_nikogo.ru/ 2>> /home/sasha/report
}


error1() 
{
	secondPart
	exit
}

trap 'error1' ERR
mkdir /home/sasha/test 2>> /home/sasha/errors
echo "catalog test was created successfully" >> /home/sasha/report
DATE_TIME=$(date +"%Y-%m-%d %T")
touch "/home/sasha/test/$DATE_TIME"
secondPart
