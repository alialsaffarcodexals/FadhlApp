function logout(){
  fetch('/logout').then(()=>location='/');
}
