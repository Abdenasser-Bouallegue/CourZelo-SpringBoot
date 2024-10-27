const dotenv = require('dotenv'); // Import dotenv at the top
dotenv.config(); // Then configure it immediately after


const app = require('./src/app');
const PORT = process.env.PORT || 5000; // Default to 5000 if PORT is not defined

// Start the server
console.log('MONGO_URI:', process.env.MONGO_URI); // Add this line to verify
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
