CREATE TABLE "discipline" (
	"id" SERIAL PRIMARY KEY,
	"name" TEXT NOT NULL);

CREATE TABLE "group" (
	"id" SERIAL PRIMARY KEY,
	"number" TEXT NOT NULL,
	"course" INTEGER NOT NULL);

CREATE TABLE "teacher" (
	"id" SERIAL PRIMARY KEY,
	"FIO" TEXT NOT NULL);


CREATE TABLE "class" (
	"id" SERIAL PRIMARY KEY,
	"id_discipline" INTEGER REFERENCES "discipline"("id"),
	"id_teacher" INTEGER REFERENCES "teacher"("id"),
	"id_group" INTEGER REFERENCES "group"("id"),
	"term" INTEGER NOT NULL,
	"form_of_control" TEXT NOT NULL,
	"lecture_hours" INTEGER NOT NULL,
	"practical_hours" INTEGER NOT NULL,
	"laboratory_hours" INTEGER NOT NULL);