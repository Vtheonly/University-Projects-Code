%{
#include <stdio.h>
int nb_lig = 0; // Initialize to 0
int nb_car = 0; // Initialize to 0
%}

/* Define regular expressions for parts of an email */
local_part [a-zA-Z0-9]+
domain_part [a-zA-Z0-9]+

%%

{local_part}@{domain_part}\.com {
    char *at_sign = strchr(yytext, '@'); // Find the position of @
    char *dot_com = strstr(yytext, ".com"); // Find the position of .com

    if (at_sign && dot_com) {
      *dot_com = '\0'; // Null-terminate at .com to extract domain
      *at_sign = '\0'; // Null-terminate at @ to extract local part

      char *domain = at_sign + 1; // Domain starts after @
      char *local = yytext;      // Local part starts at the beginning

      printf("EMAIL\t: %s\n", yytext);
      printf("Address\t: %s\n", local);
      printf("Domain\t: %s\n", domain);

      *at_sign = '@'; // Restore @ (optional, for later processing)
      *dot_com = '.'; // Restore . (optional, for later processing)
    }
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