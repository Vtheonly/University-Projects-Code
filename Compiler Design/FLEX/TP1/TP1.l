%{
#include <stdio.h>
%}


key_word_if "if";
key_word_iff "iif";





%%


{key_word_if} {printf("key_word_if  \n");}
{key_word_iff} {printf("key_word_iff  \n");}
.              {printf("autre charachter : %s\n",yytext);}


%%


int yywrap(){
	return 1; 
}



int main(int argc,char ** argv){

    if(argc>1){
        yyin=fopen(argv[0],"r");
    }
    else{
        yyin=stdin;
	    yylex();
    }
    
}
