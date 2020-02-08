#! /bin/bash
regex="$HOME/Backup-*"
last_backup_dir=$(find $regex -maxdepth 0 2> /dev/null | sort -n | tail -1)
if [[ ! -z "$last_backup_dir" ]]; then
	old_date=$(echo $last_backup_dir | awk -F "-" '{print $2 $3 $4}')
	delta_days=$((($(date +%s) - $(date --date="$old_date" +%s))/(60*60*24)))
else
	delta_days=117
fi

if (($delta_days > 7)); then
	dir="$HOME/Backup-$(date "+%Y-%m-%d")"
	mkdir $dir
	cp -r "$HOME/source/." $dir
	echo "created dir $dir with files: $(ls -m $dir)"$'\n' >> "$HOME/backup-report"
else
	new_files_notes=""
	old_files_notes=""
	ls "$HOME/source/" |
	(while read FILE; do
		if [ -f "$last_backup_dir/$FILE" ]; then
    		size1=$(wc -c "$last_backup_dir/$FILE" | awk '{print $1}')
    		size2=$(wc -c "$HOME/source/$FILE" | awk '{print $1}')
    		if [[ "$size1" != "$size2" ]]; then
    			mv "$last_backup_dir/$FILE" "$last_backup_dir/$FILE.$(date "+%Y-%m-%d")"
    			cp "$HOME/source/$FILE" "$last_backup_dir/$FILE"
    			old_files_notes+="added new version of file ${FILE}(old version ${FILE}.$(date "+%Y-%m-%d"))"$'\n'
    		fi
    	else
    		cp "$HOME/source/$FILE" "$last_backup_dir/$FILE"
    		new_files_notes+="added new file ${FILE}"$'\n'
		fi
	done
	if [[ ! -z "$old_files_notes" ]] || [[ ! -z "$new_files_notes" ]]; then
		echo "$last_backup_dir was updated $(date "+%Y-%m-%d")" >> "$HOME/backup-report"
		echo "${new_files_notes}${old_files_notes}" >> "$HOME/backup-report"
	fi)
fi 