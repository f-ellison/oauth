# oauth
Java Springboot oauth project

Referenced https://spring.io/guides/tutorials/spring-boot-oauth2
A lot of this was outdated.
Changes made.
    Went with gradle over maven.
    build.gradle
        Since using newer springboot I needed to make some other changes.
        'org.webjars:jquery:3.7.1' instead of 'org.webjars:jquery:3.4.1'
        'org.webjars:bootstrap:5.3.3' instead of 'org.webjars:bootstrap:4.3.1'
        'org.webjars:webjars-locator-lite' instead of 'org.webjars:webjars-locator-core'
    application.yaml
        spring:
            web:
                resources:
                    chain:
                        enabled: true
    Using vs code so introduced a local launch.json to select a 'dev' profile.
    Created an application-dev.yaml with secrets.
    