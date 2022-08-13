require('dotenv').config()

const fs = require('fs')
const https = require('https')
const express = require('express')
const mongoose = require('mongoose')

const app = express()
app.use(express.json())


mongoose.connect(process.env.DB_URL,{ useNewUrlParser: true , useUnifiedTopology: true })
const db = mongoose.connection
db.on('error', (error) => console.error(error))
db.once('open', () => console.log('Connected to Database'))

const utilisateurRouter = require('./Routes/utilisateurs')
app.use('/utilisateurs',utilisateurRouter)

const loginRouter = require('./routes/login')
app.use('/login',loginRouter)


app.listen(3000,() => console.log('server started on port 3000') )