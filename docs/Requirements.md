# Software Requirements Specification

For NITCONF

Version 1.0 approved 

Prepared by group 2,4 and 9 of evening batch.

# Table of Contents


##### Revision History


* [Introduction](#1-introduction)
  * 1.1 [Purpoose](#11-purpose)
  * 1.2 [Intended Audience](#12-intended-audience)
  * 1.3 [Product Scope](#13-product-scope)
  * 1.4 [Document Conventions](#14-document-conventions)
  * 1.5 [References](#15-references)
* [Overall Description](#2-overall-description)
  * 2.1 [Product Overview](#21-product-overview)
  * 2.2 [Product Functions](#22-product-functions)
  * 2.3 [User Classes and Characteristics](#23-user-classes-and-characteristics)
  * 2.4 [Operating Environment](#24-operating-environment)
  * 2.5 [Design and Implementation Constraints](#25-design-and-implementation-constraints)
  * 2.6 [User Documentation](#26-user-documentation)
  * 2.7 [Assumptions and Dependencies](#27-assumptions-and-dependencies)
* [System Features](#3-system-features)
  * 3.1 [Login and Validation](#31-login-and-validation)
  * 3.2 [User Registration](#32-user-registration)
  * 3.3 [Profile](#33-profile)
  * 3.4 [View Reviewed Papers](#34-view-reviewed-papers)
  * 3.5 [View Unrviewed Papers](#35-view-unreviewed-papers)
  * 3.6 [Filter](#36-filter)
  * 3.7 [Assign Reviewer](#37-assign-reviewer)
* [Other Nonfunctional Requirements](#4-other-nonfunctional-requirements)
  * 4.1 [Performance Requirements](#41-performance-requirements)
  * 4.2 [Safety Requirements](#42-safety-requirements)
  * 4.3 [Security Requirements](#43-security-requirements)
  * 4.4 [Software Quality Attributes](#44-software-quality-attributes)
  * 4.5 [Business Rules](#45-business-rules)
* [Appendix](#appendix)


# Revision History

## Revision History

| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|      |         |                     |           |
|      |         |                     |           |
|      |         |                     |           |


# 1. Introduction
This project is a prototype for NITCONF, a conference website designed to facilitate the submission, review and evaluation of academic papers. The section for the Program Committee includes functionality that enables members to view the submitted papers, assign reviewers to them and decide whether to accept the papers or not . The repository encompasses the entire Program Committee section for this project. This document provides a fundamental overview of the project’s goals, highlighting the significance and utility of the software product, while also focusing on the target user base.

## 1.1 Purpose

   The purpose of this document is to specify the requirements for the development of the Program Member web portal, which facilitates the assigning of reviewers to the papers uploaded by authors.

## 1.2 Intended Audience
This document is intended to several groups of audience members

1. System Designers :
   They are the primary audience. It provides crucial information guiding the design phase.
2. Tester :
   They will refer to the SRS to ensure that the actual implementation aligns seamlessly with the specified requirements.


## 1.3 Product Scope

1. User Authentication :
   A secure login page for the members of the Program Committee to access the system.

2. Dashboard :
   After successful login, users are directed to a personalised homepage, having profile section, papers which are already reviewed (Reviewed Papers) and papers which are unreviewed (Unreviewed Papers).

4. Paper Presentation :
The system organises the uploaded papers in a clear and systematic row-wise fashion for easy navigation in reviewed and unreviewed section.

5. Program Committee Functionality :
Program Committee members can seamlessly assign the reviewers to review the papers uploaded by the authors. Based on the reviews given by the reviewers, the Program Committee member decides whether to accept or reject the paper.

## 1.4 Document Conventions
This document follows IEEE formatting requirements

## 1.5 References
 Refer to the following links for additional information :

<https://www.springer.com/gp/computer-science/lncs/online-conference-service>

[https://support.springer.com/en/support/solutions/articles/6000245514-description-of-          the-end-to-end-process-in-equinocs](https://support.springer.com/en/support/solutions/articles/6000245514-description-of-the-end-to-end-process-in-equinocs) 


# 2. Overall description

## 2.1 Product Overview
   The program committee page on NITCONF will function as an interface for program committee members to manage reviewer profiles. They can view all papers submitted by authors, assign reviewing tasks to reviewers, send reminders if reviewers are behind schedule, contact authors, and ultimately make decisions to ACCEPT or REJECT papers based on the review given by reviewers.


![Alt text](./Product_Overview.jpg)

## 2.2 Product Functions

### 2.2.1 View Submissions by Authors:
Program committee can comprehensively view all papers submitted by authors, gaining a holistic understanding of the conference content. 
### 2.2.2 Assign Reviewing Tasks to Reviewers:
Program committee members have the authority to strategically allocate reviewing tasks to specific reviewers, ensuring a balanced and thorough evaluation process.
### 2.2.3 Send Reminders for Reviewer Schedule:
The platform enables committee members to issue timely reminders to reviewers, helping to keep the evaluation process on track and ensuring deadlines are met
### 2.2.4 Contact Authors for Clarifications:
Committee members can easily communicate with authors, seeking clarifications or additional information to enhance the evaluation process. 
### 2.2.5 Make Acceptance or Rejection Decisions:
Ultimately, the committee possesses the power to make crucial decisions regarding the acceptance or rejection of papers, shaping the final composition of the conference program.
### 2.2.6 Assign Additional Reviewer for Complex Cases:
 In situations where committee members find it challenging to make decisions based on past reviews and comments, they can assign an additional reviewer to thoroughly assess the paper and contribute to informed decision-making.
### 2.2.7 Filter by Tags:
 Committee members can utilise a sophisticated filtering system to categorise papers based on tags allowing for streamlined navigation.
 
## 2.3. User Classes and Characteristics

### **USER: PROGRAM COMMITTEE MEMBER**

**Frequency of Use:** Program committee members engage with the system periodically throughout the conference planning and reviewing process. Focusing on strategic decision-making and oversight.

**Functions Used:**

   - View Submissions by Authors: Program committee members can comprehensively view all papers submitted by authors, gaining a holistic understanding of the conference content.

   - Manage their Profile: They can update their profiles, ensuring accurate and up-to-date information, enhancing the overall effectiveness of the reviewing process.

   - Assign Reviewing Tasks to Reviewers: Program committee members strategically allocate reviewing tasks to specific reviewers, ensuring a balanced and thorough evaluation process.

   - Send Reminders for Reviewer Schedule: The platform enables committee members to issue timely reminders to reviewers, helping to keep the evaluation process on track and ensuring deadlines are met.

   - Contact Authors for Clarifications: Committee members can easily communicate with authors, seeking clarifications or additional information to enhance the evaluation process.

   - Make Acceptance or Rejection Decisions: The committee possesses the power to make crucial decisions regarding the acceptance or rejection of papers, shaping the final composition of the conference program.

   - Assign Additional Reviewer for Complex Cases: In situations where committee members find it challenging to make decisions based on past reviews and comments, they can assign an additional reviewer to thoroughly assess the paper and contribute to informed decision-making.


**Technical Expertise:** Program committee members should have a good understanding of the conference management system, as they are involved in various managerial and decision-making tasks. They should be able to understand how the system works and know how to navigate within the website.

**Security Levels:** Program committee members require secure login details to access the system, ensuring the confidentiality and integrity of the reviewing and decision-making process.

**Educational Level and Experience:** Program committee members possess significant expertise in their respective academic or industry fields. They have experience in conference organisation and management, enabling them to make informed decisions about paper acceptance or rejection. Their experience also allows them to effectively manage the reviewing process and assign tasks strategically.

## 2.4 Operating Environment

The Program Committee Page of NITCONF is designed to operate within a well-defined environment, incorporating key features that ensure optimal functionality, security, and performance. The operating environment encompasses hardware, software, network conditions, performance expectations, security measures, compatibility considerations, and external dependencies.

### 2.4.1 Hardware Requirements:

The system requires a dedicated server with modern processors, at least 8GB of RAM, and sufficient storage capacity to handle concurrent user sessions and data storage.

### 2.4.2 Software Requirements:
- The server must run a Java-compatible environment, with the choice of web servers like Apache Tomcat or Jetty, and Java JDK 8 or higher.
- Frontend technologies such as HTML5, CSS3, and JavaScript are utilised for the user interface.

### 2.4.3 Network requirements:
- Adequate bandwidth and network stability are crucial for seamless access and data exchange, especially for real-time features.
- The system uses HTTPS for secure communication over the internet.
  
### 2.4.4 Performance Requirements:
- The system aims for optimal performance, recommending modern processors, at least 8GB of RAM, and a stable internet connection for laptop and desktop users.
- Performance expectations include responsive web pages, low latency, and scalability to handle varying user loads

### 2.4.5 Security Requirements:
- The system implements HTTPS for secure communication.
- Security considerations include user authentication, authorization mechanisms, and data protection measures to ensure the confidentiality and integrity of user information.
  
### 2.4.6 Compatibility Requirements:
- The application is designed for cross-platform compatibility, ensuring it runs smoothly irrespective of the user's operating system.
- It is compatible with modern web browsers, including Google Chrome, Mozilla Firefox, Microsoft Edge, and Safari.


### 2.4.7 Dependencies:
- The system relies on the Spring Framework (specific version as per development), a compatible database (e.g., MySQL, PostgreSQL), and frontend technologies (HTML5, CSS3, JavaScript).
- Containerization is achieved using Docker and Kubernetes, and API testing tools such as Postman, swagger.io, and SoapUI are integrated into the development and testing process.

## 2.5 Design and implementation constraints
### 2.5.1 User Interface Consistency:
- Constraint: Ensure a consistent and intuitive user interface across all sections of the Program Committee Page.
- Implementation: Adhere to a unified design language, common navigation patterns, and consistent layout elements to provide a seamless user experience.
  
### 2.5.2 Responsive Design:
- Constraint: Design the user interface to be responsive and adaptable to various screen sizes and devices.
- Implementation: Utilise responsive design principles, CSS media queries, and flexible layouts to ensure optimal viewing on desktops, laptops, tablets, and smartphones.
  
### 2.5.3 Scalability:
- Constraint: Design the system to handle a potentially large number of papers, reviewers, and program committee members.
- Implementation: Utilise scalable database architecture, optimise queries, and implement caching mechanisms to maintain performance as the user base and data volume grow.
  
### 2.5.4 Notification Mechanism:
- Constraint: Implement a real-time notification system for Program Committee members.
- Implementation: Integrate a notification mechanism to alert committee members about new submissions, review comments, and decision outcomes.
  
### 2.5.5 Review Process Workflow:
- Constraint: Define and implement a structured workflow for the review process.
- Implementation: Develop clear steps for paper submission, reviewer assignment, review comments, and final decision-making. Ensure that the workflow is intuitive and follows the conference's guidelines.
  
### 2.5.6 Security Measures:
- Constraint: Implement robust security measures to safeguard user data and maintain the confidentiality of the review process.
- Implementation: Employ encryption for data transmission, secure user authentication, and authorization mechanisms. Regularly update security protocols to address potential vulnerabilities.

### 2.5.7 Integration with Reviewer Section:
- Constraint: Facilitate seamless communication and data exchange between the Program Committee and Reviewer sections.
- Implementation: Develop APIs or data-sharing mechanisms to enable smooth integration between the Program Committee and Reviewer sections, ensuring consistent data flow and accuracy.
  
### 2.5.8 Data Privacy Compliance:
- Constraint: Ensure compliance with data protection regulations.
- Implementation: Incorporate features for data anonymization, obtain consent where necessary, and ensure that the system adheres to relevant data protection laws such as GDPR.
  
### 2.5.9 Backup and Recovery:
- Constraint: Establish a robust backup and recovery system for data preservation.
- Implementation: Regularly backup the database, implement version control for the codebase, and devise a recovery plan to minimise data loss in case of system failures.
### 2.5.10 Documentation:
- Constraint: Provide comprehensive documentation for future maintenance and updates.
- Implementation: Document the codebase, APIs, database schema, and system architecture thoroughly. Create user manuals to guide Program Committee members through the functionalities of the system.
  
### 2.5.11 User Training:
- Constraint: Consider the varying technical proficiency of Program Committee members.
- Implementation: Develop training materials, tutorials, or conduct training sessions to ensure that Program Committee members can effectively use the system to perform their tasks.


## 2.6 User Documentation
### Login and Dashboard Navigation :

- **Login :** Program committee members log in with their details and are directed to the member's dashboard.

- **Top Navigation Bar :** Features  a collapsible navigation with the following options:
     - Profile
     - View Reviewed Papers
     - View UnReviewed Papers

### Pages and Functionalities

### - Profile Page

   **Layout** - Displays the details of the Program Committee member

   **Button :**

   - i. **Edit Profile** : includes a Edit Profile button to edit the details

### - View Reviewed Papers Page

**Layout** - Displays the reviewed papers in a row-wise manner

**Columns :** 
  - i. **Filter :** includes a filter button to display the required papers based on tags.
  - ii. **View a reviewed paper :** Here it shows paper title and some information regarding the paper and display paper upon clicking on it
  - iii. **Status :** indicates the status of the assigned reviewers for each paper (is reviewed or not ).
  - iv. **Add reviewer :** here program committee members can add reviewer if needed for further review.
   -v. **accept/reject :** program committee members can decide whether to accept the paper or to reject it.

### - View Unreviewed Papers Page

**Layout** -  Displays the unreviewed papers in a row-wise manner.

**Columns :**

   - i. **Filter** : includes a filter button to display the required papers based on tags.
   - ii. **View unreviewed papers** : Here it shows paper title and some information regarding the paper and display paper upon clicking on it
   - iii. **Assign reviewer** : here committee members can add reviewer to each paper to review and send mail to the reviewer (to notify reviewer).
   - iv. **Contact Author** : For any queries regarding the submitted papers committee members can contact the respective author.
   - v. **Send Reminder** : send reminder to the reviewer if the reviewer has not reviewed the assigned paper.

## 2.7 Assumptions and Dependencies
### 2.7.1 Assumptions	

- **Active Participation of Reviewers:**
  It is assumed that assigned reviewers will actively participate in the review process, providing timely and thorough assessments of the submitted papers.

- **Reviewer Availability:**
  The system assumes that reviewers are available to fulfill their reviewing tasks within the designated timelines.

- **Reviewer Proficiency:**
  It is assumed that Program Committee members and reviewers possess the necessary technical skills to navigate and interact with the web-based system effectively.

- **Access to Devices:**
  Users are assumed to have access to reliable internet connectivity and devices capable of running standard web browsers.

### 2.7.2 Dependencies

- **Database Management System:**
  The system is dependent on a compatible database management system, such as MySQL, PostgreSQL, or similar, for storing and retrieving data related to papers, reviewers, and decisions.

- **Web Server:**
  The web application relies on a compatible web server, such as Apache Tomcat or Jetty, for hosting and serving pages. Any changes to the web server may affect the system's performance.

- **Fronted Technologies:**
  The system utilizes frontend technologies like HTML5, CSS3, and JavaScript for creating an interactive user interface. Changes in these technologies or browser updates may impact the user experience.

- **Browser Compatibility:**
  The System is dependent on users accessing it through modern web browsers (Google Chrome , Mozilla FireFox, Safari, etc.). The documentation will provide information on recommended browsers for optimal performance.

- **User Account Management:**
  The Successful Functioning of the Program Committee module is dependent on the proper management of user accounts, including the creation , modification, and deletion of accounts as needed.


# 3. System Features

##  3.1 Login and Validation

### 3.1.1 Description and Priority
This feature enables users to log in using their email and password. System validates their details.It is of High priority.

### 3.1.2 Stimulus/Response Sequences
- Users provide their login details ( email and password ) and click the button “Login” to submit for verification .
- System validates details.
     - If validation fails, then it displays “Entered details are incorrect. Please try again “ and return to the Login page.
     - If validation succeeds, users will be redirected to the Dashboard containing Profile, Reviewed papers, and Unreviewed papers features.

### 3.1.3 Functional Requirements
1. **User Login:** Users must be able to input their email and password and able to submit the details for verification..
2. **Details verification:** The system must verify the provided details.
3. **Redirect to Dashboard:** If validation succeeds, redirect the user to the Dashboard page containing Profile, Reviewed papers, and Unreviewed papers features.
4. **Redirect to Login page:** If validation is unsuccessful, redirect the user to the Login page.

## 3.2 User Registration:

### 3.2.1 Description and Priority
   This feature enables the user to register in Program Committee Web Portal by providing basic details like name,email address, phone number and password to secure their account. This feature is available when user in login page.

### 3.2.2 Stimulus/Response Sequences
- User accesses the registration feature
- User after entering details, user submit the details by clicking register button
- Software will store the information in the database

### 3.2.3 Functional Requirements:
1. **User Registration:** Users must be able to fill the basic details (name,email,phone number and password) and able to submit the details 

## 3.3 Profile
### 3.3.1 Description and Priority
This feature allows users to manage their profile information.The basic details provided by the user during the registration process will be displayed here. Here user can edit the profile information. It is of Medium priority.

### 3.3.2 Stimulus/Response Sequences
- Users access the Profile feature ( clicks on Profile link ).
- Users update profile information. ( clicks on Edit button )
  
### 3.3.3 Functional Requirements
1. **View Profile:** Users can view their profile information.
2. **Edit Profile:** Users can edit and update their profile details.

## 3.4 View Reviewed Papers

### 3.4.1 Description and Priority
This feature allows users to view a list of papers which are reviewed and take actions on them ( Accept or Reject ). It is of High priority.

### 3.4.2 Stimulus/Response Sequences
- User accesses the View reviewed papers feature.
- User performs actions like Accept, Reject, or Assign Reviewer to a paper.
  
### 3.4.3 Functional Requirements
1. **List Reviewed Papers:** Display a list of reviewed papers when (clicks on  View reviewed papers feature).
2. **Accept Paper:** Allow the user to accept a reviewed paper (clicks on accept button).
3. **Reject Paper:** Allow the user to reject a reviewed paper (clicks on reject button).
4. **Assign Reviewer:** Allow the user to assign a reviewer to a paper(click on add reviewer button).
5. **Filter Papers:** Allow user to filter the reviewed papers based on tags and display the available papers. (click on dropdown menu button filter)

## 3.5 View Unreviewed Papers
### 3.5.1 Description and Priority
This feature enables users to view the list of available unreviewed papers and perform various actions like assign reviewer, filter, contact author. It is of Medium priority.

### 3.5.2 Stimulus/Response Sequences
- User accesses the View Unreviewed Papers feature.
- Users perform actions like filtering, adding reviewers, sending reminders, and contacting authors.
  
### 3.5.3 Functional Requirements
1. **List Unreviewed Papers:** Display a list of all papers which are unreviewed or which are newly uploaded (clicks on View unreviewed papers feature).
2. **Filter Papers:** Allow the user to filter papers based on tags (clicks on dropdown menu button filter).
3. **Assign Reviewer:** Enable the user to assign a reviewer from available reviewers to a paper.(clicks on assign button)
4. **Send Reminder:** Allow the user to send reminders to reviewers if the assigned deadline completes .
5. **Contact Author:** Provide a means for users to contact paper authors in case of any issue. User may contact the user via email or phone.

## 3.6 Filter:

### 3.6.1 Description and Priority:
   This feature enables the user to get the papers based on specific tags. User has option on which all papers to show based on tags. The software will display all the available papers which have the tag. This feature will be present in Reviewed Papers page and Unreviewed Papers page

### 3.6.2 Stimulus/Response Sequences
- User accesses the filter feature (clicks on filter button)
- User select tags
- System will display all the available papers which have the selected tags

### 3.6.3  Functional Requirements:
1. **Filter:** User upon selecting filter, system will display a dropdown menu containing a list of tags. User will select tags based on requirement. System will display all the available papers which have the selected tags 

## 3.7 Assign Reviewer:

### 3.7.1 Description and Priority:

  This feature enables the user to assign reviewers to the papers. This feature will be in Reviewed Papers page and Unreviewed Papers page.

### 3.7.2 Stimulus/Response Sequences
- User accesses the assign reviewer feature (clicks on assign reviewer button)
- System display the available reviewers
- User selects the reviewers to review the paper.
- Upon selecting the reviewer, the system will sent notification regarding the review

### 3.7.3 Functional Requirements:
1. **Assign Reviewer:** User upon selecting assign reviewer button, system will display a dropdown menu containing a list of all reviewers who are having the same tags as the paper tags. User will select reviewers. System will send notification to the assigned reviewer.

# 4. Other Nonfunctional Requirements
## 4.1 Performance Requirements
### 4.1.1 System Responsiveness
- The login process should have an average response time of less than 3 seconds.
- Paper assignment to reviewers should occur within 5 seconds of submission.
### 4.1.2 Concurrent User Handling
- The system should support up to 100 concurrent users during peak times without performance degradation.
### 4.1.3 Database Query Performance
- Database queries related to paper submissions and reviews should execute in less than 3 seconds.
  
Rationale:
- Timely responses are crucial for user satisfaction and efficient workflow.
- Efficient handling of concurrent users is essential for a smooth conference experience.
- Quick access to data is necessary for effective paper management and decision-making processes.


## 4.2 Safety Requirements
### 4.2.1 Data Integrity
- Safeguards must be in place to ensure the integrity of all data stored in the system.
- Regular backups of the system data should be performed to prevent data loss.
### 4.2.2 User Authentication
- All user authentication processes must comply with industry standards to prevent unauthorised access.
- Failed login attempts should be monitored and trigger account lockout after a specified number of unsuccessful tries.
### 4.2.3 Compliance with Privacy Regulations
- The system must adhere to data protection regulations and ensure the privacy of user information.
  
Rationale:
- Ensuring data integrity is crucial for maintaining accurate and reliable information.
- Secure user authentication is necessary to prevent unauthorized access and protect user accounts.
- Compliance with privacy regulations is essential for legal and ethical considerations.

## 4.3 Security Requirements
### 4.3.1 Encryption
- All data transmitted between the user's browser and the server must be encrypted using HTTPS.
- User passwords must be stored securely using strong, industry-standard hashing algorithms.
### 4.3.2 Access Control
- Different user roles (e.g., admin, reviewer, author) must have specific access permissions to ensure data confidentiality and integrity.
### 4.3.3 Security Auditing
- Regular security audits and vulnerability assessments should be conducted to identify and address potential security issues.
  
Rationale:
- Encryption safeguards sensitive data during transmission, protecting it from unauthorised access.
- Access control measures prevent unauthorised users from accessing or modifying data.
- Regular security audits help identify and address potential vulnerabilities, ensuring a secure system.

## 4.4 Software Quality Attributes
### 4.4.1 Usability
- The user interface should follow standard practices to ensure an intuitive and user-friendly experience.
### 4.4.2 Maintainability
- Code should be well-documented, and modular to facilitate ease of maintenance and future enhancements.
- Changes to the system should be deployable with minimal downtime.
### 4.4.3 Reliability
- The system should have an uptime of at least 99% during peak conference times.
- The system will not lag and will provide instant and accurate results to all the users.
### 4.4.4 Portability
- The system can be accessed on any smartphone or laptop.

Rationale:
- Usability is critical for user satisfaction and efficient system utilisation.
- Maintainability ensures the longevity and adaptability of the system.
- Reliability is essential to prevent disruptions during critical conference periods.

## 4.5 Business Rules
### 4.5.1 Role-Based Functions
- Authors can submit papers and view the status of their submissions.
- Reviewers can access and review the assigned papers.
- Members of the Program Committee can view all the submissions, assign reviewers for the same and accept or reject them.

Role-based functions ensure that users can only perform actions relevant to their responsibilities.
