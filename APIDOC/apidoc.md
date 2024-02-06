# Nitconf Application

This is the documentation for the Nitconf application API.

## Overview

The Nitconf application provides a set of APIs for managing PC members and papers. This documentation outlines the available endpoints, their functionalities, and usage.

## API Documentation

### Swagger UI

For a visual representation of the API documentation, you can use Swagger UI. Follow these steps:

1. Download Swagger UI from [here](https://github.com/swagger-api/swagger-ui).
2. Run Swagger UI locally.
3. Open Swagger UI and provide the path to your OpenAPI Specification file (your-api-spec.yaml).

### OpenAPI Specification

The detailed OpenAPI Specification for this API is provided in the file [your-api-spec.yaml](path/to/your-api-spec.yaml).

## Endpoints

### 1. Landing Page

- Endpoint: /api/
- Method: GET
- Summary: Get landing page
- Description: Fetches the landing page of the application.
- Response:
  - Status 200: Successful response
    - Content: HTML content for landing page
## PC Member Controller

This section outlines the endpoints provided by the PCMemberController in the Nitconf application.

### 1. PC Member Login

- Endpoint: /api/pcmember/Login?
- Method: GET
- Summary: Get PC member login page
- Description: Fetches the login page for PC members.

### 2. Dashboard

- Endpoint: /api/pcmember/Dashboard
- Method: POST
- Summary: Login to PC member dashboard
- Parameters:
  - email (required): User email for login
  - password (required): User password for login
- Responses:
  - Status 200: Successful response
    - Content: HTML content after successful login
  - Status 401: Unauthorized response
    - Content: HTML content for unauthorized access

### 3. PC Member Profile

- Endpoint: /api/pcmember/Profile
- Method: GET
- Summary: Get PC member profile page
- Description: Fetches the profile page for the currently logged-in PC member.

### 4. Edit PC Member Profile

- Endpoint: /api/pcmember/EditProfile
- Method: GET
- Summary: Get edit PC member profile page
- Description: Fetches the page for editing the profile of the currently logged-in PC member.

### 5. Update PC Member Profile

- Endpoint: /api/pcmember/UpdateProfile
- Method: POST
- Summary: Update PC member profile
- Parameters:
  - name (required): Updated PC member name
  - email (required): Updated PC member email
  - confirmpassword (required): Confirm the updated password
  - password (required): Updated PC member password
- Responses:
  - Status 200: Successful response
    - Content: HTML content after successful profile update
  - Status 401: Unauthorized response
    - Content: HTML content for unauthorized access
  - Status 404: User not found response
    - Content: HTML content for user not found
## Paper Controller

This section outlines the endpoints provided by the PaperController in the Nitconf application.

### FOR REFERENCE (UPLOAD PAPERS)

### 1. Upload Papers (For Reference)

- Endpoint: /Paper/upload
- Method: GET
- Summary: Navigate to the paper upload page
- Description: Displays the paper upload page (upload.jsp), allowing users to input paper details such as title, tags, and link for uploading.

### 2. Store Paper

- Endpoint: /Paper/StorePaper
- Method: POST
- Summary: Store paper details
- Description: Stores paper details, including title, tags, link, status, and uploadeddate. It uses the PaperStorerepo repository to save the paper details. Upon successful storage, it redirects to the success.jsp page.

The PaperController interacts with the PaperStorerepo repository and utilizes the LocalDate class to capture the current date for the uploadeddate field.
## Papers Controller

This section outlines the endpoints provided by the PapersController in the Nitconf application.

### 1. Assigned Papers

- Endpoint: /api/papers/assignedpapers
- Method: GET
- Summary: Get assigned papers page
- Description: Fetches the page displaying assigned papers.

### 2. Reviewed Papers

- Endpoint: /api/papers/reviewedpapers
- Method: GET
- Summary: Get reviewed papers page
- Description: Fetches the page displaying reviewed papers.

### 3. Unreviewed Papers

- Endpoint: /api/papers/unreviewedpapers
- Method: GET
- Summary: Get unreviewed papers page
- Description: Fetches the page displaying unreviewed papers.

### 4. Unassigned Papers

- Endpoint: /api/papers/unassignedpapers
- Method: GET
- Summary: Get unassigned papers page
- Description: Fetches the page displaying unassigned papers.

Each endpoint corresponds to a specific page related to papers, such as assigned, reviewed, unreviewed, or unassigned papers. The response for each endpoint is a ModelAndView that maps to the respective JSP page.
## Filter Controller

This section outlines the endpoints provided by the FilterController in the Nitconf application.

### 1. Reviewer Tags

- Endpoint: /api/filter/reviewertags
- Method: GET
- Summary: Process request for reviewer tags
- Description: Processes the request for reviewer tags based on a paper ID. It forwards the request to the reviewertags.jsp page, providing attributes such as paper_tag, paper_title, and paper_id.

### 2. Filter Papers by Tags

- Endpoint: /api/filter/bytags
- Method: POST
- Summary: Filter papers by tags
- Description: Filters papers based on the selected tag. It forwards the request to the bytags.jsp page, providing the selectedTag attribute.

Each endpoint corresponds to a specific functionality related to filtering papers. The FilterController interacts with the PaperStorerepo repository to fetch paper details. The response for each endpoint is a ModelAndView that maps to the respective JSP page.
## Reviewer Controller

This section outlines the endpoints provided by the ReviewerController in the Nitconf application.

### 1. Assign Reviewers to a Paper

- Endpoint: /api/reviewers/assign
- Method: POST
- Summary: Assign reviewers to a paper
- Description: Accepts a POST request to assign selected reviewers to a paper. The request should include the parameters:
  - selectedReviewers (String): Comma-separated list of reviewer IDs.
  - paper_id (String): ID of the paper to be assigned.
  
  Upon successful assignment, it redirects to the unassigned papers page.

### 2. Show Review for a Paper

- Endpoint: /api/reviewers/showreview
- Method: GET
- Summary: Show review details for a paper
- Parameters:
  - paper_id (Long): ID of the paper for which the review is to be displayed.
  
  Displays the review details for the specified paper.

  ## 3. ActionsController

### 3.1 Accept Paper

- *Endpoint:* /api/actions/accept
- *Method:* GET
- *Summary:* Accept a paper.
- *Parameters:*
  - paper_id (Long): ID of the paper to be accepted.
- *Description:* Accepts a paper and sends an email notification to the author. Redirects to the assigned papers page.
- *Response:*
  - Status 302: Redirect to /api/papers/assignedpapers.
  - Status 404: Paper not found response with a "Paper is not Found" view.
  - Status 404: Author not found response with an "Author is not Found" view.

### 3.2 Reject Paper

- *Endpoint:* /api/actions/reject
- *Method:* GET
- *Summary:* Reject a paper.
- *Parameters:*
  - paper_id (Long): ID of the paper to be rejected.
- *Description:* Rejects a paper and sends an email notification to the author. Redirects to the assigned papers page.
- *Response:*
  - Status 302: Redirect to /api/papers/assignedpapers.
  - Status 404: Paper not found response with a "Paper is not Found" view.
  - Status 404: Author not found response with an "Author is not Found" view