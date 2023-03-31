#!/bin/bash

echo "********************************"
echo "Checking code formatting"
echo "********************************"

github_actions_path=$1

jar_file_path=$?

if [ $# -eq 0 ]; then
    jar_file_path="../atcompose-tools/ktlint"
else
    jar_file_path="$github_actions_path/atcompose-tools/ktlint"
fi

java -jar $jar_file_path --android app/src/**/*.kt data/src/**/*.kt

status=$?

if [ "$status" = 0 ] ; then
  echo "Lint completed successfully."
  exit 0
else
  echo "********************************"
  echo "Ktlint issue found."
  echo "Please fix the above issues."
  echo "********************************"
  exit 1
fi