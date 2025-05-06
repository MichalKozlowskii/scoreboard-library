# scoreboard-library
Live Football World Cup Scoreboard library that shows all the ongoing matches and their scores. 

### Commit Messages
All commits contain the suitable prefix. Acceptable prefixes:
- `chore`: A tech task that's not actually a feature but still needs to be done (e.g. test integration with a 3rd-party service)
- `docs`: Documentation only changes
- `feat`: A new feature
- `fix`: A bug fix
- `perf`: A code change that improves performance
- `refactor`: A code change that neither fixes a bug nor adds a feature
- `style`: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
- `test`: Adding missing tests or correcting existing tests

### Installation
1. Clone repository
2. Go to library's directory
3. Build the library jar `mvn clean package`
4. Install the library to the local Maven repository `mvn install`
5. Add dependency to your maven project in pom.xml
```
<dependency>
    <groupId>com.sportradar</groupId>
    <artifactId>scoreboard</artifactId>
    <version>1.0</version>
</dependency>
```
