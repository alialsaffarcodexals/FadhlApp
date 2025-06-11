# FadhlApp (Web Version)

This project now uses Go to serve a small web application that replaces the old Java Swing GUI. The app presents a login screen and, when authenticated, a dashboard with links to the Media, Websites and Accounts pages. Each page is rendered using HTML templates and styled with simple CSS.

Run the server on port **1111**:

```bash
go run server.go
```

Then open `http://localhost:1111` in your browser.
