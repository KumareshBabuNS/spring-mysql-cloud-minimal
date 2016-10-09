# spring-minimal

## local test with h2
-profiles localH2,devtools

## cf



mvn package -Pdevelopment -DskipTests
cf push myapp -p targets/myapp...jar -b java_buildpack

bind  db service

check application-development.properties for environment must match VCAP_SERVICES structure

set cf environment variable SPRING_PROFILES_ACTIVE=development

cf restart
