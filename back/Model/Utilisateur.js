const mongoose = require('mongoose')
const utilisateurSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true
  },
  email:{
    type: String,
    required: true
  },
  tel:{
    type: String,
    required: true
  },
  address:{
    type: String,
    required: true
  },
  region:{
    type: String,
    required: true
  },
  profession:{
    type: String,
    required: true
  },
  subscriber:{
    type: Boolean,
    required: true
  },
  password:{
    type: String,
    required: true,
    default:'12345678'
  }

})  

module.exports = mongoose.model('Utilisateur', utilisateurSchema)