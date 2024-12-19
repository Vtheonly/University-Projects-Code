%{
#include <stdio.h>
#include <string.h>
%}

/* Regular expressions */
email [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}
local_part [a-zA-Z0-9._%+-]+
domain_part [a-zA-Z0-9.-]+\.[a-zA-Z]{2,}

/* Rules */
%%

{email} {
    printf("Full email: %s\n", yytext);
    
    /* Extract local part and domain part */
    char *at_sign = strchr(yytext, '@');  // Find '@' in the email string
    if (at_sign) {
        *at_sign = '\0';  // Null-terminate at '@' to get local part
        printf("Local part: %s\n", yytext);  // Local part (before '@')
        printf("Domain part: %s\n", at_sign + 1);  // Domain part (after '@')
    }
}

. {
    /* Catch any non-email characters (optional) */
    printf("Non-email character: %s\n", yytext);
}

%%


int yywrap(){
	return 1; 
}







/* Auxiliary C Code */
int main(int argc, char **argv) {
    if (argc > 1) {  // Check if input file is provided
        yyin = fopen(argv[1], "r"); // Open the input file
        if (!yyin) {
            perror("Cannot open file"); // Handle error
            return 1;
        }
    } else {
        fprintf(stderr, "Usage: %s <input_file>\n", argv[0]); // Print usage info
        return 1;
    }

    yylex(); // Start lexical analysis
    return 0;
}

