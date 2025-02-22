# Running the Project

- build and start all services in the background:

```bash
docker-compose up -d --build
```

- open a terminal inside the Node.js container:

```bash
docker exec -it node-app bash
```

- create topics inside the node-app container:

```bash
node admin.js
```

- create producer inside the node-app container:

```bash
node producer.js
```

- create consumers inside the node-app container:

```bash
node consumer.js <group-name>
```

- Type messages in this format in the producer:

```bash
Alice North
Bob South
```

# Resources

Video Link: [Apache Kafka Crash Course | What is Kafka?](https://youtu.be/ZJJHm_bd9Zo)
