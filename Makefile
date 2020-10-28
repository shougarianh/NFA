setup:
	apt-get -y install build-essential
	apt-get install default-jdk
run-hw2-nfa:
	javac nfa.java
	java nfa
	rm nfa.class
run-hw2-union:
	javac union.java
	java union
	rm union.class
