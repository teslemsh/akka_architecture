build:
	- docker-compose build
up:
	- docker-compose up -d
run:
	- docker-compose run app sbt run