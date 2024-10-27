// src/app.js
const express = require('express');
const connectDB = require('./config/db'); // Import DB connection
const authRoutes = require('./routes/authRoutes'); // Import auth routes
const userRoutes = require('./routes/userRoutes'); // Import user routes (verify this path)
// Initialize app
const app = express();

// Connect to Database
connectDB();

// Middleware
app.use(express.json()); // Parse JSON requests

// Routes
app.use('/api/auth', authRoutes); // Use auth routes
app.use('/api/users', userRoutes); // Use user CRUD routes

module.exports = app; // Export the app
