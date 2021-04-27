# Confluence Java Client
[![Build Status](https://travis-ci.com/cpesch/confluence-java-client.svg?branch=master)](https://travis-ci.com/cpesch/confluence-java-client)
[![Test Coverage](https://codecov.io/gh/cpesch/confluence-java-client/branch/master/graph/badge.svg)](https://codecov.io/gh/cpesch/confluence-java-client)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.cpesch.com.github.crob1140/confluence-java-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.cpesch.com.github.crob1140/confluence-java-client)

A simple Java client for the Confluence Cloud REST API.

Note: [This library](https://github.com/cpesch/confluence-java-client) 
is a fork of the seemingly dead 
[confluence-java-client](https://github.com/crob1140/confluence-java-client) 
project by Callum Robertson for which I've collected open pull 
requests and commits from forks and added functionality I needed.

## Installation

To add this package to a Gradle project, add the following to your build.gradle:
```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile group: 'io.github.cpesch.com.github.crob1140', name: 'confluence-java-client', version: '1.1.0'
}
```

## Usage

Create a new client instance:
```java
WebTarget wikiTarget = ClientBuilder.newClient().target("https://www.sample.atlassian.net/wiki");
AuthMethod basicAuth = new BasicAuth("username", "password");
Confluence client = new ConfluenceClient(wikiTarget, basicAuth);
```

Create some content:
```java
Content newPage = client.createContent(new CreateContentRequest.Builder()
    .setType(StandardContentType.PAGE)
    .setSpaceKey("SAMPLE")
    .setTitle("Sample Page")
    .setBody(ContentBodyType.STORAGE, "<ac:rich-text-body><p>SAMPLE</p></ac:rich-text-body>")
    .build());
```

Get existing content:
```java
List<Content> existingPages = client.getContent(new GetContentRequest.Builder()
    .setSpaceKey("SAMPLE")
    .setTitle("Sample Page")
    .setExpandedProperties(new ExpandedContentProperties.Builder().addVersion().build())
    .setLimit(1)
    .build())
```

Update existing content:
```java
Content updatedContent = client.updateContent(new UpdateContentRequest.Builder()
    .setId(existingPage.getId())
    .setType(existingPage.getType())
    .setStatus(ContentStatus.CURRENT)
    .setBody(ContentBodyType.STORAGE, "<ac:rich-text-body><p>Updated body</p></ac:rich-text-body>")
    .setVersion(existingPage.getVersion().getNumber() + 1)
    .build())
```

## Contribution
This client is a work-in-progress, and API methods will be added iteratively.
If there is a particular feature you would like added, feel free to raise it as an issue, or fork the repository and create a pull request with your own changes.

## License
MIT
