#!/bin/bash

# This script will run the pre-push checks in the given order
# - ktlint
# - (others in the future like checkstyle)

if bash tools/ktlint_lint_check.sh ; then
  echo "All checks passed successfully"
  exit 0
else
  exit 1
fi
