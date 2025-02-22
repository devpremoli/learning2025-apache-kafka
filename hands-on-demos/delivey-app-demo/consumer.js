const { kafka } = require("./client");
const group = process.argv[2] || "default-group"; // Default group if not provided

async function init() {
  const consumer = kafka.consumer({ groupId: group });
  await consumer.connect();
  console.log(`${group}: Consumer connected.`);

  await consumer.subscribe({ topics: ["rider-updates"], fromBeginning: true });

  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      console.log(
        `${group}: [${topic}]: PART:${partition}:`,
        message.value.toString()
      );
    },
  });

  process.on("SIGINT", async () => {
    console.log("\nGracefully shutting down consumer...");
    await consumer.disconnect();
    process.exit(0);
  });
}

init();
