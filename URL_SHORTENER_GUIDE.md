Overview
Build a service that creates short versions of long URLs and can resolve them back to the original.
The application runs at http://localhost:8080.
REST Contract


Endpoint 1: Create a short link
Method: POST
URL: <http://localhost:8080/persist>
Body: Full URL (e.g. <https://example.com/very/long/path)>
Response: The short id or short URL (e.g. 123 or <http://localhost:8080/resolve/123)>


Endpoint 2: Resolve a short link
Method: GET
URL: <http://localhost:8080/resolve/{shortUrl}>
Path variable: shortUrl — the short id (e.g. 123)
Response: The original full URL


Endpoint 3 (Bonus): Redirect
Method: GET
URL: <http://localhost:8080/resolve/{shortUrl}> (or a separate path, e.g. /r/{shortUrl})
Behaviour: HTTP redirect to the original full URL (browser navigates there)


Additional Notes
Only the short id (e.g. 123) is stored in the database. The <http://localhost:8080/resolve> part is your REST path and is not persisted.

If you skip databases or REST, a working in-memory solution with console output is acceptable.
Technology: Java preferred (we can only help with Java), IntelliJ preferred (we can only help with IntelliJ), PostgreSQL + Spring Data JPA preferred, or in-memory (e.g. HashMap).
Evaluation

Working solution: Core features work (create short link, resolve to full URL)
Clarity: Code and logic are understandable
Reasoning: You can explain your choices
Adaptability: You simplify when needed and still deliver
