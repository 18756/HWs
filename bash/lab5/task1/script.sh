#! /bin/bash
cur_dir=$(pwd)
path="$cur_dir/$1"
if [ ! -f "$path" ]; then
    echo "File don't exist: $path"
    exit
fi

if [ ! -d "$HOME/.trash" ]; then
    mkdir "$HOME/.trash"
fi
file_name=$(echo $1 | awk -F "/" '{print $NF}')
cur_time=$(date +%s%N)
ln "$path" "$HOME/.trash/$file_name($cur_time)"
rm "$path"
if [ ! -f "$HOME/.trash.log" ]; then
    touch "$HOME/.trash.log"
fi
echo "$path -> $file_name($cur_time)" >> "$HOME/.trash.log"