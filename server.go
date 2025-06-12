package main

import (
	"html/template"
	"net/http"
)

type Website struct {
	Name string
	URL  string
}

type Media struct {
	Name string
	Path string
}

type Account struct {
	Name     string
	Email    string
	Password string
}

var (
	websites = []Website{
		{"OpenAI", "https://openai.com"},
	}
	media = []Media{
		{"Sample", "sample.mp4"},
	}
	accounts = []Account{
		{"Fadhl", "fadhl@example.com", "1234"},
	}
	tpl = template.Must(template.ParseGlob("web/templates/*.html"))
)

func main() {
	http.Handle("/static/", http.StripPrefix("/static/", http.FileServer(http.Dir("web/static"))))
	http.HandleFunc("/", loginPage)
	http.HandleFunc("/login", login)
	http.HandleFunc("/logout", logout)
	http.HandleFunc("/dashboard", dashboard)
	http.HandleFunc("/websites", websitesPage)
	http.HandleFunc("/media", mediaPage)
	http.HandleFunc("/accounts", accountsPage)
	http.ListenAndServe(":1111", nil)
}

func loginPage(w http.ResponseWriter, r *http.Request) {
	tpl.ExecuteTemplate(w, "login.html", nil)
}

func login(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	if r.Form.Get("username") == "Fadhl" && r.Form.Get("password") == "1234" {
               http.SetCookie(w, &http.Cookie{Name: "auth", Value: "1", Path: "/"})
		http.Redirect(w, r, "/dashboard", http.StatusSeeOther)
		return
	}
	http.Redirect(w, r, "/", http.StatusSeeOther)
}

func logout(w http.ResponseWriter, r *http.Request) {
	http.SetCookie(w, &http.Cookie{Name: "auth", Value: "", MaxAge: -1})
	w.WriteHeader(http.StatusOK)
}

func authenticated(r *http.Request) bool {
	c, err := r.Cookie("auth")
	if err != nil || c.Value != "1" {
		return false
	}
	return true
}

func dashboard(w http.ResponseWriter, r *http.Request) {
	if !authenticated(r) {
		http.Redirect(w, r, "/", http.StatusSeeOther)
		return
	}
	tpl.ExecuteTemplate(w, "dashboard.html", nil)
}

func websitesPage(w http.ResponseWriter, r *http.Request) {
	if !authenticated(r) {
		http.Redirect(w, r, "/", http.StatusSeeOther)
		return
	}
	tpl.ExecuteTemplate(w, "websites.html", websites)
}

func mediaPage(w http.ResponseWriter, r *http.Request) {
	if !authenticated(r) {
		http.Redirect(w, r, "/", http.StatusSeeOther)
		return
	}
	tpl.ExecuteTemplate(w, "media.html", media)
}

func accountsPage(w http.ResponseWriter, r *http.Request) {
	if !authenticated(r) {
		http.Redirect(w, r, "/", http.StatusSeeOther)
		return
	}
	tpl.ExecuteTemplate(w, "accounts.html", accounts)
}
