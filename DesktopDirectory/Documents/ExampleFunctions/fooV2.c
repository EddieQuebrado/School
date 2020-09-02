#include <stdio.h>

//The compiler verifies foo definitions with declarations if we include foo.h
#include "foo.h"

//Function Definition of foo
void foo(int x) {
  printf("x=%d\n",x);
}
