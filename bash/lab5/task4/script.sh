#! /bin/bash
if [ ! -d "$HOME/restore" ]; then
    mkdir "$HOME/restore"
fi
regex="$HOME/Backup-*"
last_backup_dir=$(find $regex -maxdepth 0 2> /dev/null | sort -n | tail -1)
if [[ ! -z "$last_backup_dir" ]]; then
	ls "$last_backup_dir" |
	while read FILE; do
		if [ -f "$HOME/restore/$FILE" ]; then
    		rm "$HOME/restore/$FILE"
		fi
		if ( ! echo "$FILE" | grep -Eq ".+\.[0-9]{4}-[0-9]{2}-[0-9]{2}" ); then
    		cp "$last_backup_dir/$FILE" "$HOME/restore/$FILE"
   		fi
	done
else
	echo "No backup dir"
fi