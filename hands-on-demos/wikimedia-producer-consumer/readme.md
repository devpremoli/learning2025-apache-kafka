- create a topic

```bash
docker exec -it kafka1 kafka-topics.sh --create --topic wikimedia-stream --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
```

- create a consumer

```bash
docker exec -it kafka1 kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic wikimedia-stream --from-beginning
```

- create a producer

```bash
curl -s "https://stream.wikimedia.org/v2/stream/recentchange" | docker exec -i kafka1 kafka-console-producer.sh --broker-list localhost:9092 --topic wikimedia-stream
```
