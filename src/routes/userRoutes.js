// src/routes/userRoutes.js
const express = require('express');
const User = require('../models/User');
const router = express.Router();


// Get All Users
router.get('/', async (req, res) => {
    try {
        const users = await User.find(); // Fetch all users
        res.json(users);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Get a User by ID
router.get('/:id', async (req, res) => {
    try {
      const user = await User.findById(req.params.id);
      if (!user) return res.status(404).json({ message: 'User not found' });
      res.json(user);
    } catch (error) {
      res.status(500).json({ message: error.message });
    }
  });

  // Update User by ID
router.put('/update/:id', async (req, res) => {
    try {
      const { username, email, role } = req.body;
      const user = await User.findById(req.params.id);
  
      if (!user) return res.status(404).json({ message: 'User not found' });
  
      // Update fields
      user.username = username || user.username;
      user.email = email || user.email;
      user.role = role || user.role;
  
      await user.save();
      res.json(user);
    } catch (error) {
      res.status(500).json({ message: error.message });
    }
  });

  // Delete User by ID
router.delete('/delete/:id', async (req, res) => {
    try {
      const user = await User.findByIdAndDelete(req.params.id);
      if (!user) return res.status(404).json({ message: 'User not found' });
      res.json({ message: 'User deleted successfully' });
    } catch (error) {
      res.status(500).json({ message: error.message });
    }
  });

  module.exports = router;