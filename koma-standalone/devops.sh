#!/usr/bin/env bash

echo "ho ho!"

echo "docker run -it -p 8080:8080 koma-standalone"

echo "docker run --name koma-mysql -e MYSQL_ROOT_PASSWORD=koma-mysql -d mysql:latest"

echo "docker run --name some-app --link some-mysql:mysql -d application-that-uses-mysql"