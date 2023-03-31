#!/bin/bash

# This script will run the pre-push checks in the given order
# - ktlint
# - checkstyle
# - (others in the future)

if bash tools/ktlint_lint_check.sh && bash tools/checkstyle_lint_check.sh ; then
  echo "All checks passed successfully"
  exit 0
else
  exit 1
fi
