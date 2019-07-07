JDKPATH = /usr
LIBPATH = com/fasterxml/jackson/core/jackson-core/2.9.0/jackson-core-2.9.0.jar:com/fasterxml/jackson/core/jackson-annotations/2.9.0/jackson-annotations-2.9.0.jar:com/fasterxml/jackson/core/jackson-databind/2.9.0/jackson-databind-2.9.0.jar

CLASSPATH = .:..:$(LIBPATH)
BINPATH = $(JDKPATH)/bin
JAVAC = $(JDKPATH)/bin/javac 
JAVA  = $(JDKPATH)/bin/java 

PROGS = xx

all: $(PROGS)

compile:src/jsonDB/*/*.java
	$(JAVAC) -cp $(CLASSPATH) -d bin src/jsonDB/*/*.java

xx : compile
	$(JAVA) -cp $(CLASSPATH):bin jsonDB.db.SimpleDB -t students -u users -q query1 -q query2

