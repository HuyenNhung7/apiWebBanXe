## **Xe API**

Endpoint: GET /api/v1/xe 

Description: Retrieves a list of Xe objects based on optional query parameters. Query parameters:

Example: 

http://localhost:8080/api/v1/xe : Get all cars

http://localhost:8080/api/v1/xe?ten=B&thuongHieu=A : Get cars by ten and thuongHieu

http://localhost:8080/api/v1/xe?page=0&size=2 : 2 items per page, current page is 0

-   ten (optional): Filter by Xe name.
-   thuongHieu (optional): Filter by Xe brand.
-   page (optional, default=0): The page number to retrieve.
-   size (optional, default=100): The number of items to retrieve per page. Returns: A ResponseEntity with a XeDto object containing a list of Xe objects, total count of all Xe objects, page number, and page size.

Endpoint: POST /api/v1/xe 

Description: Adds a new Xe object. Request body: A JSON object representing the Xe to be added. Returns: The added Xe object.

Endpoint: PUT /api/v1/xe/{id} 

Description: Updates an existing Xe object by ID. Path parameter:

-   id (required): The ID of the Xe to be updated. Request body: A JSON object representing the updated Xe. Returns: The updated Xe object.

Endpoint: GET /api/v1/xe/{id} 

Description: Retrieves a single Xe object by ID. Path parameter:

-   id (required): The ID of the Xe to be retrieved. Returns: The retrieved Xe object.

Endpoint: DELETE /api/v1/xe/{id} 

Description: Deletes a single Xe object by ID. Path parameter:

-   id (required): The ID of the Xe to be deleted. Returns: A boolean indicating whether the deletion was successful.

## News API
```
- `GET /api/v1/news`: Get all news articles.
- `GET /api/v1/news/{id}`: Get a news article by its ID.
- `POST /api/v1/news`: Create a new news article.
- `PUT /api/v1/news/{id}`: Update an existing news article by its ID.
- `DELETE /api/v1/news/{id}`: Delete a news article by its ID.
```
