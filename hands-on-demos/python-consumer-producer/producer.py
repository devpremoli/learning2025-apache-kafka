from confluent_kafka import Producer
import json

# Kafka configuration
KAFKA_BROKER = "localhost:9092,localhost:9093,localhost:9094"
TOPIC_NAME = "my-topic"
NUM_PARTITIONS = 6  # Number of partitions in the topic

# Kafka producer configuration
producer_config = {
    "bootstrap.servers": KAFKA_BROKER,
    "acks": "all"  # Ensure message durability
}

# Create Kafka producer
producer = Producer(producer_config)

def delivery_report(err, msg):
    """ Callback function to get delivery status """
    if err is not None:
        print(f"❌ Message delivery failed: {err}")
    else:
        print(f"✅ Message delivered to {msg.topic()} [Partition {msg.partition()}]")

def send_message_to_partition():
    """ Send a message to each partition explicitly """
    for i in range(NUM_PARTITIONS):
        value = {"message": f"Hello Kafka from Partition {i}", "id": i}
        producer.produce(
            TOPIC_NAME,
            value=json.dumps(value).encode("utf-8"),  # Convert value to JSON string
            partition=i,  # Assign message to a specific partition
            callback=delivery_report
        )
    producer.flush()  # Ensure all messages are sent

if __name__ == "__main__":
    send_message_to_partition()
