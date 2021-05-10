<!-- PROJECT SHIELDS -->
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

A REST API who evaluates if words are anagrams, can evaluate from single words, phrases or you can use persistent phrases to evaluate anagrams.

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.

<ul>
  <li><a href="http://git-scm.com/">Git</a></li>
  <li><a href="https://www.java.com/es/download/ie_manual.jsp">Java</a></li>
  <li><a href="https://maven.apache.org/">Maven</a></li>
  <li><a href="https://www.mongodb.com/">Mongo cluster</a></li>
</ul>

### Installation

1. Create a mongo cluster and obtain a URI to the connection
2. Clone the repo
   ```sh
   git clone https://github.com/GeovanniAlexander/Anagrams.git
   ```
3. Go into the directory
   ```
   cd anagrams
   ```
4. Rename the application.example.yml to application.yml and replace the following line with your URI mongo connection:
   ```
   uri: mongodb+srv://user:password@cluster/anagrams
   ```
5. Build the proyect
   ```
   mvn compile
   ```


<!-- USAGE EXAMPLES -->
## Usage

To run the app tests
   ```
   mvn clean test
   ```
To run api from jar
   ```
   java -jar target/anagrams-0.0.1-SNAPSHOT.jar
   ```
For Swagger documentation please follow

<ul>
  <li><a href="http://anagramsbalancer-838233159.us-east-2.elb.amazonaws.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config">Swagger</a></li>
</ul>

For consume the endpoints you can use [POSTMAN](https://www.postman.com/)

  for local consume you can use 
       ```
       localhost:8080
       ```
   For remote consume you can use
       ```
       http://AnagramsBalancer-838233159.us-east-2.elb.amazonaws.com
       ```

## Contact
Geovanni Alexander Engativa - alex123joha@gmail.com









<!-- MARKDOWN LINKS & IMAGES -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/geovanni-alexander-engativa-monta%C3%B1a-30514a16a/
