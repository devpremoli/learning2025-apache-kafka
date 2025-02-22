const { kafka } = require("./client");
const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

async function init() {
  const producer = kafka.producer();

  console.log("Connecting Producer...");
  await producer.connect();
  console.log("Producer Connected Successfully");

  rl.setPrompt("> ");
  rl.prompt();

  rl.on("line", async function (line) {
    const [riderName, location] = line.split(" ");
    if (!riderName || !location) {
      console.log("Invalid input. Use: <riderName> <location>");
      return;
    }

    try {
      await producer.send({
        topic: "rider-updates",
        messages: [
          {
            partition: location.toLowerCase() === "north" ? 0 : 1,
            key: "location-update",
            value: JSON.stringify({ name: riderName, location }),
          },
        ],
      });
      console.log(`Sent update: ${riderName} -> ${location}`);
    } catch (error) {
      console.error("Error sending message:", error);
    }
  }).on("close", async () => {
    console.log("\nGracefully shutting down producer...");
    await producer.disconnect();
    process.exit(0);
  });

  process.on("SIGINT", async () => {
    console.log("\nCaught interrupt signal, closing producer...");
    await producer.disconnect();
    process.exit(0);
  });
}

init();
