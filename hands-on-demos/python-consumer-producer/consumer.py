from confluent_kafka import Consumer, KafkaException
import sys
import multiprocessing

# Kafka configuration
KAFKA_BROKER = "localhost:9092,localhost:9093,localhost:9094"
TOPIC_NAME = "my-topic"
CONSUMER_GROUP = "my-consumer-group"
NUM_CONSUMERS = 7

# Consumer configuration
consumer_config = {
    "bootstrap.servers": KAFKA_BROKER,
    "group.id": CONSUMER_GROUP,
    "auto.offset.reset": "earliest",  # Read messages from the beginning
}

def consume_messages(consumer_id):
    """ Function to consume messages """
    consumer = Consumer(consumer_config)
    consumer.subscribe([TOPIC_NAME])

    try:
        while True:
            msg = consumer.poll(1.0)  # Poll for new messages
            if msg is None:
                continue
            if msg.error():
                raise KafkaException(msg.error())
            
            print(f"Consumer-{consumer_id} | Partition: {msg.partition()} | Key: {msg.key()} | Value: {msg.value().decode('utf-8')}")
    except KeyboardInterrupt:
        print(f"Consumer-{consumer_id} shutting down...")
    finally:
        consumer.close()

if __name__ == "__main__":
    processes = []
    
    for i in range(NUM_CONSUMERS):
        p = multiprocessing.Process(target=consume_messages, args=(i,))
        p.start()
        processes.append(p)
    
    for p in processes:
        p.join()
