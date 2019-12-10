#!/usr/bin/env bash

insert() {
	data="{\"title\":\"$1\", \"duration\":$2}"
	echo $data
	curl -v -X POST -H "Content-Type:application/json" -d "$data" http://admin:admin@localhost:8081/movies
}

insert "The Lord of the Rings: The Fellowship of the Ring", 178
insert "The Lord of the Rings: The Two Towers", 179
insert "The Lord of the Rings: The Return of the King", 201
