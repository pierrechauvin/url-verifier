# Pretty simple URL Verifier (JDK8, Junit 5)

A lightweight URL verifier using Junit 5 [@ParameterizedTest](http://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests) and [@CsvFileSource](http://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-CsvFileSource) annotations. It's useful when you need to configure a scheduled [Jenkins](https://jenkins-ci.org/) job that verifies all your URLs (and if you don't have a Jenkins plugin like [SiteMonitor](https://plugins.jenkins.io/sitemonitor)). It injects a simple CSV file :

```csv
https://voxxeddays.com/;200
http://www.google.fr;200
https://www.google.fr/unknown;404
```

into JUnit 5 parameterized test :

```java
 @ParameterizedTest(name = "{index} => verify({0}) should return {1}")
 @CsvFileSource(resources = {"/urls.csv"}, delimiter = ';')
 public void urlTests( final String url, int expectedResult ) throws IOException {
   assertEquals(expectedResult, ( (HttpURLConnection) new URL( url ).openConnection()).getResponseCode() );
 }
```
