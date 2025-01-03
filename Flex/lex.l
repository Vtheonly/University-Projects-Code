
%{
#include <stdio.h>
int nb_lig = 0; // Initialize to 0
int nb_car = 0; // Initialize to 0
%}




Alphnum [a-zA-Z0-9]+



%%


{Alphnum}@{Alphnum}.com  {
    printf("EMAIL\t: %s\n", yytext);
}






\n   {++nb_lig ; nb_car=0;} // For Debugging
.    {++nb_car; }           // For Debugging

%%

int yywrap() {
  return 1;
}


int main(void)
{
    yylex();
    return 0;
}