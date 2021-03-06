//-----------------------------------------------------------------------------
//
// forkAndWait -- Demonstrates the use of fork and wait to create a new process
//
// The parent process prints a message, forks a child, prints another message,
// waits for the child to exit, and prints a final message before exiting.
//
// The child process prints a message and exits.
//
//-----------------------------------------------------------------------------
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>


//We start with a single process executing the main function
int main(void) {

  //Only one process, the parent, executes here
  printf("PARENT:  The parent's PID=%d\n",getpid());

  //Create a new, duplicate process that will execute the code below
  fflush(stdout);               //Flush parent's stdout buffer into kernel
  int pid = fork();             //Fork will return twice, once in each process

  //Both the parent and the child are executing now.  If they are executing on
  //a multi-core CPU, both processes may execute this code simultaneously.

  //Am I the child process?
  if (pid==0) {
    printf("CHILD:  I am the child\n");//The child speaks
    sleep(10);
    printf("CHILD:  Exiting\n");
    fclose(stdout);
    return 0;                          //The child exits

  //Am I the parent process?
  } else if (pid>0) {
    printf("PARENT:  The child PID=%d\n",pid);  //The parent speaks
    fflush(stdout);                    //Flush stdout buffer to kernel
    int exitStatus;                    //Exit status of the child
    wait(&exitStatus);                 //Wait for child to exit.
    printf("PARENT:  Child exitStatus=%d\n",exitStatus);
    return 0;                          //The parent's normal exit

  //Did the fork fail?
  } else {
    perror("Fork failed");             //Oh no!
  }
  return -1;                           //The parent's abnormal exit
}
