for i in $(seq 0 100)
do
	curl -X POST localhost:9000/status --header "Content-Type: application/json" -d "{\"position\": 42, \"direction\": true, \"passengers\": 222, \"battery\": 22, \"speed\": 180, \"temperature\": 24, \"id\": $i}"
done
