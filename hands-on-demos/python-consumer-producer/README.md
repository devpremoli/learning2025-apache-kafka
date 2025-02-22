# Running the Project

- build and start all services in the background:

```bash
docker-compose up -d --build
```

- virtual environment setup

```bash
python3 venv -m .venv
source .ven/bin/acitvate
pip3 install confluent-kafka
```

- create a topic

```bash
docker exec -it broker-1 /bin/kafka-topics \
  --create \
  --topic my-topic \
  --bootstrap-server broker-1:9092 \
  --partitions 6 \
  --replication-factor 2
```

- python3 consumer.py
- python3 producer.py

## Note

- Both of these command works

  ```bash
  docker exec -it broker-1 /bin/kafka-topics \
    --create \
    --topic my-topic \
    --bootstrap-server broker-1:9092 \
    --partitions 6 \
    --replication-factor 2
  ```

  ```bash
  docker exec -it broker-1 /bin/kafka-topics \
    --create \
    --topic my-topic \
    --bootstrap-server broker-1:9092 \
    --partitions 6 \
    --replication-factor 2
  ```
