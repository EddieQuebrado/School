#include <stdio.h>

//Declare prototypes for functions used before their definition
void zot(int);

int main(void) {
  zot(42);
}


void zot(int n) {
  printf("zot n=%d\n",n);
}
