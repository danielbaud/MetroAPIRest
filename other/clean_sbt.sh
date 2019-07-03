#!/bin/sh

echo "Cleaning ports 9000 ..."
lsof -t -i :9000
echo "Cleaning ports 9042 ..."
lsof -t -i :9042
echo "Reloading ..."
sbt reload
echo "Cleaning ..."
sbt clean
echo "Compiling ..."
sbt compile
echo "Done !"
