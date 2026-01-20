# Simple HTTP Android Client

This project shows how an Android app communicates with a custom HTTP server written in C.

## What it does
- A C server runs on `localhost:8080`
- An Android app sends an HTTP request to the server
- The server responds with JSON
- The app displays the response on screen

## Backend
- Written in C using sockets
- Endpoint:
    - `GET /health` → returns `{"status":"ok"}`

## Android App
- Built using Jetpack Compose
- Uses `HttpURLConnection` for networking
- Runs on Android Emulator
- Connects to host machine using `10.0.2.2`

## How to run
1. Start the C server:
   ```bash
   ./src/server
