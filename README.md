# uploadservice
This is backend service for uploading files and getting links for them.

There are 2 endpoints.
  - "/upload" 
    Post request with key "file" and file itself.
    You'll get a link to that file as Body answer.
  
  - "/files/identifier"
    Get request for the file.
    You'll able to either download or attach in your html as a picture for example.
    
  
