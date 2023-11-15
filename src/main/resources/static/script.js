document.getElementById("form").addEventListener("submit", function (e) {
    
    e.preventDefault();
    
    const formData = new FormData();
    formData.append("details", document.getElementById("details").files[0]);
    
    fetch('/read',{
    method : 'POST',
    body : formData
})
  .then(response => response.json())
  .then(data => console.log('Data received from the server:' , data))
  .catch(error => console.error(error));

});


