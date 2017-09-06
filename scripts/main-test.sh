#!/usr/bin/env bash

app_name=koma


function show_app_info(){
    echo "$app_name"
}

function help() {
  echo "#################################################"
  echo "# Serverless KOMA - The Bash Helper #"
  echo "#################################################"
  echo
  echo "Usage: main-test <action> [arguments...]"
  echo
  echo "Where <action> is one of:"
  echo
  echo "examples: "
  echo
  echo "jar tf some.jar  -> shows the classes in a jar file"
  echo "s3 sync ./sparql-lambda/build/libs/ 's3://code.dado.org'"
  echo
}

action=${1:-"help"}

case "$action" in
    app)
        show_app_info
        ;;
    *)
        help
        ;;
esac