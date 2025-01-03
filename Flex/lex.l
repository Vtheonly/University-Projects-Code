
%{
#include <stdio.h>
int nb_lig = 0; // Initialize to 0
int nb_car = 0; // Initialize to 0
%}




INTEGER [0-9]+
LPAREN \(
RPAREN \) 
PLUS \+ 
MINUS \- 
DIVIDE \/ 
MULTIPLY \* 

%%


{INTEGER} {
    printf("INTEGER\t: %s\n", yytext);
}

{LPAREN} {
    printf("LP\t: %s\n", yytext);
}

{RPAREN} {
    printf("RP\t: %s\n", yytext);
}

{MINUS} {
    printf("Minus\t: %s\n", yytext);
}

{PLUS} {
    printf("Plus\t: %s\n", yytext);
}

{DIVIDE} {
    printf("Div\t: %s\n", yytext);
}

{MULTIPLY} {
    printf("Mul\t: %s\n", yytext);
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