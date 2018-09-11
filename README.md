# uploadservice
This is backend service for uploading files and getting links for them.

There are 2 endpoints.
  - "/upload" 
    Post request with key "file" and file itself.
    You'll get a link to that file as Body answer.
  
  - "/files/identifier?size='" Get request for the file. 
    If you add after identifier optional parameter 'size' with values 's' for small and 'm' for medium, it'll be resized. 
    You'll able to either download or attach in your html as a picture for example.
  
