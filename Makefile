run-dist:
		build/install/app/bin/app

install:
		./gradlew clean install

run:
		./gradlew run

report:
		./gradlew jacocoTestReport

lint:
		./gradlew checkstyleMain checkstyleTest

test:
		./gradlew test

.PHONY: build

build:
		./gradlew build