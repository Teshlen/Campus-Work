#include <stdio.h>
#include <crypt.h>
#include  <stdlib.h>

const char pass[] = "$1$k4AS78jh$xkovv3WFLK2m55a3zBzwx0";
const char salt[] = "$1$k4AS78jh$";

int r;
int checker;

void unauthorised() {
  printf("Unauthorised access!\n");
}

void showSecret() {
	if(checker == r)
		{
  printf("Top Secret: The NSA is watching you u28163304!\n");
		}
		else 
			{
				printf("Very sorry, but the stack is not the same as it origionally was, somthing has been tampered with. call the authorities immediately!!!!!");
				return;
			}
}

void getPass() {
	checker = 0;

  char buffer[8];
  printf("Enter password: ");
  fgets(buffer,sizeof(buffer), stdin);
	//gets(buffer);

  if(strcmp(pass, crypt(buffer,salt)) == 0) {
	  checker = r;
    showSecret();
  } else {
    unauthorised();
  }
}

int main() {
r = 0;
r = rand() % 20;
  getPass();

  return 0;
}

//0x00000000004006e6 
//printf "aaaaaaaaaaaaaaaaaaaaaaa\x00\xf6\x06\x40\x00\x00" | ./overflow
//gcc -ggdb -fno-stack-protector -z execstack prac4_overflow.c -lcrypt -o prac4_overflow
//http://www.cprogramming.com/tutorial/secure.html
//gcc -ggdb -ftack-protector -strong-z execstack prac4_overflow.c -lcrypt -o prac4_overflow