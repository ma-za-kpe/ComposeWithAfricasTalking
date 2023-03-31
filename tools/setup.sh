#!/bin/bash

# INSTRUCTIONS:
# This script will move the pre-push hook from script folder to
# the .git/hooks folder
#
# Run the script from the atcompose root folder:
#
#   bash tools/setup.sh
#
# NOTE: this script should be run once after the initial codebase setup

# Move file from script folder to .git/hooks folder
cp tools/pre-push.sh .git/hooks/pre-push

# Create a folder where all the set up files will be downloaded
mkdir -p ../atcompose-tools
cd ../atcompose-tools

# Download ktlint
bash ../ComposeWithAfricasTalking/tools/ktlint_download.sh

cat <<-EOF
Checking the following settings helps avoid miscellaneous issues:
  * Settings -> Editor -> General -> Remove trailing spaces on: Modified lines
  * Settings -> Editor -> General -> Ensure every file ends with a line break
  * Settings -> Editor -> General -> Auto Import -> Optimize imports on the fly (for both Kotlin\
 and Java)
EOF
