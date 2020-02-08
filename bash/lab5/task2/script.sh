#! /bin/bash
file_name="$1"
cat "$HOME/.trash.log" | grep "/$file_name ->" |
while read line; do
	old_path=$(echo "$line" | awk '{print $1}')
	old_dir=$(echo "$old_path" | grep -o -E ".+/")
	trash_link_name=$(echo "$line" | awk '{print $3}')
	trash_path="$HOME/.trash/$trash_link_name"
	if [[ -f "$trash_path" ]]; then
		echo "$old_path"
		echo "(restore? YES or NO)"
		read ans </dev/tty
		if [[ "$ans" = "YES" ]]; then
			if [[ -d "$old_dir" ]]; then
				ln "$trash_path" "$old_path"
			else
				echo "old dir was removed, file will save in home dir"
				ln "$trash_path" "$HOME/$file_name"
			fi
			rm "$trash_path"
			exit
		fi
	fi
done