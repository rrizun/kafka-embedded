#!/bin/sh
gradle clean bootJar
java -jar build/libs/$(basename $(pwd)).jar
